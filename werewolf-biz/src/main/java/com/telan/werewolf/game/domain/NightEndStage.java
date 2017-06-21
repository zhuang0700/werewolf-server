package com.telan.werewolf.game.domain;

import com.telan.werewolf.factory.StageFactory;
import com.telan.werewolf.game.enums.StageType;
import com.telan.werewolf.game.manager.PlayerEngine;
import com.telan.werewolf.game.manager.RecordEngine;
import com.telan.werewolf.game.manager.RoundEngine;
import com.telan.werewolf.result.WeResultSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class NightEndStage extends Stage {

    public NightEndStage(){
        super();
        this.stageType = StageType.NIGHT_END;
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
        long killedPlayerId = 0;
        boolean useMedicine = false;
        long poisionedPlayerId = 0;
        Round round = gameInfo.getCurrentRound();
        List<Stage> stages = round.getNightStageList();
        Stage wolfStage = round.getStageByType(StageType.WOLF);
        if(wolfStage != null) {
            killedPlayerId = wolfStage.markedPlayerId;
        }
        Stage witchStage = round.getStageByType(StageType.WITCH);
        if(witchStage != null) {
            useMedicine = witchStage.useMedicine;
            poisionedPlayerId = witchStage.usePoisionId;
        }
        List<Long> deadPlayers = new ArrayList<>();
        if(poisionedPlayerId > 0) {
            deadPlayers.add(poisionedPlayerId);
        }
        if(killedPlayerId > 0 && !useMedicine && killedPlayerId != poisionedPlayerId) {
            deadPlayers.add(killedPlayerId);
        }
        PlayerEngine.setPlayerDead(gameInfo, deadPlayers);
        finish();
    }

    @Override
    public void roleFinish() {
        Round round = getCurrentRound();
        RoundEngine.moveToNextStatus(round);
    }

    @Override
    public WeResultSupport roleUserAction(Player player, PlayerAction action){
        return null;
    }
}
