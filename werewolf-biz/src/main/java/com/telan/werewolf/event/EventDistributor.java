package com.telan.werewolf.event;

import com.telan.werewolf.param.BaseRequestData;
import com.telan.werewolf.param.BaseResponseData;

public interface EventDistributor {
	public BaseResponseData distribute(BaseRequestData eventMsg);
}
