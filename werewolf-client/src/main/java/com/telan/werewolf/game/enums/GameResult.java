package com.telan.werewolf.game.enums;

/**
 */
public enum GameResult {
    CREATE("初始化",0),
    LIVE("存活",1),
    DEAD("死亡",2),
    GAME_OVER("游戏结束",1000);
    private String desc;
    private int type;

    GameResult(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static GameResult getByType(int type) {
        for (GameResult userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static GameResult getByName(String name) {
        if (name == null) {
            return null;
        }
        for (GameResult userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }

    public static GameResult getByTypeWithDefault(int status) {
        for (GameResult userType : values()) {
            if (userType.getType() == status) {
                return userType;
            }
        }
        return CREATE;
    }

    public static GameResult getByNameWithDefault(String name) {
        if (name == null) {
            return CREATE;
        }
        for (GameResult userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return CREATE;
    }
}