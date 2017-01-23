package com.lpmas.weixin.api.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class ReceiveMessageTypeConfig {
	public static final String RMT_TEXT = "text";// 文本
	public static final String RMT_IMAGE = "image";// 图片
	public static final String RMT_LINK = "link";// 链接
	public static final String RMT_VOICE = "voice";// 声音
	public static final String RMT_VIDEO = "video";// 视频
	public static final String RMT_SHORT_VIDEO = "shortvideo";// 短视频
	public static final String RMT_LOCATION = "location";// 地理位置
	public static final String RMT_EVENT = "event";// 事件

	public static List<StatusBean<String, String>> RECEIVE_MESSAGE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> RECEIVE_MESSAGE_TYPE_MAP = new HashMap<String, String>();

	static {
		init();
	}

	private static void init() {
		initReceiveMessageTypeList();
		initReceiveMessageTypeMap();
	}

	private static void initReceiveMessageTypeList() {
		RECEIVE_MESSAGE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		RECEIVE_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(RMT_TEXT, "文本"));
		RECEIVE_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(RMT_IMAGE, "图片"));
		RECEIVE_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(RMT_LINK, "链接"));
		RECEIVE_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(RMT_VOICE, "声音"));
		RECEIVE_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(RMT_VIDEO, "视频"));
		RECEIVE_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(RMT_SHORT_VIDEO, "短视频"));
		RECEIVE_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(RMT_LOCATION, "地理位置"));
		RECEIVE_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(RMT_EVENT, "事件"));
	}

	private static void initReceiveMessageTypeMap() {
		RECEIVE_MESSAGE_TYPE_MAP = StatusKit.toMap(RECEIVE_MESSAGE_TYPE_LIST);
	}
}
