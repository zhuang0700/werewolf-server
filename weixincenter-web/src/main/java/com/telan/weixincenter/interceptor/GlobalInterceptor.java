package com.telan.weixincenter.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.telan.weixincenter.result.WXReturnCode;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.manager.MemSessionManager;
import com.telan.werewolf.utils.SessionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.telan.weixincenter.annotation.LoginRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class GlobalInterceptor extends HandlerInterceptorAdapter {
    private static Logger log=LoggerFactory.getLogger(GlobalInterceptor.class);

    @Autowired
    public MemSessionManager memSessionManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession s=request.getSession();
        //角色权限控制访问
        return loginControl(request, response, handler);
    }
    /**角色权限控制访问*/
    private boolean loginControl(HttpServletRequest request,HttpServletResponse response, Object handler){
        HttpSession session=request.getSession();
        System.out.println(handler.getClass().getName());
        if(handler instanceof HandlerMethod){
            HandlerMethod hm=(HandlerMethod)handler;
            Object target=hm.getBean();
            Class<?> clazz=hm.getBeanType();
            Method m=hm.getMethod();
            try {
                if (clazz!=null && m != null ) {
                    boolean isClzAnnotation= clazz.isAnnotationPresent(LoginRequired.class);
                    boolean isMethondAnnotation=m.isAnnotationPresent(LoginRequired.class);
                    LoginRequired loginRequired=null;
                    //如果方法和类声明中同时存在这个注解，那么方法中的会覆盖类中的设定。
                    if(isMethondAnnotation){
                        loginRequired=m.getAnnotation(LoginRequired.class);
                    }else if(isClzAnnotation){
                        loginRequired=clazz.getAnnotation(LoginRequired.class);
                    }
                    String thirdSessionKey = SessionHelper.getSessionKey();
                    UserDO userDO = memSessionManager.getUser(thirdSessionKey);
                    if(userDO == null) {
                        //401未授权访问
                        JSONObject responseObject = new JSONObject();
                        responseObject.put("status", 0);
                        responseObject.put("code", WXReturnCode.WEIXIN_LOGIN_ERROR.getCode());
                        responseObject.put("msg", WXReturnCode.WEIXIN_LOGIN_ERROR.getDesc());
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter out = response.getWriter();
                        out.append(responseObject.toJSONString());
                        log.error("login failed. sessionKey={}, response={}", thirdSessionKey, responseObject.toJSONString());
                        return false;
                    } else {
                        session.setAttribute("user", userDO);
                    }
                    return true;
                }
            }catch(Exception e){
                log.error("loginControl exception. ", e);
            }
        }
        return true;
    }
 
}