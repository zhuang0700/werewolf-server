package com.telan.werewolf.game.domain;

import com.telan.werewolf.game.enums.RoleType;

import java.util.Date;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class GameMsg {
    private int type;

    private List<RoleType> visableRole;

    private List<Long> visablePlayer;

    private Date actionTime;

    private Object msg;
}
