package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.config.ReceiveEventConfig;

public class SendTemplateJobFinishBean extends EventBaseBean {
	public SendTemplateJobFinishBean() {
		super(ReceiveEventConfig.EVENT_SEND_TEMPLATE_JOB_FINISH);
	}

	// 发送状态
	@JacksonXmlProperty(localName = "Status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
