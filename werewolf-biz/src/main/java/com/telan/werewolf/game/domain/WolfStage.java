package com.telan.werewolf.game.domain;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.enums.*;
import com.telan.werewolf.game.manager.PlayerEngine;
import com.telan.werewolf.result.WeBaseResult;
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

    public WolfStage(GameInfo gameInfo){
        super();
        this.stageType = StageType.WOLF;
        this.roleList = new ArrayList<>();
        this.roleList.add(RoleType.WOLF.getType());
        this.setGameInfo(gameInfo);
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
        List<Player> aliveWolfList = PlayerEngine.getPlayersByRoleAndStatus(gameInfo, PlayerStatus.LIVE.getType(), RoleType.WOLF.getType());
        if(CollectionUtils.isEmpty(aliveWolfList)) {
            finish();
        }
        List<Long> killUsers = ActionUtil.findMaxVote(voteMap);
        if(CollectionUtils.isEmpty(killUsers) || killUsers.size() > 1) {
            markedPlayerId = 0;
            return;
        } else {
            markedPlayerId = killUsers.get(0);
        }
        if(aliveWolfList.size() == actionList.size() && markedPlayerId > 0) {
            finish();
        }
    }

    @Override
    public void roleFinish() {

    }

    @Override
    public WeBaseResult<ActionResult> roleUserAction(Player player, PlayerAction action){
        WeBaseResult<ActionResult> resultSupport = new WeBaseResult<ActionResult>();
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
                List<PlayerAction> actions = voteMap.get(oldAction.toPlayerId);
                if(!CollectionUtils.isEmpty(actions)) {
                    actions.remove(oldAction);
                }
            }
            actionList.add(action);
            if(voteMap.get(action.toPlayerId) == null) {
                List<PlayerAction> actions = new ArrayList<>();
                actions.add(action);
                voteMap.put(action.toPlayerId, actions);
            } else{
                voteMap.get(action.toPlayerId).add(action);
            }
            return resultSupport;
        }
        resultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
        return resultSupport;
    }

    public WeResultSupport finishUserAction(){
        analyse();
        return new WeResultSupport();
    }

}
