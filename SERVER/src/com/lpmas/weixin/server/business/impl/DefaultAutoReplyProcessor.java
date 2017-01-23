package com.lpmas.weixin.server.business.impl;

import com.lpmas.weixin.api.bean.receive.ReceiveBaseBean;
import com.lpmas.weixin.config.AutoReplyMessageConfig;
import com.lpmas.weixin.server.business.AbstractAutoReplyMessageProcessor;

public class DefaultAutoReplyProcessor extends AbstractAutoReplyMessageProcessor {

	@Override
	protected boolean isMatchRule(String rule, ReceiveBaseBean bean) {
		return false;
	}

	@Override
	protected int getAutoReplyType() {
		return AutoReplyMessageConfig.AR_TYPE_DEFAULT;
	}

	@Override
	protected String getReply(String rule, String content, String toUser, String fromUser) {
		return getTextReply(content, toUser, fromUser);
	}

	@Override
	protected boolean isProcess(ReceiveBaseBean bean) {
		return true;
	}

}
