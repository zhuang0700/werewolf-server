package com.telan.werewolf.game.enums;

/**
 */
public enum GameMsgType {
    ROUND("白天黑夜消息",1),
    STAGE("回合进度消息",2),
    KILL_RESULT("杀人结果消息",3),
    VOTE_RESULT("投票结果消息",4),
    ACTION("行动消息",5),
    GAME("游戏房间消息",6),
    ;
    private String desc;
    private int type;

    GameMsgType(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static GameMsgType getByType(int type) {
        for (GameMsgType userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static GameMsgType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (GameMsgType userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}