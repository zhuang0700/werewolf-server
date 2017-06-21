package com.telan.werewolf.game.enums;

/**
 */
public enum JudgeActionType {
    FINISH_STAGE("杀人",1),
    ANNOUNCE_DEAD("宣布死亡",2),
    ;
    private String desc;
    private int type;

    JudgeActionType(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static JudgeActionType getByType(int type) {
        for (JudgeActionType userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static JudgeActionType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (JudgeActionType userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}