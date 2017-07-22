package com.telan.werewolf.game.enums;

/**
 */
public enum ActionStatus {
    NOT_READY("未准备好",1),
    WAITING_ACTION("行动中",2),
    ALREADY_ACTION("已经行动",3),
    ADJUEST_ACTION("行动调整中",4),
    ;
    private String desc;
    private int type;

    ActionStatus(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static ActionStatus getByType(int type) {
        for (ActionStatus userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static ActionStatus getByName(String name) {
        if (name == null) {
            return null;
        }
        for (ActionStatus userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}