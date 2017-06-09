package com.telan.weixincenter.controller;

import com.alibaba.fastjson.JSON;
import com.telan.weixincenter.annotation.LoginRequired;
import com.telan.weixincenter.core.util.HttpUtils;
import com.telan.weixincenter.event.EventAcceptor;
import com.telan.weixincenter.manager.WxSessionManager;
import com.telan.weixincenter.utils.ResponseMapUtils;
import com.telan.weixincenter.utils.SignUtil;
import com.telan.weixincenter.utils.SpringHttpHolder;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.param.CreateGameParam;
import com.telan.werewolf.game.param.JoinGameParam;
import com.telan.werewolf.game.process.GameInfo;
import com.telan.werewolf.game.process.GameProcessor;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.utils.SessionHelper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/werewolf")
@LoginRequired
public class GameController {
	private final static Logger LOGGER = LoggerFactory.getLogger(GameController.class);
	@Autowired
	private EventAcceptor eventAcceptor;
	@Autowired
	private GameProcessor gameProcessor;
	
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
	@RequestMapping(value = "/createGame", method=RequestMethod.POST )
	@LoginRequired
	public Map createGame(@RequestBody CreateGameParam param, ModelMap modelMap) throws IOException
	{
		Map map = new HashMap();
		UserDO userDO = SessionHelper.getUser();
		param.setCreator(userDO);
		WeBaseResult<GameInfo> baseResult = gameProcessor.createGame(param);
		LOGGER.info("create game test, param=" + JSON.toJSONString(param) + ", modelmap=" + JSON.toJSONString(modelMap));
		return ResponseMapUtils.convertWeBaseResultToMap(baseResult);
	}

	@ResponseBody
	@RequestMapping(value = "/joinGame", method=RequestMethod.POST )
	@LoginRequired
	public Map joinGame(JoinGameParam param, ModelMap modelMap) throws IOException
	{
		Map map = new HashMap();
		WeBaseResult<GameInfo> baseResult = gameProcessor.joinGame(param);
		LOGGER.info("join game test, param=" + JSON.toJSONString(param) + ", modelmap=" + JSON.toJSONString(modelMap));
		return ResponseMapUtils.convertWeBaseResultToMap(baseResult);
	}
}
