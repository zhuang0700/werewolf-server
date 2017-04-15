package com.telan.weixincenter.event.processer;

import org.springframework.context.ApplicationContext;

public interface ProcessorMatcher {
	public EventProcessor match(String toUserName, ApplicationContext applicationContext);
}
