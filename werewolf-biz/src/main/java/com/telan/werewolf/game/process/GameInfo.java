package com.telan.werewolf.game.process;

import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.game.domain.role.BaseRole;
import com.telan.werewolf.game.domain.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class GameInfo extends BaseGameInfo {
    private static final long serialVersionUID = -3408607345186297295L;
    private Map<Long, Player> playerMap;
    private List<BaseRole> roleList;

    private Round currentRound;
    private List<Round> roundHistory;

    public void init() {
        this.playerMap = new HashMap<>();
        this.roleList = new ArrayList<>();
        this.roundHistory = new ArrayList<>();
        this.roleList = new ArrayList<>();
    }

    public GameInfo(GameDO gameDO){
        super(gameDO);
    }

    public Map<Long, Player> getPlayerMap() {
        return playerMap;
    }

    public void setPlayerMap(Map<Long, Player> playerMap) {
        this.playerMap = playerMap;
    }

    public List<BaseRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<BaseRole> roleList) {
        this.roleList = roleList;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void changeCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
        this.roundHistory.add(this.currentRound);
    }

    public List<Round> getRoundHistory() {
        return roundHistory;
    }

    public void setRoundHistory(List<Round> roundHistory) {
        this.roundHistory = roundHistory;
    }

    public void addPlayer(Player player) {
        this.playerMap.put(player.getId(), player);
    }
}
