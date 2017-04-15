//package com.yimayhd.weixincenter.utils;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//public class SysServiceSingleton {
//
//	private static final Logger LOGGER = LoggerFactory
//			.getLogger(SysServiceSingleton.class);
//
//	private static ApplicationContext context;
//	
//	private static String code = null;
//	private static String imagePath = null;
//	private static String securityUrl = null;
//	private static String adminIndexUrl = null;
//	private static String basePathUrl = null;
//	private static String oAuthVisitUserId = null;
//	/**
//	 * 商户号.
//	 */
//	private static String mchId = null;
//
//	public static String getCode() {
//		if(null == code){
//			InputStream resourceAsStream = SysServiceSingleton.class.getResourceAsStream("/application.properties");
//			Properties pps = new Properties();
//			try {
//				pps.load(resourceAsStream);
//				code = pps.getProperty("sys.code");
//			} catch (FileNotFoundException e) {
//				LOGGER.error(e.getMessage(), e);
//			} catch (IOException e) {
//				LOGGER.error(e.getMessage(), e);
//			}
//		}
//		return code;
//	}
//	
//	public static String getImagePath() {
//		if(null == imagePath){
//			InputStream resourceAsStream = SysServiceSingleton.class.getResourceAsStream("/application.properties");
//			Properties pps = new Properties();
//			try {
//				pps.load(resourceAsStream);
//				imagePath = pps.getProperty("sys.imagePath");
//			} catch (FileNotFoundException e) {
//				LOGGER.error(e.getMessage(), e);
//			} catch (IOException e) {
//				LOGGER.error(e.getMessage(), e);
//			}
//		}
//		return imagePath;
//	}
//
//	public static String getSecurityUrl() {
//		if(null == securityUrl){
//			InputStream resourceAsStream = SysServiceSingleton.class.getResourceAsStream("/application.properties");
//			Properties pps = new Properties();
//			try {
//				pps.load(resourceAsStream);
//				securityUrl = pps.getProperty("sys.securityUrl");
//			} catch (FileNotFoundException e) {
//				LOGGER.error(e.getMessage(), e);
//			} catch (IOException e) {
//				LOGGER.error(e.getMessage(), e);
//			}
//		}
//		return securityUrl;
//	}
//
//	static class SysServiceSingletonHolder {
//		static SysServiceSingleton instance = new SysServiceSingleton();
//		static {
//			context = new ClassPathXmlApplicationContext(
//					new String[] { "remoting.xml" });
//			InputStream resourceAsStream = SysServiceSingleton.class.getResourceAsStream("/application.properties");
//			Properties pps = new Properties();
//			try {
//				pps.load(resourceAsStream);
//				code = pps.getProperty("sys.code");
//				imagePath = pps.getProperty("sys.imagePath");
//				securityUrl = pps.getProperty("sys.securityUrl");
//			} catch (FileNotFoundException e) {
//				LOGGER.error(e.getMessage(), e);
//			} catch (IOException e) {
//				LOGGER.error(e.getMessage(), e);
//			}
//		}
//	}
//
//	public static SysServiceSingleton getInstance() {
//		return SysServiceSingletonHolder.instance;
//	}
//
//	/*@SuppressWarnings("finally")
//	public PlatformSysService getPlatformSysService(){
//		PlatformSysService platformSysService = null;
//		try{
//			platformSysService = (PlatformSysService)context.getBean("platformSysService");
//		}
//		catch(Exception e){
//			LOGGER.error(e.getMessage(), e);
//		}
//		finally{
//			return platformSysService;
//		}
//	}*/
//	
//	public static String getAdminIndexUrl() {
//		if(null == adminIndexUrl){
//			InputStream resourceAsStream = SysServiceSingleton.class.getResourceAsStream("/application.properties");
//			Properties pps = new Properties();
//			try {
//				pps.load(resourceAsStream);
//				adminIndexUrl = pps.getProperty("sys.adminIndexUrl");
//			} catch (FileNotFoundException e) {
//				LOGGER.error(e.getMessage(), e);
//			} catch (IOException e) {
//				LOGGER.error(e.getMessage(), e);
//			}
//		}
//		return adminIndexUrl;
//	}
//	
//	public static String getBasePathUrl() {
//		if(null == basePathUrl){
//			InputStream resourceAsStream = SysServiceSingleton.class.getResourceAsStream("/application.properties");
//			Properties pps = new Properties();
//			try {
//				pps.load(resourceAsStream);
//				basePathUrl = pps.getProperty("sys.basePathUrl");
//			} catch (FileNotFoundException e) {
//				LOGGER.error(e.getMessage(), e);
//			} catch (IOException e) {
//				LOGGER.error(e.getMessage(), e);
//			}
//		}
//		return basePathUrl;
//	}
//	
//	/*public static PlatformSysPo  getPlatformSysPo() {
//		PlatformSysPo platformSysPo = null;
//		platformSysPo = getInstance().getPlatformSysService().findByCode(code);
//		return platformSysPo;
//	}*/
//
//	public static String getOAuthVisitUserId() {
//		// TODO Auto-generated method stub
//		if(null == oAuthVisitUserId){
//			InputStream resourceAsStream = SysServiceSingleton.class.getResourceAsStream("/application.properties");
//			Properties pps = new Properties();
//			try {
//				pps.load(resourceAsStream);
//				oAuthVisitUserId = pps.getProperty("sys.oAuthVisitUserId");
//			} catch (FileNotFoundException e) {
//				LOGGER.error(e.getMessage(), e);
//			} catch (IOException e) {
//				LOGGER.error(e.getMessage(), e);
//			}
//		}
//		return oAuthVisitUserId;
//	}
//	/**
//	 * @Description:获取商户号
//	 * @return
//	 * @throws
//	 */
//	public static String getMCHID() {
//        // TODO Auto-generated method stub
//        if(null == mchId){
//            InputStream resourceAsStream = SysServiceSingleton.class.getResourceAsStream("/application.properties");
//            Properties pps = new Properties();
//            try {
//                pps.load(resourceAsStream);
//                mchId = pps.getProperty("microshop.pay.account");
//            } catch (FileNotFoundException e) {
//                LOGGER.error(e.getMessage(), e);
//            } catch (IOException e) {
//                LOGGER.error(e.getMessage(), e);
//            }
//        }
//        return mchId;
//    }
//
//}
//
