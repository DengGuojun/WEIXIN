package com.lpmas.weixin.api.request.menu;

import java.text.MessageFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.menu.GetCurrentSelfMenuInfoResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetCurrentSelfMenuInfo extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetCurrentSelfMenuInfo.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		GetCurrentSelfMenuInfoResponseBean responseBean = new GetCurrentSelfMenuInfoResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.getRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()), null);
			parseResponseResult(result, responseBean);
			
			responseBean.setResultMap((Map<String, Object>) JsonKit.toBean(result, Map.class));
		} catch (Exception e) {
			log.error("GetCurrentSelfmenuInfo Error:", e);
		}
		return responseBean;
	}
}
