package com.telan.werewolf.utils.conventor;

import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.domain.record.BaseRecord;
import com.telan.werewolf.game.domain.role.WitchRole;
import com.telan.werewolf.game.enums.*;
import com.telan.werewolf.game.manager.GameEngine;
import com.telan.werewolf.game.vo.GameState;
import com.telan.werewolf.game.vo.PlayerVO;
import com.telan.werewolf.utils.ActionUtil;
import com.telan.werewolf.utils.PlayerUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 2017/6/16.
 */
public class ResultConvertor {

    public static GameData convertToData(GameInfo gameInfo, UserDO userDO, boolean judgeMode) {
        GameData gameData = new GameData();
        if(gameInfo == null || userDO == null) {
            return null;
        }
        if(GameEngine.checkGameStatus(gameInfo) == -1) {
            return null;
        }
        gameData.gameDO = gameInfo.getGameDO();
        gameData.playerVOList = new ArrayList<>();
        Player myPlayer = null;
        for(Player player : gameInfo.getPlayerMap().values()) {
            if(userDO.getId() == player.getUserId()){
                myPlayer = player;
                break;
            }
        }
        gameData.gameState = new GameState();
        if(gameInfo.getGameStatus() == GameStatus.PROCESS.getType()) {
            gameData.gameState.setRoundStatus(gameInfo.getCurrentRound().getRoundStatus());
            gameData.gameState.setRoundNo(gameInfo.getCurrentRound().getRoundNo());
            List<Integer> stageList = new ArrayList<>();
            for(Stage stage : gameInfo.getCurrentRound().getAllStageList()) {
                if(stage.status == StageStatus.WAITING_ACTION.getType()) {
                    stageList.add(stage.stageType.getType());
                }
            }
            gameData.gameState.setStageTypeList(stageList);
        }
        boolean gameFinish = false;
        if(gameInfo.getGameStatus() == GameStatus.FINISH.getType()) {
            gameFinish = true;
            if(gameInfo.getGameDO().getResult() == GameResult.GOOD_WIN.getType()) {
                if(myPlayer.getRoleType() == RoleType.WOLF.getType()){
                    gameData.gameState.setResult(PlayerResult.LOSE.getType());
                } else {
                    gameData.gameState.setResult(PlayerResult.WIN.getType());
                }
            } else if(gameInfo.getGameDO().getResult() == GameResult.WOLF_WIN.getType()) {
                if(myPlayer.getRoleType() == RoleType.WOLF.getType()){
                    gameData.gameState.setResult(PlayerResult.WIN.getType());
                } else {
                    gameData.gameState.setResult(PlayerResult.LOSE.getType());
                }
            }
        }
        if(myPlayer != null) {
            boolean shareRoleInfo = gameInfo.getGameConfig().getShareInfoRoles().contains(myPlayer.getRoleType());
            for(Player player : gameInfo.getPlayerMap().values()) {
                PlayerDO playerDO = player.getPlayerDO();
                boolean hideRole = false;
                if(!judgeMode && userDO.getId() != playerDO.getUserId()) {
                    if(!gameFinish && (!shareRoleInfo || player.getRoleType() != myPlayer.getRoleType())) {
                        //角色信息不共享，或者该玩家和自己角色不同，均需隐藏角色信息
                        hideRole = true;
                    }
                } else {
                    gameData.recordList = player.getRecordList();
                }
                PlayerVO playerVO = PlayerConvertor.convertPlayerVO(player, hideRole);
                gameData.playerVOList.add(playerVO);
                if(player.getId() == myPlayer.getId()) {
                    gameData.myInfo = playerVO;
                }
            }
        }
        gameData.actionList = convertActionList(gameInfo, myPlayer);

        return gameData;
    }

    //只填装该用户可用action
    public static List<Action> convertActionList(GameInfo gameInfo, Player player) {
        Round currentRound = gameInfo.getCurrentRound();
        if(player.getStatus() != PlayerStatus.LIVE.getType()) {
            return new ArrayList<>();
        }
        List<Action> actionList = new ArrayList<>();
        if(currentRound != null) {
            if((currentRound.getRoundStatus() == RoundStatus.DAY.getType() || currentRound.getRoundStatus() == RoundStatus.DARK.getType()) && !CollectionUtils.isEmpty(currentRound.getAllStageList())) {
                for(Stage stage : currentRound.getAllStageList()) {
                    actionList.addAll(convertStageActionList(stage, gameInfo, player));
                }
            }
        }
        return actionList;
    }

