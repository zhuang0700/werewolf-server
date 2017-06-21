package com.telan.werewolf.game.manager;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.enums.*;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WeResultSupport;
import com.telan.werewolf.utils.ActionUtil;

/**
 * Created by weiwenliang on 17/5/23.
 */
public class ActionEngine {
    public static  WeResultSupport checkAction(Player player, PlayerAction action) {
        WeResultSupport resultSupport = new WeResultSupport();
        if(player.getStatus() == PlayerStatus.DEAD.getType()) {
            resultSupport.setErrorCode(WeErrorCode.DEAD_ACTION);
            return resultSupport;
        }
        return resultSupport;
    }

    public static WeResultSupport performAction(GameInfo gameInfo, PlayerAction action) {
        WeResultSupport weResultSupport;
        Player player = gameInfo.getPlayer(action.fromPlayerId);
        weResultSupport = checkAction(player, action);
        if(!weResultSupport.isSuccess()) {
            return weResultSupport;
        }
        Round currentRound = gameInfo.getCurrentRound();
        weResultSupport = roundCheck(currentRound, action);
        if(!weResultSupport.isSuccess()) {
            return weResultSupport;
        }
        ActionType actionType = ActionType.getByType(action.actionType);
        Stage stage = currentRound.getStageByType(StageType.getByActionType(actionType));
        if(stage == null || stage.status != StageStatus.WAITING_ACTION.getType()) {
            weResultSupport.setErrorCode(WeErrorCode.WRONG_STAGE_ACTION);
            return weResultSupport;
        }
        stage.userAction(player, action);
        RecordEngine.sendActionMsg(gameInfo, action);
//        switch (actionType) {
//            case KILL:
//                WolfStage wolfStage = (WolfStage)currentRound.getStageByType(StageType.WOLF);
//                if(wolfStage == null || wolfStage.status != StageStatus.WAITING_ACTION.getType()) {
//                    weResultSupport.setErrorCode(WeErrorCode.WRONG_STAGE_ACTION);
//                    return weResultSupport;
//                }
//                wolfStage.userAction(player, action);
//                RecordEngine.sendActionMsg(gameInfo, action);
//                break;
//            case SAVE:
//                //TODO: not finished
//                WitchStage witchStage = (WitchStage)currentRound.getStageByType(StageType.WITCH);
//                if(witchStage == null || witchStage.status != StageStatus.WAITING_ACTION.getType()) {
//                    weResultSupport.setErrorCode(WeErrorCode.WRONG_STAGE_ACTION);
//                    return weResultSupport;
//                }
//                witchStage.userAction(player, action);
//                RecordEngine.sendActionMsg(gameInfo, action);
//                break;
//        }
        return weResultSupport;
    }

    public static WeResultSupport performJudgeAction(GameInfo gameInfo, JudgeAction action) {
        WeResultSupport weResultSupport = new WeResultSupport();
        Round currentRound = gameInfo.getCurrentRound();

        JudgeActionType judgeActionType = JudgeActionType.getByType(action.getActionType());
        Stage stage = currentRound.getStageByType(StageType.getByType(action.getStageType()));
        if(stage == null || stage.status != StageStatus.WAITING_ACTION.getType()) {
            weResultSupport.setErrorCode(WeErrorCode.WRONG_STAGE_ACTION);
            return weResultSupport;
        }
        stage.analyse();
        RecordEngine.sendJudgeActionMsg(gameInfo, stage, action);
        return weResultSupport;
    }


    private static WeResultSupport roundCheck(Round currentRound, PlayerAction action) {
        WeResultSupport weResultSupport = new WeResultSupport();
        if(currentRound.getRoundStatus() == RoundStatus.NOT_START.getType() || currentRound.getRoundStatus() == RoundStatus.FINISH.getType()) {
            weResultSupport.setErrorCode(WeErrorCode.WRONG_STAGE_ACTION);
            return weResultSupport;
        } else if(currentRound.getRoundStatus() == RoundStatus.DAY.getType()) {
            if(action.actionType == ActionType.SHOOT.getType() ||
                    action.actionType == ActionType.VOTE.getType() ||
                    action.actionType == ActionType.RUN_SHERIFF.getType()) {
                return weResultSupport;
            } else {
                weResultSupport.setErrorCode(WeErrorCode.WRONG_STAGE_ACTION);
                return weResultSupport;
            }
        } else if(currentRound.getRoundStatus() == RoundStatus.DARK.getType()) {
            if(action.actionType == ActionType.KILL.getType() ||
                    action.actionType == ActionType.SAVE.getType() ||
                    action.actionType == ActionType.POISON.getType() ||
                    action.actionType == ActionType.SEE.getType()) {
                return weResultSupport;
            } else {
                weResultSupport.setErrorCode(WeErrorCode.WRONG_STAGE_ACTION);
                return weResultSupport;
            }
        } else {
            weResultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
            return weResultSupport;
        }
    }
}
