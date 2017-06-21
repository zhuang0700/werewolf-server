package com.telan.werewolf.game.domain;

import java.io.Serializable;

/**
 * Created by weiwenliang on 2017/6/16.
 */
public class GameConfig implements Serializable {
    private static final long serialVersionUID = -4619274696344227566L;

    //投票相关
    //几轮平票后结束投票
    private int MaxEqualVoteBeforeNight = 2;
    //是否存在法官角色
    private boolean hasJudgeRole = false;
    //法官角色id
    private long judgePlayerId = 0;

    public int getMaxEqualVoteBeforeNight() {
        return MaxEqualVoteBeforeNight;
    }

    public void setMaxEqualVoteBeforeNight(int maxEqualVoteBeforeNight) {
        MaxEqualVoteBeforeNight = maxEqualVoteBeforeNight;
    }

    public boolean isHasJudgeRole() {
        return hasJudgeRole;
    }

    public void setHasJudgeRole(boolean hasJudgeRole) {
        this.hasJudgeRole = hasJudgeRole;
    }

    public long getJudgePlayerId() {
        return judgePlayerId;
    }

    public void setJudgePlayerId(long judgePlayerId) {
        this.judgePlayerId = judgePlayerId;
    }
}
