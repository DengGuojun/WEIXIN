package com.lpmas.weixin.api.request.cs;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.cs.DeleteAccountResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class DeleteAccount extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(DeleteAccount.class);
	private String requestUrl = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		DeleteAccountResponseBean responseBean = new DeleteAccountResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(requestBean));
			parseResponseResult(result, responseBean);
		} catch (Exception e) {
			log.error("AddKfaccount Error:", e);
		}
		return responseBean;
	}
}
