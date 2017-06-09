package com.telan.werewolf.game.domain.role;

import com.telan.werewolf.game.domain.role.BaseRole;
import com.telan.werewolf.game.enums.RoleType;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class WolfRole extends BaseRole {

    public WolfRole(){
        this.setRole(RoleType.WOLF.getType());
    }
}
