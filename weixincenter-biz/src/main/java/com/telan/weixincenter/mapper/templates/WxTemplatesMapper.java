package com.telan.weixincenter.mapper.templates;

import com.telan.weixincenter.domain.templates.WxTemplatesDO;
import com.telan.weixincenter.domain.templates.WxTemplatesHistoryDO;
import com.telan.weixincenter.domain.templates.WxTemplatesItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxTemplatesMapper{

	void addWxTemplatesItem(WxTemplatesItemDO wxTemplatesItem);

	int templateAdd(WxTemplatesDO wxTemplates);

	List<WxTemplatesDO> queryExitByTempId(@Param("tempid") String tempid, @Param("wxMerchantId") Long wxMerchantId);

	List<WxTemplatesDO> queryWxTemplatesByMerchantId(Long wxMerchantId);

	int delWxTemplatesByTemplateId(Long wxMerchantId, @Param("templateId") Long templateId);

	void delWxTemplatesItemByTemplateId(Long wxMerchantId, @Param("templateId") Long templateId);

	WxTemplatesDO queryWxTemplatesByTemplatesIdAndMerchantId(@Param("wxMerchantId") Long wxMerchantId,
															 @Param("templateId") Long templateId);

	List<WxTemplatesItemDO> queryWxTemplatesItemByTemplateId(@Param("wxMerchantId") Long wxMerchantId,
															 @Param("templateId") Long templateId);

	int updateWxTemplates(WxTemplatesDO wxTemplates);

	List<WxTemplatesDO> queryWxTemplatesByMerchantIdAndStatus(Long wxMerchantId);

	List<WxTemplatesHistoryDO> queryWxTemplatesHistoryByMerchantId(Long wxMerchantId);

	void addWxTemplatesHistory(WxTemplatesHistoryDO wxTemplatesHistory);
	
}