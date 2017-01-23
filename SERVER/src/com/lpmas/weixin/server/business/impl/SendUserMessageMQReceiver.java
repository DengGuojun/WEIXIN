package com.lpmas.weixin.server.business.impl;

import com.lpmas.weixin.api.request.WxRequestBase;
import com.lpmas.weixin.api.request.message.SendMessage;
import com.lpmas.weixin.server.business.SendMessageMQRecevicer;

public class SendUserMessageMQReceiver extends SendMessageMQRecevicer {

	public SendUserMessageMQReceiver(String brokerId) {
		super(brokerId);
	}

	@Override
	protected WxRequestBase getRquest() {
		return new SendMessage();
	}

}
