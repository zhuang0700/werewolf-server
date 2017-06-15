package com.telan.werewolf.game.domain;

import com.telan.werewolf.game.enums.RoleType;

import java.util.Date;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class GameMsg {
    private int type;

    private int subType;

    private Visiablity visiablity;

    private Date actionTime;

    private List<Object> objects;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Visiablity getVisiablity() {
        return visiablity;
    }

    public void setVisiablity(Visiablity visiablity) {
        this.visiablity = visiablity;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }
}
