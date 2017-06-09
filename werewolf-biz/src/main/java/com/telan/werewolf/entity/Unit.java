package com.telan.werewolf.entity;

import com.telan.werewolf.entity.position.Coordinate;

/**
 * Created by weiwenliang on 2017/6/7.
 */
public class Unit {
    private Coordinate currentPosition;

    private long mass; //单位g

    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Coordinate currentPosition) {
        this.currentPosition = currentPosition;
    }

    public long getMass() {
        return mass;
    }

    public void setMass(long mass) {
        this.mass = mass;
    }
}
