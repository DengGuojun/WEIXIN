package com.lpmas.weixin.server.business.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.console.group.MessageRecieveProcessor;
import com.lpmas.console.group.bean.MessageContentBean;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.server.cache.WeixinAccessTokenCache;
import com.lpmas.weixin.server.config.WeixinServerConfig;

public class MessageRecieveRefreshAccessTokenProcessorImpl extends MessageRecieveProcessor {
	private static Logger log = LoggerFactory.getLogger(MessageRecieveRefreshAccessTokenProcessorImpl.class);

	@Override
	@SuppressWarnings("unchecked")
	public void process(MessageContentBean contentBean) {
		log.info("received message:" + contentBean.getContent());
		//只有主机才能去刷TOKEN
		if(WeixinServerConfig.IS_MASTER_SERVER){
			Map<String, String> paramMap = JsonKit.toBean(contentBean.getContent(), HashMap.class);
			int accountId = Integer.valueOf(paramMap.get("accountId"));

			WeixinAccessTokenCache cache = new WeixinAccessTokenCache();
			boolean result = cache.setAccessToken(accountId);
			if (result) {
				log.info("accessToken refresh success!");
			} else {
				log.error("accessToken refresh fail!");
			}
		}
	}
}
