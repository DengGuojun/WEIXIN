package com.lpmas.weixin.console.business;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.nosql.mongodb.MongoDBConfig;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.ReflectKit;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.config.ReceiveEventConfig;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;
import com.lpmas.weixin.bean.ReceiveEventBean;
import com.lpmas.weixin.bean.WeixinUserInfoBean;
import com.lpmas.weixin.console.dao.WeixinReceiveEventDao;

public class WeixinReceiveEventBusiness {

	Map<String, Object> result = new LinkedHashMap<String, Object>();

	public PageResultBean<ReceiveEventBean> getWeixinReceiveEventPageList(Map<String, Object> condMap,
			PageBean pageBean) {
		WeixinReceiveEventDao weixinReceiveEventDao = new WeixinReceiveEventDao();
		Map<String, Object> orderBy = new HashMap<String, Object>();
		orderBy.put("createTime", MongoDBConfig.SORT_ORDER_DESC);
		return weixinReceiveEventDao.getWeixinReceiveEventPageList(condMap, pageBean, orderBy);
	}

	public ReceiveEventBean getWeixinReceiveEventBeanByKey(String _id) {
		WeixinReceiveEventDao weixinReceiveEventDao = new WeixinReceiveEventDao();
		return weixinReceiveEventDao.getWeixinReceiveEventBeanByKey(_id);
	}

	public Map<String, Object> getDisplayMapByEventBean(int accountId, EventBaseBean bean) {
		result.clear();
		// 处理字段名
		processField(accountId, bean);
		return result;
	}

	private boolean isJavaType(Class<?> clazz) {
		if (clazz.isPrimitive() || clazz.getName().equals(String.class.getName()))
			return true;
		return false;
	}

	@SuppressWarnings("rawtypes")
	private void processField(int accountId, Object bean) {
		List<Field> fields = BeanKit.getDeclaredFieldList(bean);
		WeixinUserInfoBusiness business = new WeixinUserInfoBusiness();
		WeixinUserInfoBean userInfoBean = null;
		// 遍历字段表
		for (Field field : fields) {
			// 判断是不是原始类型
			Class<?> type = field.getType();
			if (isJavaType(type)) {
				// 不是自定义类型
				Object value = ReflectKit.getPropertyValue(bean, field.getName());
				if (field.getName().equals("event")) {
					value = MapKit.getValueFromMap(value.toString(), ReceiveEventConfig.EVENT_MAP);
				} else if (field.getName().equals("msgType")) {
					value = MapKit.getValueFromMap(value.toString(), ReceiveMessageTypeConfig.RECEIVE_MESSAGE_TYPE_MAP);
				} else if (field.getName().equals("createTime")) {
					Long time = (Long) value;
					value = DateKit.formatDate(new Date(time * 1000), DateKit.DEFAULT_DATE_TIME_FORMAT);
				} else if (field.getName().equals("fromUserName")) {
					String openId = value.toString();
					// 查出这个用户的信息
					userInfoBean = business.getWeixinUserInfoByKey(accountId, openId);
					if (userInfoBean != null) {
						// 显示头像和呢成信息
						value = "<img width='50px' src='" + userInfoBean.getHeadImageUrl() + "'/>"
								+ userInfoBean.getNickName() + "(" + openId + ")";
					}
				}
				result.put(getDisplayNameByFieldName(field.getName()), value);
			} else {
				// 是自定义类型或者LIST
				if (List.class.isAssignableFrom(type)) {
					// 是LIST
					StringBuffer sb = new StringBuffer();
					List list = (List) ReflectKit.getPropertyValue(bean, field.getName());
					Iterator iterator = list.iterator();
					while (iterator.hasNext()) {
						sb.append(iterator.next() + ",");
					}
					if (sb.length() > 0) {
						result.put(getDisplayNameByFieldName(field.getName()), sb.substring(0, sb.length() - 1));
					}
				} else {
					// 是自定义类型
					Object object = ReflectKit.getPropertyValue(bean, field.getName());
					processField(accountId, object);
				}
			}
		}
	}

	private String getDisplayNameByFieldName(String fieldName) {
		switch (fieldName) {
		case "toUserName":
			return "目标公众号";
		case "appCode":
			return "应用";
		case "storeCode":
			return "商店";
		case "accountCode":
			return "公众号代码";
		case "event":
			return "事件";
		case "msgType":
			return "消息类型";
		case "fromUserName":
			return "发起用户";
		case "createTime":
			return "发起时间";
		case "eventKey":
			return "事件KEY值";
		case "latitude":
			return "纬度";
		case "longitude":
			return "经度";
		case "precision":
			return "精确度";
		case "agentId":
			return "企业应用ID";
		case "scanType":
			return "扫码类型";
		case "scanResult":
			return "扫码结果";
		case "ticket":
			return "换取二维码的TICKET";
		case "status":
			return "状态";
		case "totalCount":
			return "分组下的粉丝总数";
		case "filterCount":
			return "过滤粉丝数";
		case "sentCount":
			return "发送成功粉丝数";
		case "errorCount":
			return "发送失败粉丝数";
		case "msgId":
			return "群发消息ID";
		case "count":
			return "图片数量";
		case "pictureMd5Sum":
			return "图片MD5值";
		case "poiName":
			return "朋友圈POI的名字";
		case "label":
			return "地址标签";
		case "locationX":
			return "X坐标";
		case "locationY":
			return "Y坐标";
		case "scale":
			return "精度/比例尺";
		case "picUrl":
			return "图片链接";
		case "mediaId":
			return "媒体id";
		case "title":
			return "消息标题";
		case "description":
			return "消息描述";
		case "url":
			return "消息链接";
		case "thumbMediaId":
			return "缩略图Id";
		case "content":
			return "文字消息内容";
		case "format":
			return "语音格式";
		case "recognition":
			return "语音识别结果";
		default:
			return "";
		}
	}

}
