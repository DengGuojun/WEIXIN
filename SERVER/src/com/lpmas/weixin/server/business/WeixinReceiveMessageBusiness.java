package com.lpmas.weixin.server.business;

import com.lpmas.weixin.api.bean.receive.MessageBaseBean;
import com.lpmas.weixin.bean.ReceiveMessageBean;
import com.lpmas.weixin.client.business.JsonUtil;
import com.lpmas.weixin.server.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.server.dao.WeixinReceiveMessageDao;

public class WeixinReceiveMessageBusiness {

	public int addReceiveMessageBean(String accountCode, MessageBaseBean bean) {
		//获取ACCOUNTID
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);
		WeixinReceiveMessageDao dao = new WeixinReceiveMessageDao();
		ReceiveMessageBean messageBean = new ReceiveMessageBean();
		messageBean.setMsgId(bean.getMsgId());
		messageBean.setMsgType(bean.getMsgType());
		messageBean.setFromUserName(bean.getFromUserName());
		messageBean.setCreateTime(System.currentTimeMillis());
		messageBean.setMessageContentClassName(bean.getClass().getName());
		messageBean.setMessageContent(JsonUtil.toJson(bean));
		messageBean.setAccountId(accountId);
		return dao.insertReceiveMessageBean(messageBean);
	}

}
