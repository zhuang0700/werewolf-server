package com.telan.weixincenter.mapper.fans;

import com.telan.weixincenter.domain.fans.WxFansTagDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxFansTagMapper{

	List<WxFansTagDO> getFansTagByMerchantId(@Param("wxMerchantId") Long wxMerchantId);

	int insertSelective(WxFansTagDO wxFansTag);

	WxFansTagDO selectByPrimaryKey(@Param("id") Long id);

	int updateByPrimaryKeySelective(WxFansTagDO wxFansTag);

	int deleteByPrimaryKey(@Param("id") Long id);


}