package com.lpmas.weixin.api.request.cs;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.cs.UpdateAccountResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class UpdateAccount extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(UpdateAccount.class);
	private String requestUrl = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		UpdateAccountResponseBean responseBean = new UpdateAccountResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(requestBean));
			parseResponseResult(result, responseBean);
		} catch (Exception e) {
			log.error("UpdateKfaccount Error:", e);
		}
		return responseBean;
	}
}
