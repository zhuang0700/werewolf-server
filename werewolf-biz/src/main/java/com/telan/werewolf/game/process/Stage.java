package com.telan.werewolf.game.process;

import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.domain.PlayerAction;
import com.telan.werewolf.game.enums.StageStatus;
import com.telan.werewolf.result.WeResultSupport;

import javax.management.relation.Role;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/16.
 */
public abstract class Stage {

    public int status = StageStatus.NOT_BEGIN.getType();

    public List<Stage> next;

    public List<Stage> before;

    public List<Role> roleList;

    protected List<PlayerAction> actionList;

    public void update(Stage prevStage){
        if(checkStageUpdate(prevStage)){
            start();
        }
    }

    public abstract boolean checkStageUpdate(Stage prevStage);

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
        return new WeResultSupport();
    }

}
