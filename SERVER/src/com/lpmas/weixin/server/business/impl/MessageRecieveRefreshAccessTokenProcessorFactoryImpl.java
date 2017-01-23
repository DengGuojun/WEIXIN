package com.lpmas.weixin.server.business.impl;

import com.lpmas.console.group.MessageRecieveProcessor;
import com.lpmas.console.group.MessageRecieveProcessorFactory;

public class MessageRecieveRefreshAccessTokenProcessorFactoryImpl extends MessageRecieveProcessorFactory {

	@Override
	public MessageRecieveProcessor getMessageRecieveProcessor() {
		return new MessageRecieveRefreshAccessTokenProcessorImpl();
	}

}
