package com.telan.werewolf.event.processer;

import com.alibaba.fastjson.JSONObject;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.enums.SocketMsgType;
import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.domain.ActionResult;
import com.telan.werewolf.game.domain.GameInfo;
import com.telan.werewolf.game.domain.PlayerAction;
import com.telan.werewolf.game.param.OperateGameParam;
import com.telan.werewolf.game.process.GameProcessor;
import com.telan.werewolf.manager.MemSessionManager;
import com.telan.werewolf.param.BaseRequestData;
import com.telan.werewolf.param.BaseResponseData;
import com.telan.werewolf.param.GameBaseRequest;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WeResultSupport;
import com.telan.werewolf.utils.ResponseMapUtils;
import com.telan.werewolf.utils.SessionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseProcessor implements EventProcessor {
	private static Logger LOGGER = LoggerFactory.getLogger(BaseProcessor.class);

	@Autowired
	private MemSessionManager memSessionManager;

	@Autowired
	private GameProcessor gameProcessor;

	public BaseResponseData processRequest(BaseRequestData requestMessage) {
		LOGGER.debug("requestMessage={}", JSONObject.toJSONString(requestMessage));

		// 消息类型
		SocketMsgType msgType = SocketMsgType.valueOfCode(requestMessage.getMsgType());

		// 通过sessionId获取UserDO
		String thirdSessionKey = requestMessage.getSessionId();
		UserDO userDO = memSessionManager.getUser(thirdSessionKey);
		if(userDO == null) {
			WeResultSupport resultSupport = new WeResultSupport();
			resultSupport.setErrorCode(WeErrorCode.WEIXIN_LOGIN_ERROR);
			return ResponseMapUtils.getFailResponse(resultSupport);
		}

		GameBaseRequest param = (GameBaseRequest)requestMessage.getMsgData();
		WeBaseResult<GameInfo> result = null;
		BaseResponseData responseData = new BaseResponseData();
		switch (msgType) {
			case START_GAME:
				result = gameProcessor.startGame(userDO.getId(), param.getGameId());
				responseData = ResponseMapUtils.getGameInfoResponse(result, userDO);
				break;
			case GAME_INFO:
				result = gameProcessor.getCurrentGameInfo(userDO.getId());
				responseData = ResponseMapUtils.getGameInfoResponse(result, userDO);
				break;
			case QUIT_GAME:
				OperateGameParam operateGameParam = (OperateGameParam)param;
				result = gameProcessor.quitGame(operateGameParam);
				responseData = ResponseMapUtils.getGameInfoResponse(result, userDO);
				break;
			case PLAYER_ACTION:
				PlayerAction playerAction  = (PlayerAction)param;
				WeBaseResult<ActionResult> actionResult = gameProcessor.playerAction(playerAction);
				responseData = ResponseMapUtils.getActionResultResponse(actionResult, userDO);
				break;
			default:
				break;
		}

		LOGGER.debug("response={}",JSONObject.toJSONString(responseData));
		return responseData;
	}

}
