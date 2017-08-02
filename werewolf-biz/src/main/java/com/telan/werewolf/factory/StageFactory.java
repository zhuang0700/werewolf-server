package com.telan.werewolf.factory;

import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.domain.role.*;
import com.telan.werewolf.game.enums.RoleType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/31.
 */
public class StageFactory {
    public static Stage createRoleStage(int type, GameInfo gameInfo) {
        RoleType roleType = RoleType.getByType(type);
        if(roleType != null) {
            switch (roleType) {
                case WOLF:
                    return new WolfStage(gameInfo);
                case WITCH:
                    return new WitchStage(gameInfo);
                case HUNTER:
                    return new HunterStage(gameInfo);
                case SEER:
                    return new SeerStage(gameInfo);
                case VILLAGER:
                    return new EmptyStage();
                default:
                    return new EmptyStage();
            }
        }
        return new EmptyStage();
    }

    public static Stage createVoteStage(GameInfo gameInfo) {
        Stage voteStage = new VoteStage();
        voteStage.setGameInfo(gameInfo);
        return voteStage;
    }


    public static Stage createSheriffStage() {
        return new SheriffStage();
    }

    public static List<Stage> createDefaultNightStages(List<BaseRole> roleList, GameInfo gameInfo) {
        List<Stage> stageList = new ArrayList<>();
        Stage wolfStage = createRoleStage(RoleType.WOLF.getType(), gameInfo);
        stageList.add(wolfStage);
        Stage nightEndStage = new NightEndStage();
        nightEndStage.setGameInfo(gameInfo);
        wolfStage.linkNext(nightEndStage);
        if(roleList.contains(new WitchRole())){
            Stage witchStage = createRoleStage(RoleType.WITCH.getType(), gameInfo);
            wolfStage.linkNext(witchStage);
            witchStage.linkNext(nightEndStage);
            stageList.add(witchStage);
        }
        if(roleList.contains(new SeerRole())){
            Stage seerStage = createRoleStage(RoleType.SEER.getType(), gameInfo);
            wolfStage.linkNext(seerStage);
            seerStage.linkNext(nightEndStage);
            stageList.add(seerStage);
        }
        stageList.add(nightEndStage);
        return stageList;
    }


    public static List<Stage> createDefaultDayStages(boolean needSheriff, GameInfo gameInfo) {
        List<Stage> stageList = new ArrayList<>();
        Stage voteStage = createVoteStage(gameInfo);
        Stage dayEndStage = new DayEndStage();
        dayEndStage.setGameInfo(gameInfo);
        if(needSheriff) {
            Stage sheriffStage = createSheriffStage();
            sheriffStage.setGameInfo(gameInfo);
            sheriffStage.linkNext(voteStage);
            voteStage.linkNext(dayEndStage);
            stageList.add(sheriffStage);
        } else {
            voteStage.linkNext(dayEndStage);
        }
        stageList.add(voteStage);
        stageList.add(dayEndStage);
        return stageList;
    }
}
