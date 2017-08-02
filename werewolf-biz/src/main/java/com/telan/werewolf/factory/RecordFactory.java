package com.telan.werewolf.factory;

import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.domain.record.*;
import com.telan.werewolf.game.domain.role.*;
import com.telan.werewolf.game.enums.GameMsgSubType;
import com.telan.werewolf.game.enums.RecordType;
import com.telan.werewolf.utils.conventor.PlayerConvertor;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.*;

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
    
    public static NormalRecord createNormalRecord(int msgSubType, Object[] contents) {
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

    public static DeathRecord createDeathRecord(GameInfo gameInfo, int msgSubType, Player deadPlayer) {
        //TODO: death reason
        DeathRecord deathRecord = new DeathRecord();
        deathRecord.setRecordType(RecordType.DEATH.getType());
        deathRecord.setMsgSubType(msgSubType);
        List<Player> deadPlayers = new ArrayList<>();
        deadPlayers.add(deadPlayer);
        deathRecord.setDeathList(PlayerConvertor.convertPlayerVOList(deadPlayers));
        Object[] contents = new Object[1];
        String content = "";
        if(CollectionUtils.isEmpty(deadPlayers)) {
            content = "没有";
        } else {
            for(Player player : deadPlayers) {
                content += " " + player.getPlayerNo() + "号";
            }
        }
        contents[0] = content;
        GameMsgSubType gameMsgSubType = GameMsgSubType.getBySubType(msgSubType);
        if(gameMsgSubType != null) {
            String msg = MessageFormat.format(gameMsgSubType.getDesc(), contents);
            deathRecord.addMsg(msg);
        }
        return deathRecord;
    }

    public static VoteRecord createVoteActionRecord(int msgSubType, List<PlayerAction> voteList, Map<Long, Player> playerMap) {
        VoteRecord voteRecord = new VoteRecord();
        voteRecord.setRecordType(RecordType.VOTE.getType());
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
            voteRecord.addMsg("投票结果：");
            for(Long votedId : votedMap.keySet()) {
                StringBuilder voteContent = new StringBuilder();
                if(votedMap.get(votedId).size() > maxVoteNum) {
                    maxVoteNum = votedMap.get(votedId).size();
                }
                for(Long voteId : votedMap.get(votedId)) {
                    voteContent.append(" ").append(playerMap.get(voteId).getPlayerNo()).append("号");
                }
                voteContent.append("投给").append(playerMap.get(votedId).getPlayerNo()).append("号\r\n");
                voteRecord.addMsg(voteContent.toString());
            }
        }
        return voteRecord;
    }

    public static RoleInfoRecord createRoleInfoRecord(int msgSubType, Map<Long, Player> playerMap) {
        RoleInfoRecord roleInfoRecord = new RoleInfoRecord();
        roleInfoRecord.setMsgSubType(msgSubType);
        roleInfoRecord.setPlayerList(new ArrayList<>(playerMap.values()));
        roleInfoRecord.setRecordType(RecordType.ROLE_INFO.getType());
        GameMsgSubType gameMsgSubType = GameMsgSubType.getBySubType(msgSubType);
        if(gameMsgSubType == null) {
            return roleInfoRecord;
        }
        List<String> msgList = new ArrayList<>();

        for(Player player : playerMap.values()) {
            List<Object> objectList = new ArrayList<>();
            objectList.add(player.getPlayerNo());
            objectList.add(player.getRole().getName());
            String msg = MessageFormat.format(gameMsgSubType.getDesc(), objectList);
            msgList.add(msg);
        }
        roleInfoRecord.setMsgList(msgList);
        return roleInfoRecord;
    }

}
