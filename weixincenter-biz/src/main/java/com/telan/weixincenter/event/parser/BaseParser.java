package com.telan.weixincenter.event.parser;

import com.alibaba.fastjson.JSONObject;
import com.telan.weixincenter.model.Event;
import com.telan.weixincenter.model.MsgType;
import com.telan.weixincenter.model.request.*;
import com.telan.weixincenter.model.response.BaseRspMsg;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseParser implements Parser<BaseReqMsg, BaseRspMsg> {
	private final static Logger LOGGER = LoggerFactory.getLogger(BaseParser.class);

	@Override
	public BaseReqMsg parseToBean(String content, ContentFormat format) {
		LOGGER.info("content:" + content);
		// FIXME 暂时写死
		try {
			if (format.getId() == ContentFormat.XML.getId()) {
				Document document = DocumentHelper.parseText(content);
				String toUserName = document.selectSingleNode("/xml/ToUserName").getText();
				String fromUserName = document.selectSingleNode("/xml/FromUserName").getText();
				String createTime = document.selectSingleNode("/xml/CreateTime").getText();
				String msgType = document.selectSingleNode("/xml/MsgType").getText();
				
				
				BaseReqMsg requestMsg = null;
				MsgType type = MsgType.valueOfCode(msgType);
				switch (type) {
				case EVENT:
					requestMsg = parseEvent(document);
					requestMsg.setToUserName(toUserName);
					requestMsg.setFromUserName(fromUserName);
					requestMsg.setCreateTime(Long.parseLong(createTime));
					requestMsg.setMsgType(type);
					break;
				case TEXT:
					TextMessageRequest request = new TextMessageRequest();
					request.setToUserName(toUserName);
					request.setFromUserName(fromUserName);
					request.setCreateTime(Long.parseLong(createTime));
					request.setMsgType(type);
					String message = document.selectSingleNode("/xml/Content").getText();
					String msgId = document.selectSingleNode("/xml/MsgId").getText();
					request.setContent(message);
					request.setMsgId(msgId);
					requestMsg = request;

					break;
				case IMAGE:
					ImageMessageRequest imgRequest = new ImageMessageRequest() ;
					imgRequest.setToUserName(toUserName);
					imgRequest.setFromUserName(fromUserName);
					imgRequest.setCreateTime(Long.parseLong(createTime));
					imgRequest.setMsgType(type);
					String picUrl = document.selectSingleNode("/xml/PicUrl").getText();
					String mediaId = document.selectSingleNode("/xml/MediaId").getText();
					String imgMsgId = document.selectSingleNode("/xml/MsgId").getText();
					imgRequest.setPicUrl(picUrl);
					imgRequest.setMsgId(imgMsgId);
					imgRequest.setMediaId(mediaId);
					requestMsg = imgRequest;
					
					break;
				case VOICE:
					break;
				case VIDEO:
					break;
				default:
					break;
				}

				return requestMsg;
			}

		} catch (Exception e) {
			LOGGER.error("content={}", content, e);
		}

		return null;
	}

	@Override
	public String parseFromBean(BaseRspMsg bean, ContentFormat format) {
		if(bean != null){
			XStream xstream = new XStream(new WeixinXppDriver());
			xstream.autodetectAnnotations(true);

			return xstream.toXML(bean);
		}
		
		return "";
	}

	public static void main(String[] args) {
		/**
		ImageTextMessageResponse response = new ImageTextMessageResponse();
		response.setFromUserName("testuser");
		response.setMsgType(MsgType.valueOfCode("text"));
		response.setCreateTime(new Date().getTime());
		response.setToUserName("ddddd");
		
		ImageTextItem item = new ImageTextItem();
		item.setTitle("item-title");
		item.setPicUrl("http//localhost");
		List<ImageTextItem> itemList = new ArrayList<ImageTextItem>();
		itemList.add(item);
		
		ImageTextArticles articles = new ImageTextArticles();
		articles.setItemList(itemList);
		response.setArticles(articles);
		*/
		String xml = "<com.yimayhd.weixincenter.model.response.ImageTextArticles><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime>";
		xml += " <MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></com.yimayhd.weixincenter.model.response.ImageTextArticles>";
		XStream xstream = new XStream(new WeixinXppDriver());
		xstream.autodetectAnnotations(true);
		TextMessageRequest request = (TextMessageRequest) xstream.fromXML(xml);
		 
		
	
		System.out.println(JSONObject.toJSONString(request));  
	}

	private BaseReqMsg parseEvent(Document document) {
		// 事件消息
		String event = document.selectSingleNode("/xml/Event").getText();
		BaseReqMsg requestMsg = null;
		Event eventType = Event.valueOfCode(event);
		Node eventKeyNode = document.selectSingleNode("/xml/EventKey");
		Node ticketNode = document.selectSingleNode("/xml/Ticket");

		switch (eventType) {
		case SUBSCRIBE:
			SubscribeEventRequest subscribeReq = new SubscribeEventRequest();
			if (eventKeyNode != null) {
				subscribeReq.setEventKey(eventKeyNode.getText());
			}

			if (ticketNode != null) {
				subscribeReq.setTicket(ticketNode.getText());
			}

			subscribeReq.setEvent(Event.valueOfCode(event));
			requestMsg = subscribeReq;

			break;
		case SCAN:
			ScanEventRequest scanReq = new ScanEventRequest();
			if (eventKeyNode != null) {
				scanReq.setEventKey(eventKeyNode.getText());
			}
			if (ticketNode != null) {
				scanReq.setTicket(ticketNode.getText());
			}

			scanReq.setEvent(Event.valueOfCode(event));
			requestMsg = scanReq;
			break;
		case LOCATION:
			String latitude = document.selectSingleNode("/xml/Latitude").getText();
			String longitude = document.selectSingleNode("/xml/Longitude").getText();
			String precision = document.selectSingleNode("/xml/Precision").getText();

			LocationEventRequest locationReq = new LocationEventRequest();
			locationReq.setLatitude(Double.parseDouble(latitude));
			locationReq.setLongitude(Double.parseDouble(longitude));
			locationReq.setPrecision(Double.parseDouble(precision));

			locationReq.setEvent(Event.valueOfCode(event));
			requestMsg = locationReq;
			break;
		case CLICK:
			MenuClickEventRequest clickReq = new MenuClickEventRequest();
			String eventKey = document.selectSingleNode("/xml/EventKey").getText();
			clickReq.setEvent(Event.valueOfCode(event));
			clickReq.setEventKey(eventKey);
			requestMsg = clickReq;
			break;
		case VIEW:
			MenuViewEventRequest viewReq = new MenuViewEventRequest();
			if (eventKeyNode != null) {
				viewReq.setEventKey(eventKeyNode.getText());
			}
			viewReq.setEvent(Event.valueOfCode(event));
			requestMsg = viewReq;
			break;
		case UNSUBSCRIBE:
			SubscribeEventRequest unSubscribeReq = new SubscribeEventRequest();
			if (eventKeyNode != null) {
				unSubscribeReq.setEventKey(eventKeyNode.getText());
			}

			if (ticketNode != null) {
				unSubscribeReq.setTicket(ticketNode.getText());
			}

			unSubscribeReq.setEvent(Event.valueOfCode(event));
			requestMsg = unSubscribeReq;

			break;
		default:
			break;
		}

		return requestMsg;

	}
}
