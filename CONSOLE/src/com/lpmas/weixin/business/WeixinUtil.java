package com.lpmas.weixin.business;

import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class WeixinUtil {

	public static boolean isSuccess(WxResponseBaseBean responseBean) {
		boolean result = false;
		if (responseBean != null) {
			int errCode = responseBean.getErrCode();
			if (errCode == 0) {
				return true;
			} else {
				return false;
			}
		}
		return result;
	}

}
