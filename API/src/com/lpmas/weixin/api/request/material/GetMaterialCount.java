package com.lpmas.weixin.api.request.material;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.material.GetMaterialCountResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetMaterialCount extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetMaterialCount.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		GetMaterialCountResponseBean responseBean = new GetMaterialCountResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.getRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()), null);
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, GetMaterialCountResponseBean.class);
		} catch (Exception e) {
			log.error("GetMaterialCount Error:", e);
		}
		return responseBean;
	}
}
