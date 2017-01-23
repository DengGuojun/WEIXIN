package com.lpmas.weixin.api.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class ReceiveEventConfig {
	public static final String EVENT_SUBSCRIBE = "subscribe";// 关注
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";// 取消关注
	public static final String EVENT_SCAN = "SCAN";// 扫码
	public static final String EVENT_LOCATION = "LOCATION";// 地理位置
	public static final String EVENT_CLICK = "CLICK";// 点击
	public static final String EVENT_VIEW = "VIEW";// 跳转连接

	public static final String EVENT_SEND_MESSAGE_JOB_FINISH = "MASSSENDJOBFINISH";// 群发消息结果
	public static final String EVENT_SEND_TEMPLATE_JOB_FINISH = "TEMPLATESENDJOBFINISH";// 发送模版消息结果

	// 企业应用使用
	public static final String EVENT_SCAN_CODE_PUSH = "scancode_push";
	public static final String EVENT_SCAN_CODE_WAIT_MESSAGE = "scancode_waitmsg";// 扫码推事件且弹出“消息接收中”提示框的事件推送
	public static final String EVENT_SEND_PICTURE_FROM_CAMERA = "pic_sysphoto";// 弹出系统拍照发图的事件推送
	public static final String EVENT_SEND_PICTURE_FROM_ALBUM = "pic_photo_or_album";// 弹出拍照或者相册发图的事件推送
	public static final String EVENT_SEND_PICTURE_FROM_WEIXIN = "pic_weixin";// 弹出微信相册发图器的事件推送
	public static final String EVENT_LOCATION_SELECT = "location_select";// 弹出地理位置选择器的事件推送

	public static List<StatusBean<String, String>> EVENT_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> EVENT_MAP = new HashMap<String, String>();

	static {
		init();
	}

	private static void init() {
		initEventList();
		initEventMap();
	}

	private static void initEventList() {
		EVENT_LIST = new ArrayList<StatusBean<String, String>>();
		EVENT_LIST.add(new StatusBean<String, String>(EVENT_SUBSCRIBE, "关注"));
		EVENT_LIST.add(new StatusBean<String, String>(EVENT_UNSUBSCRIBE, "取消关注"));
		EVENT_LIST.add(new StatusBean<String, String>(EVENT_SCAN, "扫码"));
		EVENT_LIST.add(new StatusBean<String, String>(EVENT_LOCATION, "地理位置"));
		EVENT_LIST.add(new StatusBean<String, String>(EVENT_CLICK, "点击"));
		EVENT_LIST.add(new StatusBean<String, String>(EVENT_VIEW, "跳转连接"));

		EVENT_LIST.add(new StatusBean<String, String>(EVENT_SEND_MESSAGE_JOB_FINISH, "群发消息结果"));
		EVENT_LIST.add(new StatusBean<String, String>(EVENT_SEND_TEMPLATE_JOB_FINISH, "发送模版消息结果"));
	}

	private static void initEventMap() {
		EVENT_MAP = StatusKit.toMap(EVENT_LIST);
	}
}
