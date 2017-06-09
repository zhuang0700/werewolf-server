package com.telan.werewolf.enums;

/**
 */
public enum BaseStatus {
    AVAILABLE("有效",1),
    DELETED("已删除",2);
    private String desc;
    private int type;

    BaseStatus(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static BaseStatus getByType(int type) {
        for (BaseStatus userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static BaseStatus getByName(String name) {
        if (name == null) {
            return null;
        }
        for (BaseStatus userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }

    public static BaseStatus getByTypeWithDefault(int status) {
        for (BaseStatus userType : values()) {
            if (userType.getType() == status) {
                return userType;
            }
        }
        return AVAILABLE;
    }

    public static BaseStatus getByNameWithDefault(String name) {
        if (name == null) {
            return AVAILABLE;
        }
        for (BaseStatus userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return AVAILABLE;
    }
}