package com.telan.werewolf.game.enums;

/**
 */
public enum GameResult {
    NOT_FINISH("无结果",0),
    GOOD_WIN("好人胜利",1),
    WOLF_WIN("狼人胜利",2),
    NOBODY_WIN("无人胜利",1000);
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
        return NOT_FINISH;
    }

    public static GameResult getByNameWithDefault(String name) {
        if (name == null) {
            return NOT_FINISH;
        }
        for (GameResult userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return NOT_FINISH;
    }
}