package com.telan.weixincenter.domain.menu;
/**
 * @author : zhangchao
 * @date : 2015年7月16日 上午9:59:03
 * @Description: 普通按钮（子按钮）
 * 子菜单项的封装。这里对子菜单是这样定义的：没有子菜单的菜单项，有可能是二级菜单项，也有可能是不含二级菜单的一级菜单。这类子菜单项一定会包含三个属性：type、name和key
 */
public class CommonButtonDO extends ButtonDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;    
    private String key;
    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	} 

}
