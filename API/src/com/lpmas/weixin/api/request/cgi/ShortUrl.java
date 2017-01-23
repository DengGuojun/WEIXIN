package com.lpmas.weixin.api.request.cgi;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.cgi.ShortUrlResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class ShortUrl extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(ShortUrl.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		ShortUrlResponseBean responseBean = new ShortUrlResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {			
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(requestBean));
			parseResponseResult(result, responseBean);
			responseBean = JsonKit.toBean(result, ShortUrlResponseBean.class);
		} catch (Exception e) {
			log.error("ShortUrl Error:", e);
		}
		return responseBean;
	}
}
