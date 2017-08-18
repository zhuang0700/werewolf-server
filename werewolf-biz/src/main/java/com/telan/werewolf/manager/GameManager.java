package com.telan.werewolf.manager;

import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.enums.BaseStatus;
import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.game.enums.GameStatus;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.mapper.GameDOMapper;
import com.telan.werewolf.query.GamePageQuery;
import com.telan.werewolf.query.GameQueryOption;
import com.telan.werewolf.query.PlayerPageQuery;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WePageResult;
import com.telan.werewolf.utils.conventor.GameConvertor;
import com.telan.werewolf.utils.conventor.PlayerConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.convert.ListConverter;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by weiwenliang on 15/11/3.
 *
 */
public class GameManager {
	@Autowired
	private GameDOMapper gameDOMapper;

	private final static Logger log	= LoggerFactory.getLogger(GameManager.class);

//	public Map<Long, GameInfo> batchQueryGameInfo(GamePageQuery gamePageQuery, GameQueryOption queryOption) {
//		List<GameDO> gameDOList = gameDOMapper.pageQuery(gamePageQuery);
//		Map<Long, GameInfo> gameInfoMap = GameConvertor.convertGameInfoMap(gameDOList);
//
//		return gameInfoMap;
//	}

	public WePageResult<GameDO> batchQueryGameDO(GamePageQuery gamePageQuery) {
		List<GameDO> gameDOList = gameDOMapper.pageQuery(gamePageQuery);
		WePageResult<GameDO> gameDOWePageResult = new WePageResult<>();
		gameDOWePageResult.setList(gameDOList);
		return gameDOWePageResult;
	}

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

	public Boolean updateGameById(GameDO gameDO) {
		try {
			int c = gameDOMapper.updateByPrimaryKeySelective(gameDO);
			return c == 1;
		} catch (Exception e) {
			log.error("int c = gameDOMapper.updateByPrimaryKey(gameDO); exception,gameDO:" + gameDO, e);
		}
		return false;
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
