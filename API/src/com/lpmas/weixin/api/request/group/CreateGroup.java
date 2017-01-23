package com.lpmas.weixin.api.request.group;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.group.CreateGroupRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.group.CreateGroupResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class CreateGroup extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(CreateGroup.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		CreateGroupResponseBean responseBean = new CreateGroupResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			CreateGroupRequestBean bean = (CreateGroupRequestBean) requestBean;

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("group", bean);

			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(map));
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, CreateGroupResponseBean.class);
		} catch (Exception e) {
			log.error("CreateGroup Error:", e);
		}
		return responseBean;
	}
}
