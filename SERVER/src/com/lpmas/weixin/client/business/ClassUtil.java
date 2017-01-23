package com.lpmas.weixin.client.business;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lpmas.weixin.api.bean.receive.event.ClickEventBean;
import com.lpmas.weixin.api.bean.receive.event.LocationEventBean;
import com.lpmas.weixin.api.bean.receive.event.LocationSelectEventBean;
import com.lpmas.weixin.api.bean.receive.event.ScanCodePushEventBean;
import com.lpmas.weixin.api.bean.receive.event.ScanCodeWaitMessageEventBean;
import com.lpmas.weixin.api.bean.receive.event.ScanEventBean;
import com.lpmas.weixin.api.bean.receive.event.SendMessageJobFinishBean;
import com.lpmas.weixin.api.bean.receive.event.SendPictureFromAlbumEventBean;
import com.lpmas.weixin.api.bean.receive.event.SendPictureFromCameraEventBean;
import com.lpmas.weixin.api.bean.receive.event.SendPictureFromWeixinEventBean;
import com.lpmas.weixin.api.bean.receive.event.SendTemplateJobFinishBean;
import com.lpmas.weixin.api.bean.receive.event.SubscribeEventBean;
import com.lpmas.weixin.api.bean.receive.event.UnsubscribeEventBean;
import com.lpmas.weixin.api.bean.receive.event.ViewEventBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveImageBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveLinkBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveLocationBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveShortVideoBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveTextBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveVideoBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveVoiceBean;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.message.cs.SendCardCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.cs.SendImageCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.cs.SendLinkNewsCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.cs.SendMusicCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.cs.SendNewsCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.cs.SendTextCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.cs.SendVideoCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.cs.SendVoiceCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.mass.SendCardMassMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.mass.SendImageMassMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.mass.SendNewsMassMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.mass.SendTextMassMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.mass.SendVideoMassMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.mass.SendVoiceMassMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.user.SendCardMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.user.SendImageMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.user.SendNewsMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.user.SendTextMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.user.SendVideoMessageRequestBean;
import com.lpmas.weixin.api.bean.request.message.user.SendVoiceMessageRequestBean;
import com.lpmas.weixin.api.bean.request.template.SendTemplateMessageRequestBean;
import com.lpmas.weixin.api.config.ReceiveEventConfig;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;
import com.lpmas.weixin.api.config.SendMessageTypeConfig;
import com.lpmas.weixin.api.request.message.SendCustomServiceMessage;
import com.lpmas.weixin.api.request.message.SendMassMessage;
import com.lpmas.weixin.api.request.message.SendMessage;
import com.lpmas.weixin.api.request.template.SendTemplateMessage;
import com.lpmas.weixin.client.config.MessageSendTypeConfig;

public class ClassUtil {
	private static Map<String, Class<?>> classMap = new ConcurrentHashMap<String, Class<?>>();

	public static Class<?> getClass(String className) throws ClassNotFoundException {
		Class<?> result = classMap.get(className);
		if (result == null) {
			Class<?> clazz = Class.forName(className);
			classMap.put(className, clazz);
			return clazz;
		}
		return result;
	}

