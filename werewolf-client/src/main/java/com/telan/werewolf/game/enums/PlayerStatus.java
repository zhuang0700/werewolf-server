package com.telan.werewolf.game.enums;

import java.util.ArrayList;
import java.util.List;

/**
 */
public enum PlayerStatus {
    CREATE("初始化",0),
    LIVE("存活",1),
    DEAD("死亡",2),
    QUIT("退出游戏",3),
    GAME_OVER("游戏结束",1000);
    private String desc;
    private int type;

    PlayerStatus(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static PlayerStatus getByType(int type) {
        for (PlayerStatus userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static PlayerStatus getByName(String name) {
        if (name == null) {
            return null;
        }
        for (PlayerStatus userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }

    public static PlayerStatus getByTypeWithDefault(int status) {
        for (PlayerStatus userType : values()) {
            if (userType.getType() == status) {
                return userType;
            }
        }
        return CREATE;
    }

    public static PlayerStatus getByNameWithDefault(String name) {
        if (name == null) {
            return CREATE;
        }
        for (PlayerStatus userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return CREATE;
    }

    public static List<Integer> getInGameStatus() {
        return new ArrayList<Integer>(){
            private static final long serialVersionUID = -6603082668907868062L;
            {
                add(CREATE.getType());
                add(LIVE.getType());
                add(DEAD.getType());
            }
        };
    }
}