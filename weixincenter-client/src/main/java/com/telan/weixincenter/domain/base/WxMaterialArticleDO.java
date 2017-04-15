package com.telan.weixincenter.domain.base;

import java.io.Serializable;

/**
 * @author : zhangchao
 * @date : 2015年8月26日 下午5:19:40
 * @Description: 图文的模版
 */
public class WxMaterialArticleDO implements Serializable{
	/**
	 * 
	 */  
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//图文ID
	private Long id;
	
	// 图文消息名称  
	private String title;
	
	private String writer;
	
	// 图文消息描述
	private String description;
	
	//图文外链烈性
	private String business_select_type;
	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	private String picurl;
	
	private int show_cover;
	
	private String content;
	
	private String editorValue;
	
	// 图文消息原文链接
	private String content_source_url;
	
	//外链
	private String businessurl;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public int getShow_cover() {
		return show_cover;
	}
	public void setShow_cover(int show_cover) {
		this.show_cover = show_cover;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent_source_url() {
		return content_source_url;
	}
	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}
	public String getBusinessurl() {
		return businessurl;
	}
	public void setBusinessurl(String businessurl) {
		this.businessurl = businessurl;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getBusiness_select_type() {
		return business_select_type;
	}
	public void setBusiness_select_type(String business_select_type) {
		this.business_select_type = business_select_type;
	}
	public String getEditorValue() {
		return editorValue;
	}
	public void setEditorValue(String editorValue) {
		this.editorValue = editorValue;
	}
	

}