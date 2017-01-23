package com.lpmas.weixin.api.request;

import java.util.HashMap;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public abstract class WxRequestBase {
	public void parseResponseResult(String result, WxResponseBaseBean bean) {
//		HashMap<String, Object> resultMap = JsonKit.toBean(result, HashMap.class);
//		bean.setResultMap(resultMap);
//		if (bean.getResultMap().containsKey("errcode")) {
//			bean.setErrCode((Integer) bean.getResultMap().get("errcode"));
//		}
//		if (bean.getResultMap().containsKey("errmsg")) {
//			bean.setErrMsg((String) bean.getResultMap().get("errmsg"));
//		}
	}

	public abstract WxResponseBaseBean execute(WxRequestBaseBean requestBean);
}
