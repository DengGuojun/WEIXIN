package com.lpmas.weixin.api.request.menu;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.menu.CreateMenuRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.menu.CreateMenuResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class CreateMenu extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(CreateMenu.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		CreateMenuResponseBean responseBean = new CreateMenuResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			CreateMenuRequestBean bean = (CreateMenuRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					bean.getMenuContent());
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, CreateMenuResponseBean.class);
		} catch (Exception e) {
			log.error("CreateMenu Error:", e);
		}
		return responseBean;
	}
}
