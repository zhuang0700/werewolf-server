package com.telan.werewolf.game.vo;

import com.telan.werewolf.game.enums.RoleType;
import com.telan.werewolf.game.enums.StageType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.telan.werewolf.game.domain.GameConfigConst.VICTORY_RULE_SIDE;

/**
 * Created by weiwenliang on 2017/6/16.
 */
public class UserGameConfig implements Serializable {
    private static final long serialVersionUID = -4619274696344227566L;

    //是否存在法官角色
//    private boolean hasJudgeRole = false;
    //法官角色id
//    private long judgePlayerId;

    private String victoryRule;

    private Map<Integer, Integer> roleNum;

    private long gameId;

    private boolean customRoleList = false;

    public UserGameConfig(){
        customRoleList = false;
        victoryRule = VICTORY_RULE_SIDE;
    }


    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getVictoryRule() {
        return victoryRule;
    }

    public void setVictoryRule(String victoryRule) {
        this.victoryRule = victoryRule;
    }

    public Map<Integer, Integer> getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(Map<Integer, Integer> roleNum) {
        this.roleNum = roleNum;
    }

    public boolean isCustomRoleList() {
        return customRoleList;
    }

    public void setCustomRoleList(boolean customRoleList) {
        this.customRoleList = customRoleList;
    }


}
