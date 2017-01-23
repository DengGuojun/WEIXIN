package com.lpmas.weixin.server.business;

import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.bean.ReceiveEventBean;
import com.lpmas.weixin.client.business.JsonUtil;
import com.lpmas.weixin.server.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.server.dao.WeixinReceiveEventDao;

public class WeixinReceiveEventBusiness {

	public int addReceiveEventBean(String accountCode, EventBaseBean bean) {
		// 获取ACCOUNTID
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);
		WeixinReceiveEventDao dao = new WeixinReceiveEventDao();
		ReceiveEventBean eventBean = new ReceiveEventBean();
		eventBean.setEvent(bean.getEvent());
		eventBean.setMsgType(bean.getMsgType());
		eventBean.setFromUserName(bean.getFromUserName());
		eventBean.setCreateTime(System.currentTimeMillis());
		eventBean.setEventContentClassName(bean.getClass().getName());
		eventBean.setEventContent(JsonUtil.toJson(bean));
		eventBean.setAccountId(accountId);
		return dao.insertReceiveEventBean(eventBean);
	}

}
