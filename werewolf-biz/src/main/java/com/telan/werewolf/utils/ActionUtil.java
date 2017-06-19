package com.telan.werewolf.utils;

import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.domain.PlayerAction;
import com.telan.werewolf.game.enums.ActionType;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/5/19.
 */
public class ActionUtil {
    public static PlayerAction findActionByFromId(List<PlayerAction> playerActions, long fromId) {
        if(CollectionUtils.isEmpty(playerActions)) {
            return null;
        }
        for(PlayerAction playerAction : playerActions) {
            if(playerAction.fromPlayerId == fromId) {
                return playerAction;
            }
        }
        return null;
    }

    public static PlayerAction findActionByFromIdAndType(List<PlayerAction> playerActions, long fromId, int actionType) {
        if(CollectionUtils.isEmpty(playerActions)) {
            return null;
        }
        for(PlayerAction playerAction : playerActions) {
            if(playerAction.fromPlayerId == fromId && playerAction.actionType == actionType) {
                return playerAction;
            }
        }
        return null;
    }


    public static List<Long> findMaxVote(Map<Long, List<PlayerAction>> voteMap){
        if(!CollectionUtils.isEmpty(voteMap)) {
            int max = 0;
            List<Long> ids = new ArrayList<>();
            for(Long playerId : voteMap.keySet()) {
                int votes = voteMap.get(playerId).size();
                if(max > votes) {
                    continue;
                } else if(max < votes) {
                    ids.clear();
                    max = votes;
                } else{ //equal
                    ids.add(playerId);
                }
            }
            return ids;
        }
        return null;
    }
}
