package com.telan.werewolf.utils.conventor;

import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.param.CreateGameParam;

import java.util.Date;

/**
 * Created by weiwenliang on 17/5/31.
 */
public class GameConvertor {

    public static GameDO convertGameDOForCreate(CreateGameParam param) {
        GameDO gameDO = new GameDO();
        UserDO userDO = param.getCreator();
        gameDO.setCreatorId(userDO.getId());
        gameDO.setPlayerNum(param.getPlayerNum());
        gameDO.setGmtCreated(new Date());
        gameDO.setGmtModified(new Date());
        gameDO.setHasJudge(0);
        gameDO.setType(1);
        return gameDO;
    }
}
