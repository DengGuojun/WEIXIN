package com.lpmas.weixin.server.business;

import com.lpmas.weixin.client.bean.request.SendMessageRequestBean;
import com.lpmas.weixin.server.dao.SendMessageRequestDao;

public class SendMessageRequestBusniess {

	public int addSendMessageRequestBean(SendMessageRequestBean bean) {
		SendMessageRequestDao dao = new SendMessageRequestDao();
		return dao.insertSendMessageRequestBean(bean);
	}

	public int updateSendMessageRequestBean(SendMessageRequestBean bean) {
		SendMessageRequestDao dao = new SendMessageRequestDao();
		return dao.updateSendMessageRequestBean(bean);
	}

}
