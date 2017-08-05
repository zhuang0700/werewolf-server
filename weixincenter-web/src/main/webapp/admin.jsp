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
	GameProcessor gameProcessor = (GameProcessor) wac.getBean("gameProcessor");
	MemGameManager memGameManager = (MemGameManager)wac.getBean("memGameManager");
	int code = 0;
	if (gameProcessor == null) {
		code = -1;
	}
	PrintWriter write = response.getWriter();
	//write.println(code);
	JSONArray jsonArray = new JSONArray();

	write.print(JSON.toJSONString(memGameManager.gameMap));
	for(GameInfo gameInfo : memGameManager.gameMap.values()) {
		jsonArray.add(JSON.toJSONString(gameInfo));
//		write.print(JSON.toJSONString(gameInfo));
	}
//	write.print(jsonArray.toJSONString());

	write.flush();
	write.close();


%>

