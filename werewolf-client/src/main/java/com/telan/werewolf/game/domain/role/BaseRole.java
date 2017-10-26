package com.telan.werewolf.game.domain.role;

import com.telan.werewolf.game.enums.RoleType;
import org.springframework.util.StringUtils;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class BaseRole {
    private String name;
    private String desc;
    private int role;

    public String getDesc() {
        if(StringUtils.isEmpty(desc)) {
            return RoleType.getByType(role).getDescription();
        }
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        if(StringUtils.isEmpty(name)) {
            return RoleType.getByType(role).getName();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if(BaseRole.class.isInstance(obj)) {
            BaseRole newRole = (BaseRole)obj;
            if(this.role == newRole.role) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.getRole();
    }
}