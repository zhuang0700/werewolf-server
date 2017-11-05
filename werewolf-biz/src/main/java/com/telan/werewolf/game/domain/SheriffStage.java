package com.telan.werewolf.game.domain;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.factory.GameMsgFactory;
import com.telan.werewolf.game.enums.*;
import com.telan.werewolf.game.manager.GameEngine;
import com.telan.werewolf.game.manager.PlayerEngine;
import com.telan.werewolf.game.manager.RecordEngine;
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
        GameMsg msg = GameMsgFactory.createGameMsg(GameMsgSubType.STAGE_START, Visibility.ALL, new Object[]{stageType.getDesc()});
        RecordEngine.sendNormalMsg(gameInfo, msg);
        waitingAction();
    }

    @Override
    public void roleWaitingAction() {
        return;
    }

    @Override
    public void roleAnalyse() {
        List<Player> voters = PlayerEngine.getPlayersByRoleAndStatus(gameInfo, PlayerStatus.LIVE.getType(), -1);
        if(CollectionUtils.isEmpty(voters)) {
            finish();
            return;
        }
        if(actionList.size() == voters.size()) {
            finish();
            return;
        }
    }

    private void reVote() {
        this.start();
    }
    @Override
    public void roleFinish() {
        RecordEngine.sendVoteActionMsg(gameInfo, actionList);
        List<Long> maxVoteIds = ActionUtil.findMaxVote(voteMap);
        if(!CollectionUtils.isEmpty(maxVoteIds) && maxVoteIds.size() > 1) {
            RecordEngine.sendVoteResultMsg(gameInfo, GameMsgSubType.VOTE_RESULT.getSubType(), maxVoteIds, true);
            reVote();
        } else {
            if(!CollectionUtils.isEmpty(maxVoteIds) && maxVoteIds.size() == 1) {
                GameEngine.changeSheriff(gameInfo, maxVoteIds.get(0));
                RecordEngine.sendVoteResultMsg(gameInfo, GameMsgSubType.VOTE_SHERIFF_RESULT.getSubType(), maxVoteIds, false);
            }
            GameEngine.tryEndGame(gameInfo);
        }
    }

    @Override
    public WeBaseResult<ActionResult> roleUserAction(Player player, PlayerAction action){
        WeBaseResult<ActionResult> resultSupport = new WeBaseResult<ActionResult>();
        if(action.actionType == ActionType.RUN_SHERIFF.getType()) {
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
            if(voteMap.get(action.toPlayerId) == null) {
                List<PlayerAction> actions = new ArrayList<>();
                actions.add(action);
                voteMap.put(action.toPlayerId, actions);
            } else{
                voteMap.get(action.toPlayerId).add(action);
            }
            analyse();
        }else{
            resultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
        }
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
