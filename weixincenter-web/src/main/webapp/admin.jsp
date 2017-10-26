<%@page import="java.io.PrintWriter"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="com.telan.werewolf.game.process.GameProcessor" %>
<%@ page import="com.telan.werewolf.game.domain.GameInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.telan.werewolf.manager.MemGameManager" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.support.spring.FastJsonJsonView" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.parser.ParserConfig" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.telan.werewolf.domain.UserDO" %>
<%@ page import="com.telan.werewolf.result.WeBaseResult" %>
<%@ page import="com.telan.werewolf.mapper.UserDOMapper" %>
<%@ page import="com.telan.werewolf.manager.UserManager" %>
<%@ page import="com.telan.werewolf.game.param.OperateGameParam" %>
<%@ page import="com.telan.werewolf.game.domain.PlayerAction" %>
<%@ page import="com.telan.weixincenter.utils.SpringHttpHolder" %>
<%@ page import="com.telan.werewolf.game.domain.ActionResult" %>
<%@ page import="com.telan.werewolf.game.enums.ActionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	long gameId = 0, fromId= 3, toId=2;
	String actionType = "";
	PrintWriter write = response.getWriter();
	try {
		gameId = Long.valueOf(request.getParameter("gameId"));
	} catch (NumberFormatException e) {
	}
	try {
		actionType = request.getParameter("action");
	} catch (Exception e) {
		e.printStackTrace();
	}
	try {
		fromId = Long.valueOf(request.getParameter("from"));
	} catch (NumberFormatException e) {
		e.printStackTrace();
	}
	try {
		toId = Long.valueOf(request.getParameter("to"));
	} catch (NumberFormatException e) {
		e.printStackTrace();
	}

	GameProcessor gameProcessor = (GameProcessor) wac.getBean("gameProcessor");
	MemGameManager memGameManager = (MemGameManager)wac.getBean("memGameManager");
	UserManager userManager = (UserManager)wac.getBean("userManager");

	int code = 0;
	if (gameProcessor == null) {
		code = -1;
	}
	JSONArray jsonArray = new JSONArray();
	if(StringUtils.isBlank(actionType)) {
		GameInfo gameInfo = memGameManager.getGame(gameId);
		Map playerMap = memGameManager.playerMap;
		if(gameId > 0) {
			write.print(JSON.toJSONString(gameInfo));
		} else {
			write.print(JSON.toJSONString(memGameManager.gameMap));
		}
		write.print(JSON.toJSONString(playerMap));
		write.flush();
		write.close();
	} else {
		if(actionType.equals("wolfAction")) {
			PlayerAction action = new PlayerAction();
			action.fromPlayerId = fromId > 0?fromId:3;
			action.toPlayerId = toId > 0?toId:2;
			action.setGameId(gameId);
			action.actionType = ActionType.KILL.getType();
			UserDO userDO = userManager.mockUser(request);
			action.setUserDO(userDO);
			WeBaseResult<ActionResult> baseResult = gameProcessor.playerAction(action);
			write.print(baseResult.isSuccess());
			write.print(baseResult.getResultMsg());
			write.flush();
			write.close();
		}
		if(actionType.equals("witchAction")) {
			PlayerAction action = new PlayerAction();
			action.fromPlayerId = fromId > 0?fromId:3;
			action.toPlayerId = toId > 0?toId:2;
			action.setGameId(gameId);
			action.actionType = ActionType.SAVE.getType();
			action.msg = "YES";
			UserDO userDO = userManager.mockUser(request);
			action.setUserDO(userDO);
			WeBaseResult<ActionResult> baseResult = gameProcessor.playerAction(action);
			write.print(baseResult.isSuccess());
			write.print(baseResult.getResultMsg());
			write.flush();
			write.close();
		}
		if(actionType.equals("seerAction")) {
			PlayerAction action = new PlayerAction();
			action.fromPlayerId = fromId > 0?fromId:3;
			action.toPlayerId = toId > 0?toId:2;
			action.setGameId(gameId);
			action.actionType = ActionType.SEE.getType();
			UserDO userDO = userManager.mockUser(request);
			action.setUserDO(userDO);
			WeBaseResult<ActionResult> baseResult = gameProcessor.playerAction(action);
			write.print(baseResult.isSuccess());
			write.print(baseResult.getResultMsg());
			write.flush();
			write.close();
		}
		if(actionType.equals("sheriffAction")) {
			PlayerAction action = new PlayerAction();
			action.fromPlayerId = fromId > 0?fromId:3;
			action.toPlayerId = toId > 0?toId:2;
			action.setGameId(gameId);
			action.actionType = ActionType.RUN_SHERIFF.getType();
			UserDO userDO = userManager.mockUser(request);
			action.setUserDO(userDO);
			WeBaseResult<ActionResult> baseResult = gameProcessor.playerAction(action);
			write.print(baseResult.isSuccess());
			write.print(baseResult.getResultMsg());
			write.flush();
			write.close();
		}
		if(actionType.equals("voteAction")) {
			PlayerAction action = new PlayerAction();
			action.fromPlayerId = fromId > 0?fromId:3;
			action.toPlayerId = toId > 0?toId:2;
			action.setGameId(gameId);
			action.actionType = ActionType.VOTE.getType();
			UserDO userDO = userManager.mockUser(request);
			action.setUserDO(userDO);
			WeBaseResult<ActionResult> baseResult = gameProcessor.playerAction(action);
			write.print(baseResult.isSuccess());
			write.print(baseResult.getResultMsg());
			write.flush();
			write.close();
		}
		if(actionType.equals("addPlayer")) {
			OperateGameParam param = new OperateGameParam();
			UserDO userDO = userManager.mockUser(request);
			param.setUser(userDO);
			param.setGameId(gameId);
			WeBaseResult<GameInfo> baseResult = gameProcessor.joinGame(param);
			write.print(baseResult.isSuccess());
			write.print(baseResult.getResultMsg());
			write.flush();
			write.close();
		}
	}

%>

