package com.telan.werewolf.controller;

import com.telan.werewolf.event.EventAcceptor;
import com.telan.werewolf.utils.SignUtil;
import com.telan.werewolf.utils.SpringHttpHolder;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@Controller
@RequestMapping("/werewolf")
public class WeiXinMainController {
	private final static Logger LOGGER = LoggerFactory.getLogger(WeiXinMainController.class);
	@Autowired
	private EventAcceptor eventAcceptor;
	
//	@Autowired
//	private WeixinApiUrlHolder weixinApiUrlHolder;
	
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
}
