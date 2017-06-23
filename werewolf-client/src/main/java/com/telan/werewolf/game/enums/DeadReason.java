package com.telan.werewolf.game.enums;

/**
 */
public enum DeadReason {
    KILL("狼杀",1),
    POISON("毒死",2),
    LOVER("情侣",3),
    SHOOT("狙死",4),
    VOTE("投票表决",5),
    QUIT("退出游戏",100),
    ;
    private String desc;
    private int type;

    DeadReason(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static DeadReason getByType(int type) {
        for (DeadReason userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static DeadReason getByName(String name) {
        if (name == null) {
            return null;
        }
        for (DeadReason userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}