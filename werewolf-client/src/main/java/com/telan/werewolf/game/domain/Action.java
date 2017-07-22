package com.telan.werewolf.game.domain;


import java.io.Serializable;
import java.util.List;

/**
 * Created by weiwenliang on 2017/6/16.
 */
public class Action implements Serializable {
    private static final long serialVersionUID = 3642691792135772736L;
    public int playerActionType;

    public int actionStatus;

    public List<Long> actionPlayerIds;

    public List<Long> targetPlayerIds;

    public int maxTargetNumber = 1;
}
