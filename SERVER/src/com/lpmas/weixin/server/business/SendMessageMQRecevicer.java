package com.lpmas.weixin.server.business;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.mq.activemq.ActiveMQReceiver;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.request.WxRequestBase;
import com.lpmas.weixin.bean.WeixinAccountInfoBean;
import com.lpmas.weixin.business.WeixinUtil;
import com.lpmas.weixin.client.bean.request.SendMessageRequestBean;
import com.lpmas.weixin.client.business.JsonUtil;
import com.lpmas.weixin.server.cache.WeixinAccessTokenCache;
import com.lpmas.weixin.server.cache.WeixinAccountInfoCache;

public abstract class SendMessageMQRecevicer extends ActiveMQReceiver {
	private static Logger log = LoggerFactory.getLogger(SendMessageMQRecevicer.class);

	public SendMessageMQRecevicer(String brokerId) {
		super(brokerId);
	}

	protected abstract WxRequestBase getRquest();

	@Override
	public void process(String data) {
		log.info("收到消息:" + data);
		// 尝试转化成COMMAND BEAN
		SendMessageRequestBean requestBean = JsonUtil.toBean(data, SendMessageRequestBean.class);
		if (requestBean != null) {
			try {
				requestBean.setCreateTime(DateKit.getCurrentTimestamp());
				// 获取CONTENT
				String requestContent = requestBean.getRequestContent();
				// 获取ACCOUNTCODE
				WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
				WeixinAccountInfoBean accountInfoBean = accountInfoCache
						.getWeixinAccountInfoByCondition(requestBean.getAppCode(), requestBean.getStoreCode());
				int accountId = accountInfoBean.getAccountId();
				// 获得TOKEN
				WeixinAccessTokenCache cache = new WeixinAccessTokenCache();
				String token = cache.getAccessToken(accountId);
				if (!StringKit.isValid(token)) {
					throw new RuntimeException("无法获取ACCESSTOKEN:" + data);
				}

				// 记录到MONGO
				SendMessageRequestBusniess messageRequestBusniess = new SendMessageRequestBusniess();
				int result = messageRequestBusniess.addSendMessageRequestBean(requestBean);

				if (result >= 1) {
					// 发送
					WxResponseBaseBean response = execute(token, requestContent);
					// 判断成功或者失败
					if (WeixinUtil.isSuccess(response)) {
						log.info(requestBean.getRequestContent() + ":消息发送成功!");
						// 更新结果
						requestBean.setIsSuccess(true);
						result = messageRequestBusniess.updateSendMessageRequestBean(requestBean);
						if (result <= 0) {
							log.error("消息发送成功，但结果更新失败");
						}
					} else {
						log.error(requestBean.getRequestContent() + ":消息发送失败!失败代码:" + response.getErrCode() + ",失败消息:"
								+ response.getErrMsg());
					}
				} else {
					log.error("消息发送失败，无法记录到MONGO");
				}
			} catch (Exception e) {
				log.error("无法发送消息到微信", e);
			}

		} else {
			log.error("消息反序列化失败!");
		}

	}

	private WxResponseBaseBean execute(String accessToken, String requestContent) throws Exception {
		WxRequestBase requestBase = getRquest();
		// 获得方法
		Method execute = requestBase.getClass().getMethod("execute", String.class, String.class);
		return (WxResponseBaseBean) execute.invoke(requestBase, accessToken, requestContent);
	}

}
