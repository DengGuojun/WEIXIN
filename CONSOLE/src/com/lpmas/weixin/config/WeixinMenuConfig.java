package com.lpmas.weixin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.StatusKit;

public class WeixinMenuConfig {
	// 菜单类型
	public static final Integer MENU_TYPE_DEFAULT = 1;
	public static final Integer MENU_TYPE_CONDITION = 2;
	public static List<StatusBean<Integer, String>> MENU_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static HashMap<Integer, String> MENU_TYPE_MAP = new HashMap<Integer, String>();

	// 菜单按钮类型
	public static final String BUTTON_TYPE_CLICK = "click";
	public static final String BUTTON_TYPE_VIEW = "view";
	public static final String BUTTON_TYPE_SCANCODE_PUSH = "scancode_push";
	public static final String BUTTON_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";
	public static final String BUTTON_TYPE_PIC_SYSPHOTO = "pic_sysphoto";
	public static final String BUTTON_TYPE_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	public static final String BUTTON_TYPE_PIC_WEIXIN = "pic_weixin";
	public static final String BUTTON_TYPE_LOCATION_SELECT = "location_select";
	public static final String BUTTON_TYPE_MEDIA_ID = "media_id";
	public static final String BUTTON_TYPE_VIEW_LIMITED = "view_limited";
	public static List<StatusBean<String, String>> BUTTON_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> BUTTON_TYPE_MAP = new HashMap<String, String>();
	
	//菜单个数
	public static final Integer ONE = 1;
	public static final Integer TWO = 2;
	public static final Integer THREE = 3;
	public static final Integer FOUR = 4;
	public static final Integer FIVE = 5;
	public static List<StatusBean<Integer, String>> FIRST_DEGREE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static HashMap<Integer, String> FIRST_DEGREE_MAP = new HashMap<Integer, String>();
	public static List<StatusBean<Integer, String>> SECOND_DEGREE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static HashMap<Integer, String> SECOND_DEGREE_MAP = new HashMap<Integer, String>();
	
	static {
		initMenuTypeList();
		initMenuTypeMap();

		initButtonTypeList();
		initButtonTypeMap();
		
		initFirstDegreeList();
		initFirstDegreeMap();
		
		initSecondDegreeList();
		initSecondDegreeMap();
	}

	private static void initMenuTypeList() {
		MENU_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		MENU_TYPE_LIST.add(new StatusBean<Integer, String>(MENU_TYPE_DEFAULT, "默认"));
		MENU_TYPE_LIST.add(new StatusBean<Integer, String>(MENU_TYPE_CONDITION, "条件"));
	}

	private static void initMenuTypeMap() {
		MENU_TYPE_MAP = StatusKit.toMap(MENU_TYPE_LIST);
	}

	private static void initButtonTypeList() {
		BUTTON_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		BUTTON_TYPE_LIST.add(new StatusBean<String, String>(BUTTON_TYPE_CLICK, "点击"));
		BUTTON_TYPE_LIST.add(new StatusBean<String, String>(BUTTON_TYPE_VIEW, "跳转URL"));
		BUTTON_TYPE_LIST.add(new StatusBean<String, String>(BUTTON_TYPE_SCANCODE_PUSH, "扫码推事件"));
		BUTTON_TYPE_LIST.add(new StatusBean<String, String>(BUTTON_TYPE_SCANCODE_WAITMSG, "扫码推事件且弹出“消息接收中”提示框"));
		BUTTON_TYPE_LIST.add(new StatusBean<String, String>(BUTTON_TYPE_PIC_SYSPHOTO, "弹出系统拍照发图"));
		BUTTON_TYPE_LIST.add(new StatusBean<String, String>(BUTTON_TYPE_PIC_PHOTO_OR_ALBUM, "弹出拍照或者相册发图"));
		BUTTON_TYPE_LIST.add(new StatusBean<String, String>(BUTTON_TYPE_PIC_WEIXIN, "弹出微信相册发图器"));
		BUTTON_TYPE_LIST.add(new StatusBean<String, String>(BUTTON_TYPE_LOCATION_SELECT, "弹出地理位置选择器"));
		BUTTON_TYPE_LIST.add(new StatusBean<String, String>(BUTTON_TYPE_MEDIA_ID, "下发消息（除文本消息）"));
		BUTTON_TYPE_LIST.add(new StatusBean<String, String>(BUTTON_TYPE_VIEW_LIMITED, "跳转图文消息URL"));
	}

	private static void initButtonTypeMap() {
		BUTTON_TYPE_MAP = StatusKit.toMap(BUTTON_TYPE_LIST);
	}
	
	private static void initFirstDegreeList() {
		FIRST_DEGREE_LIST = new ArrayList<StatusBean<Integer, String>>();
		FIRST_DEGREE_LIST.add(new StatusBean<Integer, String>(ONE, "1"));
		FIRST_DEGREE_LIST.add(new StatusBean<Integer, String>(TWO, "2"));
		FIRST_DEGREE_LIST.add(new StatusBean<Integer, String>(THREE, "3"));
	}

	private static void initFirstDegreeMap() {
		FIRST_DEGREE_MAP = StatusKit.toMap(FIRST_DEGREE_LIST);
	}
	
	private static void initSecondDegreeList() {
		SECOND_DEGREE_LIST = new ArrayList<StatusBean<Integer, String>>();
		SECOND_DEGREE_LIST.add(new StatusBean<Integer, String>(FOUR, "4"));
		SECOND_DEGREE_LIST.add(new StatusBean<Integer, String>(FIVE, "5"));
		SECOND_DEGREE_LIST = ListKit.combineList(FIRST_DEGREE_LIST,SECOND_DEGREE_LIST);
	}

	private static void initSecondDegreeMap() {
		SECOND_DEGREE_MAP = StatusKit.toMap(SECOND_DEGREE_LIST);
	}
}
