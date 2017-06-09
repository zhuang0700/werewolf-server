package com.telan.weixincenter.service.Impl;

import com.telan.weixincenter.model.BackType;
import com.telan.weixincenter.model.MsgType;
import com.telan.weixincenter.model.request.BaseReqMsg;
import com.telan.weixincenter.model.request.TextMessageRequest;
import com.telan.weixincenter.model.response.*;
import com.telan.weixincenter.service.MessageReplyService;
import com.telan.weixincenter.utils.AnswerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Date;

public class MessageReplyServiceImpl implements MessageReplyService {
//	@Autowired
//	private WxMaterialManager wxMaterialManager;
//	@Autowired
//	private WxServiceBackManager wxServiceBackManager;
//	@Autowired
//	private WxServiceKeywordManager wxServiceKeywordManager;
//	@Autowired
//	private WxServiceRuleManager wxServiceRuleManager;
//	@Autowired
//	private WxMenuItemManager wxMenuItemManager;

//	private TextMessageResponse getNewQuestion() {
//		TextMessageResponse textMessageResponse = new TextMessageResponse();
//		textMessageResponse.setContent(AnswerUtils.getQuestionMessage());
//		return textMessageResponse;
//	}
//
//	private TextMessageResponse getSuccsessResponse() {
//		String answer = "回答正确！";
//		TextMessageResponse textMessageResponse = new TextMessageResponse();
//		textMessageResponse.setContent(answer);
//		return textMessageResponse;
//	}
//
//	private TextMessageResponse getFailResponse() {
//		String answer = "回答错误！";
//		TextMessageResponse textMessageResponse = new TextMessageResponse();
//		textMessageResponse.setContent(answer);
//		return textMessageResponse;
//	}
//
//	private TextMessageResponse getHelpResponse() {
//		String answer = "输入‘答题’获取最新题目，输入‘回答：（答案）’答题。如：题目为‘1+1=？’，则回复‘回答：2’即可";
//		TextMessageResponse textMessageResponse = new TextMessageResponse();
//		textMessageResponse.setContent(answer);
//
//		return textMessageResponse;
//	}

	@Override
	public BaseRspMsg getCommonMessageReply(TextMessageRequest eventMsg, Long wxMerchantId, String urlPath) {
		String content = eventMsg.getContent().trim();
		if(StringUtils.isNotBlank(content)) {
			if(content.contains("答题")) {
				return getTextReplyMessage(eventMsg, AnswerUtils.getQuestionMessage());
			} else if(content.contains("#") || content.contains("回答")) {
				if(content.contains(AnswerUtils.getAnswerMessage(1))) {
					return getTextReplyMessage(eventMsg, AnswerUtils.getSuccessMessage());
				} else {
					return getTextReplyMessage(eventMsg, AnswerUtils.getFailMessage());
				}
			} else {
				return getTextReplyMessage(eventMsg, AnswerUtils.getHelpMessage());
			}
		}

		return getTextReplyMessage(eventMsg, AnswerUtils.getFailMessage());

	}

	/**
	 * 获取文本消息
	 *
	 * @param wxMaterial
	 * @param eventMsg
	 * @param wxServiceBack
	 * @return
	 */
	public TextMessageResponse getTextReplyMessage(BaseReqMsg eventMsg, String replyContent) {
		TextMessageResponse textMessage = new TextMessageResponse();
		textMessage.setContent(replyContent);
		textMessage.setToUserName(eventMsg.getFromUserName());
		textMessage.setFromUserName(eventMsg.getToUserName());
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MsgType.TEXT);

		return textMessage;
	}
