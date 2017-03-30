package com.telan.werewolf.service.fans;

import java.util.List;

import com.telan.werewolf.domain.fans.WxFansDO;
import com.telan.werewolf.domain.fans.WxFansToTagDO;

/**
 * @author : zhangchao
 * @date : 2015年8月7日 上午11:58:11
 * @Description: 粉丝标签关联
 */
public interface WxFansToTagService{

	List<WxFansToTagDO> getFansToTagByopenId(WxFansDO fans2);

	void addFansToTag(WxFansToTagDO wxFansToTag, String set_tag);

}

