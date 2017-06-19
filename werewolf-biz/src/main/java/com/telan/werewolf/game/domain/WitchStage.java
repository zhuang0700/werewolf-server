package com.telan.werewolf.game.domain;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.enums.ActionType;
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
        super();
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
        WeResultSupport resultSupport = new WeResultSupport();
        if(action.actionType == ActionType.SAVE.getType()) {
            useMedicine = true;
        } else if(action.actionType == ActionType.POISON.getType()) {
            usePoisionId = action.toPlayerId;
        }else {
            resultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
        }
        return resultSupport;
    }

    public WeResultSupport finishUserAction() {
        analyse();
        return new WeResultSupport();
    }
}
