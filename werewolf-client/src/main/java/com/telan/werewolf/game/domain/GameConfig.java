package com.telan.werewolf.game.domain;

import java.io.Serializable;

/**
 * Created by weiwenliang on 2017/6/16.
 */
public class GameConfig implements Serializable {
    private static final long serialVersionUID = -4619274696344227566L;

    //投票相关
    //几轮平票后结束投票
    public int MaxEqualVoteBeforeNight = 2;
}
