package com.telan.werewolf.game.enums;

/**
 */
public enum GameMsgSubType {
    NIGHT_START("第{0,number,integer}天黑夜开始",1, 1001),
    NIGHT_END("第{0}天黑夜结束",1, 1002),
    DAY_START("第{0}天白天开始",1, 1003),
    DAY_END("第{0}天白天结束",1, 1004),

    STAGE_START("{0}开始",2, 2001),
    STAGE_END("{0}结束",2, 2002),
    JUDGE_END_STAGE("{0}行动结束（法官操作）",2, 2003),

    DEAD_RESULT("{0}玩家死亡",3, 3001),

    VOTE_DETAIL("投票结果:",4, 4001),
    VOTE_RESULT("{0}玩家得票最高",4, 4002),
    VOTE_SHERIFF_RESULT("{0}玩家当选",4, 4003),
    VOTE_AGAIN("请再次投票",4, 4004),

    KILL_ACTION("{0}号玩家（狼人）选择了{1}号作为目标",5,5001),
    CURE_ACTION("{0}号玩家（女巫）选择使用解药",5,5002),
    POSITION_ACTION("{0}号玩家（女巫）选择对{1}号使用毒药",5,5003),
    SHOOT_ACTION("{0}号玩家（猎人）选择开枪带走{1}号",5,5004),
    SEER_ACTION("{0}号玩家（预言家）查验了{1}号（{2}）的身份",5,5005),

    QUIT_GAME("{0}号玩家退出了游戏",6,6001),
    GAME_FINISH("游戏结束",6,6002),
    GAME_RESULT("游戏结束,{0}",6,6003),
    GAME_ROLE_RESULT("{0}号身份：{1}",6,6004),
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