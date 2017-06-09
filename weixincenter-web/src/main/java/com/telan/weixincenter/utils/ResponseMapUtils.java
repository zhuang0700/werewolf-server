package com.telan.weixincenter.utils;

import com.telan.werewolf.result.WeBaseResult;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class ResponseMapUtils {
	public static Map convertWeBaseResultToMap(WeBaseResult result) {
		Map map = new HashMap();
		map.put("status", result.isSuccess()?1:0);
		map.put("msg", result.getResultMsg());
		map.put("code", result.getErrorCode());
		map.put("result", result.getValue());
		return map;
	}

}