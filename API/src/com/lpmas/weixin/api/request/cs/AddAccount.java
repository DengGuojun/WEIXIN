package com.lpmas.weixin.api.request.cs;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.cs.AddAccountResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class AddAccount extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(AddAccount.class);
	private String requestUrl = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		AddAccountResponseBean responseBean = new AddAccountResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(requestBean));
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, AddAccountResponseBean.class);
		} catch (Exception e) {
			log.error("AddKfaccount Error:", e);
		}
		return responseBean;
	}
}
