package com.telan.werewolf.event;

import com.telan.werewolf.model.request.BaseReqMsg;
import com.telan.werewolf.model.response.BaseRspMsg;

public interface EventDistributor {
	public BaseRspMsg distribute(BaseReqMsg eventMsg);
}
