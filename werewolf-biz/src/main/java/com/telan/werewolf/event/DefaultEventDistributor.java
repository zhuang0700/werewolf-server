package com.telan.werewolf.event;


import com.telan.werewolf.event.processer.BaseProcessor;
import com.telan.werewolf.event.processer.EventProcessor;
import com.telan.werewolf.event.processer.ProcessorMatcher;
import com.telan.werewolf.param.BaseRequestData;
import com.telan.werewolf.param.BaseResponseData;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DefaultEventDistributor implements EventDistributor, ApplicationContextAware {
	private static final String DEFAULT_EVENT_DISTRIBUTOR = "defaultEventProcessor";
	private static ApplicationContext applicationContext;
	
	@Autowired
	private ProcessorMatcher processorMatcher;

	@Autowired
	private BaseProcessor baseProcessor;

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}
	private EventProcessor getEventProcessor(BaseRequestData eventMsg) {

		if(null == eventMsg){
			return null;
		}

		//公众号
//		EventProcessor eventProcessor = processorMatcher.match(toUserName, applicationContext);
		EventProcessor eventProcessor = new BaseProcessor();
		if(eventProcessor != null){
			return eventProcessor;
		}else if (applicationContext.containsBean(DEFAULT_EVENT_DISTRIBUTOR)) {
			return (EventProcessor) applicationContext.getBean(DEFAULT_EVENT_DISTRIBUTOR);
		}

		return null;
	}

	@Override
	public BaseResponseData distribute(BaseRequestData eventMsg) {
		
		if(null == eventMsg){
			return null;
		}
		EventProcessor processor = baseProcessor;
		if(null==processor) {
			return null;
		}
		return processor.processRequest(eventMsg);
	}
}