//
//	/**
//	 * 获取图文回复消息
//	 *
//	 * @return
//	 */
//	public ImageTextMessageResponse getImageTextReplyMessage(WxMaterialDO wxMaterial, BaseReqMsg eventMsg,
//			Long wxMerchantId, Long materialId, String urlPath) {
//
//		ImageTextMessageResponse imageText = new ImageTextMessageResponse();
//		imageText.setToUserName(eventMsg.getFromUserName());
//		imageText.setFromUserName(eventMsg.getToUserName());
//		imageText.setCreateTime(new Date().getTime());
//
//		ImageTextItem item = new ImageTextItem();
//		String toUserName = eventMsg.getToUserName();
//		String fromUserName = eventMsg.getFromUserName();
//		String imgURL = ConfigUtils.getImagePath();// 获取图片的基本路径
//		String basePath = ConfigUtils.getCallbackDomain();// 获取项目访问的基本路径
//
//		// 拼装图文的第一个 单图文可以直接使用 多图文需要根据图文ID
//		// 查询所属的图文===========================
//		item.setTitle(wxMaterial.getMaterialTitle());
//		item.setDescription(wxMaterial.getMaterialDigest());
//		item.setPicUrl(imgURL + wxMaterial.getMaterialImgUrl());
//
//		if (StringUtils.isEmpty(wxMaterial.getMaterialLinkUrl())) {
//			item.setUrl(basePath + "/wxMaterial/materialDetails/" + wxMaterial.getId() + "/" + wxMerchantId + "?OPENID="
//					+ fromUserName + "&BeLongWeChat=" + toUserName);
//		} else {
//			String materialLinkUrl = wxMaterial.getMaterialLinkUrl() + "?OPENID=" + fromUserName + "&BeLongWeChat="
//					+ toUserName;
//
//			if (materialLinkUrl.startsWith(urlPath)) {
//				item.setUrl(materialLinkUrl);
//			} else {
//				item.setUrl(wxMaterial.getMaterialLinkUrl());
//			}
//		}
//
//		int materialType = wxMaterial.getMaterialTypeId();// 得出素材类型
//		// 判断回复的类型
//		MaterialType type = MaterialType.valueOfId(materialType);
//		switch (type) {
//		case SINGLE_IMAGE_TEXT:
//			return getSingleImageTextReplyMessage(wxMaterial, eventMsg, wxMerchantId, materialId, urlPath);
//		case MULTI_IMAGE_TEXT:
//			return getMultiImageTextReplyMessage(wxMaterial, eventMsg, wxMerchantId, materialId, urlPath);
//		case LOCATION:
//			return getLocationReplyMessage(wxMaterial, eventMsg, wxMerchantId, materialId, urlPath);
//		default:
//			// 暂时设置为null
//			imageText = null;
//			break;
//		}
//
//		return imageText;
//
//	}
//
//	public ImageTextMessageResponse getMultiImageTextReplyMessage(WxMaterialDO wxMaterial, BaseReqMsg eventMsg,
//			Long wxMerchantId, Long materialId, String urlPath) {
//
//		ImageTextMessageResponse imageText = new ImageTextMessageResponse();
//		imageText.setToUserName(eventMsg.getFromUserName());
//		imageText.setFromUserName(eventMsg.getToUserName());
//		imageText.setCreateTime(new Date().getTime());
//
//		ImageTextItem item = new ImageTextItem();
//		String toUserName = eventMsg.getToUserName();
//		String fromUserName = eventMsg.getFromUserName();
//		String imgURL = ConfigUtils.getImagePath();// 获取图片的基本路径
//		String basePath = ConfigUtils.getCallbackDomain();// 获取项目访问的基本路径
//
//		// 拼装图文的第一个 单图文可以直接使用 多图文需要根据图文ID
//		// 查询所属的图文===========================
//		item.setTitle(wxMaterial.getMaterialTitle());
//		item.setDescription(wxMaterial.getMaterialDigest());
//		item.setPicUrl(imgURL + wxMaterial.getMaterialImgUrl());
//
//		if (StringUtils.isEmpty(wxMaterial.getMaterialLinkUrl())) {
//			item.setUrl(basePath + "/wxMaterial/materialDetails/" + wxMaterial.getId() + "/" + wxMerchantId + "?OPENID="
//					+ fromUserName + "&BeLongWeChat=" + toUserName);
//		} else {
//			String materialLinkUrl = wxMaterial.getMaterialLinkUrl() + "?OPENID=" + fromUserName + "&BeLongWeChat="
//					+ toUserName;
//
//			if (materialLinkUrl.startsWith(urlPath)) {
//				item.setUrl(materialLinkUrl);
//			} else {
//				item.setUrl(wxMaterial.getMaterialLinkUrl());
//			}
//		}
//
//		List<ImageTextItem> itemList = new ArrayList<ImageTextItem>();
//
//		List<WxMaterialDO> wxMaterialList = wxMaterialManager.queryWxMaterialTypeid(materialId, wxMerchantId);// 查询关联素材
//		// 组合多图文
//		if (wxMaterialList.size() > 0)// 有附属图文
//		{
//			itemList.add(item);
//			Iterator<WxMaterialDO> iteratorMaterial = wxMaterialList.iterator();
//			while (iteratorMaterial.hasNext()) {
//				WxMaterialDO wxMaterial2 = iteratorMaterial.next();
//				ImageTextItem subItem = new ImageTextItem();
//				subItem.setTitle(wxMaterial2.getMaterialTitle());
//				subItem.setDescription(wxMaterial2.getMaterialDigest());
//				subItem.setPicUrl(imgURL + wxMaterial2.getMaterialImgUrl());
//
//				if (StringUtils.isEmpty(wxMaterial2.getMaterialLinkUrl()) || wxMaterial2.getMaterialLinkUrl() == null) {
//					subItem.setUrl(basePath + "/wxMaterial/materialDetails/" + wxMaterial2.getId() + "/" + wxMerchantId
//							+ "?OPENID=" + fromUserName + "&BeLongWeChat=" + toUserName);
//				} else {
//					String materialLinkUrl = wxMaterial2.getMaterialLinkUrl() + "?OPENID=" + fromUserName
//							+ "&BeLongWeChat=" + toUserName;
//					if (materialLinkUrl.startsWith(urlPath)) {
//						subItem.setUrl(materialLinkUrl);
//					} else {
//						subItem.setUrl(wxMaterial2.getMaterialLinkUrl());
//					}
//				}
//				itemList.add(subItem);
//			}
//		} else {// 没有附属 就返回主图文的
//			itemList.add(item);
//		}
//
//		// 设置图文消息个数
//		imageText.setArticleCount(itemList.size());
//		// 设置图文消息包含的图文集合
//		ImageTextArticles articles = new ImageTextArticles();
//		articles.setItemList(itemList);
//		// 设置图文消息包含的图文集合
//		imageText.setArticles(articles);
//		imageText.setMsgType(MsgType.NEWS);
//
//		return imageText;
//
//	}
//

