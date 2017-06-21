package com.telan.werewolf.game.manager;

import com.telan.werewolf.enums.BaseStatus;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.enums.PlayerStatus;
import com.telan.werewolf.game.enums.RoleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 2017/6/14.
 */
public class PlayerEngine {

    public static void setGameStart(Map<Long, Player> playerMap) {
        for(Player player : playerMap.values()) {
            player.setStatus(PlayerStatus.LIVE.getType());
        }
    }

    public static void quitGameAfterStart(Player player) {
        player.setGameStatus(BaseStatus.DELETED.getType());
    }

    public static boolean isJudge(GameInfo gameInfo, Player player){
        if(player.getRole().getRole() == RoleType.JUDGE.getType()) {
            return true;
        }
        //没有法官时，创建者充当法官角色
        if(!gameInfo.getGameConfig().isHasJudgeRole() && player.getUserId() == gameInfo.getCreatorId()) {
            return true;
        }
        return false;
    }

    public static void setPlayerDead(GameInfo gameInfo, List<Long> playerIds) {
        List<Player> deadPlayers = new ArrayList<>();
        for(long id : playerIds) {
            Player player = gameInfo.getPlayer(id);
            if(player != null) {
                if(player.getStatus() == PlayerStatus.LIVE.getType()) {
                    player.setStatus(PlayerStatus.DEAD.getType());
                }
            }
            deadPlayers.add(player);
        }
        RecordEngine.sendDeathMsg(gameInfo, deadPlayers);
    }
}
