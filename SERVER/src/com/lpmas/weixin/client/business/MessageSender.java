package com.lpmas.weixin.client.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.mq.activemq.ActiveMQSender;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.client.bean.request.SendMessageRequestBean;
import com.lpmas.weixin.client.config.MessageSendTypeConfig;
import com.lpmas.weixin.client.config.WeixinMQConfig;
import com.lpmas.weixin.client.factory.WeixinMQFactory;

public class MessageSender {
	private static Logger logger = LoggerFactory.getLogger(MessageSender.class);

	public boolean sendMessage(String appCode, String storeCode, String sendType, WxRequestBaseBean baseBean) {
		if (!MessageSendTypeConfig.SEND_TYPE_MAP.containsKey(sendType)) {
			logger.error(sendType + "发送类型非法");
			throw new IllegalArgumentException(sendType + "发送类型非法");
		}
		String queueName = "";
		if (sendType.equals(MessageSendTypeConfig.SEND_TYPE_TEMPLATE)) {
			queueName = WeixinMQConfig.QN_SEND_TEMPLATE_MESSAGE;
		} else if (sendType.equals(MessageSendTypeConfig.SEND_TYPE_CUSTOM)) {
			queueName = WeixinMQConfig.QN_SEND_CUSTOM_MESSAGE;
		} else if (sendType.equals(MessageSendTypeConfig.SEND_TYPE_MASS)) {
			queueName = WeixinMQConfig.QN_SEND_MASS_MESSAGE;
		} else if (sendType.equals(MessageSendTypeConfig.SEND_TYPE_USER)) {
			queueName = WeixinMQConfig.QN_SEND_USER_MESSAGE;
		}

		try {
			SendMessageRequestBean requestBean = new SendMessageRequestBean();
			requestBean.setAppCode(appCode);
			requestBean.setStoreCode(storeCode);
			requestBean.setSendType(sendType);
			requestBean.setRequestContent(JsonKit.toJson(baseBean));
			ActiveMQSender sender = (ActiveMQSender) new WeixinMQFactory().getMQSender();
			sender.send(queueName, JsonKit.toJson(requestBean));
			return true;
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}

	public boolean sendTemplateMessage(String appCode, String storeCode, WxRequestBaseBean baseBean) {
		return sendMessage(appCode, storeCode, MessageSendTypeConfig.SEND_TYPE_TEMPLATE, baseBean);
	}

	public boolean sendCustomMessage(String appCode, String storeCode, WxRequestBaseBean baseBean) {
		return sendMessage(appCode, storeCode, MessageSendTypeConfig.SEND_TYPE_CUSTOM, baseBean);
	}

	public boolean sendMassMessage(String appCode, String storeCode, WxRequestBaseBean baseBean) {
		return sendMessage(appCode, storeCode, MessageSendTypeConfig.SEND_TYPE_MASS, baseBean);
	}

	public boolean sendUserMessage(String appCode, String storeCode, WxRequestBaseBean baseBean) {
		return sendMessage(appCode, storeCode, MessageSendTypeConfig.SEND_TYPE_USER, baseBean);
	}

}
