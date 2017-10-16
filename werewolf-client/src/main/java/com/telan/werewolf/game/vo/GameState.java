package com.telan.werewolf.game.vo;

import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable {
    private static final long serialVersionUID = 3308067332869726817L;
    private List<Integer> stageTypeList;
    private int result; //1-胜利 2-失败
    private int roundNo;
    private int roundStatus;

    public List<Integer> getStageTypeList() {
        return stageTypeList;
    }

    public void setStageTypeList(List<Integer> stageTypeList) {
        this.stageTypeList = stageTypeList;
    }

    public int getRoundStatus() {
        return roundStatus;
    }

    public void setRoundStatus(int roundStatus) {
        this.roundStatus = roundStatus;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}