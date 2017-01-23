package com.lpmas.weixin.api.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class SendMessageTypeConfig {
	public static final String SMT_TEXT = "text";// 文本
	public static final String SMT_IMAGE = "image";// 图片
	public static final String SMT_VOICE = "voice";// 语音
	public static final String SMT_VIDEO = "mpvideo";// 视频
	public static final String SMT_MUSIC = "music";// 音乐
	public static final String SMT_NEWS = "mpnews";// 图文
	public static final String SMT_CARD = "wxcard";// 卡券
	public static final String SMT_LINK_NEWS = "news";// 外链图文信息，仅客服信息使用

	public static List<StatusBean<String, String>> SEND_MESSAGE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> SEND_MESSAGE_TYPE_MAP = new HashMap<String, String>();

	static {
		init();
	}

	private static void init() {
		initSendMessageTypeList();
		initSendMessageTypeMap();
	}

	private static void initSendMessageTypeList() {
		SEND_MESSAGE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		SEND_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(SMT_TEXT, "文本"));
		SEND_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(SMT_IMAGE, "图片"));
		SEND_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(SMT_VIDEO, "视频"));
		SEND_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(SMT_VOICE, "语音"));
		SEND_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(SMT_MUSIC, "音乐"));
		SEND_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(SMT_NEWS, "图文"));
		SEND_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(SMT_CARD, "卡券"));
		SEND_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(SMT_LINK_NEWS, "外链图文"));
	}

	private static void initSendMessageTypeMap() {
		SEND_MESSAGE_TYPE_MAP = StatusKit.toMap(SEND_MESSAGE_TYPE_LIST);
	}
}
