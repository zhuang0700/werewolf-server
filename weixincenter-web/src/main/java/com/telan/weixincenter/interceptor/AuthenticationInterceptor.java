//package com.yimayhd.weixincenter.interceptor;
//
//import java.util.List;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.util.PathMatcher;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import com.yimayhd.weixincenter.utils.WebUtils;
//
///**
// * 登录 权限等拦截器 **
// */
//public class AuthenticationInterceptor implements HandlerInterceptor {
//	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
//	private PathMatcher pathMatcher = new AntPathMatcher();
//	private List<String> excludedUrls;
//	private List<String> noAuthorityUrls;
//
////	@Autowired
////	private PlatformSessionService platformSessionService;
//
//	public void setExcludedUrls(List<String> excludedUrls) {
//		this.excludedUrls = excludedUrls;
//	}
//	
//	public void setNoAuthorityUrls(List<String> noAuthorityUrls) {
//		this.noAuthorityUrls = noAuthorityUrls;
//	}
//
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		for (String url : this.excludedUrls) {
//			if (pathsMatch(url, request)) {
//				return true;
//			}
//		}
//		String sessionId = null;
//		Cookie[] cookies = request.getCookies();
//		if (null != cookies) {
//			for (Cookie cookie : cookies) {
//				if ("yihg_session_id".equals(cookie.getName())) {
//					sessionId = cookie.getValue();
//				}
//			}
//		}
//
////		if (sessionId == null) {
////			logger.info("******************************************001\t\t\t  cookie为空");
////			response.sendRedirect(SysServiceSingleton.getSecurityUrl());
////			return false;
////		}
////		UserSession userSession = platformSessionService.getUserSession(sessionId);
////		if (userSession == null) {
////			logger.info("******************************************002\t\t\t  用户不存在redis中");
////			response.sendRedirect(SysServiceSingleton.getSecurityUrl());
////			return false;
////		}
////		platformSessionService.setUserSession(sessionId, 30 * 60, userSession);
//		request.setAttribute("adminIndexUrl", SysServiceSingleton.getAdminIndexUrl());
////		request.setAttribute("employeeName", userSession.getName());
//		return true;
//	}
//	
//	
//	
//	/**
//	 * 判断是否有菜单操作权限
//	 * @param list
//	 * @param request
//	 * @return
//	 */
//	/*public boolean hasPower(List<PlatformMenuPo> list,HttpServletRequest request){
//		for(PlatformMenuPo platformMenuPo : list){
//			if(pathsMatch(platformMenuPo.getUrl(), request) || (platformMenuPo.getChildMenuList() !=null
//				&& platformMenuPo.getChildMenuList().size()>0 && hasPower(platformMenuPo.getChildMenuList(), request))){
//				return true;
//			}
//		}
//		return false;
//	}*/
//
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//	}
//
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//	}
//
//	protected boolean pathsMatch(String path, ServletRequest request) {
//		String requestURI = getPathWithinApplication(request);
//
//		return pathsMatch(path, requestURI);
//	}
//
//	protected boolean pathsMatch(String pattern, String path) {
//		return this.pathMatcher.match(pattern, path);
//	}
//
//	protected String getPathWithinApplication(ServletRequest request) {
//		return WebUtils.getPathWithinApplication((HttpServletRequest) request);
//	}
//}
