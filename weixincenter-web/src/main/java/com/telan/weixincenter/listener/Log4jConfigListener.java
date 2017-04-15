package com.telan.weixincenter.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author: wuzhengfei@pajk.cn
 * @date: 2014年10月23日 下午1:43:06
 * 
 */
public class Log4jConfigListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			LogbackWebConfigurer.initLogging(sce.getServletContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LogbackWebConfigurer.shutdownLogging(sce.getServletContext());
	}

}
