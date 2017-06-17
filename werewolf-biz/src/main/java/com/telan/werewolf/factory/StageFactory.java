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

    public static List<Stage> createDefaultNightStages(List<BaseRole> roleList) {
        List<Stage> stageList = new ArrayList<>();
        Stage wolfStage = createRoleStage(RoleType.WOLF.getType());
        if(roleList.contains(new WitchRole())){
            Stage witchStage = createRoleStage(RoleType.WITCH.getType());
            wolfStage.linkNext(witchStage);
        }
        if(roleList.contains(new SeerRole())){
            Stage seerStage = createRoleStage(RoleType.SEER.getType());
            wolfStage.linkNext(seerStage);
        }
        stageList.add(wolfStage);
        return stageList;
    }


    public static List<Stage> createDefaultDayStages(boolean needSheriff) {
        List<Stage> stageList = new ArrayList<>();
        Stage voteStage = createVoteStage();
        if(needSheriff) {
            stageList.add(createSheriffStage());
            stageList.get(0).linkNext(voteStage);
        } else {
            stageList.add(voteStage);
        }
        return stageList;
    }
}
