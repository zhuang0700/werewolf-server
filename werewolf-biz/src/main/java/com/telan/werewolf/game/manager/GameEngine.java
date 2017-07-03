package com.telan.werewolf.game.manager;

import com.telan.werewolf.factory.GameMsgFactory;
import com.telan.werewolf.factory.RoundFactory;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.domain.Round;
import com.telan.werewolf.game.domain.Stage;
import com.telan.werewolf.game.domain.Visibility;
import com.telan.werewolf.game.enums.GameMsgSubType;
import com.telan.werewolf.game.enums.GameStatus;
import com.telan.werewolf.game.enums.RoundStatus;
import org.springframework.util.CollectionUtils;

/**
 * Created by weiwenliang on 17/6/6.
 */
public class GameEngine {
    public static void startGame(GameInfo currentGame){
        currentGame.setGameStatus(GameStatus.PROCESS.getType());
    }

    public static void endGame(GameInfo currentGame) {
        currentGame.setGameStatus(GameStatus.FINISH.getType());
    }
}
