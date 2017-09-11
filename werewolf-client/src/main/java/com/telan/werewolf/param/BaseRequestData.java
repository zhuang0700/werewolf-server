package com.telan.werewolf.param;

import com.telan.werewolf.domain.UserDO;

/**
 * Created by weiwenliang on 17/8/22.
 */
public class BaseRequestData {
    private String sessionId;

    private String msgType;

    private Object msgData;

    private UserDO userDO;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Object getMsgData() {
        return msgData;
    }

    public void setMsgData(Object msgData) {
        this.msgData = msgData;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }
}
