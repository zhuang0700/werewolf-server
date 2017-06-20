package com.telan.werewolf.game.domain;

import com.telan.werewolf.game.enums.StageType;
import com.telan.werewolf.game.manager.RoundEngine;
import com.telan.werewolf.result.WeResultSupport;

import java.util.HashMap;

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
        //TODO: 结算
        finish();
    }

    @Override
    public void roleFinish() {
        Round round = getCurrentRound();
        RoundEngine.finishRound(gameInfo);
    }

    @Override
    public WeResultSupport roleUserAction(Player player, PlayerAction action){
        return null;
    }
}
