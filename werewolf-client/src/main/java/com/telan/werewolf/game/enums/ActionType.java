package com.telan.werewolf.game.enums;

/**
 */
public enum ActionType {
    KILL("杀人",1),
    SAVE("救人",2),
    POISON("毒人",3),
    VOTE("投票",4),
    RUN_SHERIFF("投警长",5),
    SHOOT("狙人",6),
    SEE("验人",7),
    ;
    private String desc;
    private int type;

    ActionType(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static ActionType getByType(int type) {
        for (ActionType userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static ActionType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (ActionType userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}