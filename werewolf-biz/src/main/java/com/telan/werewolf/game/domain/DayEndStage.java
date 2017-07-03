package com.telan.werewolf.game.domain;

import com.telan.werewolf.game.enums.StageType;
import com.telan.werewolf.game.manager.PlayerEngine;
import com.telan.werewolf.game.manager.RoundEngine;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WeResultSupport;
import com.telan.werewolf.utils.ActionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class DayEndStage extends Stage {

    public DayEndStage(){
        super();
        this.stageType = StageType.DAY_END;
    }

    @Override
    public void roleStart() {
        if(voteMap == null) {
            voteMap = new HashMap<>();
        }
        analyse();
    }

    @Override
    public void roleWaitingAction() {
        return;
    }

    @Override
    public void roleAnalyse() {

        Round round = gameInfo.getCurrentRound();
        Stage voteStage = round.getStageByType(StageType.VOTE);
        if(voteStage != null) {
            voteMap = voteStage.voteMap;
        }
        List<Long> maxVotedIds = ActionUtil.findMaxVote(voteMap);

        finish();
    }

    @Override
    public void roleFinish() {
        Round round = getCurrentRound();
        RoundEngine.moveToNextStatus(gameInfo);
    }

    @Override
    public WeBaseResult<ActionResult> roleUserAction(Player player, PlayerAction action){
        return null;
    }
}
