package com.lpmas.weixin.server.business.impl;

import com.lpmas.weixin.api.request.WxRequestBase;
import com.lpmas.weixin.api.request.template.SendTemplateMessage;
import com.lpmas.weixin.server.business.SendMessageMQRecevicer;

public class SendTemplateMessageMQReceiver extends SendMessageMQRecevicer {

	public SendTemplateMessageMQReceiver(String brokerId) {
		super(brokerId);
	}

	@Override
	protected WxRequestBase getRquest() {
		return new SendTemplateMessage();
	}

}
