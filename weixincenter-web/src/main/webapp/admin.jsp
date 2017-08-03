<%@page import="java.io.PrintWriter"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="com.telan.werewolf.game.process.GameProcessor" %>
<%@ page import="com.telan.werewolf.game.domain.GameInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.telan.werewolf.manager.MemGameManager" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.support.spring.FastJsonJsonView" %>
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
	write.print(JSON.toJSON(memGameManager.gameMap.values()));
	write.flush();
	write.close();


%>

