package com.telan.werewolf.game.enums;

/**
 */
public enum GameMsgSubType {
    NIGHT_START("第{1}天黑夜开始",1, 1001),
    NIGHT_END("第{1}天黑夜结束",1, 1002),
    DAY_START("第{1}天白天开始",1, 1003),
    DAY_END("第{1}天白天结束",1, 1004),
    STAGE_BEGIN("{1}回合开始",1, 1005),

    STAGE_START("{1}开始行动",2, 2001),
    STAGE_END("{1}行动结束",2, 2002),

    DEAD_RESULT("昨夜死亡的玩家是:{1}",3, 3001),

    VOTE_RESULT("投票结果:\r\n{1}\r\n{2}玩家被处决",4, 4001),

    KILL_ACTION("{1}号玩家（狼人）选择了{2}号作为目标",5,5001),
    CURE_ACTION("{1}号玩家（女巫）选择对{2}号使用解药",5,5002),
    POSITION_ACTION("{1}号玩家（女巫）选择对{2}号使用毒药",5,5003),

    QUIT_GAME("{1}号玩家退出了游戏",6,6001),
    ;
    private String desc;
    private int type;
    private int subType;

    GameMsgSubType(String desc, int type, int subType) {
        this.desc = desc;
        this.type = type;
        this.subType = subType;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public int getSubType() {
        return subType;
    }

    public static GameMsgSubType getBySubType(int subType) {
        for (GameMsgSubType userType : values()) {
            if (userType.getSubType() == subType) {
                return userType;
            }
        }
        return null;
    }

    public static GameMsgSubType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (GameMsgSubType userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}