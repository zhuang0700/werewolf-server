package com.telan.weixincenter.event.processer;

import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class SimpleNameProcessorMatcher implements ProcessorMatcher {
	private Map<String, EventProcessor> processors = new HashMap<String, EventProcessor>();

	@Override
	public EventProcessor match(String toUserName, ApplicationContext applicationContext) {
		if (processors.containsKey(toUserName)) {
			return processors.get(toUserName);
		}

		return null;
	}

	public Map<String, EventProcessor> getProcessors() {
		return processors;
	}

	public void setProcessors(Map<String, EventProcessor> processors) {
		this.processors = processors;
	}

}
