package com.telan.werewolf.game.domain;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.enums.*;
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
public class WolfStage extends Stage {

    Map<Long, List<PlayerAction>> voteMap;

    public WolfStage(){
        super();
        this.stageType = StageType.WOLF;
        this.roleList = new ArrayList<>();
        this.roleList.add(RoleType.WOLF.getType());
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
        for(PlayerAction action : actionList) {
            if(voteMap.get(action.toPlayerId) == null) {
                List<PlayerAction> actions = new ArrayList<>();
                actions.add(action);
                voteMap.put(action.toPlayerId, actions);
            } else{
                voteMap.get(action.toPlayerId).add(action);
            }
        }
        List<Long> killUsers = ActionUtil.findMaxVote(voteMap);
        if(CollectionUtils.isEmpty(killUsers) || killUsers.size() > 1) {

        } else {
            markedPlayerId = killUsers.get(0);
        }
        finish();
    }

    @Override
    public void roleFinish() {

    }

    @Override
    public WeResultSupport roleUserAction(Player player, PlayerAction action){
        WeResultSupport resultSupport = new WeResultSupport();
        if(action.actionType == ActionType.KILL.getType()) {
            if(this.status != StageStatus.WAITING_ACTION.getType()) {
                resultSupport.setErrorCode(WeErrorCode.WRONG_STAGE_ACTION);
                return resultSupport;
            }
            Player toPlayer = getPlayerMap().get(action.toPlayerId);
            if(toPlayer == null || toPlayer.getStatus() != PlayerStatus.LIVE.getType()) {
                resultSupport.setErrorCode(WeErrorCode.WRONG_ACTION_TARGET);
                return resultSupport;
            }
            //狼人可以多次选择目标，以最后一次为准
            PlayerAction oldAction = ActionUtil.findActionByFromId(actionList, action.fromPlayerId);
            if(oldAction != null) {
                actionList.remove(oldAction);
            }
            actionList.add(action);
        }
        resultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
        return resultSupport;
    }

    public WeResultSupport finishUserAction(){
        analyse();
        return new WeResultSupport();
    }

}
