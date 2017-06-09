package com.telan.werewolf.game.domain.role;

import com.telan.werewolf.game.enums.RoleType;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class HunterRole extends BaseRole {

    public HunterRole(){
        this.setRole(RoleType.HUNTER.getType());
    }
}
