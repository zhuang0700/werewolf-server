package com.telan.weixincenter.domain.menu;
/**
 * @author :张超
 * @date :2015年7月16日 上午9:59:03
 * @Description: 复杂按钮（父按钮）
 * 父菜单项的封装。对父菜单项的定义：包含有二级菜单项的一级菜单。这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组
 */
public class ComplexButtonDO extends ButtonDO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonDO[] sub_button;//子菜单项数组
   
	public ButtonDO[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(ButtonDO[] sub_button) {
		this.sub_button = sub_button;
	}

}
