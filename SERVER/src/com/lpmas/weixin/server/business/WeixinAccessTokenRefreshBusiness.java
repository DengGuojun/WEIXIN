package com.lpmas.weixin.server.business;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.bean.WeixinAccountInfoBean;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.server.cache.WeixinAccessTokenCache;
import com.lpmas.weixin.server.cache.WeixinConfigCache;

public class WeixinAccessTokenRefreshBusiness {

	private static Logger logger = LoggerFactory.getLogger(WeixinAccessTokenRefreshBusiness.class);

	public static void refreshAccessToken(String[] args) {
		// 获取当前有效的ACCOUNT列表
		WeixinAccountInfoBusiness accountInfoBusiness = new WeixinAccountInfoBusiness();
		List<WeixinAccountInfoBean> accountInfoList = accountInfoBusiness.getWeixinAccountAllList();

		WeixinConfigCache configCache = new WeixinConfigCache();
		WeixinAccessTokenCache tokenCache = new WeixinAccessTokenCache();
		WeixinConfigBean configBean = new WeixinConfigBean();
		// 遍历
		logger.info("开始定时更新ACCESSTOKEN,@" + DateKit.formatDate(new Date(), DateKit.DEFAULT_DATE_TIME_FORMAT));
		for (WeixinAccountInfoBean accountInfoBean : accountInfoList) {
			// 分别去查APPCONFIG
			configBean = configCache.getWeixinConfigBean(accountInfoBean.getAccountId());
			if (configBean != null) {
				// 判断APPID APPSCRET是否空
				if (StringKit.isValid(configBean.getAppId()) && StringKit.isValid(configBean.getAppSecret())) {
					logger.info("开始定时尝试刷新ACCESSTOKEN(" + accountInfoBean.getAccountCode() + "),@"
							+ DateKit.getCurrentDateTime());
					// 两个都非空就尝试去更新ACCESSTOKEN
					boolean result = tokenCache.setAccessToken(accountInfoBean.getAccountId());
					if (result) {
						// 成功
						logger.info("刷新ACCESSTOKEN(" + accountInfoBean.getAccountCode() + ")成功!,@"
								+ DateKit.getCurrentDateTime());
					} else {
						// 失败
						logger.info("刷新ACCESSTOKEN(" + accountInfoBean.getAccountCode() + ")失败!,@"
								+ DateKit.getCurrentDateTime());
					}
				}
			}
		}
	}

}
