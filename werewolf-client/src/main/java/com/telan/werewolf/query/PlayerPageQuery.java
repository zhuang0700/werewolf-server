package com.telan.werewolf.query;

import java.util.Date;
import java.util.List;

/**
 * Created by weiwenliang on 2015/11/3.
 *
 */
public class PlayerPageQuery extends PageQuery {

    private static final long serialVersionUID = -3142728607169207697L;
    private List<Long> ids;
    
    private List<Integer> statusList;

    private Integer gameStatus;

    private Long userId;

    private Long gameId;

    private List<Long> gameIdList;

    private Integer role;

    private String nick;
    
    private Integer type;

    public Integer getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(Integer gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public List<Long> getGameIdList() {
        return gameIdList;
    }

    public void setGameIdList(List<Long> gameIdList) {
        this.gameIdList = gameIdList;
    }
}
