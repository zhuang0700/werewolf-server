package com.telan.werewolf.game.domain;

import com.telan.werewolf.game.enums.StageType;
import com.telan.werewolf.result.WeResultSupport;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class WitchStage extends Stage {

    Map<Long, List<PlayerAction>> voteMap;

    public boolean checkStageUpdate(Stage prevStage){
        if(prevStage != null) {
            this.markedPlayerId = prevStage.markedPlayerId;
        }
        if(CollectionUtils.isEmpty(before)) {
            return true;
        }
        for(Stage st : before) {
            if(!st.isFinish()){
                return false;
            }
        }
        //all finished
        return true;
    }

    public WitchStage(){
        this.stageType = StageType.WITCH;
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
        //TODO: to be done
        return null;
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
