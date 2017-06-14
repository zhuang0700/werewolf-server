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
                    return null;
                default:
                    return null;
            }
        }
        return null;
    }

    public static Stage createVoteStage() {
        return new VoteStage();
    }


    public static Stage createSheriffStage() {
        return new SheriffStage();
    }

    public static List<Stage> createDefaultNightStages(List<BaseRole> roleList) {
        List<Stage> stageList = new ArrayList<>();
        if(roleList.contains(new WolfRole())){
            stageList.add(createRoleStage(RoleType.WOLF.getType()));
        }
        if(roleList.contains(new WitchRole())){
            stageList.add(createRoleStage(RoleType.WITCH.getType()));
        }
        if(roleList.contains(new SeerRole())){
            stageList.add(createRoleStage(RoleType.SEER.getType()));
        }
        return stageList;
    }


    public static List<Stage> createDefaultDayStages(boolean needSheriff) {
        List<Stage> stageList = new ArrayList<>();
        if(needSheriff) {
            stageList.add(createSheriffStage());
        }
        stageList.add(createVoteStage());
        return stageList;
    }
}
