package com.lpmas.weixin.api.request.cs;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.cs.GetAccountListResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetAccountList extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetAccountList.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		GetAccountListResponseBean responseBean = new GetAccountListResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.getRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()), null);
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, GetAccountListResponseBean.class);
		} catch (Exception e) {
			log.error("ListKfaccount Error:", e);
		}
		return responseBean;
	}
}
