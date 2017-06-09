package com.telan.werewolf.mapper;

import com.telan.werewolf.domain.UserDO;

public interface UserDOMapper {
    int insert(UserDO record);

    UserDO selectByPrimaryKey(long id);

    UserDO selectByUnionId(String unionId);

    UserDO selectByOpenId(String openId);

    int updateByPrimaryKeySelective(UserDO record);

}