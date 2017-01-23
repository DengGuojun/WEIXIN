package com.lpmas.weixin.api.receive.util;

import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.util.XmlKit;
import com.lpmas.weixin.api.base.ApiErrorCode;
import com.lpmas.weixin.api.base.ApiException;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.bean.receive.ReceiveBaseBean;
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
import com.lpmas.weixin.api.config.ReceiveEventConfig;

public class ReceiveEventParser extends RecevieMessageParser {

	public static String getEventType(String message) {
		return XmlKit.getValue(message, "Event");
	}

	public static ReceiveBaseBean parse(String message) throws ApiException {
		if (!StringKit.isValid(message)) {
			throw new ApiException(ApiErrorCode.RECEIVE_MSG_ERROR, "parse event error (null) [" + message + "]");
		}

		String event = getEventType(message);
		EventBaseBean bean = null;
		if (ReceiveEventConfig.EVENT_SUBSCRIBE.equals(event)) {
			bean = getSubscribeEventBean(message);
		} else if (ReceiveEventConfig.EVENT_UNSUBSCRIBE.equalsIgnoreCase(event)) {
			bean = getUnsubscribeEventBean(message);
		} else if (ReceiveEventConfig.EVENT_SCAN.equalsIgnoreCase(event)) {
			bean = getScanEventBean(message);
		} else if (ReceiveEventConfig.EVENT_LOCATION.equalsIgnoreCase(event)) {
			bean = getLocationEventBean(message);
		} else if (ReceiveEventConfig.EVENT_CLICK.equalsIgnoreCase(event)) {
			bean = getClickEventBean(message);
		} else if (ReceiveEventConfig.EVENT_VIEW.equalsIgnoreCase(event)) {
			bean = getViewEventBean(message);
		} else if (ReceiveEventConfig.EVENT_SEND_MESSAGE_JOB_FINISH.equalsIgnoreCase(event)) {
			bean = getSendMessageJobFinishBean(message);
		} else if (ReceiveEventConfig.EVENT_SEND_TEMPLATE_JOB_FINISH.equalsIgnoreCase(event)) {
			bean = getSendTemplateJobFinishBean(message);
		} else if (ReceiveEventConfig.EVENT_SCAN_CODE_PUSH.equalsIgnoreCase(event)) {
			bean = getScanCodePushEventBean(message);
		} else if (ReceiveEventConfig.EVENT_SCAN_CODE_WAIT_MESSAGE.equalsIgnoreCase(event)) {
			bean = getScanCodeWaitMessageEventBean(message);
		} else if (ReceiveEventConfig.EVENT_SEND_PICTURE_FROM_CAMERA.equalsIgnoreCase(event)) {
			bean = getSendPictureFromCameraEventBean(message);
		} else if (ReceiveEventConfig.EVENT_SEND_PICTURE_FROM_ALBUM.equalsIgnoreCase(event)) {
			bean = getSendPictureFromAlbumEventBean(message);
		} else if (ReceiveEventConfig.EVENT_SEND_PICTURE_FROM_WEIXIN.equalsIgnoreCase(event)) {
			bean = getPicWeixinEventBean(message);
		} else if (ReceiveEventConfig.EVENT_LOCATION_SELECT.equalsIgnoreCase(event)) {
			bean = getLocationSelectEventBean(message);
		} else {
			throw new ApiException(ApiErrorCode.RECEIVE_MSG_ERROR, "receive event error (eventType) [" + event + "]");
		}

		return bean;
	}

	private static SubscribeEventBean getSubscribeEventBean(String message) {
		return XmlKit.toBean(message, SubscribeEventBean.class);
	}

	private static UnsubscribeEventBean getUnsubscribeEventBean(String message) {
		return XmlKit.toBean(message, UnsubscribeEventBean.class);
	}

	private static ScanEventBean getScanEventBean(String message) {
		return XmlKit.toBean(message, ScanEventBean.class);
	}

	private static LocationEventBean getLocationEventBean(String message) {
		return XmlKit.toBean(message, LocationEventBean.class);
	}

	private static ClickEventBean getClickEventBean(String message) {
		return XmlKit.toBean(message, ClickEventBean.class);
	}

	private static ViewEventBean getViewEventBean(String message) {
		return XmlKit.toBean(message, ViewEventBean.class);
	}

	private static SendMessageJobFinishBean getSendMessageJobFinishBean(String message) {
		return XmlKit.toBean(message, SendMessageJobFinishBean.class);
	}

	private static SendTemplateJobFinishBean getSendTemplateJobFinishBean(String message) {
		return XmlKit.toBean(message, SendTemplateJobFinishBean.class);
	}

	private static LocationSelectEventBean getLocationSelectEventBean(String message) {
		return XmlKit.toBean(message, LocationSelectEventBean.class);
	}

	private static ScanCodeWaitMessageEventBean getScanCodeWaitMessageEventBean(String message) {
		return XmlKit.toBean(message, ScanCodeWaitMessageEventBean.class);
	}

	private static ScanCodePushEventBean getScanCodePushEventBean(String message) {
		return XmlKit.toBean(message, ScanCodePushEventBean.class);
	}

	private static SendPictureFromWeixinEventBean getPicWeixinEventBean(String message) {
		return XmlKit.toBean(message, SendPictureFromWeixinEventBean.class);
	}

	private static SendPictureFromAlbumEventBean getSendPictureFromAlbumEventBean(String message) {
		return XmlKit.toBean(message, SendPictureFromAlbumEventBean.class);
	}

	private static SendPictureFromCameraEventBean getSendPictureFromCameraEventBean(String message) {
		return XmlKit.toBean(message, SendPictureFromCameraEventBean.class);
	}
}
