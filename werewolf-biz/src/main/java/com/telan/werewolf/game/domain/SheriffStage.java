package com.telan.werewolf.game.domain;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.factory.GameMsgFactory;
import com.telan.werewolf.game.enums.*;
import com.telan.werewolf.game.manager.RecordEngine;
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
public class SheriffStage extends Stage {

    Map<Long, List<PlayerAction>> voteMap;


    public SheriffStage(){
        this.stageType = StageType.SHERIFF;
    }

    @Override
    public void roleStart() {
        if(voteMap == null) {
            voteMap = new HashMap<>();
        }
        GameMsg msg = GameMsgFactory.createGameMsg(GameMsgSubType.STAGE_START, Visibility.ALL, new Object[]{stageType.name()});
        RecordEngine.sendNormalMsg(gameInfo, msg);
        waitingAction();
    }

    @Override
    public void roleWaitingAction() {
        return;
    }

    @Override
    public void roleAnalyse() {
        Map<Long, Integer> killMap = new HashMap<>();
        for(PlayerAction action : actionList) {
            if(killMap.get(action.toPlayerId) == null) {
                killMap.put(action.toPlayerId, 1);
            } else{
                killMap.put(action.toPlayerId, killMap.get(action.toPlayerId) +1);
            }
        }
    }

    @Override
    public void roleFinish() {

    }

    @Override
    public WeResultSupport roleUserAction(Player player, PlayerAction action){
        WeResultSupport resultSupport = new WeResultSupport();
        if(action.actionType == ActionType.KILL.getType()) {
            if(ActionUtil.findActionByFromId(actionList, action.fromPlayerId) != null) {
                resultSupport.setErrorCode(WeErrorCode.DUPLICATE_ACTION);
                return resultSupport;
            }
            if(player.getStatus() == PlayerStatus.DEAD.getType()) {
                resultSupport.setErrorCode(WeErrorCode.DEAD_ACTION);
                return resultSupport;
            }
            if(this.status != StageStatus.WAITING_ACTION.getType()) {
                resultSupport.setErrorCode(WeErrorCode.WRONG_STAGE_ACTION);
                return resultSupport;
            }
            actionList.add(action);
        }
        resultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
        return resultSupport;
    }

    private List<Long> findMaxVote(){
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
                } else{
                    ids.add(playerId);
                }
            }
            return ids;
        }
        return null;
    }
}
