package com.telan.werewolf.event.processer;

import com.telan.werewolf.param.BaseRequestData;
import com.telan.werewolf.param.BaseResponseData;

public interface EventProcessor {
	public BaseResponseData processRequest(BaseRequestData requestMessage);

}
