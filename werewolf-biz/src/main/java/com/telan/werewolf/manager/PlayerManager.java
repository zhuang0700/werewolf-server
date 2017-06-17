package com.telan.werewolf.manager;

import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.enums.BaseStatus;
import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.enums.PlayerStatus;
import com.telan.werewolf.mapper.PlayerDOMapper;
import com.telan.werewolf.query.PlayerPageQuery;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WePageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by weiwenliang on 15/11/3.
 *
 */
public class PlayerManager {
	@Autowired
	private PlayerDOMapper playerDOMapper;

	private final static Logger log	= LoggerFactory.getLogger(PlayerManager.class);

	public WeBaseResult<PlayerDO> getPlayerById(long id) {
		WeBaseResult<PlayerDO> result = new WeBaseResult<PlayerDO>();
		if (id <= 0) {
			result.setErrorCode(WeErrorCode.PARAM_ERROR);
			return result;
		}
		try {
			PlayerDO playerDO = playerDOMapper.selectByPrimaryKey(id);
			result.setValue(playerDO);
		} catch (Exception e) {
			log.error("playerDOMapper.selectByPrimaryKey(id); exception " + id, e);
			result.setErrorCode(WeErrorCode.READ_DB_ERROR);
		}
		return result;
	}

	public List<PlayerDO> pageQuery(PlayerPageQuery pageQuery) {
		return playerDOMapper.pageQuery(pageQuery);
	}

	public WeBaseResult<PlayerDO> findCurrentPlayerByUserId(long userId) {
		WeBaseResult<PlayerDO> result = new WeBaseResult<PlayerDO>();
		if (userId <= 0) {
			result.setErrorCode(WeErrorCode.PARAM_ERROR);
			return result;
		}
		try {
			PlayerPageQuery playerPageQuery = new PlayerPageQuery();
			playerPageQuery.setUserId(userId);
			playerPageQuery.setGameStatus(BaseStatus.AVAILABLE.getType());
			List<PlayerDO> playerDOs = playerDOMapper.pageQuery(playerPageQuery);
			result.setValue(playerDOs.get(0));
		} catch (Exception e) {
			log.error("playerDOMapper.pageQuery(id); exception " + userId, e);
			result.setErrorCode(WeErrorCode.READ_DB_ERROR);
		}
		return result;
	}

	public Boolean updatePlayerById(PlayerDO playerDO){
		try {
			int c = playerDOMapper.updateByPrimaryKeySelective(playerDO);
			return c == 1;
		} catch (Exception e) {
			log.error("int c = playerDOMapper.updateByPrimaryKey(playerDO); exception,playerDO:" + playerDO, e);
			return false;
		}
	}

	public Boolean deletePlayerById(PlayerDO playerDO) {
		try {
			if (playerDO.getStatus() == BaseStatus.DELETED.getType())
				return true;
			playerDO.setStatus(BaseStatus.DELETED.getType());
			int c = playerDOMapper.deleteByPrimaryKey(playerDO.getId());
			return c == 1;
		} catch (Exception e) {
			log.error("playerDOMapper.updateByPrimaryKey(playerDO); exception,playerDO:" + playerDO, e);
		}
		return false;
	}

	public PlayerDO insertPlayer(PlayerDO playerDO) {
		playerDO.setStatus(PlayerStatus.CREATE.getType());
		playerDO.setGameStatus(BaseStatus.AVAILABLE.getType());
		Date now = new Date();
		playerDO.setGmtCreated(now);
		playerDO.setGmtModified(now);
		int row = playerDOMapper.insert(playerDO);
		return playerDO;
	}

}
