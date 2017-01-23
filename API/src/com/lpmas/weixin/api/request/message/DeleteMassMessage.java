package com.lpmas.weixin.api.request.message;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.message.DeleteMassMessageRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.message.DeleteMassMessageResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class DeleteMassMessage extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(DeleteMassMessage.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		DeleteMassMessageResponseBean responseBean = new DeleteMassMessageResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			DeleteMassMessageRequestBean bean = (DeleteMassMessageRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));
			
			responseBean = JsonKit.toBean(result, DeleteMassMessageResponseBean.class);
		} catch (Exception e) {
			log.error("DeleteSendAllMsg Error:", e);
		}
		return responseBean;
	}
}
