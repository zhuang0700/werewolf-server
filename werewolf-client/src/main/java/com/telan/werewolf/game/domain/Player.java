package com.telan.werewolf.game.domain;

import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.role.BaseRole;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class Player {
    private int status;
    private UserDO userDO;
    private PlayerDO playerDO;
    private BaseRole role;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public BaseRole getRole() {
        return role;
    }

    public void setRole(BaseRole role) {
        this.role = role;
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
}
