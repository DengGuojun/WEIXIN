package com.lpmas.weixin.api.request.material;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.material.AddNewsRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.material.AddNewsResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class AddNews extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(AddNews.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		AddNewsResponseBean responseBean = new AddNewsResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			AddNewsRequestBean bean = (AddNewsRequestBean) requestBean;

			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, AddNewsResponseBean.class);
		} catch (Exception e) {
			log.error("AddNews Error:", e);
		}
		return responseBean;
	}
}
