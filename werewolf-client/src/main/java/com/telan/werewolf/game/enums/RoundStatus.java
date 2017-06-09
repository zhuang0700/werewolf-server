package com.telan.werewolf.game.enums;

/**
 */
public enum RoundStatus {
    NOT_STAT("未开始", 0),
    DARK("黑夜",1),
    DAY("白天",2),
    FINISH("已结束",3),
;
    private String desc;
    private int type;

    RoundStatus(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static RoundStatus getByType(int type) {
        for (RoundStatus userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static RoundStatus getByName(String name) {
        if (name == null) {
            return null;
        }
        for (RoundStatus userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}