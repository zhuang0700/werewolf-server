package com.telan.werewolf.game.enums;

/**
 */
public enum VisibilityType {
    ALL("全部",1),
    BY_ROLE("指定角色可见",2),
    BY_PLAYER("指定玩家可见",3),
    JUDGE_ONLY("仅法官可见",4),
    ;
    private String desc;
    private int type;

    VisibilityType(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static VisibilityType getByType(int type) {
        for (VisibilityType userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static VisibilityType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (VisibilityType userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}