package com.telan.werewolf.utils.conventor;

import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.enums.*;
import com.telan.werewolf.utils.PlayerUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 2017/6/16.
 */
public class ResultConvertor {

    public static GameData convertToData(GameInfo gameInfo, UserDO userDO) {
        GameData gameData = new GameData();
        if(gameInfo == null || userDO == null) {
            return null;
        }
        gameData.gameDO = gameInfo.getGameDO();
        gameData.playerDOList = new ArrayList<>();
        for(Player player : gameInfo.getPlayerMap().values()) {
            PlayerDO playerDO = player.getPlayerDO();
            playerDO.setRole(0);
            gameData.playerDOList.add(playerDO);
            if(player.getUserId() == userDO.getId()) {
                gameData.recordList = player.getRecordList();
            }
        }
        gameData.actionList = convertActionList(gameInfo);
        return gameData;
    }

    public static List<Action> convertActionList(GameInfo gameInfo) {
        Round currentRound = gameInfo.getCurrentRound();
        List<Action> actionList = new ArrayList<>();
        if(currentRound != null) {
            if(currentRound.getRoundStatus() == RoundStatus.DAY.getType() && !CollectionUtils.isEmpty(currentRound.getDayStageList())) {
                for(Stage stage : currentRound.getDayStageList()) {
                    actionList.addAll(convertStageActionList(stage, gameInfo));
                }
            } else if(currentRound.getRoundStatus() == RoundStatus.DARK.getType() && !CollectionUtils.isEmpty(currentRound.getNightStageList())) {
                for(Stage stage : currentRound.getNightStageList()) {
                    actionList.addAll(convertStageActionList(stage, gameInfo));
                }
            }
        }
        return actionList;
    }

    private static List<Action> convertStageActionList(Stage myStage, GameInfo gameInfo) {
        if(myStage.status != StageStatus.WAITING_ACTION.getType()) {
            return new ArrayList<>();
        }
        List<Action> myActionList =convertAction(myStage, gameInfo);
        if(!CollectionUtils.isEmpty(myStage.next)) {
            for(Stage stage : myStage.next) {
                myActionList.addAll(convertStageActionList(stage, gameInfo));
            }
        }
        return myActionList;
    }

    public static List<Action> convertAction(final Stage stage, GameInfo gameInfo) {
        List<Action> actionList = new ArrayList<>();
        Action action = new Action();
        switch (stage.stageType) {
            case WOLF:
                action.playerActionType = ActionType.KILL.name();
                action.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), RoleType.WOLF.getType(), PlayerStatus.LIVE.getType());
                action.targetPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, PlayerStatus.LIVE.getType());
                actionList.add(action);
                return actionList;
            case WITCH:
                action.playerActionType = ActionType.SAVE.name();
                action.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), RoleType.WITCH.getType(), PlayerStatus.LIVE.getType());
                action.targetPlayerIds = new ArrayList<Long>(){{add(stage.markedPlayerId);}};
                actionList.add(action);
                Action positionAction = new Action();
                positionAction.playerActionType = ActionType.POISON.name();
                positionAction.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), RoleType.WITCH.getType(), PlayerStatus.LIVE.getType());
                positionAction.targetPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, PlayerStatus.LIVE.getType());
                actionList.add(positionAction);
                return actionList;
            case SEER:
                action.playerActionType = ActionType.SEE.name();
                action.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), RoleType.SEER.getType(), PlayerStatus.LIVE.getType());
                action.targetPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, PlayerStatus.LIVE.getType());
                actionList.add(action);
                return actionList;
            case SHERIFF:
                action.playerActionType = ActionType.RUN_SHERIFF.name();
                action.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, -1);
                action.targetPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, -1);
                actionList.add(action);
                return actionList;
            case VOTE:
                action.playerActionType = ActionType.VOTE.name();
                action.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, PlayerStatus.LIVE.getType());
                action.targetPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, PlayerStatus.LIVE.getType());
                actionList.add(action);
                return actionList;
        }
        return actionList;
    }
}
