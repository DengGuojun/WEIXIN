package com.lpmas.weixin.client.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class MessageSendTypeConfig {
	
	public static final String SEND_TYPE_USER = "user";
	public static final String SEND_TYPE_MASS = "mass";
	public static final String SEND_TYPE_CUSTOM = "custom";
	public static final String SEND_TYPE_TEMPLATE = "template";
	
	public static List<StatusBean<String, String>> SEND_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> SEND_TYPE_MAP = new HashMap<String, String>();

	static {
		initMessageTypeList();
		initMessageTypeMap();
	}

	private static void initMessageTypeList() {
		SEND_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		SEND_TYPE_LIST.add(new StatusBean<String, String>(SEND_TYPE_USER, "群发特定用户"));
		SEND_TYPE_LIST.add(new StatusBean<String, String>(SEND_TYPE_MASS, "群发所有用户"));
		SEND_TYPE_LIST.add(new StatusBean<String, String>(SEND_TYPE_CUSTOM, "客服消息发送"));
		SEND_TYPE_LIST.add(new StatusBean<String, String>(SEND_TYPE_TEMPLATE, "模板消息发送"));
	}

	private static void initMessageTypeMap() {
		SEND_TYPE_MAP = StatusKit.toMap(SEND_TYPE_LIST);
	}
}
