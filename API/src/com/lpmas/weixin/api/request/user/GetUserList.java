package com.lpmas.weixin.api.request.user;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.user.GetUserListRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.user.GetUserListResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetUserList extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetUserList.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token={0}&next_openid={1}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		GetUserListResponseBean responseBean = new GetUserListResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			GetUserListRequestBean bean = (GetUserListRequestBean) requestBean;
			String result = apiInvoker.getRequest(
					MessageFormat.format(requestUrl, requestBean.getAccessToken(), bean.getNextOpenId()), null);
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, GetUserListResponseBean.class);
			responseBean.setOpenIdList((List<String>) responseBean.getData().get("openid"));
		} catch (Exception e) {
			log.error("ListUser Error:", e);
		}
		return responseBean;
	}
}
