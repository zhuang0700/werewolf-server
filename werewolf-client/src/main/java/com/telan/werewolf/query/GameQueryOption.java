package com.telan.werewolf.query;

import java.util.List;

/**
 * Created by weiwenliang on 2015/11/3.
 *
 */
public class GameQueryOption {

    private static final long serialVersionUID = -3142728607169207697L;

    private boolean needPlayer = true;

    public boolean isNeedPlayer() {
        return needPlayer;
    }

    public void setNeedPlayer(boolean needPlayer) {
        this.needPlayer = needPlayer;
    }
}
