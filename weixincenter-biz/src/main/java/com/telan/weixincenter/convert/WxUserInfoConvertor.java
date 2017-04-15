package com.telan.weixincenter.convert;


import com.telan.weixincenter.domain.result.WxSubscribeFansDTO;
import com.telan.weixincenter.entity.WxUserInfo;

public class WxUserInfoConvertor {
	public static WxSubscribeFansDTO wxUserInfo2WxSubscribeFansDTO(WxUserInfo info){
		if( info == null ){
			 return null;
		}
		WxSubscribeFansDTO dto = new WxSubscribeFansDTO();
		dto.setSubscribe(info.getSubscribe());
		dto.setOpenId(info.getOpenid());
		dto.setNickName(info.getNickname());
		dto.setSex(info.getSex());
		dto.setLanguage(info.getLanguage());
		dto.setCity(info.getCity());
		dto.setCountry(info.getCountry());
		dto.setHeadimgurl(info.getHeadimgurl());
		dto.setSubscribeTime(info.getSubscribe_time());
		dto.setRemark(info.getRemark());
		return dto; 
	}	
}
