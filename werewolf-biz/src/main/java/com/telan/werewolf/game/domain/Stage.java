package com.telan.werewolf.game.domain;

import com.telan.werewolf.game.enums.StageStatus;
import com.telan.werewolf.game.enums.StageType;
import com.telan.werewolf.game.manager.ActionEngine;
import com.telan.werewolf.manager.MemGameManager;
import com.telan.werewolf.result.WeResultSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/16.
 */
public abstract class Stage {
    @Autowired
    public ActionEngine actionEngine;

    @Autowired
    public MemGameManager memGameManager;

    public int status = StageStatus.NOT_BEGIN.getType();

    public long markedPlayerId;

    public StageType stageType;

    public List<Stage> next;

    public List<Stage> before;

    public List<Role> roleList;

    protected List<PlayerAction> actionList;

    public void linkNext(Stage nextStage) {
        if(next == null) {
            next = new ArrayList<>();
        }
        next.add(nextStage);
        if(nextStage.before == null) {
            nextStage.before = new ArrayList<>();
        }
        nextStage.before.add(this);
    }

    public void update(Stage prevStage){
        if(checkStageUpdate(prevStage)){
            start();
        }
    }

    public boolean checkStageUpdate(Stage prevStage){
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

    public void start(){
        this.status = StageStatus.BEGIN.getType();
        roleStart();
    }

    public abstract void roleStart();

    public void waitingAction(){
        this.status = StageStatus.WAITING_ACTION.getType();
        roleWaitingAction();
    }

    public abstract void roleWaitingAction();

    public void analyse(){
        this.status = StageStatus.ANALYSE.getType();
        roleAnalyse();
    }

    public abstract void roleAnalyse();

    public void end(){
        this.status = StageStatus.FINISH.getType();
        roleFinish();
    }

    public abstract void roleFinish();

    public boolean isFinish() {
        return status == StageStatus.FINISH.getType();
    }

    public WeResultSupport userAction(Player player, PlayerAction action){
        WeResultSupport resultSupport = actionEngine.checkAction(player, action);
        if(!resultSupport.isSuccess()) {
            return resultSupport;
        }
        return roleUserAction(player, action);
    }

    public abstract WeResultSupport roleUserAction(Player player, PlayerAction action);
}
