package com.lpmas.weixin.api.bean.request.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

//设置行业可在MP中完成，每月可修改行业1次
public class SetIndustryRequestBean extends WxRequestBaseBean {
	@JsonProperty("industry_id1")
	private String industryId1 = "0";
	@JsonProperty("industry_id2")
	private String industryId2 = "0";

	public String getIndustryId1() {
		return industryId1;
	}

	public void setIndustryId1(String industryId1) {
		this.industryId1 = industryId1;
	}

	public String getIndustryId2() {
		return industryId2;
	}

	public void setIndustryId2(String industryId2) {
		this.industryId2 = industryId2;
	}
}
