package com.telan.werewolf.mapper;

import com.telan.werewolf.domain.GameDO;
import com.telan.werewolf.query.GamePageQuery;

import java.util.List;

public interface GameDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(GameDO record);

    GameDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(GameDO record);

    List<GameDO> pageQuery(GamePageQuery pageQuery);
}