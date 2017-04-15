package com.telan.weixincenter.domain.menu;

import java.io.Serializable;

/**
 * @author : zhangchao
 * @date : 2015年7月16日 上午9:58:43
 * @Description: 按钮的基类
 * 菜单项的基类，所有一级菜单、二级菜单都共有一个相同的属性，那就是name
 */
public class ButtonDO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String name;  

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
