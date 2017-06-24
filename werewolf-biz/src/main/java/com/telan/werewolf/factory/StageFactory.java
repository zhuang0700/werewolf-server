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
    public static Stage createRoleStage(int type) {
        RoleType roleType = RoleType.getByType(type);
        if(roleType != null) {
            switch (roleType) {
                case WOLF:
                    return new WolfStage();
                case WITCH:
                    return new WitchStage();
                case HUNTER:
                    return new HunterStage();
                case SEER:
                    return new SeerStage();
                case VILLAGER:
                    return new EmptyStage();
                default:
                    return new EmptyStage();
            }
        }
        return new EmptyStage();
    }

    public static Stage createVoteStage() {
        return new VoteStage();
    }


    public static Stage createSheriffStage() {
        return new SheriffStage();
    }

    public static List<Stage> createDefaultNightStages(List<BaseRole> roleList, GameInfo gameInfo) {
        List<Stage> stageList = new ArrayList<>();
        Stage wolfStage = createRoleStage(RoleType.WOLF.getType());
        stageList.add(wolfStage);
        Stage nightEndStage = new NightEndStage();
        if(roleList.contains(new WitchRole())){
            Stage witchStage = createRoleStage(RoleType.WITCH.getType());
            witchStage.setGameInfo(gameInfo);
            wolfStage.linkNext(witchStage);
            witchStage.linkNext(nightEndStage);
            stageList.add(witchStage);
        }
        if(roleList.contains(new SeerRole())){
            Stage seerStage = createRoleStage(RoleType.SEER.getType());
            wolfStage.setGameInfo(gameInfo);
            wolfStage.linkNext(seerStage);
            seerStage.linkNext(nightEndStage);
            stageList.add(seerStage);
        }
        return stageList;
    }


    public static List<Stage> createDefaultDayStages(boolean needSheriff, GameInfo gameInfo) {
        List<Stage> stageList = new ArrayList<>();
        Stage voteStage = createVoteStage();
        Stage dayEndStage = new DayEndStage();
        voteStage.setGameInfo(gameInfo);
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
