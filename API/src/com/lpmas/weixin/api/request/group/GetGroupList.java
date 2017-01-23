package com.lpmas.weixin.api.request.group;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.group.GetGroupListResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetGroupList extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetGroupList.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token={0}";

	public GetGroupListResponseBean execute(WxRequestBaseBean requestBean) {
		GetGroupListResponseBean responseBean = new GetGroupListResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.getRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()), null);
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, GetGroupListResponseBean.class);
		} catch (Exception e) {
			log.error("ListGroup Error:", e);
		}
		return responseBean;
	}
}
