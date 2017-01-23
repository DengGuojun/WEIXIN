package com.lpmas.weixin.api.request.material;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.material.DeleteMaterialRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.material.DeleteMaterialResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class DeleteMaterial extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(AddNews.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		DeleteMaterialResponseBean responseBean = new DeleteMaterialResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			DeleteMaterialRequestBean bean = (DeleteMaterialRequestBean) requestBean;

			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));
			parseResponseResult(result, responseBean);
			
			responseBean = JsonKit.toBean(result, DeleteMaterialResponseBean.class);
		} catch (Exception e) {
			log.error("AddNews Error:", e);
		}
		return responseBean;
	}
}
