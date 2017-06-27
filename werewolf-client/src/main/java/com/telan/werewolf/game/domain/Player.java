package com.telan.werewolf.game.domain;

import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.record.BaseRecord;
import com.telan.werewolf.game.domain.role.BaseRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class Player {
    private UserDO userDO;
    private PlayerDO playerDO;
    private BaseRole role;
    private List<BaseRecord> recordList;
    private int deadReason;

    public int getStatus() {
        return playerDO.getStatus();
    }

    public void setStatus(int status) {
        this.playerDO.setStatus(status);
    }

    public int getGameStatus() {
        return playerDO.getGameStatus();
    }

    public void setGameStatus(int status) {
        this.playerDO.setGameStatus(status);
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public int getRoleType() {
        if(role == null) {
            return 0;
        }
        return role.getRole();
    }

    public BaseRole getRole() {
        return role;
    }

    public void setRole(BaseRole role) {
        this.role = role;
        this.playerDO.setRole(role.getRole());
    }

    public PlayerDO getPlayerDO() {
        return playerDO;
    }

    public void setPlayerDO(PlayerDO playerDO) {
        this.playerDO = playerDO;
    }

    public long getId(){
        return playerDO.getId();
    }

    public long getUserId() {
        return userDO.getId();
    }

    public long getGameId() {
        return playerDO.getGameId();
    }

    public long getPlayerNo() {
        return playerDO.getPlayerNo();
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

    public int getDeadReason() {
        return deadReason;
    }

    public void setDeadReason(int deadReason) {
        this.deadReason = deadReason;
    }
}
