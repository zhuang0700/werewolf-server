package com.telan.weixincenter.model.response;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

public class ImageTextArticles {
	@XStreamImplicit(itemFieldName="item")
	private List<ImageTextItem> itemList = new ArrayList<ImageTextItem>();

	public List<ImageTextItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<ImageTextItem> itemList) {
		this.itemList = itemList;
	}

}
