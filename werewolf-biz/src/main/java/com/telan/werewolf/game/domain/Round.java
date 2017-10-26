package com.telan.werewolf.game.domain;

import com.telan.werewolf.game.domain.record.BaseRecord;
import com.telan.werewolf.game.enums.StageType;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class Round implements Serializable{
    private static final long serialVersionUID = -3047191963545009851L;

    private int roundNo;
    private int roundStatus;
    //白天回合流程头
    private List<Stage> dayStageList;
    //黑夜回合流程头
    private List<Stage> nightStageList;
    private List<BaseRecord> recordList;
    private Stage hunterStage;
    private List<Stage> allStageList;
    private long sheriffPlayerId;

    public long getSheriffPlayerId() {
        return sheriffPlayerId;
    }

    public void setSheriffPlayerId(long sheriffPlayerId) {
        this.sheriffPlayerId = sheriffPlayerId;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public int getRoundStatus() {
        return roundStatus;
    }

    public void setRoundStatus(int roundStatus) {
        this.roundStatus = roundStatus;
    }

    public List<Stage> getDayStageList() {
        return dayStageList;
    }

    public void setDayStageList(List<Stage> dayStageList) {
        this.dayStageList = dayStageList;
    }

    public List<Stage> getNightStageList() {
        return nightStageList;
    }

    public void setNightStageList(List<Stage> nightStageList) {
        this.nightStageList = nightStageList;
    }

    public List<BaseRecord> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<BaseRecord> recordList) {
        this.recordList = recordList;
    }

    public void addRecord(BaseRecord record) {
        if(recordList == null) {
            recordList = new ArrayList<>();
        }
        recordList.add(record);
    }

    public Stage getStageByType(StageType stageType) {
        if(CollectionUtils.isEmpty(allStageList)) {
            return null;
        }
        for(Stage stage : allStageList) {
            if(stage.stageType == stageType) {
                return stage;
            }
        }
        return null;
    }

    public Stage getHunterStage() {
        return hunterStage;
    }

    public void setHunterStage(Stage hunterStage) {
        this.hunterStage = hunterStage;
    }

    public List<Stage> getAllStageList() {
        return allStageList;
    }

    public void setAllStageList(List<Stage> allStageList) {
        this.allStageList = allStageList;
    }

    public void addStageList(List<Stage> stageList) {
        if(CollectionUtils.isEmpty(this.allStageList)){
            this.allStageList = stageList;
        } else {
            this.allStageList.addAll(stageList);
        }
    }
}
