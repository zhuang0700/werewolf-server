package com.telan.werewolf.game.enums;

/**
 */
public enum PlayerResult {
    NOT_FINISH("无结果",0),
    WIN("胜利",1),
    LOSE("失败",2);
    private String desc;
    private int type;

    PlayerResult(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static PlayerResult getByType(int type) {
        for (PlayerResult userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static PlayerResult getByName(String name) {
        if (name == null) {
            return null;
        }
        for (PlayerResult userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }

    public static PlayerResult getByTypeWithDefault(int status) {
        for (PlayerResult userType : values()) {
            if (userType.getType() == status) {
                return userType;
            }
        }
        return NOT_FINISH;
    }

    public static PlayerResult getByNameWithDefault(String name) {
        if (name == null) {
            return NOT_FINISH;
        }
        for (PlayerResult userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return NOT_FINISH;
    }
}