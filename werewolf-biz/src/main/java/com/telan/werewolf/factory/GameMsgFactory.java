package com.telan.werewolf.factory;

import com.telan.werewolf.game.domain.GameMsg;
import com.telan.werewolf.game.domain.Visiablity;
import com.telan.werewolf.game.domain.role.*;
import com.telan.werewolf.game.enums.GameMsgSubType;
import com.telan.werewolf.game.enums.RoleType;

import java.util.Date;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/31.
 */
public class GameMsgFactory {
    public static GameMsg createGameMsg(GameMsgSubType gameMsgSubType, Visiablity visiablity, List<Object> objects) {
        GameMsg gameMsg = new GameMsg();
        gameMsg.setSubType(gameMsgSubType.getSubType());
        gameMsg.setType(gameMsgSubType.getType());
        gameMsg.setActionTime(new Date());
        gameMsg.setVisiablity(visiablity);
        gameMsg.setObjects(objects);
        return gameMsg;
    }
}
