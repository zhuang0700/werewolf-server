package com.telan.werewolf.utils.conventor;

import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.param.CreateGameParam;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/5/31.
 */
public class GameConvertor{


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

    public static Map<Long, GameInfo> convertGameInfoMap(List<GameDO> gameDOList) {
        if(CollectionUtils.isEmpty(gameDOList)) {
            return new HashMap<>();
        }
        Map<Long,GameInfo> gameDOMap = new HashMap<>();
        for(GameDO gameDO : gameDOList) {
            GameInfo gameInfo = new GameInfo(gameDO);
            gameDOMap.put(gameDO.getId(), gameInfo);
        }
        return gameDOMap;
    }

    public static Long convertGameDO(GameDO gameDO) {
        return gameDO.getId();
    }

    public static DefaultConvertor defaultConvertor;

    public static DefaultConvertor getDefaultConvertor() {
        return defaultConvertor;
    }

    public class DefaultConvertor implements Converter<GameDO, Long> {
        @Override
        public Long convert(GameDO gameDO) {
            return convertGameDO(gameDO);
        }
    }
}
