package com.telan.werewolf.game.domain.record;

import com.telan.werewolf.game.domain.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class DeathRecord extends BaseRecord {
    private int msgSubType;
    private List<String> msgList;
    private List<Player> deathList;
    private int deathReason;

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

    public List<Player> getDeathList() {
        return deathList;
    }

    public void setDeathList(List<Player> deathList) {
        this.deathList = deathList;
    }

    public int getDeathReason() {
        return deathReason;
    }

    public void setDeathReason(int deathReason) {
        this.deathReason = deathReason;
    }
}
