package com.lpmas.weixin.api.request.menu;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.menu.DeleteMenuResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class DeleteMenu extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(DeleteMenu.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		DeleteMenuResponseBean responseBean = new DeleteMenuResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.getRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()), null);
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, DeleteMenuResponseBean.class);
		} catch (Exception e) {
			log.error("DeleteMenu Error:", e);
		}
		return responseBean;
	}
}
