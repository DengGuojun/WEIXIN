package com.lpmas.weixin.api.request.menu;

import java.text.MessageFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.menu.TryConditionalMenuRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.menu.TryConditionalMenuResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class TryConditionalMenu extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(TryConditionalMenu.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		TryConditionalMenuResponseBean responseBean = new TryConditionalMenuResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			TryConditionalMenuRequestBean bean = (TryConditionalMenuRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));

			responseBean.setMenuContent(JsonKit.toBean(result, Map.class));
		} catch (Exception e) {
			log.error("DeleteMenu Error:", e);
		}
		return responseBean;
	}
}
