package com.telan.werewolf.factory;

import com.telan.werewolf.game.domain.GameMsg;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.domain.PlayerAction;
import com.telan.werewolf.game.domain.record.BaseRecord;
import com.telan.werewolf.game.domain.record.DeathRecord;
import com.telan.werewolf.game.domain.record.NormalRecord;
import com.telan.werewolf.game.domain.record.VoteRecord;
import com.telan.werewolf.game.domain.role.*;
import com.telan.werewolf.game.enums.GameMsgSubType;
import com.telan.werewolf.game.enums.RecordType;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/5/31.
 */
public class RecordFactory {
    public static BaseRecord createRecord(int recordType) {
        RecordType type = RecordType.getByType(recordType);
        if(type == null) {
            return null;
        }
        switch (type) {
            case NORMAL:
                NormalRecord normalRecord = new NormalRecord();
                normalRecord.setRecordType(recordType);
                return new NormalRecord();
            case DEATH:
                return new DeathRecord();
            case VOTE:
                return new VoteRecord();
            default:
                return new NormalRecord();
        }
    }
    
    public static NormalRecord createNormalRecord(int msgSubType, List<Object> contents) {
        NormalRecord normalRecord = new NormalRecord();
        normalRecord.setRecordType(RecordType.NORMAL.getType());
        normalRecord.setMsgSubType(msgSubType);
        GameMsgSubType gameMsgSubType = GameMsgSubType.getBySubType(msgSubType);
        if(gameMsgSubType != null) {
            String msg = MessageFormat.format(gameMsgSubType.getDesc(), contents);
            normalRecord.addMsg(msg);
        }
        return normalRecord;
    }

    public static DeathRecord createDeathRecord(int msgSubType, List<Player> deadPlayers) {
        DeathRecord deathRecord = new DeathRecord();
        deathRecord.setRecordType(RecordType.DEATH.getType());
        deathRecord.setMsgSubType(msgSubType);
        deathRecord.setDeathList(deadPlayers);
        List<Object> contents = new ArrayList<>();
        String content = "";
        if(CollectionUtils.isEmpty(deadPlayers)) {
            content = "无，平安夜";
        } else {
            for(Player player : deadPlayers) {
                content += " " + player.getPlayerDO().getPlayerNo() + "号";
            }
        }
        contents.add(content);
        GameMsgSubType gameMsgSubType = GameMsgSubType.getBySubType(msgSubType);
        if(gameMsgSubType != null) {
            String msg = MessageFormat.format(gameMsgSubType.getDesc(), contents);
            deathRecord.addMsg(msg);
        }
        return deathRecord;
    }

    public static VoteRecord createVoteActionRecord(int msgSubType, List<PlayerAction> voteList, Map<Long, Player> playerMap, boolean voteAgain) {
        VoteRecord voteRecord = new VoteRecord();
        voteRecord.setRecordType(RecordType.DEATH.getType());
        voteRecord.setMsgSubType(msgSubType);
        Map<Long, Long> voteMap = new HashMap<>();
        Map<Long, List<Long>> votedMap = new HashMap<>();
        for(PlayerAction vote : voteList) {
            voteMap.put(vote.fromPlayerId, vote.toPlayerId);
            Player fromPlayer = playerMap.get(vote.fromPlayerId);
            List<Long> voterList = votedMap.get(vote.toPlayerId);
            if(voterList == null) {
                voterList = new ArrayList<>();
            }
            voterList.add(fromPlayer.getId());
            votedMap.put(vote.toPlayerId, voterList);
        }
        voteRecord.setVoteMap(voteMap);
        if(CollectionUtils.isEmpty(votedMap)) {
            voteRecord.addMsg("无投票结果");
        } else {
            int maxVoteNum = 0;
            long maxVotedId = 0;
            for(Long votedId : votedMap.keySet()) {
                StringBuilder voteContent = new StringBuilder();
                if(votedMap.get(votedId).size() > maxVoteNum) {
                    maxVoteNum = votedMap.get(votedId).size();
                    maxVotedId = votedId;
                }
                for(Long voteId : votedMap.get(votedId)) {
                    voteContent.append(" ").append(playerMap.get(voteId).getPlayerNo()).append("号");
                }
                voteContent.append("投给").append(playerMap.get(votedId).getPlayerNo()).append("号\r\n");
                voteRecord.addMsg(voteContent.toString());
            }
            if(maxVotedId <= 0) {
                if(voteAgain) {
                    voteRecord.addMsg("请再次投票");
                } else {
                    GameMsgSubType gameMsgSubType = GameMsgSubType.getBySubType(msgSubType);
                    List<Object> contents = new ArrayList<>();
                    contents.add("没有");
                    if(gameMsgSubType != null) {
                        String msg = MessageFormat.format(gameMsgSubType.getDesc(), contents);
                        voteRecord.addMsg(msg);
                    }
                }
            } else {
                GameMsgSubType gameMsgSubType = GameMsgSubType.getBySubType(msgSubType);
                List<Object> contents = new ArrayList<>();
                contents.add(playerMap.get(maxVotedId).getPlayerNo() + "号");
                if(gameMsgSubType != null) {
                    String msg = MessageFormat.format(gameMsgSubType.getDesc(), contents);
                    voteRecord.addMsg(msg);
                }
            }
        }
        return voteRecord;
    }

}
