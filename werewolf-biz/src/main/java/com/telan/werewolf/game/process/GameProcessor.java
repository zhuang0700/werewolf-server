package com.telan.werewolf.game.process;

import com.alibaba.fastjson.JSON;
import com.telan.weixincenter.result.WXBaseResult;
import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.factory.RoundFactory;
import com.telan.werewolf.game.domain.role.BaseRole;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.enums.PlayerStatus;
import com.telan.werewolf.game.manager.RoleManager;
import com.telan.werewolf.game.manager.RoundManager;
import com.telan.werewolf.game.param.CreateGameParam;
import com.telan.werewolf.game.param.JoinGameParam;
import com.telan.werewolf.game.manager.ActionManager;
import com.telan.werewolf.manager.GameManager;
import com.telan.werewolf.manager.MemGameManager;
import com.telan.werewolf.manager.PlayerManager;
import com.telan.werewolf.manager.UserManager;
import com.telan.werewolf.query.PlayerPageQuery;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.utils.conventor.GameConvertor;
import com.telan.werewolf.utils.conventor.PlayerConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

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
	private ActionManager actionManager;
	@Autowired
	private PlayerManager playerManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private RoundManager roundManager;
	@Autowired
	private UserManager userManager;

	private final static Logger log	= LoggerFactory.getLogger(GameProcessor.class);

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
		initGameInfo(gameInfo);
		memGameManager.addGame(gameInfo);
		Player player = PlayerConvertor.convertPlayer(playerDO, param.getCreator());
		gameInfo.addPlayer(player);
		memGameManager.addPlayer(player);
		baseResult.setValue(gameInfo);
		return baseResult;
	}

	public WeBaseResult<GameInfo> joinGame(JoinGameParam param) {
		WeBaseResult<GameInfo> baseResult = new WeBaseResult<>();
		GameInfo currentGame = findCurrentGame(param.getCreator().getId());
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
				PlayerDO playerDO = PlayerConvertor.convertFromUser(param.getCreator(), param.getGameId());
				playerDO = playerManager.insertPlayer(playerDO);
				Player player = PlayerConvertor.convertPlayer(playerDO, param.getCreator());
				gameInfo.addPlayer(player);
				baseResult.setValue(gameInfo);
				return baseResult;
			} else {
				baseResult.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
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
		gameInfo.setRoleList(roleManager.initRoleList(gameInfo.getPlayerNum()));
		//分配角色
		roleManager.allocateRole(gameInfo.getRoleList(), gameInfo.getPlayerMap());
		//初始化回合
		Round firstRound = RoundFactory.createRound(1, gameInfo.getRoleList());
		gameInfo.changeCurrentRound(firstRound);
		roundManager.startRound(gameInfo);
		result.setValue(gameInfo);
		return result;
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
		if(gameInfo == null) {
			return getGameInfoFromDB(gameId);
		}
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
		initGameInfo(gameInfo);

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

	private long findCurrentGameIdFromDB(long userId) {
		PlayerPageQuery playerPageQuery = new PlayerPageQuery();
		playerPageQuery.setUserId(userId);
		playerPageQuery.setStatusList(new ArrayList<Integer>(){
			private static final long serialVersionUID = -8804793808495319423L;
			{
			add(PlayerStatus.CREATE.getType());
			add(PlayerStatus.LIVE.getType());
			add(PlayerStatus.DEAD.getType());
		}});
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
		Player player = memGameManager.getPlayerByUserId(userId);
		if(player == null) {
			return findCurrentGameIdFromDB(userId);
		}
		return player.getGameId();
	}


	private GameInfo findCurrentGame(long userId) {
		Player player = memGameManager.getPlayerByUserId(userId);
		long gameId = 0;
		if(player == null) {
			gameId = findCurrentGameIdFromDB(userId);
		} else {
			gameId = player.getGameId();
		}
		return memGameManager.getGame(gameId);
	}

	private void initGameInfo(GameInfo gameInfo) {
		gameInfo.setPlayerMap(new HashMap<Long, Player>());
		gameInfo.setRoleList(new ArrayList<BaseRole>());
		gameInfo.setRoundHistory(new ArrayList<Round>());
		gameInfo.setRoleList(new ArrayList<BaseRole>());
	}
}
