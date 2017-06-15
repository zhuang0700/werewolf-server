package com.telan.werewolf.game.domain.record;


import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public abstract class BaseRecord {
    private int recordType;
    abstract public List<String> getRecordMsgs();

    public int getRecordType() {
        return recordType;
    }

    public void setRecordType(int recordType) {
        this.recordType = recordType;
    }
}