	public static String getReceiveBeanClassName(String msgType, String event) {
		if (msgType == null) {
			throw new IllegalArgumentException("参数错误!msgType:" + msgType + "event:" + event);
		}
		// 首先判断是消息还是事件
		if (ReceiveMessageTypeConfig.RMT_EVENT.equals(msgType)) {
			if (event == null) {
				throw new IllegalArgumentException("参数错误!msgType:" + msgType + "event:" + event);
			}
			// 事件
			if (ReceiveEventConfig.EVENT_SUBSCRIBE.equals(event)) {
				return SubscribeEventBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_UNSUBSCRIBE.equals(event)) {
				return UnsubscribeEventBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_SCAN.equals(event)) {
				return ScanEventBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_LOCATION.equals(event)) {
				return LocationEventBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_CLICK.equals(event)) {
				return ClickEventBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_VIEW.equals(event)) {
				return ViewEventBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_SEND_MESSAGE_JOB_FINISH.equals(event)) {
				return SendMessageJobFinishBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_SEND_TEMPLATE_JOB_FINISH.equals(event)) {
				return SendTemplateJobFinishBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_SCAN_CODE_PUSH.equals(event)) {
				return ScanCodePushEventBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_SCAN_CODE_WAIT_MESSAGE.equals(event)) {
				return ScanCodeWaitMessageEventBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_SEND_PICTURE_FROM_CAMERA.equals(event)) {
				return SendPictureFromCameraEventBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_SEND_PICTURE_FROM_ALBUM.equals(event)) {
				return SendPictureFromAlbumEventBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_SEND_PICTURE_FROM_WEIXIN.equals(event)) {
				return SendPictureFromWeixinEventBean.class.getName();
			} else if (ReceiveEventConfig.EVENT_LOCATION_SELECT.equals(event)) {
				return LocationSelectEventBean.class.getName();
			} else {
				return "";
			}
		} else if (ReceiveMessageTypeConfig.RMT_TEXT.equals(msgType)) {
			// 消息
			return ReceiveTextBean.class.getName();
		} else if (ReceiveMessageTypeConfig.RMT_IMAGE.equals(msgType)) {
			return ReceiveImageBean.class.getName();
		} else if (ReceiveMessageTypeConfig.RMT_LINK.equals(msgType)) {
			return ReceiveLinkBean.class.getName();
		} else if (ReceiveMessageTypeConfig.RMT_VOICE.equals(msgType)) {
			return ReceiveVoiceBean.class.getName();
		} else if (ReceiveMessageTypeConfig.RMT_VIDEO.equals(msgType)) {
			return ReceiveVideoBean.class.getName();
		} else if (ReceiveMessageTypeConfig.RMT_SHORT_VIDEO.equals(msgType)) {
			return ReceiveShortVideoBean.class.getName();
		} else if (ReceiveMessageTypeConfig.RMT_LOCATION.equals(msgType)) {
			return ReceiveLocationBean.class.getName();
		}
		return "";
	}

	public static String getUserRequestBeanClassByMessageType(String messageType) {

		if (SendMessageTypeConfig.SMT_CARD.equals(messageType)) {
			return SendCardMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_IMAGE.equals(messageType)) {
			return SendImageMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_NEWS.equals(messageType)) {
			return SendNewsMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_TEXT.equals(messageType)) {
			return SendTextMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_VIDEO.equals(messageType)) {
			return SendVideoMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_VOICE.equals(messageType)) {
			return SendVoiceMessageRequestBean.class.getName();
		}
		return "";
	}

	public static String getMassRequestBeanClassByMessageType(String messageType) {

		if (SendMessageTypeConfig.SMT_CARD.equals(messageType)) {
			return SendCardMassMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_IMAGE.equals(messageType)) {
			return SendImageMassMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_NEWS.equals(messageType)) {
			return SendNewsMassMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_TEXT.equals(messageType)) {
			return SendTextMassMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_VIDEO.equals(messageType)) {
			return SendVideoMassMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_VOICE.equals(messageType)) {
			return SendVoiceMassMessageRequestBean.class.getName();
		}
		return "";
	}

	public static String getCustomRequestBeanClassByMessageType(String messageType) {

		if (SendMessageTypeConfig.SMT_CARD.equals(messageType)) {
			return SendCardCustomServiceMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_IMAGE.equals(messageType)) {
			return SendImageCustomServiceMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_NEWS.equals(messageType)) {
			return SendNewsCustomServiceMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_TEXT.equals(messageType)) {
			return SendTextCustomServiceMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_VIDEO.equals(messageType)) {
			return SendVideoCustomServiceMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_VOICE.equals(messageType)) {
			return SendVoiceCustomServiceMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_MUSIC.equals(messageType)) {
			return SendMusicCustomServiceMessageRequestBean.class.getName();
		} else if (SendMessageTypeConfig.SMT_LINK_NEWS.equals(messageType)) {
			return SendLinkNewsCustomServiceMessageRequestBean.class.getName();
		}
		return "";
	}

