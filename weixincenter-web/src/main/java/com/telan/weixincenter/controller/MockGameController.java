package com.telan.weixincenter.controller;

import com.alibaba.fastjson.JSON;
import com.telan.weixincenter.annotation.LoginRequired;
import com.telan.weixincenter.event.EventAcceptor;
import com.telan.weixincenter.manager.WxSessionManager;
import com.telan.werewolf.utils.ResponseMapUtils;
import com.telan.weixincenter.utils.SpringHttpHolder;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.game.param.CreateGameParam;
import com.telan.werewolf.game.param.OperateGameParam;
import com.telan.werewolf.game.process.GameProcessor;
import com.telan.werewolf.manager.UserManager;
import com.telan.werewolf.result.WeBaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mock")
public class MockGameController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MockGameController.class);
	@Autowired
	private EventAcceptor eventAcceptor;
	@Autowired
	private GameProcessor gameProcessor;
	@Autowired
	private UserManager userManager;
	
//	@Autowired
//	private WeixinApiUrlHolder weixinApiUrlHolder;
	@Autowired
	private WxSessionManager wxSessionManager;

	@RequestMapping(value = "/test", method=RequestMethod.GET )
	public void hello() throws IOException
	{
		HttpServletResponse response = SpringHttpHolder.getResponse();
		PrintWriter out = response.getWriter();

		out.print("hello");
		out.close();
		out = null;
	}

	@ResponseBody
	@RequestMapping(value = "/createGame", method=RequestMethod.GET )
	@LoginRequired
	public Map createGame(CreateGameParam param, ModelMap modelMap) throws IOException
	{
		Map map = new HashMap();
		HttpServletRequest request = SpringHttpHolder.getRequest();
		UserDO userDO = userManager.mockUser(request);
		param.setPlayerNum(7);
		param.setCreator(userDO);
		WeBaseResult<GameInfo> baseResult = gameProcessor.createGame(param);
//		LOGGER.info("create game test, param=" + JSON.toJSONString(param) + ", modelmap=" + JSON.toJSONString(modelMap));
		return ResponseMapUtils.convertGameInfo(baseResult, userDO);
	}

	@ResponseBody
	@RequestMapping(value = "/joinGame", method=RequestMethod.GET )
	@LoginRequired
	public Map joinGame(OperateGameParam param, ModelMap modelMap) throws IOException
	{
		Map map = new HashMap();
		HttpServletRequest request = SpringHttpHolder.getRequest();
		UserDO userDO = userManager.mockUser(request);
		param.setUser(userDO);
		long gameId = 0;
		try {
			gameId = Long.valueOf(request.getParameter("gameId"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		param.setGameId(gameId);
		WeBaseResult<GameInfo> baseResult = gameProcessor.joinGame(param);
//		LOGGER.info("join game test, param=" + JSON.toJSONString(param) + ", modelmap=" + JSON.toJSONString(modelMap));

		if(param.getMockPlayerNum() > 0) {
			List<UserDO> userDOList = userManager.mockUserList(param.getMockPlayerNum());
			for(UserDO mockUser : userDOList) {
				param.setUser(mockUser);
				WeBaseResult<GameInfo> baseMockResult = gameProcessor.joinGame(param);
				LOGGER.info("join game mock user, userDO=" + JSON.toJSONString(mockUser) + ", modelmap=" + JSON.toJSONString(modelMap));
			}
		}
		return ResponseMapUtils.convertGameInfo(baseResult, userDO);
	}

	@ResponseBody
	@RequestMapping(value = "/gameInfo", method=RequestMethod.GET )
	@LoginRequired
	public Map getGameInfo(ModelMap modelMap) throws IOException
	{
		Map map = new HashMap();
		HttpServletRequest request = SpringHttpHolder.getRequest();
		UserDO userDO = userManager.mockUser(request);
		WeBaseResult<GameInfo> baseResult = gameProcessor.getCurrentGameInfo(userDO.getId());
//		LOGGER.info("get game info, result=" + JSON.toJSONString(baseResult));
		if(!baseResult.isSuccess() && baseResult.getErrorCode() == WeErrorCode.NO_ACTIVE_GAME.getErrorCode()) {
			map.put("status", 1);
			map.put("msg", baseResult.getResultMsg());
			map.put("result", null);
			return map;
		}
		return ResponseMapUtils.convertGameInfo(baseResult, userDO);
	}

	@ResponseBody
	@RequestMapping(value = "/startGame", method=RequestMethod.GET )
	@LoginRequired
	public Map startGame(long gameId, ModelMap modelMap) throws IOException
	{
		Map map = new HashMap();
		HttpServletRequest request = SpringHttpHolder.getRequest();
		UserDO userDO = userManager.mockUser(request);
		if(gameId <= 0) {
			try {
				gameId = Long.valueOf(request.getParameter("gameId"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		WeBaseResult<GameInfo> baseResult = gameProcessor.startGame(userDO.getId(), gameId);
//		LOGGER.info("startGame, result=" + JSON.toJSONString(baseResult));
		if(!baseResult.isSuccess() && baseResult.getErrorCode() == WeErrorCode.NO_ACTIVE_GAME.getErrorCode()) {
			map.put("status", 1);
			map.put("msg", baseResult.getResultMsg());
			map.put("result", null);
			return map;
		}
		return ResponseMapUtils.convertGameInfo(baseResult, userDO);
	}

	@ResponseBody
	@RequestMapping(value = "/quitGame", method=RequestMethod.GET )
	@LoginRequired
	public Map quitGame(OperateGameParam param, ModelMap modelMap) throws IOException
	{
		Map map = new HashMap();
		HttpServletRequest request = SpringHttpHolder.getRequest();
		UserDO userDO = userManager.mockUser(request);
		param.setUser(userDO);
		WeBaseResult<GameInfo> baseResult = gameProcessor.quitGame(param);
		//TODO: 测试逻辑
		GameInfo gameInfo = baseResult.getValue();
		for(Player player : gameInfo.getPlayerMap().values()) {
			if(player.getUserId() != userDO.getId()) {
				param.setUser(player.getUserDO());
				gameProcessor.quitGame(param);
			}
		}
//		LOGGER.info("startGame, result=" + JSON.toJSONString(baseResult));
		if(!baseResult.isSuccess() && baseResult.getErrorCode() == WeErrorCode.NO_ACTIVE_GAME.getErrorCode()) {
			map.put("status", 1);
			map.put("msg", baseResult.getResultMsg());
			map.put("result", null);
			return map;
		}
		return ResponseMapUtils.convertGameInfo(baseResult, userDO);
	}

	@ResponseBody
	@RequestMapping(value = "/playerAction", method=RequestMethod.POST )
	public Map playerAction(long fromPlayerId, long toPlayerId, long gameId, int actionType, ModelMap modelMap) throws IOException
	{
		PlayerAction action = new PlayerAction();
		action.fromPlayerId = fromPlayerId;
		action.toPlayerId = toPlayerId;
		action.setGameId(gameId);
		action.actionType = actionType;
		HttpServletRequest request = SpringHttpHolder.getRequest();
		UserDO userDO = userManager.mockUser(request);
		action.setUserDO(userDO);
		WeBaseResult<ActionResult> baseResult = gameProcessor.playerAction(action);
		LOGGER.info("playerAction, result={}", baseResult);
		return ResponseMapUtils.convertActionResult(baseResult, userDO);
	}

	@ResponseBody
	@RequestMapping(value = "/judgeAction", method=RequestMethod.GET )
	@LoginRequired
	public Map judgeAction(long gameId, int actionType, int stageType, ModelMap modelMap) throws IOException
	{
		HttpServletRequest request = SpringHttpHolder.getRequest();
		UserDO userDO = userManager.mockUser(request);
		JudgeAction action = new JudgeAction();
		action.setActionType(actionType);
		action.setStageType(stageType);
		action.setGameId(gameId);
		action.setUserDO(userDO);
		WeBaseResult<GameInfo> baseResult = gameProcessor.judgeAction(action);
//		LOGGER.info("JudgeAction, result={}" , baseResult);
		return ResponseMapUtils.convertGameInfo(baseResult, userDO);
	}
}
