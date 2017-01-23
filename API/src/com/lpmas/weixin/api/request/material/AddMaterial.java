package com.lpmas.weixin.api.request.material;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiFileInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.material.AddMaterialRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.material.AddMaterialResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class AddMaterial extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(AddMaterial.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		AddMaterialResponseBean responseBean = new AddMaterialResponseBean();
		ApiFileInvoker apiInvoker = new ApiFileInvoker();
		try {
			AddMaterialRequestBean bean = (AddMaterialRequestBean) requestBean;

			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("description", JsonKit.toJson(bean));

			String result = apiInvoker.uploadFile(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					"media", bean.getMediaFile(), paramMap);
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, AddMaterialResponseBean.class);
		} catch (Exception e) {
			log.error("UploadMedia Error:", e);
		}
		return responseBean;
	}
}
