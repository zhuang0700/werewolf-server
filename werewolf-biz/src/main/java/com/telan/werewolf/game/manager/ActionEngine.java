package com.telan.werewolf.game.manager;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.domain.PlayerAction;
import com.telan.werewolf.game.enums.ActionType;
import com.telan.werewolf.game.enums.PlayerStatus;
import com.telan.werewolf.game.enums.StageStatus;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WeResultSupport;
import com.telan.werewolf.utils.ActionUtil;

/**
 * Created by weiwenliang on 17/5/23.
 */
public class ActionEngine {
    public WeResultSupport checkAction(Player player, PlayerAction action) {
        WeResultSupport resultSupport = new WeResultSupport();
        if(player.getStatus() == PlayerStatus.DEAD.getType()) {
            resultSupport.setErrorCode(WeErrorCode.DEAD_ACTION);
            return resultSupport;
        }
        return resultSupport;
    }
}
