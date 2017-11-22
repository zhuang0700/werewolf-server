package com.telan.werewolf.game.process;

import com.alibaba.fastjson.JSON;
import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.enums.BaseStatus;
import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.factory.GameMsgFactory;
import com.telan.werewolf.factory.RoundFactory;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.domain.role.BaseRole;
import com.telan.werewolf.game.enums.GameMsgSubType;
import com.telan.werewolf.game.enums.GameStatus;
import com.telan.werewolf.game.manager.*;
import com.telan.werewolf.game.param.CreateGameParam;
import com.telan.werewolf.game.param.OperateGameParam;
import com.telan.werewolf.game.vo.UserGameConfig;
import com.telan.werewolf.manager.GameManager;
import com.telan.werewolf.manager.MemGameManager;
import com.telan.werewolf.manager.PlayerManager;
import com.telan.werewolf.manager.UserManager;
import com.telan.werewolf.param.BaseResponseData;
import com.telan.werewolf.query.PlayerPageQuery;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WeResultSupport;
import com.telan.werewolf.utils.ResponseMapUtils;
import com.telan.werewolf.utils.conventor.GameConvertor;
import com.telan.werewolf.utils.conventor.PlayerConvertor;
import com.telan.werewolf.utils.conventor.ResultConvertor;
import com.telan.werewolf.websocket.Webserver;
import org.jsoup.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created by weiwenliang on 15/11/3.
 *
 */
public class GameProcessor {
	@Autowired
	private MemGameManager memGameManager;
	@Autowired
	private GameManager gameManager;
	@Autowired
	private PlayerManager playerManager;
	@Autowired
	private UserManager userManager;

	private final static Logger log	= LoggerFactory.getLogger(GameProcessor.class);

	public void loadAllLiveGame() {
		memGameManager.init();
		for(GameInfo gameInfo : memGameManager.gameMap.values()) {
			if(gameInfo.getGameStatus() == GameStatus.CREATE.getType()) {
				eraseGameIfQuit(gameInfo);
			} else if(gameInfo.getGameStatus()== GameStatus.PROCESS.getType()) {
				this.startGame(gameInfo);
			}
			memGameManager.syncGameToDB(gameInfo.getGameId(), true, true);
		}
	}

	public WeBaseResult<GameInfo> createGame(CreateGameParam param) {
		WeBaseResult<GameInfo> baseResult = new WeBaseResult<>();
		GameInfo currentGame = findCurrentGame(param.getCreator().getId());
		if(currentGame != null) {
			baseResult.setErrorCode(WeErrorCode.HAS_ACTIVE_GAME);
			baseResult.setValue(currentGame);
			return baseResult;
		}
		//创建游戏
		GameDO gameDO = GameConvertor.convertGameDOForCreate(param);
		gameDO = gameManager.insertGame(gameDO);
		//创建玩家
		PlayerDO playerDO = PlayerConvertor.convertFromUser(param.getCreator(), gameDO.getId());
		playerDO = playerManager.insertPlayer(playerDO);
		GameInfo gameInfo = new GameInfo(gameDO);
		gameInfo.init();
//		initGameInfo(gameInfo);
		memGameManager.addGame(gameInfo);
		Player player = PlayerConvertor.convertPlayer(playerDO, param.getCreator());
		gameInfo.addPlayer(player);
		memGameManager.addPlayer(player);
		baseResult.setValue(gameInfo);
		return baseResult;
	}

	public WeBaseResult<GameInfo> joinGame(OperateGameParam param) {
		WeBaseResult<GameInfo> baseResult = new WeBaseResult<>();
		GameInfo currentGame = findCurrentGame(param.getUser().getId());
		if(currentGame != null) {
			if(param.getGameId() == currentGame.getGameId()) {
				baseResult.setValue(currentGame);
				return baseResult;
			}
			baseResult.setErrorCode(WeErrorCode.HAS_ACTIVE_GAME);
			baseResult.setValue(currentGame);
			return baseResult;
		}
		//查找游戏
		GameInfo gameInfo = memGameManager.getGame(param.getGameId());
		if(gameInfo == null) {
			baseResult = getGameInfoFromDB(param.getGameId());
			if(!baseResult.isSuccess() || baseResult.getValue() == null) {
				return baseResult;
			}
			gameInfo = baseResult.getValue();
		}
		//创建玩家
		synchronized (gameInfo.getPlayerMap()) {
			if(gameInfo.getPlayerMap().size() < gameInfo.getPlayerNum()) {
				PlayerDO playerDO = PlayerConvertor.convertFromUser(param.getUser(), param.getGameId());
				playerDO = playerManager.insertPlayer(playerDO);
				Player player = PlayerConvertor.convertPlayer(playerDO, param.getUser());
				gameInfo.addPlayer(player);
				memGameManager.addPlayer(player);
				baseResult.setValue(gameInfo);
				memGameManager.syncGameToDB(gameInfo.getGameId(), true, false);
				return baseResult;
			} else {
				baseResult.setErrorCode(WeErrorCode.MAX_PLAYER_ACHIVED);
				return baseResult;
			}
		}
	}

