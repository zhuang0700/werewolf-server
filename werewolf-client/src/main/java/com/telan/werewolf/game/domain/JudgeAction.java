package com.telan.werewolf.game.domain;

import com.telan.werewolf.domain.UserDO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class JudgeAction implements Serializable{
    private static final long serialVersionUID = 8998537348647354299L;
    private UserDO userDO;

    private long gameId;

    private List<Long> extraPlayerList;

    private Date actionTime;

    private int actionType;

    private int stageType;

    private long targetPlayerId;

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public List<Long> getExtraPlayerList() {
        return extraPlayerList;
    }

    public void setExtraPlayerList(List<Long> extraPlayerList) {
        this.extraPlayerList = extraPlayerList;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public int getStageType() {
        return stageType;
    }

    public void setStageType(int stageType) {
        this.stageType = stageType;
    }

    public long getTargetPlayerId() {
        return targetPlayerId;
    }

    public void setTargetPlayerId(long targetPlayerId) {
        this.targetPlayerId = targetPlayerId;
    }
}
