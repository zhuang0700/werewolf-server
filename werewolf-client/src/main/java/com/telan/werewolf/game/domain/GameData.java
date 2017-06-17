package com.telan.werewolf.game.domain;

import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.game.domain.record.BaseRecord;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weiwenliang on 2017/6/16.
 */
public class GameData implements Serializable {
    private static final long serialVersionUID = -4619274696344227566L;
    public List<BaseRecord> recordList;

    public GameDO gameDO;

    public List<PlayerDO> playerDOList;

    public List<Action> actionList;
}
