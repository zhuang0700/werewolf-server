package com.telan.weixincenter.mapper.base;

import com.telan.weixincenter.domain.base.WxServiceKeywordDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxServiceKeywordMapper{

	List<WxServiceKeywordDO> infoKeyWordLike(@Param("content") String content, @Param("wxMerchantId") Long wxMerchantId);

	void deleteByRuId(@Param("ruleId") Long ruleId);

	List<WxServiceKeywordDO> queryWxKeyWordListByRuleId(@Param("ruleId") Long ruleId);

	int insertSelective(WxServiceKeywordDO wxServiceKeyword);

	int updateByPrimaryKeySelective(WxServiceKeywordDO wxServiceKeyword);

	int deleteByPrimaryKey(@Param("id") Long id);



}