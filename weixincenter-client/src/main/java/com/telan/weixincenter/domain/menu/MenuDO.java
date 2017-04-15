package com.telan.weixincenter.domain.menu;

import java.io.Serializable;

/**
 * @author :张超
 * @date :2015年7月16日 上午9:59:03
 * @Description: 菜单
 * 整个菜单对象的封装，菜单对象包含多个菜单项（最多只能有3个），这些菜单项即可以是子菜单项（不含二级菜单的一级菜单），也可以是父菜单项（包含二级菜单的菜单项）
 */
public class MenuDO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonDO[] button;  

	public ButtonDO[] getButton() {
		return button;
	}

	public void setButton(ButtonDO[] button) {
		this.button = button;
	}

}
