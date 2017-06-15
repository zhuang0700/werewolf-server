package com.telan.werewolf.game.manager;

import com.telan.werewolf.factory.RecordFactory;
import com.telan.werewolf.factory.RoundFactory;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.domain.GameMsg;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.domain.Round;
import com.telan.werewolf.game.domain.record.BaseRecord;
import com.telan.werewolf.game.domain.role.BaseRole;
import com.telan.werewolf.game.enums.GameMsgSubType;
import com.telan.werewolf.game.enums.RoleType;
import com.telan.werewolf.game.enums.RoundStatus;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/6/6.
 */
public class RecordEngine {
    public void sendNormalMsg(GameInfo gameInfo, GameMsg msg) {
        Round currentRound = gameInfo.getCurrentRound();
        BaseRecord record = RecordFactory.createNormalRecord(msg.getSubType(), msg.getObjects());
        currentRound.addRecord(record);
        Map<Long, Player> playerMap = gameInfo.getPlayerMap();
        for(Player player : playerMap.values()) {
            if(msg.getVisiablity().isVisiable(player)) {
                player.addRecord(record);
            }
        }
    }
}
