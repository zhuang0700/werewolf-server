package com.telan.weixincenter.event;

import com.telan.weixincenter.model.request.BaseReqMsg;
import com.telan.weixincenter.model.response.BaseRspMsg;

public interface EventDistributor {
	public BaseRspMsg distribute(BaseReqMsg eventMsg);
}
