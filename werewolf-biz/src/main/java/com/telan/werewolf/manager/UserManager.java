package com.telan.werewolf.manager;

import com.alibaba.fastjson.JSON;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.enums.WeErrorCode;
import com.telan.werewolf.mapper.UserDOMapper;
import com.telan.werewolf.result.WeBaseResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by weiwenliang on 17/5/10.
 */
public class UserManager {
    public static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    UserDOMapper userDOMapper;

    @Autowired
    MemSessionManager memSessionManager;
    /**
     * 根据userid获取用户信息
     * @param userId
     * @return
     */
    public WeBaseResult<UserDO> getUserById(long userId){
        WeBaseResult<UserDO> result = new WeBaseResult<UserDO>();
        if(userId <= 0){
            result.setErrorCode(WeErrorCode.PARAM_ERROR);
            return result;
        }
        try{
            UserDO userDO = userDOMapper.selectByPrimaryKey(userId);
            result.setValue(userDO);
        }catch(Exception e){
            result.setErrorCode(WeErrorCode.READ_DB_ERROR);
            logger.error("userDOMapper.selectByPrimaryKey error,userId={}",userId);
        }
        return result;
    }

    /**
     * 根据unionId获取用户信息
     * @param unionId
     * @return
     */
    public WeBaseResult<UserDO> getUserByUnionId(String unionId){
        WeBaseResult<UserDO> result = new WeBaseResult<UserDO>();
        if(StringUtils.isBlank(unionId)){
            result.setErrorCode(WeErrorCode.PARAM_ERROR);
            return result;
        }
        try{
            UserDO userDO = userDOMapper.selectByUnionId(unionId);
            result.setValue(userDO);
        }catch(Exception e){
            result.setErrorCode(WeErrorCode.READ_DB_ERROR);
            logger.error("userDOMapper.selectByUnionId error,unionId={}",unionId);
        }
        return result;
    }

    /**
     * 根据unionId获取用户信息
     * @param openId
     * @return
     */
    public WeBaseResult<UserDO> getUserByOpenId(String openId){
        WeBaseResult<UserDO> result = new WeBaseResult<UserDO>();
        if(StringUtils.isBlank(openId)){
            result.setErrorCode(WeErrorCode.PARAM_ERROR);
            return result;
        }
        try{
            UserDO userDO = userDOMapper.selectByOpenId(openId);
            result.setValue(userDO);
        }catch(Exception e){
            result.setErrorCode(WeErrorCode.READ_DB_ERROR);
            logger.error("userDOMapper.selectByUnionId error,openId={}",openId);
        }
        return result;
    }

    public UserDO addUser(UserDO userDO) {
        if(userDO == null) {
            return null;
        }
        try{
            int i = userDOMapper.insert(userDO);
            return userDO;
        }catch(Exception e){
            logger.error("userDOMapper.insert(userDO); error,userDO={}", JSON.toJSONString(userDO));
        }
        return null;
    }

    public String login(String sessionKey, UserDO user) {
        UserDO dbUser = userDOMapper.selectByOpenId(user.getOpenId());
        if(dbUser == null) { //若不存在，注册
            dbUser = addUser(user);
        }
        String wolfSessionKey = memSessionManager.addUser(sessionKey, dbUser);
        return wolfSessionKey;
    }
}