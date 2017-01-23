package com.lpmas.weixin.api.request.menu;

import java.text.MessageFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.menu.GetMenuResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetMenu extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetMenu.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token={0}";

	public GetMenuResponseBean execute(WxRequestBaseBean requestBean) {
		GetMenuResponseBean responseBean = new GetMenuResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.getRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()), null);
		
			responseBean.setMenuContent(JsonKit.toBean(result, Map.class));
		} catch (Exception e) {
			log.error("GetMenu Error:", e);
		}
		return responseBean;
	}
}
