package com.lpmas.weixin.api.bean.response.material;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetMaterialListResponseBean<T> extends WxResponseBaseBean {
	@JsonProperty("total_count")
	private int totalCount;
	@JsonProperty("item_count")
	private int itemCount;
	@JsonProperty("item")
	private List<T> itemList;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public List<T> getItemList() {
		return itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}
}
