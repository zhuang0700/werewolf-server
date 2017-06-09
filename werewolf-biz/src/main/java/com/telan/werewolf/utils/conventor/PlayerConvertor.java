package com.telan.werewolf.utils.conventor;

import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.enums.PlayerStatus;
import com.telan.werewolf.game.factory.RoleFactory;

/**
 * Created by weiwenliang on 17/5/31.
 */
public class PlayerConvertor {

    public static Player convertPlayer(PlayerDO playerDO, UserDO userDO) {
        Player player = new Player();
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
}