//
//	public ImageTextMessageResponse getLocationReplyMessage(WxMaterialDO wxMaterial, BaseReqMsg eventMsg,
//			Long wxMerchantId, Long materialId, String urlPath) {
//
//		ImageTextMessageResponse imageText = new ImageTextMessageResponse();
//
//		imageText.setToUserName(eventMsg.getFromUserName());
//		imageText.setFromUserName(eventMsg.getToUserName());
//		imageText.setCreateTime(new Date().getTime());
//
//		ImageTextItem item = new ImageTextItem();
//		String toUserName = eventMsg.getToUserName();
//		String fromUserName = eventMsg.getFromUserName();
//		String imgURL = ConfigUtils.getImagePath();// 获取图片的基本路径
//		String basePath = ConfigUtils.getCallbackDomain();// 获取项目访问的基本路径
//
//		// 拼装图文的第一个 单图文可以直接使用 多图文需要根据图文ID
//		// 查询所属的图文===========================
//		item.setTitle(wxMaterial.getMaterialTitle());
//		item.setDescription(wxMaterial.getMaterialDigest());
//		item.setPicUrl(imgURL + wxMaterial.getMaterialImgUrl());
//
//		if (StringUtils.isEmpty(wxMaterial.getMaterialLinkUrl())) {
//			item.setUrl(basePath + "/wxMaterial/materialDetails/" + wxMaterial.getId() + "/" + wxMerchantId + "?OPENID="
//					+ fromUserName + "&BeLongWeChat=" + toUserName);
//		} else {
//			String materialLinkUrl = wxMaterial.getMaterialLinkUrl() + "?OPENID=" + fromUserName + "&BeLongWeChat="
//					+ toUserName;
//
//			if (materialLinkUrl.startsWith(urlPath)) {
//				item.setUrl(materialLinkUrl);
//			} else {
//				item.setUrl(wxMaterial.getMaterialLinkUrl());
//			}
//		}
//
//		List<ImageTextItem> itemList = new ArrayList<ImageTextItem>();
//		// 取出当前地理位置,获取坐标
//		String addr = wxMaterial.getMaterialAddr();
//		/**
//		 * String str = ""; try { str =
//		 * Jsoup.connect("http://api.map.baidu.com/geocoder/v2/?address=" + addr
//		 * +
//		 * "&output=json&ak=E7447790a300f2e9590cb7ab2d22af45&callback=showLocation"
//		 * ).get().text(); } catch (IOException e) { // TODO Auto-generated
//		 * catch block e.printStackTrace(); } String lng =
//		 * str.substring(str.indexOf("lng") + 5, str.indexOf("lat") - 3); //
//		 * 截取坐标 String lat = str.substring(str.indexOf("lat") + 5,
//		 * str.indexOf("precise") - 4);
//		 */
//
//		ImageTextItem subItem = new ImageTextItem();
//		subItem.setTitle(wxMaterial.getMaterialTitle());
//		subItem.setDescription("点击可查看商家地理位置");
//		subItem.setPicUrl(basePath + "resources/images/3D_map.jpg");
//		subItem.setUrl("http://map.baidu.com/mobile/webapp/place/detail/qt=s&wd=" + addr
//				+ "&c=131&searchFlag=bigBox&version=5&exptype=dep&center_rank=1/i=0&showall=1&detail_from=list");
//		itemList.add(subItem);
//		// 设置图文消息个数
//		imageText.setArticleCount(itemList.size());
//		// 设置图文消息包含的图文集合
//		ImageTextArticles articles = new ImageTextArticles();
//		articles.setItemList(itemList);
//		// 设置图文消息包含的图文集合
//		imageText.setArticles(articles);
//
//		imageText.setMsgType(MsgType.LOCATION);
//
//		return imageText;
//
//	}
//
//	public ImageTextMessageResponse getSingleImageTextReplyMessage(WxMaterialDO wxMaterial, BaseReqMsg eventMsg,
//			Long wxMerchantId, Long materialId, String urlPath) {
//
//		ImageTextMessageResponse imageText = new ImageTextMessageResponse();
//		imageText.setToUserName(eventMsg.getFromUserName());
//		imageText.setFromUserName(eventMsg.getToUserName());
//		imageText.setCreateTime(new Date().getTime());
//
//		ImageTextItem item = new ImageTextItem();
//		String toUserName = eventMsg.getToUserName();
//		String fromUserName = eventMsg.getFromUserName();
//		String imgURL = ConfigUtils.getImagePath();// 获取图片的基本路径
//		String basePath = ConfigUtils.getCallbackDomain();// 获取项目访问的基本路径
//
//		// 拼装图文的第一个 单图文可以直接使用 多图文需要根据图文ID
//		// 查询所属的图文===========================
//		item.setTitle(wxMaterial.getMaterialTitle());
//		item.setDescription(wxMaterial.getMaterialDigest());
//		item.setPicUrl(imgURL + wxMaterial.getMaterialImgUrl());
//
//		if (StringUtils.isEmpty(wxMaterial.getMaterialLinkUrl())) {
//			item.setUrl(basePath + "/wxMaterial/materialDetails/" + wxMaterial.getId() + "/" + wxMerchantId + "?OPENID="
//					+ fromUserName + "&BeLongWeChat=" + toUserName);
//		} else {
//			String materialLinkUrl = wxMaterial.getMaterialLinkUrl() + "?OPENID=" + fromUserName + "&BeLongWeChat="
//					+ toUserName;
//
//			if (materialLinkUrl.startsWith(urlPath)) {
//				item.setUrl(materialLinkUrl);
//			} else {
//				item.setUrl(wxMaterial.getMaterialLinkUrl());
//			}
//		}
//
//		List<ImageTextItem> itemList = new ArrayList<ImageTextItem>();
//		itemList.add(item);
//		// 设置图文消息个数
//		imageText.setArticleCount(itemList.size());
//		ImageTextArticles articles = new ImageTextArticles();
//		articles.setItemList(itemList);
//		// 设置图文消息包含的图文集合
//		imageText.setArticles(articles);
//		imageText.setMsgType(MsgType.NEWS);
//
//		return imageText;
//
//	}
//
	@Override
	public BaseRspMsg getCommonEventReply(BaseReqMsg eventMsg, Long wxMerchantId, String urlPath) {
		// 0为被关注时回复；1为自动回复
		// 自动回复 back_type=1 通过back_type查询素材并进行分类型显示
		int backType = BackType.SUBSCRIBE_REPLY.getId();
//		WxServiceBackDO wxServiceBack = wxServiceBackManager.queryWxBackByMerchantIdAndBackType(wxMerchantId, backType);


		return getTextReplyMessage(eventMsg, AnswerUtils.getDefaultMessage());
	}
