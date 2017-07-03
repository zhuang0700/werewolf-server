package com.telan.werewolf.game.manager;

import com.telan.werewolf.factory.GameMsgFactory;
import com.telan.werewolf.factory.RecordFactory;
import com.telan.werewolf.factory.RoundFactory;
import com.telan.werewolf.game.domain.Stage;
import com.telan.werewolf.game.domain.Visibility;
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
        moveToNextStatus(currentGame);
    }

    public static Round finishRound(GameInfo currentGame, boolean gameFinish) {
        currentGame.getCurrentRound().setRoundStatus(RoundStatus.FINISH.getType());
        if(!gameFinish) {
            Round round = RoundFactory.createRound(currentGame.getCurrentRound().getRoundNo() + 1, currentGame);
            currentGame.changeCurrentRound(round);
            startRound(currentGame);
            return round;
        }
        return null;
    }

    public static void moveToNextStatus(GameInfo currentGame) {
        Round round = currentGame.getCurrentRound();
        RoundStatus roundStatus = RoundStatus.getByType(round.getRoundStatus());
        Object[] roundNoContent = new Object[1];
        if(roundStatus == null) {
            return;
        }
        roundNoContent[0] =round.getRoundNo();
//        roundNoContent.add(round.getRoundNo());
        switch (roundStatus) {
            case NOT_START:
                round.setRoundStatus(RoundStatus.DARK.getType());
                RecordEngine.sendNormalMsg(currentGame, GameMsgFactory.createGameMsg(GameMsgSubType.NIGHT_START, Visibility.ALL, roundNoContent));
//                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.NIGHT_START.getSubType(), roundNoContent));
                if(CollectionUtils.isEmpty(round.getNightStageList())) {
                    moveToNextStatus(currentGame);
                    break;
                }
                for(Stage stage : round.getNightStageList()) {
                    stage.update(null);
                }
                break;
            case DARK:
                round.setRoundStatus(RoundStatus.DAY.getType());
                RecordEngine.sendNormalMsg(currentGame, GameMsgFactory.createGameMsg(GameMsgSubType.NIGHT_END, Visibility.ALL, roundNoContent));
                RecordEngine.sendNormalMsg(currentGame, GameMsgFactory.createGameMsg(GameMsgSubType.DAY_START, Visibility.ALL, roundNoContent));

//                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.NIGHT_END.getSubType(), roundNoContent));
//                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.DAY_START.getSubType(), roundNoContent));
                if(CollectionUtils.isEmpty(round.getDayStageList())) {
                    moveToNextStatus(currentGame);
                    break;
                }
                for(Stage stage : round.getDayStageList()) {
                    stage.update(null);
                }
                break;
            case DAY:
                round.setRoundStatus(RoundStatus.FINISH.getType());
                RecordEngine.sendNormalMsg(currentGame, GameMsgFactory.createGameMsg(GameMsgSubType.DAY_END, Visibility.ALL, roundNoContent));
                finishRound(currentGame, false);
//                round.addRecord(RecordFactory.createNormalRecord(GameMsgSubType.DAY_END.getSubType(), roundNoContent));
                break;
        }
    }
}
