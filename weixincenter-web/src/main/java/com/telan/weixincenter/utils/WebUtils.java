package com.telan.weixincenter.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtils {
	public static final String DEFAULT_CHARACTER_ENCODING = "ISO-8859-1";
	public static final String INCLUDE_CONTEXT_PATH_ATTRIBUTE = "javax.servlet.include.context_path";
	public static final String INCLUDE_REQUEST_URI_ATTRIBUTE = "javax.servlet.include.request_uri";

	public static String getPathWithinApplication(HttpServletRequest request) {
		String contextPath = getContextPath(request);
		String requestUri = getRequestUri(request);
		if (StringUtils.startsWithIgnoreCase(requestUri, contextPath)) {
			String path = requestUri.substring(contextPath.length());
			return StringUtils.hasText(path) ? path : "/";
		}

		return requestUri;
	}

	public static String getPathWithinApplication() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String contextPath = getContextPath(request);
		String requestUri = getRequestUri(request);
		if (StringUtils.startsWithIgnoreCase(requestUri, contextPath)) {
			String path = requestUri.substring(contextPath.length());
			return StringUtils.hasText(path) ? path : "/";
		}

		return requestUri;
	}

	public static String getContextPath(HttpServletRequest request) {
		String contextPath = (String) request
				.getAttribute("javax.servlet.include.context_path");
		if (contextPath == null) {
			contextPath = request.getContextPath();
		}
		if ("/".equals(contextPath)) {
			contextPath = "";
		}
		return decodeRequestString(request, contextPath);
	}

	public static String getRequestUri(HttpServletRequest request) {
		String uri = (String) request
				.getAttribute("javax.servlet.include.request_uri");
		if (uri == null) {
			uri = request.getRequestURI();
		}
		return normalize(decodeAndCleanUriString(request, uri));
	}

	@SuppressWarnings("deprecation")
	public static String decodeRequestString(HttpServletRequest request,
			String source) {
		String enc = determineEncoding(request);
		try {
			return URLDecoder.decode(source, enc);
		} catch (UnsupportedEncodingException ex) {

		}
		return URLDecoder.decode(source);
	}

	private static String decodeAndCleanUriString(HttpServletRequest request,
			String uri) {
		uri = decodeRequestString(request, uri);
		int semicolonIndex = uri.indexOf(';');
		return semicolonIndex != -1 ? uri.substring(0, semicolonIndex) : uri;
	}

	public static String normalize(String path) {
		return normalize(path, true);
	}

	private static String normalize(String path, boolean replaceBackSlash) {
		if (path == null) {
			return null;
		}

		String normalized = path;

		if ((replaceBackSlash) && (normalized.indexOf('\\') >= 0)) {
			normalized = normalized.replace('\\', '/');
		}
		if (normalized.equals("/")) {
			return "/";
		}

		if (!normalized.startsWith("/")) {
			normalized = "/" + normalized;
		}
		while (true) {
			int index = normalized.indexOf("//");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index)
					+ normalized.substring(index + 1);
		}

		while (true) {
			int index = normalized.indexOf("/./");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index)
					+ normalized.substring(index + 2);
		}

		while (true) {
			int index = normalized.indexOf("/../");
			if (index < 0)
				break;
			if (index == 0)
				return null;
			int index2 = normalized.lastIndexOf('/', index - 1);
			normalized = normalized.substring(0, index2)
					+ normalized.substring(index + 3);
		}

		return normalized;
	}

	protected static String determineEncoding(HttpServletRequest request) {
		String enc = request.getCharacterEncoding();
		if (enc == null) {
			enc = "ISO-8859-1";
		}
		return enc;
	}
}