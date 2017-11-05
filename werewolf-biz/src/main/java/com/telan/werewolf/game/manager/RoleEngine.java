package com.telan.werewolf.game.manager;

import com.telan.werewolf.game.domain.GameConfig;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.domain.role.BaseRole;
import com.telan.werewolf.game.domain.role.VillagerRole;
import com.telan.werewolf.game.enums.RoleType;
import com.telan.werewolf.factory.RoleFactory;
import com.telan.werewolf.utils.RandomUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/6/6.
 */
public class RoleEngine {

    public static List<BaseRole> initRoleList(GameInfo gameInfo) {
        int playerNum = gameInfo.getPlayerNum();
        List<Integer> roleList = new ArrayList<>();
        if(gameInfo.getGameConfig().isCustomRoleList() && !CollectionUtils.isEmpty(gameInfo.getGameConfig().getRoleNum())) {
            Map<Integer, Integer> roleNumMap = gameInfo.getGameConfig().getRoleNum();
            List<Integer> roleTypeList = new ArrayList<>();
            for(Integer role : roleNumMap.keySet()) {
                for(int i=0;i<roleNumMap.get(role);i++){
                    roleList.add(role);
                }
            }
            return formRoleList(new int[0]);
        }
        switch (playerNum) {
            case 1:
            case 2:
                int[] roleTypes_2 = {RoleType.WOLF.getType(),RoleType.WITCH.getType()};
                return formRoleList(roleTypes_2);
            case 3:
                int[] roleTypes_3 = {RoleType.WOLF.getType(),RoleType.WITCH.getType(),RoleType.SEER.getType()};
                return formRoleList(roleTypes_3);
            case 4:
            case 5:
            case 6:
                int[] roleTypes_6 = {RoleType.WOLF.getType(),RoleType.WOLF.getType(),RoleType.SEER.getType(),RoleType.WITCH.getType(),RoleType.VILLAGER.getType(),RoleType.VILLAGER.getType()};
                return formRoleList(roleTypes_6);
            case 7:
            case 8:
            case 9:
            default:
                int[] roleTypes_default = {RoleType.WOLF.getType(),RoleType.WOLF.getType(),RoleType.WOLF.getType(),RoleType.WITCH.getType(),RoleType.HUNTER.getType(),RoleType.SEER.getType(),RoleType.VILLAGER.getType(),RoleType.VILLAGER.getType(),RoleType.VILLAGER.getType()};
                return formRoleList(roleTypes_default);
        }
    }

    public static void allocateRole(List<BaseRole> roleList, Map<Long, Player> playerMap) {
        int playerNums = playerMap.size();
        int roles = roleList.size();
        List<Integer> randomSeq = RandomUtil.generateSeq(0, roles - 1, roleList.size());
        int i = 0;
        for(Long playId : playerMap.keySet()) {
            Player player = playerMap.get(playId);
            if(i < randomSeq.size()) {
                player.setRole(roleList.get(randomSeq.get(i)));
            } else {
                player.setRole(new VillagerRole());
            }
            i++;
        }
    }

    private static List<BaseRole> formRoleList(int[] roleTypes) {
        List<BaseRole> baseRoleList = new ArrayList<>();
        for(int roleType : roleTypes) {
            baseRoleList.add(RoleFactory.createRoleById(roleType));
        }
        return baseRoleList;
    }

}