    private static List<BaseRecord> revertRecords(List<BaseRecord> records) {
        List<BaseRecord> newRecords = new ArrayList<>();
        if(CollectionUtils.isEmpty(records)) {
            return newRecords;
        }
        for(int i=records.size()-1;i>=0;i--) {
            newRecords.add(records.get(i));
        }
        return newRecords;
    }
    private static List<Action> convertStageActionList(final Stage stage, GameInfo gameInfo, Player player) {
        if(stage.status != StageStatus.WAITING_ACTION.getType()) {
            return new ArrayList<>();
        }
        if(stage.roleList != null && !stage.roleList.contains(player.getRoleType())) {
            return new ArrayList<>();
        }
        List<Action> actionList = new ArrayList<>();
        Action action = new Action();
        switch (stage.stageType) {
            case WOLF:
                action.playerActionType = ActionType.KILL.getType();
                action.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), RoleType.WOLF.getType(), PlayerStatus.LIVE.getType());
                action.targetPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, PlayerStatus.LIVE.getType());
                if(stage.getAction(player.getId()) != null) {
                    action.actionStatus = ActionStatus.ADJUEST_ACTION.getType();
                } else {
                    action.actionStatus = ActionStatus.WAITING_ACTION.getType();
                }
                actionList.add(action);
                return actionList;
            case WITCH:
                action.playerActionType = ActionType.SAVE.getType();
                action.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), RoleType.WITCH.getType(), PlayerStatus.LIVE.getType());
                action.targetPlayerIds = new ArrayList<Long>(){{add(stage.markedPlayerId);}};
                WitchRole role = (WitchRole)player.getRole();
                if(stage.getAction(player.getId(), ActionType.SAVE.getType()) != null || role.getMedicineLeft() < 1) {
                    action.actionStatus = ActionStatus.ALREADY_ACTION.getType();
                } else {
                    action.actionStatus = ActionStatus.WAITING_ACTION.getType();
                }
                actionList.add(action);
                Action positionAction = new Action();
                positionAction.playerActionType = ActionType.POISON.getType();
                positionAction.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), RoleType.WITCH.getType(), PlayerStatus.LIVE.getType());
                positionAction.targetPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, PlayerStatus.LIVE.getType());
                if(stage.getAction(player.getId(), ActionType.POISON.getType()) != null || role.getPoision() < 1) {
                    positionAction.actionStatus = ActionStatus.ALREADY_ACTION.getType();
                } else {
                    positionAction.actionStatus = ActionStatus.WAITING_ACTION.getType();
                }
                actionList.add(positionAction);
                return actionList;
            case SEER:
                action.playerActionType = ActionType.SEE.getType();
                action.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), RoleType.SEER.getType(), PlayerStatus.LIVE.getType());
                action.targetPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, PlayerStatus.LIVE.getType());
                if(stage.getAction(player.getId()) != null) {
                    action.actionStatus = ActionStatus.ALREADY_ACTION.getType();
                } else {
                    action.actionStatus = ActionStatus.WAITING_ACTION.getType();
                }
                actionList.add(action);
                return actionList;
            case SHERIFF:
                action.playerActionType = ActionType.RUN_SHERIFF.getType();
                action.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, -1);
                action.targetPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, -1);
                if(stage.getAction(player.getId()) != null) {
                    action.actionStatus = ActionStatus.ALREADY_ACTION.getType();
                } else {
                    action.actionStatus = ActionStatus.WAITING_ACTION.getType();
                }
                actionList.add(action);
                return actionList;
            case VOTE:
                action.playerActionType = ActionType.VOTE.getType();
                action.actionPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, PlayerStatus.LIVE.getType());
                action.targetPlayerIds = PlayerUtil.getPlayerIdsByRoleAndStatus(gameInfo.getPlayerMap(), -1, PlayerStatus.LIVE.getType());
                if(stage.getAction(player.getId()) != null) {
                    action.actionStatus = ActionStatus.ALREADY_ACTION.getType();
                } else {
                    action.actionStatus = ActionStatus.WAITING_ACTION.getType();
                }
                actionList.add(action);
                return actionList;
        }
        return actionList;
    }
}
