package com.telan.werewolf.game.domain;

import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.param.GameBaseRequest;

import java.util.Date;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class PlayerAction extends GameBaseRequest {
    private UserDO userDO;

    public long fromPlayerId;

    public long toPlayerId;

    public List<Long> extraPlayerList;

    public Date actionTime;

    public int actionType;

    public String msg = "YES";

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }
}
