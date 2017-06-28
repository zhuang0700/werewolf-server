package com.telan.werewolf.game.domain;


import com.telan.werewolf.game.vo.PlayerVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weiwenliang on 2017/6/16.
 */
public class ActionResult implements Serializable {
    private static final long serialVersionUID = 3642691792135772736L;
    private PlayerVO targetPlayer;

    private int stageStatus;

    public PlayerVO getTargetPlayer() {
        return targetPlayer;
    }

    public void setTargetPlayer(PlayerVO targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public int getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(int stageStatus) {
        this.stageStatus = stageStatus;
    }
}
