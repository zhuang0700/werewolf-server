package com.telan.weixincenter.event;

import com.telan.weixincenter.event.processer.EventProcessor;
import com.telan.weixincenter.event.processer.ProcessorMatcher;
import com.telan.weixincenter.model.request.BaseReqMsg;
import com.telan.weixincenter.model.response.BaseRspMsg;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//DEFAULT GF MF SHANGMAO
public class DefaultEventDistributor implements EventDistributor, ApplicationContextAware {
	private static final String DEFAULT_EVENT_DISTRIBUTOR = "defaultEventProcessor";
	private static ApplicationContext applicationContext;
	
	@Autowired
	private ProcessorMatcher processorMatcher;

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}
	private EventProcessor getEventProcessor(BaseReqMsg eventMsg) {

		if(null == eventMsg){
			return null;
		}

		//公众号
		String toUserName = eventMsg.getToUserName();
		EventProcessor eventProcessor = processorMatcher.match(toUserName, applicationContext);

		if(eventProcessor != null){
			return eventProcessor;
		}else if (applicationContext.containsBean(DEFAULT_EVENT_DISTRIBUTOR)) {
			return (EventProcessor) applicationContext.getBean(DEFAULT_EVENT_DISTRIBUTOR);
		}

		return null;
	}

	@Override
	public BaseRspMsg distribute(BaseReqMsg eventMsg) {
		
		if(null == eventMsg){
			return null;
		}
		EventProcessor processor = getEventProcessor(eventMsg);
		if(null==processor) {
			return null;
		}


		return processor.processRequest(eventMsg);
	}
}
