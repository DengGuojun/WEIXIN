package com.lpmas.weixin.api.request.material;

import java.io.IOException;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.material.MaterialInfoBean;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.material.GetMaterialListRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.material.GetMaterialListResponseBean;
import com.lpmas.weixin.api.bean.response.material.NewsListResponseBean;
import com.lpmas.weixin.api.config.MaterialTypeConfig;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetMaterialList extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetMaterialList.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		WxResponseBaseBean responseBean = new WxResponseBaseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			GetMaterialListRequestBean bean = (GetMaterialListRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(requestBean));
			
			if (bean.getMediaType().equals(MaterialTypeConfig.MT_NEWS)) {// 图文
				responseBean = processNewsList(result);
			} else {
				responseBean = processMaterialList(result);
			}
		} catch (Exception e) {
			log.error("GetMaterialList Error:", e);
		}
		return responseBean;
	}

	private WxResponseBaseBean processNewsList(String json)
			throws JsonParseException, JsonMappingException, IOException {
		TypeReference<GetMaterialListResponseBean<NewsListResponseBean>> type = new TypeReference<GetMaterialListResponseBean<NewsListResponseBean>>() {
		};
		GetMaterialListResponseBean<MaterialInfoBean> bean = JsonKit.getObjectMapper().readValue(json, type);
		return bean;
	}

	private WxResponseBaseBean processMaterialList(String json)
			throws JsonParseException, JsonMappingException, IOException {
		TypeReference<GetMaterialListResponseBean<MaterialInfoBean>> type = new TypeReference<GetMaterialListResponseBean<MaterialInfoBean>>() {
		};
		GetMaterialListResponseBean<MaterialInfoBean> bean = JsonKit.getObjectMapper().readValue(json, type);
		return bean;
	}
}
