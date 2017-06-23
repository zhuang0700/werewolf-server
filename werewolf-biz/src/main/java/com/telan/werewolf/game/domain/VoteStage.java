package com.telan.werewolf.game.domain;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.enums.*;
import com.telan.werewolf.game.manager.PlayerEngine;
import com.telan.werewolf.game.manager.RecordEngine;
import com.telan.werewolf.manager.MemGameManager;
import com.telan.werewolf.result.WeResultSupport;
import com.telan.werewolf.utils.ActionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class VoteStage extends Stage {

    private int repeatNum = 1;

    public VoteStage(){
        super();
        this.stageType = StageType.VOTE;
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
        voteMap = ActionUtil.convertListToMap(actionList);
        List<Long> maxVoteIds = ActionUtil.findMaxVote(voteMap);
        RecordEngine.sendVoteActionMsg(gameInfo, actionList);
        if(!CollectionUtils.isEmpty(maxVoteIds) && maxVoteIds.size() > 1 && repeatNum < getGameConfig().getMaxEqualVoteBeforeNight()) {
            RecordEngine.sendVoteResultMsg(gameInfo, GameMsgSubType.VOTE_RESULT.getSubType(), maxVoteIds, true);
            reVote();
        } else {
            RecordEngine.sendVoteResultMsg(gameInfo, GameMsgSubType.VOTE_RESULT.getSubType(), maxVoteIds, false);
            if(!CollectionUtils.isEmpty(maxVoteIds) && maxVoteIds.size() == 1) {
                Map<Long, Integer> deathInfo = new HashMap<>();
                deathInfo.put(maxVoteIds.get(0), DeadReason.VOTE.getType());
                if(!PlayerEngine.setPlayerDead(gameInfo, deathInfo, this)) {
                    //stop
                    return;
                }
            }
            finish();
        }
    }

    private void reVote() {
        this.repeatNum++;
        this.start();
    }

    @Override
    public void roleFinish() {

    }

    @Override
    public WeResultSupport roleUserAction(Player player, PlayerAction action){
        WeResultSupport resultSupport = new WeResultSupport();
        if(action.actionType == ActionType.VOTE.getType()) {
            if(ActionUtil.findActionByFromId(actionList, action.fromPlayerId) != null) {
                resultSupport.setErrorCode(WeErrorCode.DUPLICATE_ACTION);
                return resultSupport;
            }
            if(this.status != StageStatus.WAITING_ACTION.getType()) {
                resultSupport.setErrorCode(WeErrorCode.WRONG_STAGE_ACTION);
                return resultSupport;
            }
            Player toPlayer = getPlayerMap().get(action.toPlayerId);
            if(toPlayer == null || toPlayer.getStatus() != PlayerStatus.LIVE.getType()) {
                resultSupport.setErrorCode(WeErrorCode.WRONG_ACTION_TARGET);
                return resultSupport;
            }
            actionList.add(action);
        } else {
            resultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
        }
        return resultSupport;
    }
}
