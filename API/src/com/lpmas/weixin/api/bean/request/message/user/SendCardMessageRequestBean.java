package com.lpmas.weixin.api.bean.request.message.user;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.message.SendMessageRequestBean;
import com.lpmas.weixin.api.config.SendMessageTypeConfig;

public class SendCardMessageRequestBean extends SendMessageRequestBean {
	public SendCardMessageRequestBean() {
		super(SendMessageTypeConfig.SMT_CARD);
	}

	@JsonIgnore
	private String cardId = "";
	@JsonProperty("wxcard")
	private Map<String, Object> paramMap;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Map<String, Object> getParamMap() {
		paramMap = new HashMap<String, Object>();
		paramMap.put("card_id", cardId);
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
