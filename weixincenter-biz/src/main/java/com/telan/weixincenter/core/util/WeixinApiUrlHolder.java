package com.telan.weixincenter.core.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

public class WeixinApiUrlHolder {
	@Autowired
	private Properties weixinConfigs;

	private String weixinapiDomain;

	/**
	 * 获取token的微信api地址
	 * 
	 * @return
	 */
	public String getAccessTokenUrl() {
		return weixinConfigs.getProperty("access.token.url");
	}

	public String getJsapiTicketUrl() {
		return weixinConfigs.getProperty("get.jsapi.ticket");
	}

	public String getMenuCreateUrl() {
		return weixinConfigs.getProperty("menu.create.url");
	}

	public String getFansUrl() {
		return weixinConfigs.getProperty("get.fans.url");
	}

	public String getBaseUrl() {
		return weixinConfigs.getProperty("base.url");
	}

	public String getSnsapiUserInfoBaseUrl() {
		return weixinConfigs.getProperty("snsapi.userinfo.baseurl");
	}

	public String getSnsapiBaseurl() {
		return weixinConfigs.getProperty("snsapi.base.baseurl");
	}

	public String getUserGroupUrl() {
		return weixinConfigs.getProperty("get.userGroup.url");
	}

	public String getAllGroupUrl() {
		return weixinConfigs.getProperty("get.allGroup.url");
	}

	public String getAddGroupUrl() {
		return weixinConfigs.getProperty("add.group.url");
	}

	public String getUpdateGroupUrl() {
		return weixinConfigs.getProperty("update.group.url");
	}

	public String getDelGroupUrl() {

		return weixinConfigs.getProperty("del.group.url");
	}

	public String getMoveGroupUrl() {
		return weixinConfigs.getProperty("move.group.url");
	}

	public String getSendMassTemplateUrl() {
		return weixinConfigs.getProperty("send.massTemplate.url");
	}

	public String getLongUrltoShortUrl() {
		return weixinConfigs.getProperty("longUrl.toShort.url");
	}

	public String getQrcodeCreateUrl() {
		return weixinConfigs.getProperty("qrcode.create.url");
	}

	public String getUserSummaryUrl() {
		return weixinConfigs.getProperty("get.userSummary.url");
	}

	public String getUserCumulateUrl() {
		return weixinConfigs.getProperty("get.userCumulate.url");
	}

	public String getUploadMediaUrl() {
		return weixinConfigs.getProperty("upload.media.url");
	}

	public String getDownloadMediaUrl() {
		return weixinConfigs.getProperty("download.media.url");
	}

	public String getWeixinCenterDomain() {
		return weixinapiDomain;
	}

	public Properties getWeixinConfigs() {
		return weixinConfigs;
	}

	public void setWeixinConfigs(Properties weixinConfigs) {
		this.weixinConfigs = weixinConfigs;
	}

	public String getWeixinapiDomain() {
		return weixinapiDomain;
	}

	public void setWeixinapiDomain(String weixinapiDomain) {
		this.weixinapiDomain = weixinapiDomain;
	}

}
