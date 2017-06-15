package com.telan.werewolf.game.param;

import com.telan.werewolf.domain.UserDO;

/**
 * Created by weiwenliang on 17/5/29.
 */
public class OperateGameParam {
    private UserDO user;

    private int mockPlayerNum;

    private long gameId;

    public UserDO getUser() {
        return user;
    }

    public void setUser(UserDO user) {
        this.user = user;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getMockPlayerNum() {
        return mockPlayerNum;
    }

    public void setMockPlayerNum(int mockPlayerNum) {
        this.mockPlayerNum = mockPlayerNum;
    }
}
