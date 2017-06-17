package com.telan.werewolf.factory;

import com.telan.werewolf.game.domain.record.BaseRecord;
import com.telan.werewolf.game.domain.role.BaseRole;
import com.telan.werewolf.game.enums.RoundStatus;
import com.telan.werewolf.game.domain.Round;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/31.
 */
public class RoundFactory {
    public static Round createRound(int roundNo, List<BaseRole> roleList) {
        Round round = new Round();
        round.setRoundNo(roundNo);
        round.setRoundStatus(RoundStatus.NOT_START.getType());
        round.setDayStageList(StageFactory.createDefaultDayStages(roundNo==1));
        round.setNightStageList(StageFactory.createDefaultNightStages(roleList));
        round.setRecordList(new ArrayList<BaseRecord>());
        return round;
    }
}
