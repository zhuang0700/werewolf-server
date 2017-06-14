package com.telan.werewolf.manager;

import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.enums.GameStatus;
import com.telan.werewolf.mapper.UserDOMapper;
import com.telan.werewolf.query.GamePageQuery;
import com.telan.werewolf.query.GameQueryOption;
import com.telan.werewolf.query.PlayerPageQuery;
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

	public MemGameManager(int initSize) {
		loadAllLiveGame();
	}

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
		playerMap.put(player.getId(), player);
		userPlayerMap.put(player.getUserId(), player);
	}

	public Player getPlayerByUserId(Long userId) {
		return userPlayerMap.get(userId);
	}

	public void loadAllLiveGame() {
		GamePageQuery gamePageQuery = new GamePageQuery();
		gamePageQuery.setStatusList(GameStatus.getLiveStatus());
		gamePageQuery.setNeedPageQuery(false);
		WePageResult<GameDO> gameDOWePageResult = gameManager.batchQueryGameDO(gamePageQuery);
		List<GameDO> gameDOList = gameDOWePageResult.getList();
		Map<Long, GameInfo> gameInfoMap = GameConvertor.convertGameInfoMap(gameDOList);
		List<Long> gameIdList = new ListConverter<>(GameConvertor.getDefaultConvertor()).convert(gameDOList);
		PlayerPageQuery playerPageQuery = new PlayerPageQuery();
		playerPageQuery.setGameIdList(gameIdList);
		playerPageQuery.setNeedPageQuery(false);
		List<PlayerDO> playerDOList = playerManager.pageQuery(playerPageQuery);
		Map<Long, UserDO> userDOMap = userManager.getUserByIds(PlayerConvertor.convertUserIdList(playerDOList));

		Map<Long, Player> playerMap = new HashMap<>();
		Map<Long, Player> userPlayerMap = new HashMap<>();
		for(PlayerDO playerDO : playerDOList) {
			Player player = PlayerConvertor.convertPlayer(playerDO, userDOMap.get(playerDO.getUserId()));
			playerMap.put(player.getId(), player);
			userPlayerMap.put(player.getUserId(), player);
			GameInfo gameInfo = gameInfoMap.get(player.getGameId());
			if(gameInfo == null) {
				logger.error("loadAllLiveGame error. gameInfo lost. player={}", player);
				continue;
			}
			gameInfo.addPlayer(player);
		}
		this.gameMap = gameInfoMap;
		this.playerMap = playerMap;
		this.userPlayerMap = userPlayerMap;
	}
}
