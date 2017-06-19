package com.telan.werewolf.mapper;

import com.telan.werewolf.domain.UserDO;

import java.util.List;

public interface UserDOMapper {
    int insert(UserDO record);

    UserDO selectByPrimaryKey(long id);

    List<UserDO> batchSelectByIds(List<Long> list);

    UserDO selectByUnionId(String unionId);

    UserDO selectByOpenId(String openId);

    int updateByPrimaryKeySelective(UserDO record);

}