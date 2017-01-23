package com.lpmas.weixin.api.request.menu;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.menu.DeleteConditionalMenuRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.menu.DeleteConditionalMenuResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class DeleteConditionalMenu extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(DeleteConditionalMenu.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		DeleteConditionalMenuResponseBean responseBean = new DeleteConditionalMenuResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			DeleteConditionalMenuRequestBean bean = (DeleteConditionalMenuRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));

			responseBean = JsonKit.toBean(result, DeleteConditionalMenuResponseBean.class);
		} catch (Exception e) {
			log.error("DeleteMenu Error:", e);
		}
		return responseBean;
	}
}
