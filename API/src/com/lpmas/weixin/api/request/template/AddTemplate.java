package com.lpmas.weixin.api.request.template;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.template.AddTemplateResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class AddTemplate extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(AddTemplate.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		AddTemplateResponseBean responseBean = new AddTemplateResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(requestBean));
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, AddTemplateResponseBean.class);
		} catch (Exception e) {
			log.error("GetTemplateId Error:", e);
		}
		return responseBean;
	}

}
