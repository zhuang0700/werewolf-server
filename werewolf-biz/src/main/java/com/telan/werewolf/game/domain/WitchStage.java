package com.telan.werewolf.game.domain;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.domain.role.WitchRole;
import com.telan.werewolf.game.enums.*;
import com.telan.werewolf.game.manager.PlayerEngine;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WeResultSupport;
import com.telan.werewolf.utils.ActionUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class WitchStage extends Stage {

    public boolean checkStageUpdate(Stage prevStage){
        if(prevStage != null) {
            this.markedPlayerId = prevStage.markedPlayerId;
        }
        if(CollectionUtils.isEmpty(before)) {
            return true;
        }
        for(Stage st : before) {
            if(!st.isFinish()){
                return false;
            }
        }
        //all finished
        return true;
    }

    public WitchStage(GameInfo gameInfo){
        super();
        this.stageType = StageType.WITCH;
        this.roleList = new ArrayList<>();
        this.roleList.add(RoleType.WITCH.getType());
        this.setGameInfo(gameInfo);
    }

    @Override
    public void roleStart() {
        if(voteMap == null) {
            voteMap = new HashMap<>();
        }
        waitingAction();
    }

    @Override
    public void roleWaitingAction() {
        return;
    }

    @Override
    public void roleAnalyse() {
        List<Player> witchs = PlayerEngine.getPlayersByRoleAndStatus(gameInfo, PlayerStatus.LIVE.getType(), RoleType.WITCH.getType());
        if(CollectionUtils.isEmpty(witchs)) {
            finish();
            return;
        }
        WitchRole witchRole = (WitchRole)witchs.get(0).getRole();
        PlayerAction saveAction = ActionUtil.findActionByFromIdAndType(actionList, -1, ActionType.SAVE.getType());
        PlayerAction poisionAction = ActionUtil.findActionByFromIdAndType(actionList, -1, ActionType.POISON.getType());
        if(saveAction != null || witchRole.getMedicineLeft() < 1) {
            finish();
        }
        if(poisionAction != null || witchRole.getPoision() < 1) {
            finish();
        }
        return;
    }

    @Override
    public void roleFinish() {

    }

    @Override
    public WeBaseResult<ActionResult> roleUserAction(Player player, PlayerAction action){
        WeBaseResult<ActionResult> resultSupport = new WeBaseResult<ActionResult>();
        WitchRole witchRole = (WitchRole)player.getRole();
        if(action.actionType == ActionType.SAVE.getType() && ActionUtil.findActionByFromIdAndType(actionList, player.getId(), ActionType.SAVE.getType()) == null) {
            if(ActionMsg.NO.name().equals(action.msg)) {
                actionList.add(action);
                return resultSupport;
            }
            if(witchRole.getMedicineLeft() >= 1) {
                useMedicine = true;
                witchRole.setMedicineLeft(witchRole.getMedicineLeft() - 1);
                actionList.add(action);
            } else {
                resultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
            }
        } else if(action.actionType == ActionType.POISON.getType() && ActionUtil.findActionByFromIdAndType(actionList, player.getId(), ActionType.POISON.getType()) == null) {
            if(ActionMsg.NO.name().equals(action.msg)) {
                actionList.add(action);
                return resultSupport;
            }
            if(witchRole.getPoision() >= 1) {
                usePoisionId = action.toPlayerId;
                witchRole.setMedicineLeft(witchRole.getPoision() - 1);
                actionList.add(action);
            } else {
                resultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
            }
        }else {
            resultSupport.setErrorCode(WeErrorCode.UNSUPPORT_ACTION);
        }
        return resultSupport;
    }
}