	public static String getRequestClassName(String sendType, String messageType) {

		if (MessageSendTypeConfig.SEND_TYPE_CUSTOM.equals(sendType)) {
			return getCustomRequestBeanClassByMessageType(messageType);
		} else if (MessageSendTypeConfig.SEND_TYPE_MASS.equals(sendType)) {
			return getMassRequestBeanClassByMessageType(messageType);
		} else if (MessageSendTypeConfig.SEND_TYPE_USER.equals(sendType)) {
			return getUserRequestBeanClassByMessageType(messageType);
		} else if (MessageSendTypeConfig.SEND_TYPE_TEMPLATE.equals(sendType)) {
			return SendTemplateMessageRequestBean.class.getName();
		}
		return "";
	}

	/**
	 * 根据CLASS NAME知道MESSAGE TYPE
	 */
	public static String getMessageTypeByRequestBean(WxRequestBaseBean requestBean) {

		if (requestBean instanceof SendCardMessageRequestBean) {
			return SendMessageTypeConfig.SMT_CARD;
		} else if (requestBean instanceof SendImageMessageRequestBean) {
			return SendMessageTypeConfig.SMT_IMAGE;
		} else if (requestBean instanceof SendNewsMessageRequestBean) {
			return SendMessageTypeConfig.SMT_NEWS;
		} else if (requestBean instanceof SendTextMessageRequestBean) {
			return SendMessageTypeConfig.SMT_TEXT;
		} else if (requestBean instanceof SendVideoMessageRequestBean) {
			return SendMessageTypeConfig.SMT_VIDEO;
		} else if (requestBean instanceof SendVoiceMessageRequestBean) {
			return SendMessageTypeConfig.SMT_VOICE;
		}

		else if (requestBean instanceof SendCardMassMessageRequestBean) {
			return SendMessageTypeConfig.SMT_CARD;
		} else if (requestBean instanceof SendImageMassMessageRequestBean) {
			return SendMessageTypeConfig.SMT_IMAGE;
		} else if (requestBean instanceof SendNewsMassMessageRequestBean) {
			return SendMessageTypeConfig.SMT_NEWS;
		} else if (requestBean instanceof SendTextMassMessageRequestBean) {
			return SendMessageTypeConfig.SMT_TEXT;
		} else if (requestBean instanceof SendVideoMassMessageRequestBean) {
			return SendMessageTypeConfig.SMT_VIDEO;
		} else if (requestBean instanceof SendVoiceMassMessageRequestBean) {
			return SendMessageTypeConfig.SMT_VOICE;
		}

		else if (requestBean instanceof SendCardCustomServiceMessageRequestBean) {
			return SendMessageTypeConfig.SMT_CARD;
		} else if (requestBean instanceof SendImageCustomServiceMessageRequestBean) {
			return SendMessageTypeConfig.SMT_IMAGE;
		} else if (requestBean instanceof SendNewsCustomServiceMessageRequestBean) {
			return SendMessageTypeConfig.SMT_NEWS;
		} else if (requestBean instanceof SendTextCustomServiceMessageRequestBean) {
			return SendMessageTypeConfig.SMT_TEXT;
		} else if (requestBean instanceof SendVideoCustomServiceMessageRequestBean) {
			return SendMessageTypeConfig.SMT_VIDEO;
		} else if (requestBean instanceof SendVoiceCustomServiceMessageRequestBean) {
			return SendMessageTypeConfig.SMT_VOICE;
		} else if (requestBean instanceof SendMusicCustomServiceMessageRequestBean) {
			return SendMessageTypeConfig.SMT_MUSIC;
		} else if (requestBean instanceof SendLinkNewsCustomServiceMessageRequestBean) {
			return SendMessageTypeConfig.SMT_LINK_NEWS;
		}
		return "";

	}

	public static String getExecuteClassNameBySendType(String sendType) {
		if (MessageSendTypeConfig.SEND_TYPE_CUSTOM.equals(sendType)) {
			return SendCustomServiceMessage.class.getName();
		} else if (MessageSendTypeConfig.SEND_TYPE_MASS.equals(sendType)) {
			return SendMassMessage.class.getName();
		} else if (MessageSendTypeConfig.SEND_TYPE_USER.equals(sendType)) {
			return SendMessage.class.getName();
		} else if (MessageSendTypeConfig.SEND_TYPE_TEMPLATE.equals(sendType)) {
			return SendTemplateMessage.class.getName();
		}
		return "";
	}
}
