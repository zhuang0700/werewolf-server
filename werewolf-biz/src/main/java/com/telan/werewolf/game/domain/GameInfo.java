package com.telan.werewolf.game.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.game.domain.role.BaseRole;

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
    @JSONField(serialize=false)
    private List<Round> roundHistory;
    private long sheriffPlayerId = 0;

    public void init() {
        this.playerMap = new HashMap<>();
        this.roleList = new ArrayList<>();
        this.roundHistory = new ArrayList<>();
        this.roleList = new ArrayList<>();
        this.setGameConfig(new GameConfig());
    }

    public GameInfo(GameDO gameDO){
        super(gameDO);
    }

    public Map<Long, Player> getPlayerMap() {
        return playerMap;
    }

    public Player getPlayer(long playerId) {
        if(playerMap != null) {
            return playerMap.get(playerId);
        }
        return null;
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

    public void removePlayer(long playerId) {
        playerMap.remove(playerId);
    }

    public Player getCreator() {
        for(Player player : playerMap.values()) {
            if(player.getUserId() == getGameDO().getCreatorId()) {
                return player;
            }
        }
        return null;
    }

    public long getSheriffPlayerId() {
        return sheriffPlayerId;
    }

    public void setSheriffPlayerId(long sheriffPlayerId) {
        this.sheriffPlayerId = sheriffPlayerId;
    }

    public List<Player> getPlayerList() {
        return new ArrayList<>(playerMap.values());
    }
}
