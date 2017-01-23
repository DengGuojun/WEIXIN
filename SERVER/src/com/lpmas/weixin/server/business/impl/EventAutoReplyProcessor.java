package com.lpmas.weixin.server.business.impl;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.ReflectKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.bean.receive.ReceiveBaseBean;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;
import com.lpmas.weixin.config.AutoReplyMessageConfig;
import com.lpmas.weixin.server.business.AbstractAutoReplyMessageProcessor;

public class EventAutoReplyProcessor extends AbstractAutoReplyMessageProcessor {
	private static Logger logger = LoggerFactory.getLogger(EventAutoReplyProcessor.class);

	@Override
	protected boolean isMatchRule(String rule, ReceiveBaseBean bean) {
		String event = "";
		try {
			// 尝试强转
			EventBaseBean eventBean = (EventBaseBean) bean;
			event = eventBean.getEvent();

			// 拿到RULE,FIELD,VALUE
			String ruleEvent = getRule(rule);
			String field = getField(rule);
			String value = getRuleValue(rule);

			// 判断FIELD与VALUE
			if (StringKit.isValid(field)) {
				if (!StringKit.isValid(value))
					return false;
				Method getter = bean.getClass().getMethod(ReflectKit.getGetterName(field));
				String beanValue = (String) getter.invoke(bean);
				// 判断值是否相等
				return value.equalsIgnoreCase(beanValue);
			}
			// 判断事件
			return event.equalsIgnoreCase(ruleEvent);

		} catch (Exception e) {
			logger.error("", e.getMessage());
			return false;
		}
	}

	@Override
	protected int getAutoReplyType() {
		return AutoReplyMessageConfig.AR_TYPE_EVENT;
	}

	@Override
	protected String getReply(String rule, String content, String toUser, String fromUser) {
		return getTextReply(content, toUser, fromUser);
	}

	@Override
	protected boolean isProcess(ReceiveBaseBean bean) {
		// 只处理事件
		if (bean.getMsgType().equals(ReceiveMessageTypeConfig.RMT_EVENT)) {
			return true;
		}
		return false;
	}

}
