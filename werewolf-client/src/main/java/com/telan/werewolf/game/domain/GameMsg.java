package com.telan.werewolf.game.domain;

import java.util.Date;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class GameMsg {
    private int type;

    private int subType;

    private Visibility visibility;

    private Date actionTime;

    private Object[] objects;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }
}
