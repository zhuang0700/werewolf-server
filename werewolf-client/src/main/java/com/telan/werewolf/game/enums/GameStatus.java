package com.telan.werewolf.game.enums;

/**
 */
public enum GameStatus {
    CREATE("未开始",0),
    INIT("初始化",1),
    PROCESS("进行中",2),
    FINISH("已结束",3),;
    private String desc;
    private int type;

    GameStatus(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static GameStatus getByType(int type) {
        for (GameStatus userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static GameStatus getByName(String name) {
        if (name == null) {
            return null;
        }
        for (GameStatus userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }

    public static GameStatus getByTypeWithDefault(int status) {
        for (GameStatus userType : values()) {
            if (userType.getType() == status) {
                return userType;
            }
        }
        return CREATE;
    }

    public static GameStatus getByNameWithDefault(String name) {
        if (name == null) {
            return CREATE;
        }
        for (GameStatus userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return CREATE;
    }
}