package com.lpmas.weixin.api.request.template;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.template.SetIndustryResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class SetIndustry extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(SetIndustry.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		SetIndustryResponseBean responseBean = new SetIndustryResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {System.out.println(JsonKit.toJson(requestBean));
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(requestBean));
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, SetIndustryResponseBean.class);
		} catch (Exception e) {
			log.error("SetIndustry Error:", e);
		}
		return responseBean;
	}
}
