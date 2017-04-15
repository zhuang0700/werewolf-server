package com.telan.weixincenter.core.util;

import com.alibaba.fastjson.JSONObject;
import com.telan.weixincenter.constant.WxConstant;
import org.apache.commons.lang3.StringUtils;

public class WxApiUtil {

	public static boolean isWeixinRequestSuccess(JSONObject jsonObj) {
		if (jsonObj == null || (jsonObj.containsKey(WxConstant.WEIXIN_ERROR_CODE_FIELD)
				&& jsonObj.getIntValue(WxConstant.WEIXIN_ERROR_CODE_FIELD) != WxConstant.WEIXIN_SUCCESS_CODE)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 解析eventKey中的QrsceneCode，
	 * @param eventKey
	 * @return
	 */
	public static int getQrsceneChannelId(String eventKey){
		String qrsceneCode = "00";
		if (StringUtils.isNotEmpty(eventKey) && eventKey.contains(WxConstant.QRSCENE_PREFIX)) {
			// 代表二维码扫描关注
			qrsceneCode = eventKey.substring(WxConstant.QRSCENE_PREFIX.length());
		}
		String channelId = qrsceneCode.substring(WxConstant.CHANNEL_INDEX);// 真实的渠道ID
		if( StringUtils.isNumeric(channelId) ){
			return Integer.parseInt(channelId) ;
		}
		return 0;
	}
}
