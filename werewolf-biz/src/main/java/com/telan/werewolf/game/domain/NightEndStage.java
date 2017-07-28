package com.telan.werewolf.game.domain;

import com.telan.werewolf.factory.StageFactory;
import com.telan.werewolf.game.enums.DeadReason;
import com.telan.werewolf.game.enums.StageType;
import com.telan.werewolf.game.manager.GameEngine;
import com.telan.werewolf.game.manager.PlayerEngine;
import com.telan.werewolf.game.manager.RecordEngine;
import com.telan.werewolf.game.manager.RoundEngine;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WeResultSupport;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Long, Integer> deathInfo = new HashMap<>();
        if(poisionedPlayerId > 0) {
            deadPlayers.add(poisionedPlayerId);
            deathInfo.put(poisionedPlayerId, DeadReason.POISON.getType());
        }
        if(killedPlayerId > 0 && !useMedicine && killedPlayerId != poisionedPlayerId) {
            deadPlayers.add(killedPlayerId);
            deathInfo.put(killedPlayerId, DeadReason.KILL.getType());
        }
        if(!PlayerEngine.setPlayerDead(gameInfo, deathInfo, this)){
            return;
        }
        finish();
    }

    @Override
    public void roleFinish() {
        if(!GameEngine.tryEndGame(gameInfo)) {
            RoundEngine.moveToNextStatus(gameInfo);
        }
    }

    @Override
    public WeBaseResult<ActionResult> roleUserAction(Player player, PlayerAction action){
        return null;
    }
}
