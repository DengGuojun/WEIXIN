package com.lpmas.weixin.server.business;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.api.bean.receive.ReceiveBaseBean;
import com.lpmas.weixin.server.business.impl.DefaultAutoReplyProcessor;
import com.lpmas.weixin.server.business.impl.EventAutoReplyProcessor;
import com.lpmas.weixin.server.business.impl.KeyWordAutoRepalyProcessor;

@SuppressWarnings("rawtypes")
public class AutoReplyMessageProcessor {
	private static Logger logger = LoggerFactory.getLogger(AutoReplyMessageProcessor.class);
	public static List<Class> AUTO_REPLY_PROCESSOR_CHAIN = new LinkedList<Class>();

	static {
		AUTO_REPLY_PROCESSOR_CHAIN = new LinkedList<Class>();
		AUTO_REPLY_PROCESSOR_CHAIN.add(KeyWordAutoRepalyProcessor.class);
		AUTO_REPLY_PROCESSOR_CHAIN.add(EventAutoReplyProcessor.class);
		AUTO_REPLY_PROCESSOR_CHAIN.add(DefaultAutoReplyProcessor.class);
	}

	public static ReturnMessageBean process(String accountCode, ReceiveBaseBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		try {
			for (Class clazz : AUTO_REPLY_PROCESSOR_CHAIN) {
				AbstractAutoReplyMessageProcessor processor = (AbstractAutoReplyMessageProcessor) clazz.newInstance();
				result = processor.getReplyMessage(accountCode, bean);
				if (result.getCode() == Constants.STATUS_VALID) {
					break;
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}
}