//

//
//	@Override
//	public BaseRspMsg getMenuMessageReply(MenuClickEventRequest eventMsg, Long wxMerchantId, String urlPath) {
//		WxMaterialDO wxMaterial = null;
//		int evenkeyNum = Integer.parseInt(eventMsg.getEventKey());
//		List<WxMaterialDO> wxMaterialList = wxMaterialManager.queryEventKey(evenkeyNum, wxMerchantId);
//		if (!CollectionUtils.isEmpty(wxMaterialList)) {
//			wxMaterial = wxMaterialList.get(0);
//			Long materialId = wxMaterial.getId();
//			MaterialType materialType = MaterialType.valueOfId(wxMaterial.getMaterialTypeId());
//			switch (materialType) {
//			case SINGLE_IMAGE_TEXT:
//				return getImageTextReplyMessage(wxMaterial, eventMsg, wxMerchantId, materialId, urlPath);
//			case MULTI_IMAGE_TEXT:
//				return getMultiImageTextReplyMessage(wxMaterial, eventMsg, wxMerchantId, materialId, urlPath);
//			case TEXT:
//				// FIXME
//				WxMenuItemDO wxMenuItem = wxMenuItemManager.queryWxItemInfo(evenkeyNum, wxMerchantId);
//				String textContent = "";
//				// 文本
//				if (wxMenuItem.getText() != null && !wxMenuItem.getText().equalsIgnoreCase("")) {
//					textContent = wxMenuItem.getText();
//				} else {
//					textContent = "正在获取信息,请稍后……";
//				}
//				return getTextReplyMessage(eventMsg, textContent);
//
//			default:
//				return null;
//			}
//
//		}else{
//			WxMenuItemDO wxMenuItem = wxMenuItemManager.queryWxItemInfo(evenkeyNum,wxMerchantId);
//			String textContent = "";
//			// 文本
//			if (wxMenuItem.getText() != null && !wxMenuItem.getText().equalsIgnoreCase("")) {
//				textContent = wxMenuItem.getText();
//			} else {
//				textContent = "正在获取信息,请稍后……";
//			}
//			return getTextReplyMessage(eventMsg, textContent);
//		}
//
//	}

}
