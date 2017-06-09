package com.telan.werewolf.utils;

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: SessionKeyUtil
 * @Description: Description of this class
 * @author weiwenliang
 */

public class SessionKeyUtil {
	public static String generateSessionKey() {
		String uuid = UUID.randomUUID().toString();
		Date date = new Date();
		String sessionKey = date.getTime() + "##" + uuid;
		return sessionKey;
	}
}
