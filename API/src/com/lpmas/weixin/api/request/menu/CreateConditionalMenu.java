package com.lpmas.weixin.api.request.menu;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.menu.CreateConditionalMenuRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.menu.CreateConditionalMenuResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class CreateConditionalMenu extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(CreateConditionalMenu.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		CreateConditionalMenuResponseBean responseBean = new CreateConditionalMenuResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			CreateConditionalMenuRequestBean bean = (CreateConditionalMenuRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));

			responseBean = JsonKit.toBean(result, CreateConditionalMenuResponseBean.class);
		} catch (Exception e) {
			log.error("CreateMenu Error:", e);
		}
		return responseBean;
	}
}
