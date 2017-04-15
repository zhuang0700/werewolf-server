package com.telan.weixincenter.mapper.fans;


import com.telan.weixincenter.domain.fans.WxFansDO;
import com.telan.weixincenter.domain.fans.WxFansTagDO;
import com.telan.weixincenter.domain.fans.WxFansToTagDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxFansToTagMapper{

	List<WxFansTagDO> getFansTagByMerchantId(@Param("wxMerchantId") Long wxMerchantId);

	int delFansToTagByOppenid(WxFansToTagDO wxFansToTag);

	List<WxFansToTagDO> getFansToTagByopenId(WxFansDO fans2);

	int insertSelective(WxFansToTagDO wxFansToTag);

}