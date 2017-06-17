package com.telan.werewolf.game.domain;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.enums.ActionType;
import com.telan.werewolf.game.enums.PlayerStatus;
import com.telan.werewolf.game.enums.StageStatus;
import com.telan.werewolf.game.enums.StageType;
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
        this.stageType = StageType.WOLF;
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
            if(this.status != StageStatus.WAITING_ACTION.getType()) {
                resultSupport.setErrorCode(WeErrorCode.WRONG_STAGE_ACTION);
                return resultSupport;
            }
            Player toPlayer = memGameManager.getPlayer(action.toPlayerId);
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
