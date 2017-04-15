package com.telan.weixincenter.mapper.base;


import com.telan.weixincenter.domain.base.WxMaterialDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxMaterialMapper{

	List<WxMaterialDO> queryWxMaterialTypeid(@Param("id") Long id, @Param("wxMerchantId") Long wxMerchantId);

	List<WxMaterialDO> queryEventKey(@Param("evenkeyNum") Integer evenkeyNum, @Param("wxMerchantId") Long wxMerchantId);

	List<WxMaterialDO> queryWxMaterialByMaterialTypeId(@Param("materialTypeId") Integer materialTypeId,
													   @Param("wxMerchantId") Long wxMerchantId);

	List<WxMaterialDO> queryWxMaterialByMerchantId(@Param("wxMerchantId") Long wxMerchantId);

	void deleteByMaterialNextId(@Param("id") Long id, @Param("wxMerchantId") Long wxMerchantId);

	int insertSelective(WxMaterialDO wxMaterial);

	WxMaterialDO selectByPrimaryKey(@Param("id") Long id);

	int updateByPrimaryKeySelective(WxMaterialDO wxMaterial);

	int deleteByPrimaryKey(@Param("id") Long id);
     
}