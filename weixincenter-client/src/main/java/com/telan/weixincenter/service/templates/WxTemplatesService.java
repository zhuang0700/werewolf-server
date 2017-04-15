package com.telan.weixincenter.service.templates;

import java.util.List;

import com.telan.weixincenter.domain.templates.WxTemplatesDO;
import com.telan.weixincenter.domain.templates.WxTemplatesHistoryDO;
import com.telan.weixincenter.domain.templates.WxTemplatesItemDO;

/**
 * @author : zhangchao
 * @date : 2015年8月7日 下午2:42:02
 * @Description: 微信模版
 */
public interface WxTemplatesService{

	int templateAdd(WxTemplatesDO wxTemplates, String[] field_name,
			String[] field_value, String[] field_color);

	List<WxTemplatesDO> queryExitByTempId(String tempid, Long wxMerchantId);

	List<WxTemplatesDO> queryWxTemplatesByMerchantId(Long wxMerchantId);

	int delWxTemplatesByTemplateId(Long wxMerchantId, Long templateId);

	WxTemplatesDO queryWxTemplatesByTemplatesIdAndMerchantId(Long wxMerchantId,
			Long templateId);

	List<WxTemplatesItemDO> queryWxTemplatesItemByTemplateId(Long wxMerchantId,
			Long templateId);

	int updateWxTemplates(WxTemplatesDO wxTemplates, String[] field_name,
			String[] field_value, String[] field_color);

	List<WxTemplatesDO> queryWxTemplatesByMerchantIdAndStatus(Long wxMerchantId);

	List<WxTemplatesHistoryDO> queryWxTemplatesHistoryByUserId(Long wxMerchantId);

	void addWxTemplatesHistory(WxTemplatesHistoryDO wxTemplatesHistory);

}
