package com.lpmas.weixin.console.business;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.bean.WeixinConfigInfoBean;
import com.lpmas.weixin.business.WeixinConfigInfoBusinessAdaptor;
import com.lpmas.weixin.config.WeixinAppConfig;

public class WeixinConfigBusiness {
	private static final Logger log = LoggerFactory.getLogger(WeixinConfigBusiness.class);

	public WeixinConfigBean getWeixinConfigBean(int accountId) {
		WeixinConfigInfoBusinessAdaptor business = new WeixinConfigInfoBusinessAdaptor();
		WeixinConfigBean bean = new WeixinConfigBean();

		HashMap<String, Object> configMap = new HashMap<String, Object>();
		for (StatusBean<String, String> statusBean : WeixinAppConfig.CONFIG_KEY_LIST) {
			String configCode = WeixinAppConfig.getWeixinConfigCode(accountId, statusBean.getStatus());
			WeixinConfigInfoBean weixinConfigInfoBean = business.getWeixinConfigInfoByCode(configCode);
			if (weixinConfigInfoBean != null) {
				configMap.put(StringKit.lowerFirstChar(statusBean.getStatus()), weixinConfigInfoBean.getConfigValue());
			}
		}
		try {
			bean = BeanKit.map2Bean(configMap, WeixinConfigBean.class);
			bean.setAccountId(accountId);
		} catch (Exception e) {
			log.error("", e);
		}

		return bean;
	}

}
