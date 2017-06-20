package com.telan.werewolf.game.manager;

import com.telan.werewolf.game.domain.GameConfig;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.domain.role.BaseRole;
import com.telan.werewolf.game.domain.role.VillagerRole;
import com.telan.werewolf.game.enums.RoleType;
import com.telan.werewolf.factory.RoleFactory;
import com.telan.werewolf.utils.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/6/6.
 */
public class RoleEngine {

    public static List<BaseRole> initRoleList(int playerNum) {
        List<BaseRole> roleList = new ArrayList<>();
        if(playerNum < 3) {
            RoleType[] roleTypes_2 = {RoleType.WOLF,RoleType.WITCH};
            return formRoleList(roleTypes_2);
        }
        switch (playerNum) {
            case 6:
                RoleType[] roleTypes_6 = {RoleType.WOLF,RoleType.WOLF,RoleType.SEER,RoleType.VILLAGER,RoleType.VILLAGER,RoleType.VILLAGER};
                return formRoleList(roleTypes_6);
            default:
                RoleType[] roleTypes_default = {RoleType.WOLF,RoleType.WOLF,RoleType.WOLF,RoleType.WITCH,RoleType.HUNTER,RoleType.SEER,RoleType.VILLAGER,RoleType.VILLAGER,RoleType.VILLAGER};
                return formRoleList(roleTypes_default);
        }
    }

    public static void allocateRole(List<BaseRole> roleList, Map<Long, Player> playerMap) {
        int playerNums = playerMap.size();
        int roles = roleList.size();
        List<Integer> randomSeq = RandomUtil.generateSeq(0, roles - 1, playerNums);
        int i = 0;
        for(Long playId : playerMap.keySet()) {
            Player player = playerMap.get(playId);
            if(i++ < randomSeq.size()) {
                player.setRole(roleList.get(i));
            } else {
                player.setRole(new VillagerRole());
            }
        }
    }

    private static List<BaseRole> formRoleList(RoleType[] roleTypes) {
        List<BaseRole> baseRoleList = new ArrayList<>();
        for(RoleType roleType : roleTypes) {
            baseRoleList.add(RoleFactory.createRoleById(roleType.getType()));
        }
        return baseRoleList;
    }

}
