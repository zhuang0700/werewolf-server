package com.telan.werewolf.game.domain.role;

import com.telan.werewolf.game.enums.RoleType;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class WitchRole extends BaseRole {

    public WitchRole(){
        this.setRole(RoleType.WITCH.getType());
    }

    private int medicineLeft = 1;

    private int poision = 1;

    public int getMedicineLeft() {
        return medicineLeft;
    }

    public void setMedicineLeft(int medicineLeft) {
        this.medicineLeft = medicineLeft;
    }

    public int getPoision() {
        return poision;
    }

    public void setPoision(int poision) {
        this.poision = poision;
    }
}
