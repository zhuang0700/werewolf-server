package com.telan.werewolf.game.domain;

import com.telan.werewolf.game.enums.VisibilityType;

import java.util.List;

/**
 * Created by weiwenliang on 2017/6/15.
 */
public class Visibility {

    public static Visibility ALL;

    private VisibilityType type;

    private List<Integer> visableRoleType;

    private List<Long> visablePlayer;

    //默认开启，记录将被加入游戏记录保存
    private boolean needSave = true;

    public VisibilityType getType() {
        return type;
    }

    public void setType(VisibilityType type) {
        this.type = type;
    }

    public List<Integer> getVisableRoleType() {
        return visableRoleType;
    }

    public void setVisableRoleType(List<Integer> visableRoleType) {
        this.type = VisibilityType.BY_ROLE;
        this.visableRoleType = visableRoleType;
    }

    public List<Long> getVisablePlayer() {
        return visablePlayer;
    }

    public void setVisablePlayer(List<Long> visablePlayer) {
        this.type = VisibilityType.BY_PLAYER;
        this.visablePlayer = visablePlayer;
    }

    public boolean isNeedSave() {
        return needSave;
    }

    public void setNeedSave(boolean needSave) {
        this.needSave = needSave;
    }

    static {
        ALL = new Visibility();
        ALL.setType(VisibilityType.ALL);
    }

    public boolean isVisiable(Player player) {
        if(type == null) {
            return false;
        }
        switch (type) {
            case ALL:
                return true;
            case BY_PLAYER:
                if(visablePlayer.contains(player.getId())) {
                    return true;
                }
                return false;
            case BY_ROLE:
                if(visableRoleType.contains(player.getRole().getRole())) {
                    return true;
                }
                return false;
        }
        return false;
    }
}
