package com.lpmas.weixin.api.request.group;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.group.UpdateMemberToGroupResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class UpdateMemberToGroup extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(UpdateMemberToGroup.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		UpdateMemberToGroupResponseBean responseBean = new UpdateMemberToGroupResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {			
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(requestBean));
			parseResponseResult(result, responseBean);
			
			responseBean = JsonKit.toBean(result, UpdateMemberToGroupResponseBean.class);
		} catch (Exception e) {
			log.error("MoveGroup Error:", e);
		}
		return responseBean;
	}
}
