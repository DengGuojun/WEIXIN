package com.lpmas.weixin.api.request.user;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.user.GetUserInfoRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.user.GetUserInfoResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetUserInfo extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetUserInfo.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}&lang={2}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		GetUserInfoResponseBean responseBean = new GetUserInfoResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			GetUserInfoRequestBean bean = (GetUserInfoRequestBean) requestBean;
			String result = apiInvoker.getRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken(),
					bean.getOpenId(), bean.getLanguage()), null);
			parseResponseResult(result, responseBean);
//			if (responseBean.getErrCode() == 0) {
//				responseBean.setSubscribe((Integer) responseBean.getResultMap().get("subscribe"));
//				responseBean.setCity((String) responseBean.getResultMap().get("city"));
//				responseBean.setCountry((String) responseBean.getResultMap().get("country"));
//				responseBean.setHeadimgurl((String) responseBean.getResultMap().get("headimgurl"));
//				responseBean.setLanguage((String) responseBean.getResultMap().get("language"));
//				responseBean.setNickname((String) responseBean.getResultMap().get("nickname"));
//				responseBean.setOpenid((String) responseBean.getResultMap().get("openid"));
//				responseBean.setProvince((String) responseBean.getResultMap().get("province"));
//				responseBean.setSex((Integer) responseBean.getResultMap().get("sex"));
//				try {
//					responseBean.setSubscribe_time(
//							Long.valueOf(responseBean.getResultMap().get("subscribe_time").toString()));
//				} catch (Exception e) {
//					responseBean.setSubscribe_time(null);
//				}
//				responseBean.setUnionid((String) responseBean.getResultMap().get("unionid"));
//			}
			responseBean = JsonKit.toBean(result, GetUserInfoResponseBean.class);
		} catch (Exception e) {
			log.error("GetUserInfo Error:", e);
		}
		return responseBean;
	}
}
