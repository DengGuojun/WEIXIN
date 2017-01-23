package com.lpmas.weixin.api.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class ReplyMessageTypeConfig {
	public static final String PMT_TEXT = "text";// 文本
	public static final String PMT_NEWS = "news";// 图文
	public static final String PMT_IMAGE = "image";// 图片
	public static final String PMT_VOICE = "voice";// 语音
	public static final String PMT_VIDEO = "video";// 视频
	public static final String PMT_MUSIC = "music";// 音乐

	public static List<StatusBean<String, String>> REPLY_MESSAGE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> REPLY_MESSAGE_TYPE_MAP = new HashMap<String, String>();

	static {
		init();
	}

	private static void init() {
		initReplyMessageTypeList();
		initReplyMessageTypeMap();
	}

	private static void initReplyMessageTypeList() {
		REPLY_MESSAGE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		REPLY_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(PMT_TEXT, "文本"));
		REPLY_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(PMT_IMAGE, "图片"));
		REPLY_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(PMT_VIDEO, "视频"));
		REPLY_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(PMT_VOICE, "语音"));
		REPLY_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(PMT_NEWS, "图文"));
		REPLY_MESSAGE_TYPE_LIST.add(new StatusBean<String, String>(PMT_MUSIC, "音乐"));
	}

	private static void initReplyMessageTypeMap() {
		REPLY_MESSAGE_TYPE_MAP = StatusKit.toMap(REPLY_MESSAGE_TYPE_LIST);
	}
}
