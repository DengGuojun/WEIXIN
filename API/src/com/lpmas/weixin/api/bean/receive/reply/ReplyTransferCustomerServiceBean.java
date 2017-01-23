package com.lpmas.weixin.api.bean.receive.reply;

import com.lpmas.weixin.api.bean.receive.ReplyMessageBaseBean;

public class ReplyTransferCustomerServiceBean extends ReplyMessageBaseBean {

	// 指定会话接入的客服账号
	private String account;

	public ReplyTransferCustomerServiceBean() {
		super("transfer_customer_service");
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
