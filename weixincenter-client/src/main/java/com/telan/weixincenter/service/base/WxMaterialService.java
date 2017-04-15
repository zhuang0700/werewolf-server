package com.telan.weixincenter.service.base;

import java.util.List;

import com.telan.weixincenter.domain.base.WxMaterialDO;
import com.telan.weixincenter.domain.base.WxMaterialArticleDO;
/**
 * @author : zhangchao
 * @date : 2015年7月29日 上午10:39:08
 * @Description: 素材库接口
 */
public interface WxMaterialService{

	List<WxMaterialDO> queryWxMaterialTypeid(Long materialId, Long wxMerchantId);

	/**
	 * @author : zhangchao
	 * @date : 2015年8月3日 上午11:18:14
	 * @Description: 根据自定义菜单是evenkey来查询所对应的素材
	 */
	List<WxMaterialDO> queryEventKey(Integer evenkeyNum, Long wxMerchantId);

	/**
	 * @author : zhangchao
	 * @date : 2015年8月14日 下午2:56:03
	 * @Description: 查询素材库列表  根据素材类型
	 */
	List<WxMaterialDO> queryWxMaterialByMaterialTypeId(Integer materialTypeId,
			Long wxMerchantId);

	List<WxMaterialDO> queryWxMaterialByUserId(Long wxMerchantId);

	/**
	 * @author : zhangchao
	 * @date : 2015年8月24日 上午10:33:22
	 * @Description: 按分页查询(只查数据，分页走拦截器)
	 */
//	PageBean<WxMaterial> selectWxMaterialWithPageBean(
//			PageBean<WxMaterial> pageBean);

	/**
	 * @author : zhangchao
	 * @date : 2015年8月25日 下午3:42:50
	 * @Description: 删除图文下面的多图文
	 */
	void deleteByMaterialNextId(Long materialId, Long wxMerchantId);

	/**
	 * @author : zhangchao
	 * @param wxMerchantId 
	 * @param editorValue 
	 * @date : 2015年9月8日 下午7:44:11
	 * @Description: 图文的操作
	 */
	int insertWxMaterial(List<WxMaterialArticleDO> wxMaterialArticles,
			String[] imgUrl, Long wxMerchantId);

	/**
	 * @author : zhangchao
	 * @date : 2015年9月11日 上午9:57:18
	 * @Description: 修改图文
	 */
	int updateWxMaterial(List<WxMaterialArticleDO> wxMaterialArticles,
			String[] imgUrl, Long wxMerchantId);

	/**
	 * @author : zhangchao
	 * @date : 2015年11月18日 下午4:54:22
	 * @Description: 插入数据库并且返回主键
	 */
	Long insertAndReturnMateridalId(WxMaterialDO wxMaterial1);

}
