package com.telan.werewolf.game.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.telan.werewolf.game.enums.StageStatus;
import com.telan.werewolf.game.enums.StageType;
import com.telan.werewolf.game.manager.ActionEngine;
import com.telan.werewolf.game.manager.RecordEngine;
import com.telan.werewolf.game.manager.RoundEngine;
import com.telan.werewolf.manager.MemGameManager;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WeResultSupport;
import com.telan.werewolf.utils.ActionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.management.relation.Role;
import java.util.*;

/**
 * Created by weiwenliang on 17/5/16.
 */
public abstract class Stage {

    private Timer timer;

    @JSONField(serialize=false)
    protected GameInfo gameInfo;

    @JSONField(serialize=false)
    public Map<Long, List<PlayerAction>> voteMap;

    public int status = StageStatus.NOT_BEGIN.getType();

    //狼人选中目标id,0表示不刀
    public long markedPlayerId;

    public boolean useMedicine;

    //大于0表示用毒，目标id
    public long usePoisionId;

    public StageType stageType;

    @JSONField(serialize=false)
    public List<Stage> next;
    @JSONField(serialize=false)
    public List<Stage> before;

    public List<Integer> roleList;

    protected List<PlayerAction> actionList;

    public int getStatus() {
        return status;
    }

    public String getStageTypeDesc() {
        return stageType.getDesc();
    }

    public PlayerAction getAction(long fromId) {
        return ActionUtil.findActionByFromId(actionList, fromId);
    }

    public PlayerAction getAction(long fromId, int actionType) {
        return ActionUtil.findActionByFromIdAndType(actionList, fromId, actionType);
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    @JSONField(serialize=false)
    public GameConfig getGameConfig() {
        return gameInfo.getGameConfig();
    }

    @JSONField(serialize=false)
    public Round getCurrentRound() {
        return gameInfo.getCurrentRound();
    }

    @JSONField(serialize=false)
    public Map<Long, Player> getPlayerMap() {
        return gameInfo.getPlayerMap();
    }

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
        StageStatus stageStatus = StageStatus.getByType(this.status);
        switch (stageStatus) {
            case NOT_BEGIN:
                if (checkStageUpdate(prevStage)) {
                        start();
                }
                break;
            case BEGIN:
            case WAITING_ACTION:
            case FINISH:
                break;
            case ANALYSE:
                finish();
                break;
        }
    }

    public boolean checkStageUpdate(Stage prevStage){
        if(CollectionUtils.isEmpty(before)) {
            return true;
        }
        for(Stage st : before) {
            if (!st.isFinish()) {
                return false;
            }
        }
        //all finished
        return true;
    }

    public void start(){
        synchronized(this) {
            this.status = StageStatus.BEGIN.getType();
        }
        this.voteMap = new HashMap<>();
        this.actionList = new ArrayList<>();
        this.timer = new Timer();
        roleStart();
    }

    public abstract void roleStart();

    public void waitingAction(){
        synchronized(this) {
            this.status = StageStatus.WAITING_ACTION.getType();
        }
        roleWaitingAction();
        if(getGameConfig().isEnableActionTimeout()) {
            timer.schedule(new MyTask(), getGameConfig().getNoAvailableActionDelay(), getGameConfig().getActionTimeOut(stageType.getType()));
        } else {
            timer.schedule(new MyTask(), getGameConfig().getNoAvailableActionDelay());
        }
    }

    public abstract void roleWaitingAction();

    public void analyse(){
        roleAnalyse();
    }

    public abstract void roleAnalyse();

    public void finish(){
        synchronized(this) {
            this.status = StageStatus.FINISH.getType();
        }
        roleFinish();
        if(!CollectionUtils.isEmpty(next)) {
            for(Stage stage : next) {
                stage.update(this);
            }
        }
    }

    public abstract void roleFinish();

    public boolean isFinish() {
        synchronized(this) {
            return status == StageStatus.FINISH.getType();
        }
    }

    public WeBaseResult<ActionResult> userAction(Player player, PlayerAction action){
        synchronized(this) {
            WeBaseResult<ActionResult> resultSupport = ActionEngine.checkAction(player, action);
            if(!resultSupport.isSuccess()) {
                return resultSupport;
            }
            resultSupport = roleUserAction(player, action);
            if(resultSupport.isSuccess()) {
                RecordEngine.sendActionMsg(gameInfo, action);
            }
            analyse();
            return resultSupport;
        }
    }

    public abstract WeBaseResult<ActionResult> roleUserAction(Player player, PlayerAction action);

    class MyTask extends TimerTask {
        public void run(){
            if(status == StageStatus.WAITING_ACTION.getType()) {
//                System.out.println("-------timer start: stage type = " + stageType.name());
                analyse();
            } else {
                cancel();
            }
        }
    }
}