	public WeBaseResult<GameInfo> startGame(long userId, long gameId) {
		WeBaseResult<GameInfo> result = new WeBaseResult<>();
		GameInfo gameInfo = memGameManager.getGame(gameId);
		if(gameInfo == null) {
			result.setErrorCode(WeErrorCode.WRONG_GAME);
			return result;
		}
		if(userId != gameInfo.getCreatorId()) {
			result.setErrorCode(WeErrorCode.HAS_ACTIVE_GAME);
			return result;
		}
		if(gameInfo.getGameStatus() != GameStatus.CREATE.getType()) {
			result.setErrorCode(WeErrorCode.GAME_INIT);
			return result;
		}
		gameInfo.setRoleList(RoleEngine.initRoleList(gameInfo));
		//分配角色
		RoleEngine.allocateRole(gameInfo.getRoleList(), gameInfo.getPlayerMap());
		//分配玩家编号
		PlayerEngine.initPlayerNumber(gameInfo.getPlayerMap());
		//初始化回合
		Round firstRound = RoundFactory.createRound(1, gameInfo);
		gameInfo.changeCurrentRound(firstRound);
		RoundEngine.startRound(gameInfo);
		PlayerEngine.setGameStart(gameInfo.getPlayerMap());

		result.setValue(gameInfo);
		gameInfo.setGameStatus(GameStatus.PROCESS.getType());
		memGameManager.syncGameToDB(gameInfo.getGameId(), true, false);
		return result;
	}

	public WeBaseResult<GameInfo> quitGame(OperateGameParam param) {
		WeBaseResult<GameInfo> result = new WeBaseResult<>();
		GameInfo gameInfo = memGameManager.getGame(param.getGameId());
		if(gameInfo == null) {
			result.setErrorCode(WeErrorCode.WRONG_GAME);
			return result;
		}
		Player player = memGameManager.getPlayerByUserId(param.getUser().getId(), param.getGameId());
		if(player == null || player.getGameId() != gameInfo.getGameId()) {
			result.setErrorCode(WeErrorCode.WRONG_GAME);
			return result;
		}
		GameStatus gameStatus = GameStatus.getByTypeWithDefault(gameInfo.getGameStatus());
		switch (gameStatus) {
			case CREATE:
				result = quitGameBeforeStart(gameInfo, player.getId());
				break;
			case INIT:
				result.setErrorCode(WeErrorCode.GAME_INIT);
				return result;
			case PROCESS:
				result = quitGameAfterStart(gameInfo, player.getId());
				break;
			case FINISH:
				result = quitGameAfterStart(gameInfo, player.getId());
				return result;
		}
		eraseGameIfQuit(gameInfo);
		return result;
	}

	public WeBaseResult<ActionResult> playerAction(PlayerAction action) {
		WeBaseResult<ActionResult> result = new WeBaseResult<>();
		GameInfo gameInfo = memGameManager.getGame(action.getGameId());
		if(gameInfo == null) {
			result.setErrorCode(WeErrorCode.WRONG_GAME);
			return result;
		}
		Player player = memGameManager.getPlayerByUserId(action.getUserDO().getId(), action.getGameId());
		if(player == null) {
			result.setErrorCode(WeErrorCode.WRONG_GAME);
			return result;
		}
		action.fromPlayerId = player.getId();
		if(gameInfo.getGameStatus() != GameStatus.PROCESS.getType()) {
			result.setErrorCode(WeErrorCode.WRONG_GAME);
			return result;
		}
		result = ActionEngine.performAction(gameInfo, action);
		if(gameInfo.getGameStatus() == GameStatus.FINISH.getType()) {
			memGameManager.syncGameToDB(gameInfo.getGameId(), true, false);
		}
		return result;
	}

