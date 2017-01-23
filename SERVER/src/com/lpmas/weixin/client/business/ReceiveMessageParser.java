package com.lpmas.weixin.client.business;

import java.util.HashMap;
import java.util.Map;

import com.lpmas.framework.util.ReflectKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.api.config.ReceiveEventConfig;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;

public class ReceiveMessageParser {

	public static String CLASS_NAME_KEY = "className";
	public static String CONTETNT_KEY = "content";
	private String toUserName = "";
	private String fromUserName = "";
	private String msgType = "";
	private String event = "";
	private Object baseBean;

	@SuppressWarnings("unchecked")
	public ReceiveMessageParser(String content) throws Exception {
		Map<String, String> contentMap = JsonUtil.toBean(content, HashMap.class);
		this.baseBean = JsonUtil.toBean(contentMap.get(CONTETNT_KEY),
				ClassUtil.getClass(contentMap.get(CLASS_NAME_KEY)));

		this.msgType = get("msgType");
		if (isEvent()) {
			this.event = get("event");
		}
		this.toUserName = get("toUserName");
		this.fromUserName = get("fromUserName");
	}

	public String getToUserName() {
		return toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public String getMsgType() {
		return msgType;
	}

	public String getEvent() {
		return event;
	}

	/**
	 * 从收到的对象中取出对应字段的值 不存在这个字段返回NULL
	 * 
	 * @param field
	 * @return
	 */
	public String get(String field) {
		String result = ReflectKit.getPropertyValue(baseBean, field).toString();
		return result == null ? "" : result;
	}

	/**
	 * 判断是否普通消息
	 * 
	 * @return
	 */
	public boolean isMessage() {
		if (!StringKit.isValid(msgType))
			return false;
		if (ReceiveMessageTypeConfig.RMT_IMAGE.equalsIgnoreCase(msgType)
				|| ReceiveMessageTypeConfig.RMT_LINK.equalsIgnoreCase(msgType)
				|| ReceiveMessageTypeConfig.RMT_LOCATION.equalsIgnoreCase(msgType)
				|| ReceiveMessageTypeConfig.RMT_SHORT_VIDEO.equalsIgnoreCase(msgType)
				|| ReceiveMessageTypeConfig.RMT_TEXT.equalsIgnoreCase(msgType)
				|| ReceiveMessageTypeConfig.RMT_VIDEO.equalsIgnoreCase(msgType)
				|| ReceiveMessageTypeConfig.RMT_VOICE.equalsIgnoreCase(msgType)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否文字消息
	 * 
	 * @return
	 */
	public boolean isTextMessage() {
		if (!isMessage())
			return false;
		if (ReceiveMessageTypeConfig.RMT_TEXT.equalsIgnoreCase(msgType))
			return true;
		return false;
	}

	/**
	 * 判断是否图片消息
	 * 
	 * @return
	 */
	public boolean isImageMessage() {
		if (!isMessage())
			return false;
		if (ReceiveMessageTypeConfig.RMT_IMAGE.equalsIgnoreCase(msgType))
			return true;
		return false;
	}

	/**
	 * 判断是否语音消息
	 * 
	 * @return
	 */
	public boolean isVoiceMessage() {
		if (!isMessage())
			return false;
		if (ReceiveMessageTypeConfig.RMT_VOICE.equalsIgnoreCase(msgType))
			return true;
		return false;
	}

	/**
	 * 判断是否视频消息
	 * 
	 * @return
	 */
	public boolean isVideoMessage() {
		if (!isMessage())
			return false;
		if (ReceiveMessageTypeConfig.RMT_VIDEO.equalsIgnoreCase(msgType))
			return true;
		return false;
	}

	/**
	 * 判断是否小视频消息
	 * 
	 * @return
	 */
	public boolean isShortVideoMessage() {
		if (!isMessage())
			return false;
		if (ReceiveMessageTypeConfig.RMT_SHORT_VIDEO.equalsIgnoreCase(msgType))
			return true;
		return false;
	}

	/**
	 * 判断是否地理位置消息
	 * 
	 * @return
	 */
	public boolean isLocationMessage() {
		if (!isMessage())
			return false;
		if (ReceiveMessageTypeConfig.RMT_LOCATION.equalsIgnoreCase(msgType))
			return true;
		return false;
	}

	/**
	 * 判断是否链接消息
	 * 
	 * @return
	 */
	public boolean isLinkMessage() {
		if (!isMessage())
			return false;
		if (ReceiveMessageTypeConfig.RMT_LINK.equalsIgnoreCase(msgType))
			return true;
		return false;
	}

	/**
	 * 判断是否事件
	 * 
	 * @return
	 */
	public boolean isEvent() {
		if (!StringKit.isValid(msgType))
			return false;
		if (ReceiveMessageTypeConfig.RMT_EVENT.equalsIgnoreCase(msgType))
			return true;
		return false;
	}

	/**
	 * 判断是否关注事件
	 * 
	 * @return
	 */
	public boolean isSubscribeEvent() {
		if (!isEvent() || !StringKit.isValid(event))
			return false;
		if (ReceiveEventConfig.EVENT_SUBSCRIBE.equals(event))
			return true;
		return false;
	}

	/**
	 * 判断是否取消关注事件
	 * 
	 * @return
	 */
	public boolean isUnsubscribeEvent() {
		if (!isEvent() || !StringKit.isValid(event))
			return false;
		if (ReceiveEventConfig.EVENT_UNSUBSCRIBE.equals(event))
			return true;
		return false;
	}

	/**
	 * 判断是否上报地理位置事件
	 * 
	 * @return
	 */
	public boolean isReportLocationEvent() {
		if (!isEvent() || !StringKit.isValid(event))
			return false;
		if (ReceiveEventConfig.EVENT_LOCATION.equals(event))
			return true;
		return false;
	}

	/**
	 * 判断是否地理位置选择事件
	 * 
	 * @return
	 */
	public boolean isLocationSelectEvent() {
		if (!isEvent() || !StringKit.isValid(event))
			return false;
		if (ReceiveEventConfig.EVENT_LOCATION_SELECT.equals(event))
			return true;
		return false;
	}

	/**
	 * 判断是否菜单点击事件(点击菜单获取消息)
	 * 
	 * @return
	 */
	public boolean isMenuClickEvent() {
		if (!isEvent() || !StringKit.isValid(event))
			return false;
		if (ReceiveEventConfig.EVENT_CLICK.equals(event))
			return true;
		return false;
	}

	/**
	 * 判断是否菜单点击事件(点击跳转链接)
	 * 
	 * @return
	 */
	public boolean isMenuLinkEvent() {
		if (!isEvent() || !StringKit.isValid(event))
			return false;
		if (ReceiveEventConfig.EVENT_VIEW.equals(event))
			return true;
		return false;
	}

	/**
	 * 判断是否扫码并关注事件(带参数二维码)
	 * 
	 * @return
	 */
	public boolean isSubscribeBySceneQrcodeEvent() {
		if (!isEvent() || !StringKit.isValid(event))
			return false;
		if (ReceiveEventConfig.EVENT_SUBSCRIBE.equals(event) && StringKit.isValid((String) get("eventKey")))
			return true;
		return false;
	}

	/**
	 * 判断是否扫描带参数二维码
	 * 
	 * @return
	 */
	public boolean isScanSceneQrcodeEvent() {
		if (!isEvent() || !StringKit.isValid(event))
			return false;
		if (ReceiveEventConfig.EVENT_SCAN.equals(event) && StringKit.isValid((String) get("eventKey")))
			return true;
		return false;
	}

}
