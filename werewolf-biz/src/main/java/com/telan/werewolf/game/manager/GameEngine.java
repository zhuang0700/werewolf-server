package com.telan.werewolf.game.manager;

import com.telan.werewolf.enums.BaseStatus;
import com.telan.werewolf.factory.GameMsgFactory;
import com.telan.werewolf.factory.RoundFactory;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.enums.*;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by weiwenliang on 17/6/6.
 */
public class GameEngine {
    public static void startGame(GameInfo currentGame){
        currentGame.setGameStatus(GameStatus.PROCESS.getType());
    }

    public static int checkGameStatus(GameInfo gameInfo) {
        Player creator = gameInfo.getCreator();
        if(creator == null || creator.getGameStatus() == BaseStatus.DELETED.getType()) {
            return GameStatus.FINISH.getType();
        } else if(gameInfo.getGameStatus() == GameStatus.PROCESS.getType()){
            try {
                check(gameInfo.getCurrentRound()!=null);
                check(gameInfo.getCreator()!=null);
                check(!CollectionUtils.isEmpty(gameInfo.getPlayerMap()));
                check(!CollectionUtils.isEmpty(gameInfo.getRoleList()));
            } catch (Exception e) {
                PlayerEngine.setGameEnd(gameInfo.getPlayerMap());
                gameInfo.setGameStatus(GameStatus.FINISH.getType());
                return -1;
            }
            return gameInfo.getGameStatus();
        }
        return gameInfo.getGameStatus();
    }

    public static boolean tryEndGame(GameInfo currentGame) {
        int gameResult =checkGameFinish(currentGame);
        if(gameResult == GameResult.NOT_FINISH.getType()) {
            return false;
        }
        //finish
        currentGame.setGameStatus(GameStatus.FINISH.getType());
        currentGame.getGameDO().setResult(gameResult);
        RoundEngine.finishRound(currentGame, true);
        GameMsg gameEndMsg = GameMsgFactory.createGameMsg(GameMsgSubType.GAME_RESULT, Visibility.ALL, new Object[]{GameResult.getByType(gameResult).getDesc()});
        RecordEngine.sendNormalMsg(currentGame, gameEndMsg);
//        PlayerEngine.setGameEnd(currentGame.getPlayerMap());
        return true;
    }

    private static int checkGameFinish(GameInfo gameInfo) {
        List<Player> wolves = PlayerEngine.getPlayersByRoleAndStatus(gameInfo, PlayerStatus.LIVE.getType(), RoleType.WOLF.getType());
        List<Player> villagers = PlayerEngine.getPlayersByRoleAndStatus(gameInfo, PlayerStatus.LIVE.getType(), RoleType.VILLAGER.getType());
        List<Player> allAlive = PlayerEngine.getPlayersByRoleAndStatus(gameInfo, PlayerStatus.LIVE.getType(), 0);

        if(CollectionUtils.isEmpty(allAlive)) {
            return GameResult.NOBODY_WIN.getType();
        }
        if(CollectionUtils.isEmpty(wolves)) {
            return GameResult.GOOD_WIN.getType();
        }
        int villagerNum = villagers.size();
        int wolfNum = wolves.size();
        int superNum = allAlive.size() - wolfNum - villagerNum;
        if(GameConfigConst.VICTORY_RULE_SIDE.equals(gameInfo.getGameConfig().getVictoryRule())) {
            if(villagerNum <= 0 || superNum <= 0) {
                return GameResult.WOLF_WIN.getType();
            }
        }
        if(GameConfigConst.VICTORY_RULE_CAMP.equals(gameInfo.getGameConfig().getVictoryRule())) {
            if(villagerNum <= 0 && superNum <= 0) {
                return GameResult.WOLF_WIN.getType();
            }
        }
        return GameResult.NOT_FINISH.getType();
    }

    public static void check(boolean exp) throws Exception {
        if(!exp) {
            throw new Exception();
        }
    }

    public static void changeSheriff(GameInfo gameInfo, long newSheriffId) {
        gameInfo.setSheriffPlayerId(newSheriffId);
        gameInfo.getCurrentRound().setSheriffPlayerId(newSheriffId);
    }
}
