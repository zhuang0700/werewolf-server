package com.telan.werewolf.game.manager;

import com.alibaba.fastjson.JSON;
import com.telan.werewolf.factory.GameMsgFactory;
import com.telan.werewolf.factory.RecordFactory;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.domain.record.BaseRecord;
import com.telan.werewolf.game.domain.record.DeathRecord;
import com.telan.werewolf.game.domain.record.RoleInfoRecord;
import com.telan.werewolf.game.domain.record.VoteRecord;
import com.telan.werewolf.game.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

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
            if(isVisiable(player, msg.getVisibility(), gameInfo)) {
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
                msg = GameMsgFactory.createGameMsg(GameMsgSubType.KILL_ACTION, visibility, new Object[]{fromPlayer.getPlayerNo(), toPlayer.getPlayerNo()});
                break;
            case SAVE:
                visibility.setVisableRoleType(new ArrayList<Integer>(){{add(RoleType.WITCH.getType());}});
                msg = GameMsgFactory.createGameMsg(GameMsgSubType.CURE_ACTION, visibility, new Object[]{fromPlayer.getPlayerNo()});
                break;
            case POISON:
                visibility.setVisableRoleType(new ArrayList<Integer>(){{add(RoleType.WITCH.getType());}});
                msg = GameMsgFactory.createGameMsg(GameMsgSubType.POSITION_ACTION, visibility, new Object[]{fromPlayer.getPlayerNo(), toPlayer.getPlayerNo()});
                break;
            case SHOOT:
                visibility.setType(VisibilityType.ALL);
                msg = GameMsgFactory.createGameMsg(GameMsgSubType.POSITION_ACTION, visibility, new Object[]{fromPlayer.getPlayerNo(), toPlayer.getPlayerNo()});
                break;
            case SEE:
                visibility.setVisableRoleType(new ArrayList<Integer>(){{add(RoleType.SEER.getType());}});
                msg = GameMsgFactory.createGameMsg(GameMsgSubType.SEER_ACTION, visibility, new Object[]{fromPlayer.getPlayerNo(), toPlayer.getPlayerNo(), toPlayer.getRole().getName()});
                break;
            case VOTE:
            case RUN_SHERIFF:
                break;
        }
        if(msg != null) {
            sendNormalMsg(gameInfo, msg);
        }
    }

    public static void sendJudgeActionMsg(GameInfo gameInfo, Stage stage, JudgeAction action) {
        JudgeActionType judgeActionType = JudgeActionType.getByType(action.getActionType());
        if(judgeActionType == null) {
            log.error("sendActionMsg error. unknown action = {}", JSON.toJSONString(action));
            return;
        }
        List<Object> objects = new ArrayList<>();
        objects.add(stage.stageType.getDesc());
        GameMsg msg = null;
        switch (judgeActionType) {
            case FINISH_STAGE:
                msg = GameMsgFactory.createGameMsg(GameMsgSubType.JUDGE_END_STAGE, Visibility.JUDGE_ONLY, new Object[]{stage.stageType.getDesc()});
                break;
            default:
                break;
        }
        if(msg != null) {
            sendNormalMsg(gameInfo, msg);
        }
    }

    public static void sendVoteActionMsg(GameInfo gameInfo, List<PlayerAction> actionList) {
        Round currentRound = gameInfo.getCurrentRound();
        VoteRecord record = RecordFactory.createVoteActionRecord(GameMsgSubType.VOTE_DETAIL.getSubType(), actionList, gameInfo.getPlayerMap());
        currentRound.addRecord(record);
        Map<Long, Player> playerMap = gameInfo.getPlayerMap();
        for(Player player : playerMap.values()) {
            player.addRecord(record);
        }
    }

    public static void sendVoteResultMsg(GameInfo gameInfo, int gameMsgSubType, List<Long> maxVotedIds, boolean voteAgain) {
        Round currentRound = gameInfo.getCurrentRound();
        if(CollectionUtils.isEmpty(maxVotedIds)) {
            return;
        }
        String playersStr = "";
        for (Long playerId : maxVotedIds) {
            playersStr += gameInfo.getPlayer(playerId).getPlayerNo()+"号 ";
        }
        BaseRecord record = RecordFactory.createNormalRecord(gameMsgSubType, new Object[]{playersStr});
        currentRound.addRecord(record);
        Map<Long, Player> playerMap = gameInfo.getPlayerMap();
        for(Player player : playerMap.values()) {
            player.addRecord(record);
        }

        if(voteAgain) {
            //重新投票
            BaseRecord voteAgainRecord = RecordFactory.createNormalRecord(GameMsgSubType.VOTE_AGAIN.getSubType(), null);
            currentRound.addRecord(voteAgainRecord);
            for(Player player : playerMap.values()) {
                player.addRecord(voteAgainRecord);
            }
        }

    }

    public static void sendRunSheriffResultMsg(GameInfo gameInfo, List<PlayerAction> actionList) {
        Round currentRound = gameInfo.getCurrentRound();
        VoteRecord record = RecordFactory.createVoteActionRecord(GameMsgSubType.VOTE_SHERIFF_RESULT.getSubType(), actionList, gameInfo.getPlayerMap());
        currentRound.addRecord(record);
        Map<Long, Player> playerMap = gameInfo.getPlayerMap();
        for(Player player : playerMap.values()) {
            player.addRecord(record);
        }
    }

    public static void sendDeathMsg(GameInfo gameInfo, Player deadPlayer) {
        List<Object> objectList = new ArrayList<>();
        DeathRecord deathRecord = RecordFactory.createDeathRecord(gameInfo, GameMsgSubType.DEAD_RESULT.getSubType(), deadPlayer);
        Round currentRound = gameInfo.getCurrentRound();
        currentRound.addRecord(deathRecord);
        Map<Long, Player> playerMap = gameInfo.getPlayerMap();
        for(Player player : playerMap.values()) {
            player.addRecord(deathRecord);
        }
    }

    public static void sendRoleInfoMsg(GameInfo gameInfo) {
        RoleInfoRecord roleInfoRecord = RecordFactory.createRoleInfoRecord(GameMsgSubType.GAME_ROLE_RESULT.getSubType(), gameInfo.getPlayerMap());
        Round currentRound = gameInfo.getCurrentRound();
        currentRound.addRecord(roleInfoRecord);
        Map<Long, Player> playerMap = gameInfo.getPlayerMap();
        for(Player player : playerMap.values()) {
            player.addRecord(roleInfoRecord);
        }
    }

    public static boolean isVisiable(Player player, Visibility visibility, GameInfo gameInfo) {
        if(visibility.getType() == null) {
            return false;
        }
        switch (visibility.getType()) {
            case ALL:
                return true;
            case BY_PLAYER:
                if(visibility.getVisablePlayer().contains(player.getId())) {
                    return true;
                }
                return false;
            case BY_ROLE:
                if(visibility.getVisableRoleType().contains(player.getRoleType())) {
                    return true;
                }
                return false;
            case JUDGE_ONLY:
                if(PlayerEngine.isJudge(gameInfo, player)) {
                    return true;
                }
                return false;
        }
        return false;
    }
}
