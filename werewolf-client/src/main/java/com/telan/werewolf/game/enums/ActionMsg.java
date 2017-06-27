package com.telan.werewolf.game.enums;

/**
 */
public enum ActionMsg {
    YES("是",1),
    NO("否",2),
    ;
    private String desc;
    private int type;

    ActionMsg(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static ActionMsg getByType(int type) {
        for (ActionMsg userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static ActionMsg getByName(String name) {
        if (name == null) {
            return null;
        }
        for (ActionMsg userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}