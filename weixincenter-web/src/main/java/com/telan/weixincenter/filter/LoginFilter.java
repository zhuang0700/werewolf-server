package com.telan.weixincenter.filter;

import com.alibaba.fastjson.JSONObject;
import com.telan.weixincenter.result.WXReturnCode;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.manager.MemSessionManager;
import com.telan.werewolf.utils.SessionHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author create by xinzhanguo on 2016/06/15
 * @Description
 */
public class LoginFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    @Autowired
    MemSessionManager memSessionManager;

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        HttpSession session = httpRequest.getSession();
        String thirdSessionKey = SessionHelper.getSessionKey(httpRequest);
        UserDO userDO = memSessionManager.getUser(thirdSessionKey);
        if(userDO == null) {
            //401未授权访问
            JSONObject responseObject = new JSONObject();
            responseObject.put("status", 0);
            responseObject.put("code", WXReturnCode.WEIXIN_LOGIN_ERROR);
            responseObject.put("msg", WXReturnCode.WEIXIN_LOGIN_ERROR.getDesc());
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.append(responseObject.toJSONString());
            logger.error("login failed. sessionKey={}, response={}", thirdSessionKey, responseObject.toJSONString());
        } else {
            session.setAttribute("user", userDO);
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
