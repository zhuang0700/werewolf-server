package com.telan.werewolf.game.domain;

import com.telan.werewolf.result.WeResultSupport;
import org.springframework.util.CollectionUtils;

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
        finish();
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
