package com.telan.werewolf.game.domain;

import com.telan.werewolf.domain.GameDO;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class BaseGameInfo implements Serializable {
    private static final long serialVersionUID = 6871822939225847741L;
    private GameDO gameDO;
    private GameConfig gameConfig;
    private Date endTime;

    public BaseGameInfo(GameDO gameDO) {
        this.gameDO = gameDO;
    }

    public long getGameId() {
        return gameDO.getId();
    }

    public GameDO getGameDO() {
        return gameDO;
    }

    public long getCreatorId() {
        return gameDO.getCreatorId();
    }

    public Date getStartTime() {
        return gameDO.getGmtCreated();
    }


    public Date getEndTime() {
        return endTime;
    }


    public int getPlayerNum() {
        return gameDO.getPlayerNum();
    }

    public int getGameStatus() {
        return gameDO.getStatus();
    }

    public void setGameStatus(int status) {
        this.gameDO.setStatus(status);
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public void setGameConfig(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }
}
