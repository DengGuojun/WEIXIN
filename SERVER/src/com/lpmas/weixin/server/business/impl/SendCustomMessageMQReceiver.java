package com.lpmas.weixin.server.business.impl;

import com.lpmas.weixin.api.request.WxRequestBase;
import com.lpmas.weixin.api.request.message.SendCustomServiceMessage;
import com.lpmas.weixin.server.business.SendMessageMQRecevicer;

public class SendCustomMessageMQReceiver extends SendMessageMQRecevicer {

	public SendCustomMessageMQReceiver(String brokerId) {
		super(brokerId);
	}

	@Override
	protected WxRequestBase getRquest() {
		return new SendCustomServiceMessage();
	}

}
