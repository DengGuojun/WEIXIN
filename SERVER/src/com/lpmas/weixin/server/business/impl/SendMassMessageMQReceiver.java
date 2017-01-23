package com.lpmas.weixin.server.business.impl;

import com.lpmas.weixin.api.request.WxRequestBase;
import com.lpmas.weixin.api.request.message.SendMassMessage;
import com.lpmas.weixin.server.business.SendMessageMQRecevicer;

public class SendMassMessageMQReceiver extends SendMessageMQRecevicer {

	public SendMassMessageMQReceiver(String brokerId) {
		super(brokerId);
	}

	@Override
	protected WxRequestBase getRquest() {
		return new SendMassMessage();
	}

}
