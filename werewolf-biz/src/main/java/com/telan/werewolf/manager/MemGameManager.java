package com.telan.werewolf.manager;

import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.process.GameInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;


public class MemGameManager {
	private static final Logger logger = LoggerFactory.getLogger("SessionManager");

	@Autowired
	public GameManager gameManager;

	public Map<Long, GameInfo> gameMap;

	public Map<Long, Player> playerMap;

	public Map<Long, Player> userPlayerMap;

	public MemGameManager(int initSize) {
		gameMap = new HashMap<>(initSize);
		playerMap = new HashMap<>();
		userPlayerMap = new HashMap<>();

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
}
