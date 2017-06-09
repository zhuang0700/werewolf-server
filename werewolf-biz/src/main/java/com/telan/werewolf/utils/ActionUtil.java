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
}
