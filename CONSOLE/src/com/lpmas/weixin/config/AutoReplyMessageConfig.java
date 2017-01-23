package com.lpmas.weixin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class AutoReplyMessageConfig {

	public static int AR_TYPE_DEFAULT = 1;
	public static int AR_TYPE_KEYWORD = 2;
	public static int AR_TYPE_EVENT = 3;
	public static List<StatusBean<Integer, String>> AR_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static Map<Integer, String> AR_TYPE_MAP = new HashMap<Integer, String>();

	static {
		initArTypeList();
		initArTypeMap();
	}

	private static void initArTypeList() {
		AR_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		AR_TYPE_LIST.add(new StatusBean<Integer, String>(AR_TYPE_DEFAULT, "默认回复"));
		AR_TYPE_LIST.add(new StatusBean<Integer, String>(AR_TYPE_KEYWORD, "关键字回复"));
		AR_TYPE_LIST.add(new StatusBean<Integer, String>(AR_TYPE_EVENT, "事件回复"));
	}

	private static void initArTypeMap() {
		AR_TYPE_MAP = StatusKit.toMap(AR_TYPE_LIST);
	}

}
