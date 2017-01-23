package com.lpmas.weixin.api.request.group;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.group.DeleteGroupRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.group.DeleteGroupResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class DeleteGroup extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(DeleteGroup.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		DeleteGroupResponseBean responseBean = new DeleteGroupResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			DeleteGroupRequestBean bean = (DeleteGroupRequestBean) requestBean;

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("group", bean);

			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(map));
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, DeleteGroupResponseBean.class);
		} catch (Exception e) {
			log.error("MoveGroup Error:", e);
		}
		return responseBean;
	}
}
