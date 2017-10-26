package com.telan.werewolf.manager;

import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.enums.BaseStatus;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.enums.GameStatus;
import com.telan.werewolf.mapper.UserDOMapper;
import com.telan.werewolf.query.GamePageQuery;
import com.telan.werewolf.query.GameQueryOption;
import com.telan.werewolf.query.PlayerPageQuery;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WePageResult;
import com.telan.werewolf.utils.conventor.GameConvertor;
import com.telan.werewolf.utils.conventor.PlayerConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.convert.ListConverter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemGameManager {
	private static final Logger logger = LoggerFactory.getLogger(MemGameManager.class);

	@Autowired
	public GameManager gameManager;

	@Autowired
	public PlayerManager playerManager;

	@Autowired
	public UserManager userManager;

	public Map<Long, GameInfo> gameMap;

	public Map<Long, Player> playerMap;

	public Map<Long, Player> userPlayerMap;

	public GameInfo getGame(Long gameId) {
		return gameMap.get(gameId);
	}

	public Player getPlayer(Long playerId) {
		return playerMap.get(playerId);
	}

	public void addGame(GameInfo gameInfo) {
		gameMap.put(gameInfo.getGameId(), gameInfo);
		if(!CollectionUtils.isEmpty(gameInfo.getPlayerMap())) {
			for(Player player : gameInfo.getPlayerMap().values()) {
				addPlayer(player);
			}
		}
	}

	public void addPlayer(Player player) {
		if(playerMap.get(player.getId()) == null) {
			playerMap.put(player.getId(), player);
		}
		if(userPlayerMap.get(player.getId()) == null) {
			userPlayerMap.put(player.getUserId(), player);
		}
		GameInfo gameInfo = gameMap.get(player.getId());
		if(gameInfo != null && gameInfo.getPlayer(player.getId()) == null) {
			gameInfo.addPlayer(player);
		}
	}

	public void removePlayer(Player player) {
		if(player == null) {
			return;
		}
		playerMap.remove(player.getId());
		userPlayerMap.remove(player.getUserId());
	}

	public void removePlayerInGame(Player player) {
		if(player == null) {
			return;
		}
		userPlayerMap.remove(player.getUserId());
	}

	public Player getPlayerByUserId(long userId, long gameId) {
		Player player = userPlayerMap.get(userId);
		return player;
	}

	public Player loadPlayer(long userId, long gameId) {
		WeBaseResult<UserDO> userDOWeBaseResult = userManager.getUserById(userId);
		UserDO userDO = userDOWeBaseResult.getValue();
		PlayerPageQuery playerPageQuery = new PlayerPageQuery();
		playerPageQuery.setUserId(userId);
		if(gameId > 0) {
			playerPageQuery.setGameId(gameId);
		} else {
			playerPageQuery.setGameStatus(BaseStatus.AVAILABLE.getType());
		}
		List<PlayerDO> playerDOList = playerManager.pageQuery(playerPageQuery);
		PlayerDO playerDO = null;
		if(!CollectionUtils.isEmpty(playerDOList)) {
			playerDO = playerDOList.get(0);
		}
		Player player = PlayerConvertor.convertPlayer(playerDO, userDO);
		return player;
	}

	public void init() {
		gameMap = new HashMap<>();
		playerMap = new HashMap<>();
		userPlayerMap = new HashMap<>();
	}

	public void loadAllAliveGame(){
		GamePageQuery gamePageQuery = new GamePageQuery();
		gamePageQuery.setStatusList(GameStatus.getLiveStatus());
		gamePageQuery.setNeedPageQuery(false);
		WePageResult<GameDO> gameDOWePageResult = gameManager.batchQueryGameDO(gamePageQuery);
		List<GameDO> gameDOList = gameDOWePageResult.getList();
		List<Long> gameIdList = new ListConverter<>(GameConvertor.getDefaultConvertor()).convert(gameDOList);
		PlayerPageQuery playerPageQuery = new PlayerPageQuery();
		playerPageQuery.setGameIdList(gameIdList);
		playerPageQuery.setNeedPageQuery(false);
		playerPageQuery.setGameStatus(BaseStatus.AVAILABLE.getType());
		List<PlayerDO> playerDOList = playerManager.pageQuery(playerPageQuery);

		Map<Long, UserDO> userDOMap = userManager.getUserByIds(PlayerConvertor.convertUserIdList(playerDOList));

		if(!CollectionUtils.isEmpty(userDOMap) && !CollectionUtils.isEmpty(playerDOList)) {
			for(PlayerDO playerDO : playerDOList) {
				Player player = PlayerConvertor.convertPlayer(playerDO, userDOMap.get(playerDO.getUserId()));
				if(player == null) {
					logger.error("player info lost error. playerDO={}", playerDO);
					continue;
				}
				playerMap.put(player.getId(), player);
				userPlayerMap.put(player.getUserId(), player);
			}
		}
		this.gameMap = GameConvertor.convertGameInfoMap(gameDOList, playerMap);
	}

	public void syncAllGameToDB(boolean eraseExpireGame) {
		for(Long gameId : gameMap.keySet()) {
			syncGameToDB(gameId, false, false);
		}
		for(Player player : playerMap.values()) {
			playerManager.updatePlayerById(player.getPlayerDO());
		}
		if(eraseExpireGame) {
			//TODO:
		}
	}

	public void syncGameToDB(long gameId, boolean syncPlayer, boolean eraseExpireGame) {
		GameInfo gameInfo = gameMap.get(gameId);
		if(gameInfo == null) {
			return;
		}
		gameManager.updateGameById(gameInfo.getGameDO());
		if(syncPlayer) {
			for(Player player : gameInfo.getPlayerMap().values()) {
				playerManager.updatePlayerById(player.getPlayerDO());
			}
		}
		if(eraseExpireGame) {
			if(gameInfo.getGameStatus() == GameStatus.FINISH.getType()) {
				for(Player player : gameInfo.getPlayerMap().values()) {
					playerMap.remove(player.getId());
					userPlayerMap.remove(player.getUserId());
					gameInfo.getPlayerMap().remove(player.getId());
				}
				gameMap.remove(gameId);
				gameInfo = null;
				System.gc();
			}
		}
	}

	public void destroy() {
		this.gameMap.clear();
		this.gameMap = null;
		this.playerMap.clear();
		this.playerMap = null;
		this.userPlayerMap.clear();
		this.userPlayerMap = null;
	}
}
