package com.telan.werewolf.game.enums;

/**
 */
public enum StageType {
    WOLF("狼人回合",1),
    WITCH("女巫回合",2),
    VOTE("投票",4),
    SHERIFF("选警长",5),
    HUNTER("猎人回合",6),
    SEER("预言家回合",7),
    NIGHT_END("黑夜结束",8),
    DAY_END("白天结束",9),
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

    public static StageType getByActionType(ActionType actionType) {
        if(actionType == null) {
            return null;
        }
        switch (actionType) {
            case KILL:
                return WOLF;
            case SAVE:
            case POISON:
                return WITCH;
            case SEE:
                return SEER;
            case SHOOT:
                return HUNTER;
            case VOTE:
                return VOTE;
            case RUN_SHERIFF:
                return SHERIFF;
        }
        return null;
    }
}