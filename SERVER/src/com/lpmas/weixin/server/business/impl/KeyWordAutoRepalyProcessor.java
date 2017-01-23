package com.lpmas.weixin.server.business.impl;

import com.lpmas.weixin.api.bean.receive.ReceiveBaseBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveTextBean;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;
import com.lpmas.weixin.config.AutoReplyMessageConfig;
import com.lpmas.weixin.server.business.AbstractAutoReplyMessageProcessor;

public class KeyWordAutoRepalyProcessor extends AbstractAutoReplyMessageProcessor {

	@Override
	protected boolean isMatchRule(String rule, ReceiveBaseBean bean) {
		if (!(bean instanceof ReceiveTextBean))
			return false;
		ReceiveTextBean textBean = (ReceiveTextBean) bean;
		String text = textBean.getContent().trim();
		return rule.equals(text) || text.matches(rule);
	}

	@Override
	protected int getAutoReplyType() {
		return AutoReplyMessageConfig.AR_TYPE_KEYWORD;
	}

	@Override
	protected String getReply(String rule, String content, String toUser, String fromUser) {
		return getTextReply(content, toUser, fromUser);
	}

	@Override
	protected boolean isProcess(ReceiveBaseBean bean) {
		// 只处理非事件
		if (bean.getMsgType().equals(ReceiveMessageTypeConfig.RMT_EVENT)) {
			return false;
		}
		return true;
	}

}
