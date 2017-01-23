package com.lpmas.weixin.api.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class MaterialTypeConfig {
	public static final String MT_IMAGE = "image";
	public static final String MT_VIDEO = "video";
	public static final String MT_VOICE = "voice";
	public static final String MT_NEWS = "news";

	public static List<StatusBean<String, String>> MATERIAL_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> MATERIAL_TYPE_MAP = new HashMap<String, String>();

	static {
		init();
	}

	private static void init() {
		initMaterialTypeList();
		initMaterialTypeMap();
	}

	private static void initMaterialTypeList() {
		MATERIAL_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		MATERIAL_TYPE_LIST.add(new StatusBean<String, String>(MT_IMAGE, "图片"));
		MATERIAL_TYPE_LIST.add(new StatusBean<String, String>(MT_VIDEO, "视频"));
		MATERIAL_TYPE_LIST.add(new StatusBean<String, String>(MT_VOICE, "语音"));
		MATERIAL_TYPE_LIST.add(new StatusBean<String, String>(MT_NEWS, "图文"));
	}

	private static void initMaterialTypeMap() {
		MATERIAL_TYPE_MAP = StatusKit.toMap(MATERIAL_TYPE_LIST);
	}
}
