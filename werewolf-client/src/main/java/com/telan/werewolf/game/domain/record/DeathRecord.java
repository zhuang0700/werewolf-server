package com.telan.werewolf.game.domain.record;

import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.vo.PlayerVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class DeathRecord extends BaseRecord {
    private int msgSubType;
    private List<String> recordMsgs;
    private List<PlayerVO> deathList;
    private int deathReason;

    public List<String> getRecordMsgs(){
        return recordMsgs;
    }

    public void setMsgSubType(int msgSubType) {
        this.msgSubType = msgSubType;
    }

    public int getMsgSubType() {
        return msgSubType;
    }

    public void addMsg(String msg) {
        if(recordMsgs == null) {
            recordMsgs = new ArrayList<>();
        }
        recordMsgs.add(msg);
    }

    public List<PlayerVO> getDeathList() {
        return deathList;
    }

    public void setDeathList(List<PlayerVO> deathList) {
        this.deathList = deathList;
    }

    public int getDeathReason() {
        return deathReason;
    }

    public void setDeathReason(int deathReason) {
        this.deathReason = deathReason;
    }
}
