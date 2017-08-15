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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	int gameId = 0;
	try {
		gameId = Integer.valueOf(request.getParameter("gameId"));
	} catch (NumberFormatException e) {
		gameId = 0;
	}
	GameProcessor gameProcessor = (GameProcessor) wac.getBean("gameProcessor");
	MemGameManager memGameManager = (MemGameManager)wac.getBean("memGameManager");
	int code = 0;
	if (gameProcessor == null) {
		code = -1;
	}
	PrintWriter write = response.getWriter();
	//write.println(code);
	JSONArray jsonArray = new JSONArray();

	if(gameId > 0) {
		write.print(JSON.toJSONString(memGameManager.gameMap.get(gameId)));
	} else {
		write.print(JSON.toJSONString(memGameManager.gameMap));
	}

	write.flush();
	write.close();


%>

