package com.telan.werewolf.game.param;

import com.telan.werewolf.domain.UserDO;

/**
 * Created by weiwenliang on 17/5/29.
 */
public class JoinGameParam {
    private UserDO creator;

    private int mockPlayerNum;

    private long gameId;

    public UserDO getCreator() {
        return creator;
    }

    public void setCreator(UserDO creator) {
        this.creator = creator;
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
