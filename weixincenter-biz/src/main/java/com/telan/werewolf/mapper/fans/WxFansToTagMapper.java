package com.telan.werewolf.mapper.fans;


import com.telan.werewolf.domain.fans.WxFansDO;
import com.telan.werewolf.domain.fans.WxFansTagDO;
import com.telan.werewolf.domain.fans.WxFansToTagDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxFansToTagMapper{

	List<WxFansTagDO> getFansTagByMerchantId(@Param("wxMerchantId") Long wxMerchantId);

	int delFansToTagByOppenid(WxFansToTagDO wxFansToTag);

	List<WxFansToTagDO> getFansToTagByopenId(WxFansDO fans2);

	int insertSelective(WxFansToTagDO wxFansToTag);

}