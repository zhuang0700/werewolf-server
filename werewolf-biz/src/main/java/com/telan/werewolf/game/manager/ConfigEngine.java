package com.telan.werewolf.game.manager;

import com.telan.werewolf.game.domain.GameConfig;
import com.telan.werewolf.game.vo.UserGameConfig;

/**
 * Created by weiwenliang on 17/6/6.
 */
public class ConfigEngine {
    public static GameConfig convertGameConfig(GameConfig oldConfig, UserGameConfig userGameConfig) {
        oldConfig.setVictoryRule(userGameConfig.getVictoryRule());
        oldConfig.setRoleNum(userGameConfig.getRoleNum());
        oldConfig.setCustomRoleList(userGameConfig.isCustomRoleList());
        return oldConfig;
    }
}
