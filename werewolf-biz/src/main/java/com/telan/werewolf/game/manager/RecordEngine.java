package com.telan.werewolf.game.manager;

import com.alibaba.fastjson.JSON;
import com.telan.werewolf.factory.GameMsgFactory;
import com.telan.werewolf.factory.RecordFactory;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.domain.record.BaseRecord;
import com.telan.werewolf.game.domain.record.VoteRecord;
import com.telan.werewolf.game.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/6/6.
 */
public class RecordEngine {
    public final static Logger log = LoggerFactory.getLogger(RecordEngine.class);

    public static void sendNormalMsg(GameInfo gameInfo, GameMsg msg) {
        Round currentRound = gameInfo.getCurrentRound();
        BaseRecord record = RecordFactory.createNormalRecord(msg.getSubType(), msg.getObjects());
        currentRound.addRecord(record);
        Map<Long, Player> playerMap = gameInfo.getPlayerMap();
        for(Player player : playerMap.values()) {
            if(msg.getVisibility().isVisiable(player)) {
                player.addRecord(record);
            }
        }
    }
//
//    public static void sendKillMsg(GameInfo gameInfo, PlayerAction action) {
//        Round currentRound = gameInfo.getCurrentRound();
//        Visibility visibility = new Visibility();
//        visibility.setVisableRoleType(new ArrayList<Integer>(){{add(RoleType.WOLF.getType());}});
//        List<Object> objects = new ArrayList<>();
//        Player fromPlayer = gameInfo.getPlayer(action.fromPlayerId);
//        Player toPlayer = gameInfo.getPlayer(action.toPlayerId);
//        objects.add(fromPlayer.getPlayerNo());
//        objects.add(toPlayer.getPlayerNo());
//        GameMsg msg = GameMsgFactory.createGameMsg(GameMsgSubType.KILL_ACTION, visibility, objects);
//        sendNormalMsg(gameInfo, msg);
//    }

    public static void sendActionMsg(GameInfo gameInfo, PlayerAction action) {
        Round currentRound = gameInfo.getCurrentRound();
        ActionType actionType = ActionType.getByType(action.actionType);
        if(actionType == null) {
            log.error("sendActionMsg error. unknown action = {}", JSON.toJSONString(action));
            return;
        }
        Visibility visibility = new Visibility();
        List<Object> objects = new ArrayList<>();
        Player fromPlayer = gameInfo.getPlayer(action.fromPlayerId);
        Player toPlayer = gameInfo.getPlayer(action.toPlayerId);
        GameMsg msg = null;

        switch (actionType) {
            case KILL:
                visibility.setVisableRoleType(new ArrayList<Integer>(){{add(RoleType.WOLF.getType());}});
                objects.add(fromPlayer.getPlayerNo());
                objects.add(toPlayer.getPlayerNo());
                msg = GameMsgFactory.createGameMsg(GameMsgSubType.KILL_ACTION, visibility, objects);
                break;
            case SAVE:
                visibility.setVisableRoleType(new ArrayList<Integer>(){{add(RoleType.WITCH.getType());}});
                objects.add(fromPlayer.getPlayerNo());
                objects.add(toPlayer.getPlayerNo());
                msg = GameMsgFactory.createGameMsg(GameMsgSubType.CURE_ACTION, visibility, objects);
                break;
            case POISON:
                visibility.setVisableRoleType(new ArrayList<Integer>(){{add(RoleType.WITCH.getType());}});
                objects.add(fromPlayer.getPlayerNo());
                objects.add(toPlayer.getPlayerNo());
                msg = GameMsgFactory.createGameMsg(GameMsgSubType.POSITION_ACTION, visibility, objects);
                break;
            case SHOOT:
                visibility.setType(VisibilityType.ALL);
                objects.add(fromPlayer.getPlayerNo());
                objects.add(toPlayer.getPlayerNo());
                msg = GameMsgFactory.createGameMsg(GameMsgSubType.POSITION_ACTION, visibility, objects);
                break;
            case SEE:
                visibility.setVisableRoleType(new ArrayList<Integer>(){{add(RoleType.SEER.getType());}});
                objects.add(fromPlayer.getPlayerNo());
                objects.add(toPlayer.getPlayerNo());
                objects.add(toPlayer.getRole().getName());
                msg = GameMsgFactory.createGameMsg(GameMsgSubType.SEER_ACTION, visibility, objects);
                break;
            case VOTE:
            case RUN_SHERIFF:
                break;
        }
        if(msg != null) {
            sendNormalMsg(gameInfo, msg);
        }
    }


    public static void sendVoteActionMsg(GameInfo gameInfo, List<PlayerAction> actionList) {
        Round currentRound = gameInfo.getCurrentRound();
        VoteRecord record = RecordFactory.createVoteActionRecord(GameMsgSubType.VOTE_DEATH_RESULT.getSubType(), actionList, gameInfo.getPlayerMap(), true);
        currentRound.addRecord(record);
        Map<Long, Player> playerMap = gameInfo.getPlayerMap();
        for(Player player : playerMap.values()) {
            player.addRecord(record);
        }
    }

    public static void sendVoteResultMsg(GameInfo gameInfo, int gameMsgSubType, long maxVotedId, boolean voteAgain) {
        Round currentRound = gameInfo.getCurrentRound();
        List<Object> contents = new ArrayList<>();
        BaseRecord record = null;
        if(maxVotedId > 0) {
            contents.add(gameInfo.getPlayer(maxVotedId).getPlayerNo() +"号");
            record = RecordFactory.createNormalRecord(gameMsgSubType, contents);
        } else {
            if(voteAgain) {
                //重新投票
                record = RecordFactory.createNormalRecord(GameMsgSubType.VOTE_AGAIN.getSubType(), contents);
            } else {
                //无人得票最高，进入下一阶段
                contents.add("没有");
                record = RecordFactory.createNormalRecord(gameMsgSubType, contents);
            }
        }
        currentRound.addRecord(record);
        Map<Long, Player> playerMap = gameInfo.getPlayerMap();
        for(Player player : playerMap.values()) {
            player.addRecord(record);
        }
    }

    public static void sendRunSheriffResultMsg(GameInfo gameInfo, List<PlayerAction> actionList) {
        Round currentRound = gameInfo.getCurrentRound();
        VoteRecord record = RecordFactory.createVoteActionRecord(GameMsgSubType.VOTE_SHERIFF_RESULT.getSubType(), actionList, gameInfo.getPlayerMap(), false);
        currentRound.addRecord(record);
        Map<Long, Player> playerMap = gameInfo.getPlayerMap();
        for(Player player : playerMap.values()) {
            player.addRecord(record);
        }
    }
}
