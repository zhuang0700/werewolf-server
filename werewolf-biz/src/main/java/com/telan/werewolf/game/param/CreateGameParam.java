package com.telan.werewolf.game.param;

import com.telan.werewolf.domain.UserDO;

/**
 * Created by weiwenliang on 17/5/29.
 */
public class CreateGameParam {
    private UserDO creator;
    private int playerNum;

    public UserDO getCreator() {
        return creator;
    }

    public void setCreator(UserDO creator) {
        this.creator = creator;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }
}
