package com.telan.werewolf.game.manager;

import com.telan.werewolf.factory.RecordFactory;
import com.telan.werewolf.factory.RoundFactory;
import com.telan.werewolf.game.domain.Stage;
import com.telan.werewolf.game.enums.GameMsgSubType;
import com.telan.werewolf.game.enums.RoundStatus;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.domain.Round;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/6/6.
 */
public class RoundEngine {
    public static void startRound(GameInfo currentGame){
        moveToNextStatus(currentGame.getCurrentRound());
    }

    public static Round finishRound(GameInfo currentGame) {
        moveToNextStatus(currentGame.getCurrentRound());
        Round round = RoundFactory.createRound(currentGame.getCurrentRound().getRoundNo() + 1, currentGame);
        currentGame.changeCurrentRound(round);
        startRound(currentGame);
        return round;
    }

    public static void moveToNextStatus(Round round) {
        RoundStatus roundStatus = RoundStatus.getByType(round.getRoundStatus());
        List<Object> roundNoContent = new ArrayList<>();
        if(roundStatus == null) {
            return;
        }
        roundNoContent.add(round.getRoundNo());
        switch (roundStatus) {
            case NOT_START:
                round.setRoundStatus(RoundStatus.DARK.getType());
                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.NIGHT_START.getSubType(), roundNoContent));
                if(CollectionUtils.isEmpty(round.getNightStageList())) {
                    moveToNextStatus(round);
                    break;
                }
                for(Stage stage : round.getNightStageList()) {
                    stage.update(null);
                }
                break;
            case DARK:
                round.setRoundStatus(RoundStatus.DAY.getType());
                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.NIGHT_END.getSubType(), roundNoContent));
                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.DAY_START.getSubType(), roundNoContent));
                if(CollectionUtils.isEmpty(round.getDayStageList())) {
                    moveToNextStatus(round);
                    break;
                }
                for(Stage stage : round.getDayStageList()) {
                    stage.update(null);
                }
                break;
            case DAY:
                round.setRoundStatus(RoundStatus.FINISH.getType());
                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.DAY_END.getSubType(), roundNoContent));
                break;
        }
    }
}
