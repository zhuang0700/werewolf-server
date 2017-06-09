package com.telan.werewolf.game.manager;

import com.telan.werewolf.factory.RecordFactory;
import com.telan.werewolf.factory.RoundFactory;
import com.telan.werewolf.game.enums.GameMsgSubType;
import com.telan.werewolf.game.enums.RoundStatus;
import com.telan.werewolf.game.process.GameInfo;
import com.telan.werewolf.game.process.Round;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/6/6.
 */
public class RoundManager {
    public void startRound(GameInfo currentGame){
        moveToNextStatus(currentGame.getCurrentRound());
    }

    public Round finishRound(GameInfo currentGame) {
        moveToNextStatus(currentGame.getCurrentRound());
        Round round = RoundFactory.createRound(currentGame.getCurrentRound().getRoundNo() + 1, currentGame.getRoleList());
        currentGame.changeCurrentRound(round);
        startRound(currentGame);
        return round;
    }

    private void moveToNextStatus(Round round) {
        RoundStatus roundStatus = RoundStatus.getByType(round.getRoundStatus());
        List<Object> roundNoContent = new ArrayList<>();
        if(roundStatus == null) {
            return;
        }
        roundNoContent.add(round.getRoundNo());
        switch (roundStatus) {
            case NOT_STAT:
                round.setRoundStatus(RoundStatus.DARK.getType());
                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.NIGHT_START.getSubType(), roundNoContent));
                break;
            case DARK:
                round.setRoundStatus(RoundStatus.DAY.getType());
                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.NIGHT_END.getSubType(), roundNoContent));
                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.DAY_START.getSubType(), roundNoContent));
                break;
            case DAY:
                round.setRoundStatus(RoundStatus.FINISH.getType());
                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.DAY_END.getSubType(), roundNoContent));
                break;
        }
    }
}