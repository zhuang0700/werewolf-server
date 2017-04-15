package com.telan.weixincenter.domain.menu;
/**
 * @author :张超
 * @date :2015年7月16日 上午9:59:03
 * @Description: view类型的菜单 
 */
public class ViewButtonDO extends ButtonDO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;  
    private String url;   
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}  

}
