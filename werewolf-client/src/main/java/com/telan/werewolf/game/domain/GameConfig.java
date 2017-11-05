package com.telan.werewolf.game.domain;

import com.telan.werewolf.game.enums.ActionType;
import com.telan.werewolf.game.enums.RoleType;
import com.telan.werewolf.game.enums.StageType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.telan.werewolf.game.domain.GameConfigConst.*;

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

    private String victoryRule = VICTORY_RULE_CAMP;

    private List<Integer> shareInfoRoles = new ArrayList<>();

    private long defaultActionTimeout = 30000; //单位ms

    private long noAvailableActionDelay = 10000; //单位ms

    private Map<Integer, Long> actionTimeoutMap;

    private boolean enableActionTimeout = true;

    private Map<Integer, Integer> roleNum;

    private boolean customRoleList = false;

    private boolean needSheriff = false;

    public GameConfig(){
        shareInfoRoles.add(RoleType.WOLF.getType());
        actionTimeoutMap = new HashMap<>();
        for(StageType stageType : StageType.values()) {
            actionTimeoutMap.put(stageType.getType(), defaultActionTimeout);
        }
    }

    public boolean isNeedSheriff() {
        return needSheriff;
    }

    public void setNeedSheriff(boolean needSheriff) {
        this.needSheriff = needSheriff;
    }

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

    public String getVictoryRule() {
        return victoryRule;
    }

    public void setVictoryRule(String victoryRule) {
        this.victoryRule = victoryRule;
    }

    public List<Integer> getShareInfoRoles() {
        return shareInfoRoles;
    }

    public void setShareInfoRoles(List<Integer> shareInfoRoles) {
        this.shareInfoRoles = shareInfoRoles;
    }

    public long getDefaultActionTimeout() {
        return defaultActionTimeout;
    }

    public void setDefaultActionTimeout(long defaultActionTimeout) {
        this.defaultActionTimeout = defaultActionTimeout;
    }

    public void setActionTimeoutMap(Map<Integer, Long> actionTimeoutMap) {
        this.actionTimeoutMap = actionTimeoutMap;
    }

    public long getNoAvailableActionDelay() {
        return noAvailableActionDelay;
    }

    public void setNoAvailableActionDelay(long noAvailableActionDelay) {
        this.noAvailableActionDelay = noAvailableActionDelay;
    }

    public boolean isEnableActionTimeout() {
        return enableActionTimeout;
    }

    public void setEnableActionTimeout(boolean enableActionTimeout) {
        this.enableActionTimeout = enableActionTimeout;
    }

    public long getActionTimeOut(int stageType) {
        return actionTimeoutMap.get(stageType);
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
