package com.telan.werewolf.mapper;

import com.telan.werewolf.domain.PlayerDO;
import com.telan.werewolf.game.domain.Player;
import com.telan.werewolf.query.PlayerPageQuery;

import java.util.List;

public interface PlayerDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(PlayerDO record);

    PlayerDO selectByPrimaryKey(long id);

    List<PlayerDO> pageQuery(PlayerPageQuery playerPageQuery);

    int updateByPrimaryKeySelective(PlayerDO record);
}