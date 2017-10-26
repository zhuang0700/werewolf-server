package com.telan.weixincenter.controller;

import com.telan.weixincenter.domain.user.UserSessionInfo;
import com.telan.weixincenter.event.EventAcceptor;
import com.telan.weixincenter.manager.WxSessionManager;
import com.telan.weixincenter.utils.SignUtil;
import com.telan.weixincenter.utils.SpringHttpHolder;

import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.enums.BaseStatus;
import com.telan.werewolf.manager.MemSessionManager;
import com.telan.werewolf.manager.UserManager;
import org.apache.commons.io.IOUtils;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/weixin")
public class WeiXinMainController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(WeiXinMainController.class);
	@Autowired
	private EventAcceptor eventAcceptor;
	
//	@Autowired
//	private WeixinApiUrlHolder weixinApiUrlHolder;
	@Autowired
	private WxSessionManager wxSessionManager;
	@Autowired
	private MemSessionManager memSessionManager;
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value = "/main", method=RequestMethod.GET )
	public void checkSignature(String signature,String timestamp,String nonce,String echostr,ModelMap modelMap) throws IOException
	{
		HttpServletResponse response = SpringHttpHolder.getResponse();
		PrintWriter out = response.getWriter();
		
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			LOGGER.info("接口检测校验成功！");
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	@RequestMapping(value = "/main", method=RequestMethod.POST )
	public void processRequest() throws Exception
	{    
//		System.out.println(weixinApiUrlHolder.getAccessTokenUrl());
		HttpServletRequest request = SpringHttpHolder.getRequest();
		HttpServletResponse response = SpringHttpHolder.getResponse();
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		InputStream input = request.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		IOUtils.copy(input, output);
		
		//获取请求的内容
		String requestContent = new String(output.toByteArray(),"UTF-8");
		
		LOGGER.debug("requestContent={}",requestContent);
		//处理请求
		String responseContent = eventAcceptor.doAccept(requestContent);
		LOGGER.info("responseContent={}",responseContent);
		
		PrintWriter out = response.getWriter();
		//写入响应内容到response中
		out.print(responseContent);
		out.close();
	}

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
	@RequestMapping(value = "/login", method=RequestMethod.POST )
	public Map login() throws IOException
	{
		HttpServletRequest request = SpringHttpHolder.getRequest();
		HttpServletResponse response = SpringHttpHolder.getResponse();
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

//		InputStream input = request.getInputStream();
//		Map requestMap = request.getParameterMap();
		String encryptedData = request.getParameter("encryptedData");
		String iv = request.getParameter("iv");
		String code = request.getParameter("code");
		LOGGER.info("encryptedData={}, iv={}, code={}",encryptedData, iv, code);

		//登录凭证不能为空
		Map map = new HashMap();
		if (code == null || code.length() == 0) {
			map.put("status", 0);
			map.put("msg", "code 不能为空");
			return map;
		}
		//处理请求
		UserSessionInfo userSessionInfo = wxSessionManager.getSessionInfo(encryptedData, iv, code);

		if(userSessionInfo == null) {
			LOGGER.error("wxSessionManager.getSessionInfo(encryptedData, iv, code); failed.");
			map.put("status", 0);
			map.put("msg", "解析用户信息失败");
			return map;
		}
		String sessionKey = userSessionInfo.getSessionKey();
		String unionId = userSessionInfo.getUnionId();
		String openId = userSessionInfo.getOpenId();
		UserDO userDO = new UserDO();
		userDO.setSessionKey(sessionKey);
		userDO.setStatus(BaseStatus.AVAILABLE.getType());
		userDO.setGmtCreated(new Date());
		userDO.setGmtModified(new Date());
		userDO.setNick(userSessionInfo.getNickName());
		userDO.setAvatar(userSessionInfo.getAvatarUrl());
		userDO.setUnionId(unionId);
		userDO.setOpenId(openId);
		String werewolfSessionKey = userManager.login(sessionKey, userDO);
//
//		PrintWriter out = response.getWriter();
//		//写入响应内容到response中
//		out.print(responseContent);
//		out.close();
		map.put("userInfo", userSessionInfo);
		map.put("thirdSessionKey", werewolfSessionKey);
		map.put("msg", "success");
		map.put("status", 1);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/encypt", method=RequestMethod.POST )
	public Map encypt() throws IOException
	{
		HttpServletRequest request = SpringHttpHolder.getRequest();
		HttpServletResponse response = SpringHttpHolder.getResponse();
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

//		InputStream input = request.getInputStream();
//		Map requestMap = request.getParameterMap();
		String encryptedData = request.getParameter("encryptedData");
		String iv = request.getParameter("iv");
		String code = request.getParameter("code");
		LOGGER.info("encryptedData={}, iv={}, code={}",encryptedData, iv, code);

		//处理请求
		UserSessionInfo userSessionInfo = wxSessionManager.getSessionInfo(encryptedData, iv, code);
		Map map = new HashMap();
		if(userSessionInfo == null) {
			LOGGER.error("wxSessionManager.getSessionInfo(encryptedData, iv, code); failed.");
			map.put("status", 0);
			map.put("msg", "解析用户信息失败");
			return map;
		}
		String sessionKey = userSessionInfo.getSessionKey();
		String unionId = userSessionInfo.getUnionId();
		String openId = userSessionInfo.getOpenId();
		UserDO userDO = new UserDO();
		userDO.setSessionKey(sessionKey);
		userDO.setStatus(BaseStatus.AVAILABLE.getType());
		userDO.setGmtCreated(new Date());
		userDO.setGmtModified(new Date());
		userDO.setNick(userSessionInfo.getNickName());
		userDO.setAvatar(userSessionInfo.getAvatarUrl());
		userDO.setUnionId(unionId);
		userDO.setOpenId(openId);
		String werewolfSessionKey = userManager.login(sessionKey, userDO);
//
//		PrintWriter out = response.getWriter();
//		//写入响应内容到response中
//		out.print(responseContent);
//		out.close();
		map.put("userInfo", userSessionInfo);
		map.put("thirdSessionKey", werewolfSessionKey);
		map.put("msg", "success");
		map.put("status", 1);
		return map;
	}
}
