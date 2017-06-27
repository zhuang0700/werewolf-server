package com.telan.werewolf.game.vo;

import java.io.Serializable;
import java.util.Date;

public class PlayerVO implements Serializable {
    private static final long serialVersionUID = 3308067332869726817L;
    private long id;

    private long userId;

    private long gameId;

    private int type;

    private int status;

    //是否在游戏中
    private int gameStatus;

    private String nick;

    private String avatar;

    private int role;

    private int playerNo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }
}