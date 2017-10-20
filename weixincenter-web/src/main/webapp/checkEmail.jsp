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
<%@ page import="com.telan.weixincenter.utils.EmailUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

	PrintWriter write = response.getWriter();
	EmailUtils.receive();

%>

