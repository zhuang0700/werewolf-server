package com.telan.werewolf.game.domain;

import com.telan.werewolf.game.domain.record.BaseRecord;

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
    private List<Stage> dayStageList;
    private List<Stage> nightStageList;
    private List<BaseRecord> recordList;

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
}
