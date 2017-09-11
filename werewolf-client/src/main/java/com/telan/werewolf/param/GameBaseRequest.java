package com.telan.werewolf.param;

import com.telan.werewolf.domain.UserDO;

/**
 * Created by weiwenliang on 17/8/22.
 */
public class GameBaseRequest {
    private long gameId;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }
}
