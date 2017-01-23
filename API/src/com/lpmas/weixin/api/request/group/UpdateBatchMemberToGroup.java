package com.lpmas.weixin.api.request.group;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.group.UpdateBatchMemberToGroupResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class UpdateBatchMemberToGroup extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(UpdateBatchMemberToGroup.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		UpdateBatchMemberToGroupResponseBean responseBean = new UpdateBatchMemberToGroupResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(requestBean));
			parseResponseResult(result, responseBean);
			
			responseBean = JsonKit.toBean(result, UpdateBatchMemberToGroupResponseBean.class);
		} catch (Exception e) {
			log.error("MoveGroup Error:", e);
		}
		return responseBean;
	}
}
