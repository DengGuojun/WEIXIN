package com.lpmas.weixin.api.receive.util;

import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.util.XmlKit;
import com.lpmas.weixin.api.base.ApiErrorCode;
import com.lpmas.weixin.api.base.ApiException;
import com.lpmas.weixin.api.bean.receive.ReceiveBaseBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveImageBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveLinkBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveLocationBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveShortVideoBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveTextBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveVideoBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveVoiceBean;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;

public class RecevieMessageParser {
	// protected static void setAttribute(String message, ReceiveBaseBean bean)
	// {
	// bean.setToUserName(StringKit.validStr(root.elementText("ToUserName")));
	// bean.setMsgType(StringKit.validStr(root.elementText("MsgType")));
	// bean.setFromUserName(StringKit.validStr(root.elementText("FromUserName")));
	//
	// String createTime = StringKit.isValid(root.elementText("CreateTime")) ?
	// root.elementText("CreateTime") : "0";
	// bean.setCreateTime(Long.parseLong(createTime));
	// }
	//
	// private static void setMessageAttribute(String message, MessageBaseBean
	// bean) {
	// setAttribute(root, bean);
	// bean.setMsgId(StringKit.validStr(root.elementText("MsgId")));
	// }

	public static String getMessageType(String message) {
		return XmlKit.getValue(message, "MsgType");
	}

	public static ReceiveBaseBean parse(String message) throws ApiException {
		if (!StringKit.isValid(message)) {
			throw new ApiException(ApiErrorCode.RECEIVE_MSG_ERROR, "parse event error (null) [" + message + "]");
		}

		String msgType = getMessageType(message);
		ReceiveBaseBean bean = null;
		if (ReceiveMessageTypeConfig.RMT_TEXT.equalsIgnoreCase(msgType)) {
			bean = getReceiveTextBean(message);
		} else if (ReceiveMessageTypeConfig.RMT_IMAGE.equalsIgnoreCase(msgType)) {
			bean = getReceiveImageBean(message);
		} else if (ReceiveMessageTypeConfig.RMT_LINK.equalsIgnoreCase(msgType)) {
			bean = getReceiveLinkBean(message);
		} else if (ReceiveMessageTypeConfig.RMT_VOICE.equalsIgnoreCase(msgType)) {
			bean = getReceiveVoiceBean(message);
		} else if (ReceiveMessageTypeConfig.RMT_VIDEO.equalsIgnoreCase(msgType)) {
			bean = getReceiveVideoBean(message);
		} else if (ReceiveMessageTypeConfig.RMT_SHORT_VIDEO.equalsIgnoreCase(msgType)) {
			bean = getReceiveShortVideoBean(message);
		} else if (ReceiveMessageTypeConfig.RMT_LOCATION.equalsIgnoreCase(msgType)) {
			bean = getReceiveLocationBean(message);
		} else if (ReceiveMessageTypeConfig.RMT_EVENT.equalsIgnoreCase(msgType)) {
			bean = ReceiveEventParser.parse(message);
		} else {
			throw new ApiException(ApiErrorCode.RECEIVE_MSG_ERROR, "parse message error (msgType) [" + msgType + "]");
		}
		return bean;
	}

	private static ReceiveTextBean getReceiveTextBean(String message) {
		return XmlKit.toBean(message, ReceiveTextBean.class);
	}

	private static ReceiveImageBean getReceiveImageBean(String message) {
		return XmlKit.toBean(message, ReceiveImageBean.class);
	}

	private static ReceiveVoiceBean getReceiveVoiceBean(String message) {
		return XmlKit.toBean(message, ReceiveVoiceBean.class);
	}

	private static ReceiveVideoBean getReceiveVideoBean(String message) {
		return XmlKit.toBean(message, ReceiveVideoBean.class);
	}

	private static ReceiveShortVideoBean getReceiveShortVideoBean(String message) {
		return XmlKit.toBean(message, ReceiveShortVideoBean.class);
	}

	private static ReceiveLocationBean getReceiveLocationBean(String message) {
		return XmlKit.toBean(message, ReceiveLocationBean.class);
	}

	private static ReceiveLinkBean getReceiveLinkBean(String message) {
		return XmlKit.toBean(message, ReceiveLinkBean.class);
	}

}
