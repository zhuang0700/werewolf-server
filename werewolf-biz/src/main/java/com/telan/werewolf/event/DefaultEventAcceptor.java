package com.telan.werewolf.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.telan.werewolf.param.BaseRequestData;
import com.telan.werewolf.param.BaseResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultEventAcceptor implements EventAcceptor {
	private final static Logger LOGGER = LoggerFactory.getLogger(DefaultEventAcceptor.class);

	@Autowired
	private EventDistributor eventDistributor;

	@Override
	public String doAccept(String content) {
		String responseContent = null ;
		try {
			// 转换请求信息为事件对象
			BaseRequestData request = null;
			try {
				JSONObject jsonObj = (JSONObject) JSON.parse(content);
				request = JSONObject.toJavaObject(jsonObj, BaseRequestData.class);
			} catch (Exception e) {
				LOGGER.error("accept message error. unknown message:", content, e);
			}
			LOGGER.debug("request={}", JSONObject.toJSONString(request));
			// 分发事件
			BaseResponseData response = eventDistributor.distribute(request);
			LOGGER.debug("response={}", JSONObject.toJSONString(response));
			// 获取事件的响应
		} catch (Exception e) {
			LOGGER.error("doAccept   content={}", JSON.toJSONString(content), e);
		}
		return responseContent;
	}
}
