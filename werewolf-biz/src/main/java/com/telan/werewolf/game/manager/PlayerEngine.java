package com.telan.werewolf.game.manager;

import com.telan.werewolf.enums.BaseStatus;
import com.telan.werewolf.factory.StageFactory;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.domain.role.HunterRole;
import com.telan.werewolf.game.enums.GameStatus;
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

    public static void setGameEnd(Map<Long, Player> playerMap) {
        for(Player player : playerMap.values()) {
            player.setGameStatus(BaseStatus.DELETED.getType());
        }
    }

    public static void initPlayerNumber(Map<Long, Player> playerMap) {
        int playerNo = 1;
        for(Player player : playerMap.values()) {
            player.getPlayerDO().setPlayerNo(playerNo);
            playerNo++;
        }
    }

    public static void quitGameAfterStart(Player player) {
        player.setGameStatus(BaseStatus.DELETED.getType());
    }

    public static boolean isJudge(GameInfo gameInfo, Player player){
        if(player.getRoleType() == RoleType.JUDGE.getType()) {
            return true;
        }
        //没有法官时，创建者充当法官角色
        if(!gameInfo.getGameConfig().isHasJudgeRole() && player.getUserId() == gameInfo.getCreatorId()) {
            return true;
        }
        return false;
    }

    public static List<Player> getPlayersByRoleAndStatus(GameInfo gameInfo, int playerStatus, int roleType) {
        List<Player> players = new ArrayList<>();
        for(Player player : gameInfo.getPlayerMap().values()) {
            if((playerStatus > 0 && player.getStatus() != playerStatus)){
                continue;
            }
            if(roleType > 0 && player.getRoleType() != roleType) {
                continue;
            }
            if(player.getGameStatus() != BaseStatus.AVAILABLE.getType()) {
                continue;
            }
            players.add(player);

        }
        return players;
    }

    //return true means continue, false means stop
    public static boolean setPlayerDead(GameInfo gameInfo, Map<Long, Integer> deathInfo, Stage stage) {
        List<Player> deadPlayers = new ArrayList<>();
        boolean noHunter = true;
        for(long id : deathInfo.keySet()) {
            Player player = gameInfo.getPlayer(id);
            if(player != null) {
                if(player.getStatus() == PlayerStatus.LIVE.getType()) {
                    player.setStatus(PlayerStatus.DEAD.getType());
                }
                player.setDeadReason(deathInfo.get(id));
                RecordEngine.sendDeathMsg(gameInfo, player);
            }
            deadPlayers.add(player);
        }
        for(Player player : deadPlayers) {
            if(player.getRoleType() == RoleType.HUNTER.getType()) {
                addHunterStage(gameInfo, stage);
                noHunter = false;
            }
        }
        GameEngine.tryEndGame(gameInfo);
        return noHunter;
    }

    private static void addHunterStage(GameInfo gameInfo, Stage stage) {
        Round currentRound = gameInfo.getCurrentRound();
        Stage hunterStage = StageFactory.createRoleStage(RoleType.HUNTER.getType(), gameInfo);
        hunterStage.linkNext(stage);
//        currentRound.setHunterStage(hunterStage);
        currentRound.getAllStageList().add(hunterStage);
        hunterStage.update(stage);
    }

}
