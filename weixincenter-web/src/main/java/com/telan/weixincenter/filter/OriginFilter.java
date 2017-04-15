package com.telan.weixincenter.filter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author create by xinzhanguo on 2016/06/15
 * @Description
 */
public class OriginFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(OriginFilter.class);
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        //解决跨域问题
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

//        String referer = getReferer(httpRequest) ;
//        httpResponse.addHeader("Access-Control-Allow-Origin", referer);
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.addHeader("Access-Control-Allow-Headers", "application/json; charset=UTF-8");
        httpResponse.addHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST");
        httpResponse.addHeader("Allow", "OPTIONS,GET,POST");

        chain.doFilter(httpRequest, httpResponse);


    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    private String getReferer(HttpServletRequest request){
        String referer = request.getHeader("Referer");
        if(!StringUtils.isEmpty(referer)){
            String regex = "http://[^/]*";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(referer);
            if(m.find()){
                String prefixReferer = m.group(0);
                logger.info("getReferer prefixReferer="+prefixReferer);
                return prefixReferer;

            }
//            logger.info("getReferer prefixReferer=*");
        }
        return "*";
    }
}
