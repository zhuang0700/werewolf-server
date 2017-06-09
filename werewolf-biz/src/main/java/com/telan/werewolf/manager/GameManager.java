package com.telan.werewolf.manager;

import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.enums.BaseStatus;
import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.enums.GameStatus;
import com.telan.werewolf.mapper.GameDOMapper;
import com.telan.werewolf.result.WeBaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by weiwenliang on 15/11/3.
 *
 */
public class GameManager {
	@Autowired
	private GameDOMapper gameDOMapper;

	private final static Logger log	= LoggerFactory.getLogger(GameManager.class);

	public WeBaseResult<GameDO> getGameById(long id) {
		WeBaseResult<GameDO> result = new WeBaseResult<GameDO>();
		if (id <= 0) {
			result.setErrorCode(WeErrorCode.PARAM_ERROR);
			return result;
		}
		try {
			GameDO gameDO = gameDOMapper.selectByPrimaryKey(id);
			result.setValue(gameDO);
		} catch (Exception e) {
			log.error("gameDOMapper.selectByPrimaryKey(id); exception " + id, e);
			result.setErrorCode(WeErrorCode.READ_DB_ERROR);
		}
		return result;
	}

	public Boolean updateGameById(GameDO gameDO) throws Exception {
		try {
			int c = gameDOMapper.updateByPrimaryKeySelective(gameDO);
			return c == 1;
		} catch (Exception e) {
			log.error("int c = gameDOMapper.updateByPrimaryKey(gameDO); exception,gameDO:" + gameDO, e);
			throw e;
		}
	}

	public Boolean deleteGameById(GameDO gameDO) {
		try {
			if (gameDO.getStatus() == BaseStatus.DELETED.getType())
				return true;
			gameDO.setStatus(BaseStatus.DELETED.getType());
			int c = gameDOMapper.deleteByPrimaryKey(gameDO.getId());
			return c == 1;
		} catch (Exception e) {
			log.error("gameDOMapper.updateByPrimaryKey(gameDO); exception,gameDO:" + gameDO, e);
		}
		return false;
	}

	public GameDO insertGame(GameDO gameDO) {
		gameDO.setStatus(GameStatus.CREATE.getType());
		Date now = new Date();
		gameDO.setGmtCreated(now);
		gameDO.setGmtModified(now);
		int row = gameDOMapper.insert(gameDO);
		return gameDO;
	}

}