	public WeBaseResult<GameInfo> judgeAction(JudgeAction action) {
		WeBaseResult<GameInfo> result = new WeBaseResult<>();
		GameInfo gameInfo = memGameManager.getGame(action.getGameId());
		if(gameInfo == null) {
			result.setErrorCode(WeErrorCode.WRONG_GAME);
			return result;
		}
		Player player = memGameManager.getPlayerByUserId(action.getUserDO().getId(), action.getGameId());
		if(player == null) {
			result.setErrorCode(WeErrorCode.WRONG_GAME);
			return result;
		}
		if(gameInfo.getGameStatus() != GameStatus.PROCESS.getType()) {
			result.setErrorCode(WeErrorCode.WRONG_GAME);
			return result;
		}
		if(!PlayerEngine.isJudge(gameInfo, player)) {
			result.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
		}
		WeResultSupport weResultSupport = ActionEngine.performJudgeAction(gameInfo, action);
		if(!weResultSupport.isSuccess()) {
			result.setErrorCode(weResultSupport.getErrorCode());
			return result;
		}
		result.setValue(gameInfo);
//		memGameManager.syncGameToDB(gameInfo.getGameId(), true, false);
		return result;
	}

	public WeResultSupport configGame(UserGameConfig userGameConfig, UserDO userDO) {
		GameInfo gameInfo = memGameManager.getGame(userGameConfig.getGameId());
		if(gameInfo.getCreatorId() != userDO.getId()) {
			WeResultSupport resultSupport = new WeResultSupport();
			resultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
			return resultSupport;
		}
		gameInfo.setGameConfig(ConfigEngine.convertGameConfig(gameInfo.getGameConfig(), userGameConfig));
		return new WeResultSupport();
	}

	public WeBaseResult<GameInfo> getCurrentGameInfo(long userId) {
		WeBaseResult<GameInfo> baseResult = new WeBaseResult<>();
		GameInfo currentGame = findCurrentGame(userId);
		if(currentGame == null) {
			return baseResult;
		}
		baseResult.setValue(currentGame);
		return baseResult;
	}

	public WeBaseResult<GameInfo> getGameInfo(long userId, long gameId) {
		WeBaseResult<GameInfo> baseResult = new WeBaseResult<>();
		long currentGameId = findCurrentGameId(userId);
		if(currentGameId <=0) {
			baseResult.setErrorCode(WeErrorCode.NO_ACTIVE_GAME);
			return baseResult;
		}
		if(gameId > 0) {
			if(currentGameId != gameId) {
				baseResult.setErrorCode(WeErrorCode.WRONG_GAME);
				return baseResult;
			}
		}
		GameInfo gameInfo = memGameManager.getGame(currentGameId);
//		if(gameInfo == null) {
//			return getGameInfoFromDB(gameId);
//		}
		baseResult.setValue(gameInfo);
		return baseResult;
	}

	public WeBaseResult<GameInfo> getGameInfoFromDB(long gameId) {
		WeBaseResult<GameInfo> baseResult = new WeBaseResult<>();
		WeBaseResult<GameDO> gameDOBaseResult = gameManager.getGameById(gameId);
		if(!gameDOBaseResult.isSuccess() || gameDOBaseResult.getValue() == null) {
			baseResult.setErrorCode(WeErrorCode.WRONG_GAME);
			return baseResult;
		}
		GameDO gameDO = gameDOBaseResult.getValue();
		GameInfo gameInfo = new GameInfo(gameDO);
		gameInfo.init();

		PlayerPageQuery playerPageQuery = new PlayerPageQuery();
		playerPageQuery.setGameId(gameId);
		playerPageQuery.setNeedPageQuery(false);
		List<PlayerDO> playerDOList = playerManager.pageQuery(playerPageQuery);
		Map<Long, UserDO> userDOMap = userManager.getUserByIds(PlayerConvertor.convertUserIdList(playerDOList));
		gameInfo.setPlayerMap(PlayerConvertor.convertPlayerMap(playerDOList, userDOMap));
		//TODO: not finished
		memGameManager.addGame(gameInfo);
		baseResult.setValue(gameInfo);
		return baseResult;
	}

	public void eraseGameIfQuit(GameInfo gameInfo) {
		Player player = memGameManager.getPlayerByUserId(gameInfo.getCreatorId(), gameInfo.getGameId());
		if(GameEngine.checkGameStatus(gameInfo) == GameStatus.FINISH.getType()) {
			gameInfo.setGameStatus(GameStatus.FINISH.getType());
			PlayerEngine.setGameEnd(gameInfo.getPlayerMap());
		}
	}

