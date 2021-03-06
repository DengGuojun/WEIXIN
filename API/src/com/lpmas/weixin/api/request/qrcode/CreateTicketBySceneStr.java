package com.lpmas.weixin.api.request.qrcode;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.qrcode.CreateTicketBySceneStrRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.qrcode.CreateTicketResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class CreateTicketBySceneStr extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(CreateTicketBySceneStr.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		CreateTicketResponseBean responseBean = new CreateTicketResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			CreateTicketBySceneStrRequestBean bean = (CreateTicketBySceneStrRequestBean) requestBean;

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("scene_str", bean.getSceneStr());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("scene", paramMap);

			bean.setActionInfo(map);

			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, CreateTicketResponseBean.class);
		} catch (Exception e) {
			log.error("CreateTicketBySceneStr Error:", e);
		}
		return responseBean;
	}
}
