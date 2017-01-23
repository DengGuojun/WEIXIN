package com.lpmas.weixin.api.request.user;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.user.UpdateUserRemarkResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class UpdateUserRemark extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(UpdateUserRemark.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		UpdateUserRemarkResponseBean responseBean = new UpdateUserRemarkResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(requestBean));
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, UpdateUserRemarkResponseBean.class);
		} catch (Exception e) {
			log.error("UpdateUserRemark Error:", e);
		}
		return responseBean;
	}
}
