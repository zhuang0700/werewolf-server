package com.telan.werewolf.game.enums;

/**
 */
public enum StageStatus {
    NOT_BEGIN("未开始",0),
    BEGIN("开始",1),
    WAITING_ACTION("等待玩家行动",2),
    ANALYSE("结算中",3),
    FINISH("结束",4),
;
    private String desc;
    private int type;

    StageStatus(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static StageStatus getByType(int type) {
        for (StageStatus userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static StageStatus getByName(String name) {
        if (name == null) {
            return null;
        }
        for (StageStatus userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}