package com.telan.werewolf.game.domain.role;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class BaseRole {
    private String name;
    private String desc;
    private int role;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
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
}

