package com.telan.werewolf.game.domain;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by weiwenliang on 2017/6/15.
 */
public class Visiablity {

    public static Visiablity ALL;

    private boolean isAll;

    private List<Integer> visableRoleType;

    private List<Long> visablePlayer;

    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean all) {
        isAll = all;
    }

    public List<Integer> getVisableRoleType() {
        return visableRoleType;
    }

    public void setVisableRoleType(List<Integer> visableRoleType) {
        this.visableRoleType = visableRoleType;
    }

    public List<Long> getVisablePlayer() {
        return visablePlayer;
    }

    public void setVisablePlayer(List<Long> visablePlayer) {
        this.visablePlayer = visablePlayer;
    }

    static {
        ALL = new Visiablity();
        ALL.setAll(true);
    }

    public boolean isVisiable(Player player) {
        if(isAll) {
            return true;
        }
        if(!CollectionUtils.isEmpty(visablePlayer)) {
            if(visablePlayer.contains(player.getId())) {
                return true;
            }
        }
        if(!CollectionUtils.isEmpty(visableRoleType)) {
            if(visableRoleType.contains(player.getRole().getRole())) {
                return true;
            }
        }
        return false;
    }
}
