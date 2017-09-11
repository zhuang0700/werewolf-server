package com.telan.werewolf.game.param;

import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.BaseGameInfo;
import com.telan.werewolf.param.GameBaseRequest;

/**
 * Created by weiwenliang on 17/5/29.
 */
public class OperateGameParam extends GameBaseRequest{
    private UserDO user;

    private int mockPlayerNum;

    public UserDO getUser() {
        return user;
    }

    public void setUser(UserDO user) {
        this.user = user;
    }

    public int getMockPlayerNum() {
        return mockPlayerNum;
    }

    public void setMockPlayerNum(int mockPlayerNum) {
        this.mockPlayerNum = mockPlayerNum;
    }
}
