package com.telan.werewolf.game.enums;

/**
 */
public enum StageType {
    WOLF("狼人",1),
    WITCH("女巫",2),
    VOTE("投票",4),
    SHERIFF("投警长",5),
    HUNTER("狙人",6),
    SEER("预言家",7),
    ;
    private String desc;
    private int type;

    StageType(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static StageType getByType(int type) {
        for (StageType userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static StageType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (StageType userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}