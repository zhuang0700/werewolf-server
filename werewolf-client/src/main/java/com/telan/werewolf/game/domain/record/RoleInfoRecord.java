package com.telan.werewolf.game.domain.record;

import com.telan.werewolf.game.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class RoleInfoRecord extends BaseRecord {
    private static final long serialVersionUID = 8191115429231595725L;

    private int msgSubType;
    private List<String> msgList;
    private List<Player> playerList;

    public List<String> getRecordMsgs(){
        return msgList;
    }

    public void setMsgSubType(int msgSubType) {
        this.msgSubType = msgSubType;
    }

    public int getMsgSubType() {
        return msgSubType;
    }

    public void setMsgList(List<String> msgList) {
        this.msgList = msgList;
    }

    public void addMsg(String msg) {
        if(msgList == null) {
            msgList = new ArrayList<>();
        }
        msgList.add(msg);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
