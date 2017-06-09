package com.telan.werewolf.manager;

import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.utils.SessionHelper;
import com.telan.werewolf.utils.SessionKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


public class MemSessionManager {
	private static final Logger logger = LoggerFactory.getLogger("SessionManager");

	public HashMap<String, UserDO> usersMap;

	public UserDO getUser(){
		return getUser(SessionHelper.getSessionKey());
	}

	public MemSessionManager(int initSize) {
		usersMap = new HashMap<>(initSize);
	}
	/**
	 * 将user缓存到tair
	 * @param sessionKey
	 * @param user
	 * @return
	 */
	public String addUser(String sessionKey, UserDO user) {
		user.setSessionKey(sessionKey);
		String werewolfSessionKey = SessionKeyUtil.generateSessionKey();
		usersMap.put(werewolfSessionKey, user);
		return werewolfSessionKey;
	}

	/**
	 * 根据werewolfSessionKey获取当前用户信息
	 * @param werewolfSessionKey
	 * @return
	 */
	public UserDO getUser(String werewolfSessionKey) {
		UserDO user = usersMap.get(werewolfSessionKey);
		return user;
	}


}
