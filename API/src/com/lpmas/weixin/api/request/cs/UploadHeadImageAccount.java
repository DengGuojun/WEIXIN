package com.lpmas.weixin.api.request.cs;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.weixin.api.base.ApiFileInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.cs.UploadHeadImageAccountRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.cs.UploadHeadImageAccountResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class UploadHeadImageAccount extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(UploadHeadImageAccount.class);
	private String requestUrl = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token={0}&kf_account={1}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		UploadHeadImageAccountResponseBean responseBean = new UploadHeadImageAccountResponseBean();
		ApiFileInvoker apiInvoker = new ApiFileInvoker();
		try {
			UploadHeadImageAccountRequestBean bean = (UploadHeadImageAccountRequestBean) requestBean;
			String result = apiInvoker.uploadFile(
					MessageFormat.format(requestUrl, requestBean.getAccessToken(), bean.getAccount()), "media",
					bean.getMedia());
			parseResponseResult(result, responseBean);
		} catch (Exception e) {
			log.error("AddKfaccount Error:", e);
		}
		return responseBean;
	}
}
