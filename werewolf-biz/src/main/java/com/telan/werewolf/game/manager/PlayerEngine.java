package com.telan.werewolf.game.manager;

import com.telan.werewolf.enums.BaseStatus;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.enums.PlayerStatus;

import java.util.Map;

/**
 * Created by weiwenliang on 2017/6/14.
 */
public class PlayerEngine {

    public static void setGameStart(Map<Long, Player> playerMap) {
        for(Player player : playerMap.values()) {
            player.setStatus(PlayerStatus.LIVE.getType());
        }
    }

    public static void quitGameAfterStart(Player player) {
        player.setGameStatus(BaseStatus.DELETED.getType());
    }
}
