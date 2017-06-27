package com.telan.werewolf.game.domain;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.enums.*;
import com.telan.werewolf.game.manager.PlayerEngine;
import com.telan.werewolf.result.WeResultSupport;
import com.telan.werewolf.utils.ActionUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class SeerStage extends Stage {

    public SeerStage(){
        super();
        this.stageType = StageType.SEER;
        this.roleList = new ArrayList<>();
        this.roleList.add(RoleType.SEER.getType());
    }

    @Override
    public void roleStart() {
        if(voteMap == null) {
            voteMap = new HashMap<>();
        }
        waitingAction();
    }

    @Override
    public void roleWaitingAction() {
        return;
    }

    @Override
    public void roleAnalyse() {
        List<Player> seer = PlayerEngine.getPlayersByRoleAndStatus(gameInfo, PlayerStatus.LIVE.getType(), RoleType.SEER.getType());
        if(CollectionUtils.isEmpty(seer)) {
            finish();
            return;
        }
        if(!CollectionUtils.isEmpty(actionList)) {
            finish();
            return;
        }
    }

    @Override
    public void roleFinish() {

    }

    @Override
    public WeResultSupport roleUserAction(Player player, PlayerAction action){
        WeResultSupport resultSupport = new WeResultSupport();
        if(action.actionType == ActionType.SEE.getType()) {
            if(ActionUtil.findActionByFromId(actionList, action.fromPlayerId) != null) {
                resultSupport.setErrorCode(WeErrorCode.DUPLICATE_ACTION);
                return resultSupport;
            }
            if(player.getStatus() == PlayerStatus.DEAD.getType()) {
                resultSupport.setErrorCode(WeErrorCode.DEAD_ACTION);
                return resultSupport;
            }
            actionList.add(action);
            analyse();
            return resultSupport;
        }
        resultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
        return resultSupport;
    }
}
