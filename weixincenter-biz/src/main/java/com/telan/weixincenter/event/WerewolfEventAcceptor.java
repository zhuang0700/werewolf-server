package com.telan.weixincenter.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.telan.weixincenter.event.parser.BaseParser;
import com.telan.weixincenter.event.parser.ContentFormat;
import com.telan.weixincenter.model.request.BaseReqMsg;
import com.telan.weixincenter.model.response.BaseRspMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class WerewolfEventAcceptor implements EventAcceptor {
	private final static Logger LOGGER = LoggerFactory.getLogger(WerewolfEventAcceptor.class);

	@Autowired
	private EventDistributor eventDistributor;
	@Autowired
	private BaseParser xmlParser;

	@Override
	public String doAccept(String content) {
		String responseContent = null ;
		try {
			// 转换请求信息为事件对象
			BaseReqMsg request = xmlParser.parseToBean(content, ContentFormat.XML);
			LOGGER.debug("request={}", JSONObject.toJSONString(request));
			// 分发事件
			BaseRspMsg response = eventDistributor.distribute(request);
			LOGGER.debug("response={}", JSONObject.toJSONString(response));
			// 获取事件的响应
			responseContent = xmlParser.parseFromBean(response, ContentFormat.XML);
			LOGGER.debug("responseContent={}", responseContent);
		} catch (Exception e) {
			LOGGER.error("doAccept   content={}", JSON.toJSONString(content), e);
		}
		return responseContent;
	}
}
