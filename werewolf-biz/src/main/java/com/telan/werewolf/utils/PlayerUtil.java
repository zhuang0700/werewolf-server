package com.telan.werewolf.utils;

import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.domain.PlayerAction;
import org.springframework.util.CollectionUtils;
import sun.java2d.pipe.AAShapePipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/5/19.
 */
public class PlayerUtil {
    public static List<Player> getPlayerByRoleAndStatus(Map<Long, Player> playerMap, int role, int status) {
        if(CollectionUtils.isEmpty(playerMap)) {
            return null;
        }
        List<Player> targetPlayers = new ArrayList<>();
        for(Player player : playerMap.values()) {
            if(role > 0 && player.getRoleType() != role) {
                continue;
            }
            if(status > 0 && player.getStatus() != status) {
                continue;
            }
            //else
            targetPlayers.add(player);
        }
        return targetPlayers;
    }

    public static List<Long> getPlayerIdsByRoleAndStatus(Map<Long, Player> playerMap, int role, int status) {
        if(CollectionUtils.isEmpty(playerMap)) {
            return null;
        }
        List<Long> targetPlayerIds = new ArrayList<>();
        for(Player player : playerMap.values()) {
            if(role >= 0 && player.getRoleType() != role) {
                continue;
            }
            if(status >= 0 && player.getStatus() != status) {
                continue;
            }
            //else
            targetPlayerIds.add(player.getId());
        }
        return targetPlayerIds;
    }
}
