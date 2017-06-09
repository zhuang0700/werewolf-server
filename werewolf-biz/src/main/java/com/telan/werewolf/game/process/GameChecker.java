package com.telan.werewolf.game.process;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.telan.werewolf.game.manager.ActionManager;
import com.telan.werewolf.manager.GameManager;
import com.telan.werewolf.manager.MemGameManager;
import com.telan.werewolf.manager.PlayerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by weiwenliang on 15/11/3.
 *
 */
public class GameChecker {
	@Autowired
	private MemGameManager memGameManager;
	@Autowired
	private GameManager gameManager;
	@Autowired
	private ActionManager actionManager;
	@Autowired
	private PlayerManager playerManager;

	private final static Logger log	= LoggerFactory.getLogger(GameChecker.class);

}
