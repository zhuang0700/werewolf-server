package com.telan.werewolf.game.enums;

/**
 */
public enum RoleType {
    NONE("未分配",0),
    WOLF("狼人",1),
    WITCH("女巫",2),
    SEER("预言家",3),
    VILLAGER("村民",4),
    HUNTER("猎人",5),
;
    private String desc;
    private int type;

    RoleType(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static RoleType getByType(int type) {
        for (RoleType userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static RoleType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (RoleType userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}