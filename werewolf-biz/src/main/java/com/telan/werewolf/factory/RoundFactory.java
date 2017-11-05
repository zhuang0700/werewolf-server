package com.telan.werewolf.factory;

import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.domain.Stage;
import com.telan.werewolf.game.domain.record.BaseRecord;
import com.telan.werewolf.game.domain.role.BaseRole;
import com.telan.werewolf.game.enums.RoundStatus;
import com.telan.werewolf.game.domain.Round;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/31.
 */
public class RoundFactory {
    public static Round createRound(int roundNo, GameInfo gameInfo) {
        Round round = new Round();
        round.setRoundNo(roundNo);
        round.setRoundStatus(RoundStatus.NOT_START.getType());
        List<Stage> dayStageList = StageFactory.createDefaultDayStages(roundNo==1&&gameInfo.getGameConfig().isNeedSheriff(), gameInfo);
        round.setDayStageList(findHeadStages(dayStageList));
        round.addStageList(dayStageList);
        List<Stage> nightStageList = StageFactory.createDefaultNightStages(gameInfo.getRoleList(), gameInfo);
        round.setNightStageList(nightStageList);
        round.addStageList(nightStageList);
        round.setRecordList(new ArrayList<BaseRecord>());
        return round;
    }

    private static List<Stage> findHeadStages(List<Stage> stageList) {
        List<Stage> result = new ArrayList<>();
        if(CollectionUtils.isEmpty(stageList)) {
            return null;
        }
        for(Stage stage : stageList) {
            if(CollectionUtils.isEmpty(stage.before)) {
                result.add(stage);
            }
        }
        return result;
    }
}
