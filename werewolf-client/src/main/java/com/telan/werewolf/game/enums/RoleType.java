package com.telan.werewolf.game.enums;

/**
 */
public enum RoleType {
    NONE("未分配",0,"无"),
    JUDGE("法官",1000,"无"),
    WOLF("狼人",1,"无"),
    WITCH("女巫",2,"无"),
    SEER("预言家",3,"无"),
    VILLAGER("村民",4,"无"),
    HUNTER("猎人",5,"无"),
;
    private String name;
    private int type;
    private String description;

    RoleType(String name, int type,String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
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