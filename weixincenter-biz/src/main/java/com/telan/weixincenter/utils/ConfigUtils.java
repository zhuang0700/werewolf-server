package com.telan.weixincenter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
	private static final Properties CONFIGS = new Properties();
	private final static Logger LOGGER = LoggerFactory.getLogger(ConfigUtils.class);
	private final static String DEFAULT_CONFIG_FILE= "/application.properties";

	static {
		InputStream resourceAsStream = ConfigUtils.class.getResourceAsStream(DEFAULT_CONFIG_FILE);
		try {
			CONFIGS.load(resourceAsStream);
		} catch (Exception e) {
			LOGGER.error("error to load config files.",e);
		}
	}

	/**
	 * 获取回调域名
	 * 
	 * @return
	 */
	public static String getCallbackDomain() {
		return CONFIGS.getProperty("sys.basePathUrl");
	}

	/**
	 * 获取图片服务器地址
	 * 
	 * @return
	 */
	public static String getImagePath() {
		return CONFIGS.getProperty("sys.imagePath");
	}

	public static String getOauthVisitUserId() {
		return CONFIGS.getProperty("sys.oAuthVisitUserId");
	}

}
