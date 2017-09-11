package com.telan.werewolf.param;

import com.telan.werewolf.game.domain.ActionResult;
import com.telan.werewolf.game.domain.GameData;

/**
 * Created by weiwenliang on 17/8/22.
 */
public class BaseResponseData {
    private int status;

    private int code;

    private String msg;

    private GameData gameData;

    private ActionResult actionResult;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public ActionResult getActionResult() {
        return actionResult;
    }

    public void setActionResult(ActionResult actionResult) {
        this.actionResult = actionResult;
    }
}


