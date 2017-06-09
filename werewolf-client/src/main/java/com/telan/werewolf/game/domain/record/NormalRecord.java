package com.telan.werewolf.game.domain.record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class NormalRecord extends BaseRecord {
    private int msgSubType;
    private List<String> msgList;

    public List<String> getRecordMsgs(){
        return msgList;
    }

    public void setMsgSubType(int msgSubType) {
        this.msgSubType = msgSubType;
    }

    public int getMsgSubType() {
        return msgSubType;
    }

    public void addMsg(String msg) {
        if(msgList == null) {
            msgList = new ArrayList<>();
        }
        msgList.add(msg);
    }
}
