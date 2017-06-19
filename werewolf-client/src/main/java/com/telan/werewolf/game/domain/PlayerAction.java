package com.telan.werewolf.game.domain;

import java.util.Date;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class PlayerAction {
    public long fromPlayerId;

    public long toPlayerId;

    public long gameId;

    public List<Long> extraPlayerList;

    public Date actionTime;

    public int actionType;

    public Object msg;
}
