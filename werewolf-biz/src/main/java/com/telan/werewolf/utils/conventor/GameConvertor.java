package com.telan.werewolf.utils.conventor;

import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.domain.role.BaseRole;
import com.telan.werewolf.game.enums.GameStatus;
import com.telan.werewolf.game.param.CreateGameParam;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.CollectionUtils;

import java.util.*;

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

    public static Map<Long, GameInfo> convertGameInfoMap(List<GameDO> gameDOList, Map<Long, Player> playerMap) {
        if(CollectionUtils.isEmpty(gameDOList)) {
            return new HashMap<>();
        }
        Map<Long,GameInfo> gameDOMap = new HashMap<>();
        for(GameDO gameDO : gameDOList) {
            GameInfo gameInfo = new GameInfo(gameDO);
            gameInfo.init();
            if(gameInfo.getGameStatus() != GameStatus.CREATE.getType()) {
                Set<BaseRole> roleSet = new HashSet<BaseRole>();
                for(Player player : playerMap.values()) {
                    if(player.getGameId() == gameInfo.getGameId()) {
                        gameInfo.addPlayer(player);
                        roleSet.add(player.getRole());
                    }
                }
                List<BaseRole> roleList = new ArrayList<>();
                roleList.addAll(roleSet);
                gameInfo.setRoleList(roleList);
            }
            gameDOMap.put(gameDO.getId(), gameInfo);
        }
        return gameDOMap;
    }

    public static Long convertGameDO(GameDO gameDO) {
        return gameDO.getId();
    }

    public static DefaultConvertor defaultConvertor;

    public static DefaultConvertor getDefaultConvertor() {
        if(defaultConvertor == null) {
            defaultConvertor = new DefaultConvertor();
        }
        return defaultConvertor;
    }

    public static class DefaultConvertor implements Converter<GameDO, Long> {
        @Override
        public Long convert(GameDO gameDO) {
            return convertGameDO(gameDO);
        }
    }
}
