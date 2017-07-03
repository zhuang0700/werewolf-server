package com.telan.werewolf.utils.conventor;

import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.enums.*;
import com.telan.werewolf.game.vo.PlayerVO;
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
        gameData.gameDO = gameInfo.getGameDO();
        gameData.playerVOList = new ArrayList<>();
        Player myPlayer = null;
        for(Player player : gameInfo.getPlayerMap().values()) {
            if(userDO.getId() == player.getUserId()){
                myPlayer = player;
                break;
            }
        }
        if(myPlayer != null) {
            boolean shareRoleInfo = gameInfo.getGameConfig().getShareInfoRoles().contains(myPlayer.getRoleType());
            for(Player player : gameInfo.getPlayerMap().values()) {
                PlayerDO playerDO = player.getPlayerDO();
                boolean hideRole = false;
                if(!judgeMode && userDO.getId() != playerDO.getUserId()) {
                    if(!shareRoleInfo || player.getRoleType() != myPlayer.getRoleType()) {
                        //角色信息不共享，或者该玩家和自己角色不同，均需隐藏角色信息
                        hideRole = true;
                    }
                } else {
                    gameData.recordList = player.getRecordList();
                }
                PlayerVO playerVO = PlayerConvertor.convertPlayerVO(player, hideRole);
                gameData.playerVOList.add(playerVO);
            }
        }
        gameData.actionList = convertActionList(gameInfo, myPlayer);
        return gameData;
    }

    //只填装该用户可用action
    public static List<Action> convertActionList(GameInfo gameInfo, Player player) {
        Round currentRound = gameInfo.getCurrentRound();
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
