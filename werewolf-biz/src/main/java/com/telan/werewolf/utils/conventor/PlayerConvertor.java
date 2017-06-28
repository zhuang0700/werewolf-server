package com.telan.werewolf.utils.conventor;

import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.enums.PlayerStatus;
import com.telan.werewolf.factory.RoleFactory;
import com.telan.werewolf.game.vo.PlayerVO;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by weiwenliang on 17/5/31.
 */
public class PlayerConvertor {

    public static Player convertPlayer(PlayerDO playerDO, UserDO userDO) {
        Player player = new Player();
        if(userDO == null || playerDO == null) {
            return null;
        }
        player.setPlayerDO(playerDO);
        player.setUserDO(userDO);
        player.setRole(RoleFactory.createRoleById(playerDO.getRole()));
        player.setStatus(playerDO.getStatus());
        return player;
    }

    public static PlayerDO convertFromUser(UserDO userDO, long gameId) {
        PlayerDO playerDO = new PlayerDO();
        playerDO.setAvatar(userDO.getAvatar());
        playerDO.setNick(userDO.getNick());
        playerDO.setStatus(PlayerStatus.CREATE.getType());
        playerDO.setGameId(gameId);
        playerDO.setUserId(userDO.getId());
        return playerDO;
    }

    public static List<Long> convertUserIdList(List<PlayerDO> playerDOList) {
        if(CollectionUtils.isEmpty(playerDOList)) {
            return null;
        }
        List<Long> userIdList = new ArrayList<>();
        for(PlayerDO playerDO : playerDOList) {
            Long userId = playerDO.getUserId();
            userIdList.add(userId);
        }
        return userIdList;
    }

    public static List<Player> convertPlayerList(List<PlayerDO> playerDOList, Map<Long, UserDO> userDOMap) {
        if(CollectionUtils.isEmpty(playerDOList) || CollectionUtils.isEmpty(userDOMap)) {
            return new ArrayList<>();
        }
        List<Player> players = new ArrayList<>();
        for(PlayerDO playerDO : playerDOList) {
            players.add(convertPlayer(playerDO, userDOMap.get(playerDO.getUserId())));
        }
        return players;
    }


    public static Map<Long, Player> convertPlayerMap(List<PlayerDO> playerDOList, Map<Long, UserDO> userDOMap) {
        if(CollectionUtils.isEmpty(playerDOList) || CollectionUtils.isEmpty(userDOMap)) {
            return new HashMap();
        }
        Map<Long, Player> playerMap = new HashMap<>();
        for(PlayerDO playerDO : playerDOList) {
            playerMap.put(playerDO.getId(), convertPlayer(playerDO, userDOMap.get(playerDO.getUserId())));
        }
        return playerMap;
    }

    public static PlayerVO convertPlayerVO(Player player, boolean hideRole) {
        PlayerVO playerVO = new PlayerVO();
        PlayerDO playerDO = player.getPlayerDO();
        playerVO.setNick(playerDO.getNick());
        playerVO.setAvatar(playerDO.getAvatar());
        playerVO.setGameId(player.getGameId());
        playerVO.setId(player.getId());
        playerVO.setStatus(player.getStatus());
        playerVO.setGameStatus(player.getGameStatus());
        playerVO.setType(playerDO.getType());
        playerVO.setUserId(playerDO.getUserId());
        playerVO.setPlayerNo(playerDO.getPlayerNo());
        if (!hideRole) {
            playerVO.setRole(playerDO.getRole());
        }
        return playerVO;
    }

    public static List<PlayerVO> convertPlayerVOList(List<Player> playerList) {
        if(CollectionUtils.isEmpty(playerList)) {
            return new ArrayList<>();
        }
        List<PlayerVO> playerVOList = new ArrayList<>();
        for(Player player : playerList) {
            playerVOList.add(convertPlayerVO(player, true));
        }
        return playerVOList;
    }
}