	private long findCurrentGameIdFromDB(long userId) {
		PlayerPageQuery playerPageQuery = new PlayerPageQuery();
		playerPageQuery.setUserId(userId);
		playerPageQuery.setGameStatus(BaseStatus.AVAILABLE.getType());
		List<PlayerDO> playerDOs = playerManager.pageQuery(playerPageQuery);
		if(CollectionUtils.isEmpty(playerDOs)) {
			return 0;
		}
		if(playerDOs.size() > 1) {
			log.error("findCurrentGameId error. More than one active game found. playerDOs=" + JSON.toJSONString(playerDOs));
		}
		return playerDOs.get(0).getGameId();
	}

	private long findCurrentGameId(long userId) {
		Player player = memGameManager.getPlayerByUserId(userId, 0);
		if(player == null) {
			return 0;
		}
		return player.getGameId();
	}

	private GameInfo findCurrentGame(long userId) {
		Player player = memGameManager.getPlayerByUserId(userId, 0);

		long gameId = 0;
		if(player == null) {
			return null;
		} else {
			if(player.getGameStatus() == BaseStatus.DELETED.getType()) {
				memGameManager.removePlayer(player);
				return null;
			}
			gameId = player.getGameId();
		}
		return memGameManager.getGame(gameId);
	}
//
//	private void initGameInfo(GameInfo gameInfo) {
//		gameInfo.setPlayerMap(new HashMap<Long, Player>());
//		gameInfo.setRoleList(new ArrayList<BaseRole>());
//		gameInfo.setRoundHistory(new ArrayList<Round>());
//		gameInfo.setRoleList(new ArrayList<BaseRole>());
//	}

	private WeBaseResult<GameInfo> quitGameBeforeStart(GameInfo gameInfo, long playerId) {
		WeBaseResult<GameInfo> result = new WeBaseResult<>();
		Player player = memGameManager.getPlayer(playerId);
		boolean deleteResult = playerManager.deletePlayerById(player.getPlayerDO());
		if(!deleteResult) {
			result.setErrorCode(WeErrorCode.SYSTEM_ERROR);
			return result;
		}
		memGameManager.removePlayer(player);
		gameInfo.removePlayer(player.getId());
		result.setValue(gameInfo);
		return result;
	}

	private WeBaseResult<GameInfo> quitGameAfterStart(GameInfo gameInfo, long playerId) {
		WeBaseResult<GameInfo> result = new WeBaseResult<>();
		Player player = memGameManager.getPlayer(playerId);
		log.info("quitGameAfterStart begin, player:"+JSON.toJSONString(player));
		PlayerEngine.quitGameAfterStart(player);
		playerManager.updatePlayerById(player.getPlayerDO());
		GameMsg gameMsg = GameMsgFactory.createGameMsg(GameMsgSubType.QUIT_GAME, Visibility.ALL, new Object[]{player.getPlayerNo()});
		RecordEngine.sendNormalMsg(gameInfo, gameMsg);
		memGameManager.removePlayer(player);
		log.info("quitGameAfterStart finish, player:" + JSON.toJSONString(player));
		result.setValue(gameInfo);
		return result;
	}

	private void startGame(GameInfo gameInfo) {
		//初始化回合
		Round firstRound = RoundFactory.createRound(1, gameInfo);
		gameInfo.changeCurrentRound(firstRound);
		RoundEngine.startRound(gameInfo);
		PlayerEngine.setGameStart(gameInfo.getPlayerMap());
		gameInfo.setGameStatus(GameStatus.PROCESS.getType());
	}

	public void updateGameInfo(GameInfo gameInfo) {
		Map<Long,String> responseMap = new HashMap<>();
		for(Player player : gameInfo.getPlayerMap().values()) {
			UserDO userDO = player.getUserDO();
			BaseResponseData baseResponseData = ResponseMapUtils.getGameInfoResponse(gameInfo, userDO);
			responseMap.put(userDO.getId(), JSON.toJSONString(baseResponseData));
		}
		int retry = 0;
		while (retry < 3) {
//			try {
//                Webserver.sendMessage(responseMap);
//            } catch (IOException e) {
//                log.error("sendMessage failed. retried {} times", retry, e);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				retry++;
//				continue;
//            }
			break;
		}
	}
}
