package com.telan.werewolf.factory;

import com.telan.werewolf.game.domain.role.*;
import com.telan.werewolf.game.enums.RoleType;

/**
 * Created by weiwenliang on 17/5/31.
 */
public class RoleFactory {
    public static BaseRole createRoleById(int role) {
        RoleType roleType = RoleType.getByType(role);
        if(roleType == null) {
            return null;
        }
        switch (roleType) {
            case WOLF:
                return new WolfRole();
            case WITCH:
                return new WitchRole();
            case HUNTER:
                return new HunterRole();
            case SEER:
                return new SeerRole();
            case VILLAGER:
                return new VillagerRole();
            default:
                return new BaseRole();
        }
    }
}
