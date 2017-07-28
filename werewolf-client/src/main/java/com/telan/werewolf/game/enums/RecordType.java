package com.telan.werewolf.game.enums;

/**
 */
public enum RecordType {
    NORMAL("普通记录",1),
    DEATH("死亡记录",2),
    VOTE("投票记录",3),
    ROLE_INFO("身份记录",4),
;
    private String desc;
    private int type;

    RecordType(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static RecordType getByType(int type) {
        for (RecordType userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static RecordType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (RecordType userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}