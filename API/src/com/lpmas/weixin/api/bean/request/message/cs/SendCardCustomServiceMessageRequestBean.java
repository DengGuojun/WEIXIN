package com.lpmas.weixin.api.bean.request.message.cs;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.message.SendCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.config.SendMessageTypeConfig;

public class SendCardCustomServiceMessageRequestBean extends SendCustomServiceMessageRequestBean {
	public SendCardCustomServiceMessageRequestBean() {
		super(SendMessageTypeConfig.SMT_CARD);
	}

	@JsonIgnore
	private String cardId = "";
	@JsonIgnore
	private String cardExt = "";
	@JsonProperty("wxcard")
	private Map<String, Object> paramMap;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardExt() {
		return cardExt;
	}

	public void setCardExt(String cardExt) {
		this.cardExt = cardExt;
	}

	public Map<String, Object> getParamMap() {
		paramMap = new HashMap<String, Object>();
		paramMap.put("card_id", cardId);
		paramMap.put("card_ext", cardExt);
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
