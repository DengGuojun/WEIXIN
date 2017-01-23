package com.lpmas.weixin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class WeixinPropertyConfig {
	public static final Integer TEMPLATETYPE = 1;
	public static List<StatusBean<Integer, String>> PROPERTY_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static HashMap<Integer, String> PROPERTY_TYPE_MAP = new HashMap<Integer, String>();

	static {
		initTemplateTypeList();
		initTemplateTypeMap();
	}

	private static void initTemplateTypeList() {
		PROPERTY_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		PROPERTY_TYPE_LIST.add(new StatusBean<Integer, String>(TEMPLATETYPE, "模板"));
	}

	private static void initTemplateTypeMap() {
		PROPERTY_TYPE_MAP = StatusKit.toMap(PROPERTY_TYPE_LIST);
	}
}
