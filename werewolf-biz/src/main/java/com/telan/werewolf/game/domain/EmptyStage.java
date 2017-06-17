package com.telan.werewolf.game.domain;

import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.enums.ActionType;
import com.telan.werewolf.game.enums.PlayerStatus;
import com.telan.werewolf.game.enums.StageStatus;
import com.telan.werewolf.result.WeResultSupport;
import com.telan.werewolf.utils.ActionUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 17/5/15.
 */
public class EmptyStage extends Stage {

    @Override
    public boolean checkStageUpdate(Stage prevStage) {
        if(CollectionUtils.isEmpty(before)){
            return true;
        }
        for(Stage st : before) {
            if(!st.isFinish()){
                return false;
            }
        }
        //all finished
        return true;
    }

    @Override
    public void roleStart() {
        end();
    }

    @Override
    public void roleWaitingAction() {
        return;
    }

    @Override
    public void roleAnalyse() {

    }

    @Override
    public void roleFinish() {
    }

    @Override
    public WeResultSupport userAction(Player player, PlayerAction action){
        return null;
    }

    @Override
    public WeResultSupport roleUserAction(Player player, PlayerAction action) {
        return null;
    }

}
