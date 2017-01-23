package com.lpmas.weixin.server.business;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.ReflectKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.api.bean.receive.ReceiveBaseBean;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;
import com.lpmas.weixin.bean.QrcodeInfoBean;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.client.business.JsonUtil;
import com.lpmas.weixin.client.business.ReceiveMessageParser;
import com.lpmas.weixin.client.factory.WeixinMQFactory;
import com.lpmas.weixin.server.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.server.cache.WeixinConfigCache;
import com.lpmas.weixin.server.config.ReceiveDispatchConfig;

public class WeixinReceiveDispatcher {

	private static Logger logger = LoggerFactory.getLogger(WeixinReceiveDispatcher.class);

	private boolean isDispatch(String accountCode, ReceiveBaseBean baseBean, ReceiveMessageParser parser)
			throws Exception {
		// TODO 根据这些消息来做是否分发的判断

		// 临时代码
		// 只分发事件中的二维码事件
		if (parser.isEvent()) {
			// 事件只分发事件中的扫描带参数二维码
			if (parser.isSubscribeBySceneQrcodeEvent() || parser.isScanSceneQrcodeEvent()) {
				return true;
			} else {
				return false;
			}
		} else if (parser.isMessage()) {
			// 消息
			return false;
		} else {
			return false;
		}
	}

	public ReturnMessageBean doDispatch(String accountCode, ReceiveBaseBean baseBean) {
		Map<String, String> sendContent = new HashMap<String, String>();
		sendContent.put(ReceiveMessageParser.CLASS_NAME_KEY, baseBean.getClass().getName());
		sendContent.put(ReceiveMessageParser.CONTETNT_KEY, JsonUtil.toJson(baseBean));

		String sendContentJson = JsonUtil.toJson(sendContent);
		ReturnMessageBean result = new ReturnMessageBean();
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);

		try {
			ReceiveMessageParser parser = new ReceiveMessageParser(sendContentJson);
			if (ReceiveMessageTypeConfig.RMT_EVENT.equals(baseBean.getMsgType())) {
				// 是扫描二维码事件，就要置换EVENTKEY
				if (parser.isSubscribeBySceneQrcodeEvent() || parser.isScanSceneQrcodeEvent()) {
					// 取出ticket
					String eventKey = parser.get("eventKey");
					if (eventKey == null) {
						eventKey = "";
					}
					eventKey = eventKey.replace("qrscene_", "");// 统一去掉qrscene_
					// 获得APP场景ID
					WeixinQrcodeInfoBusiness qrcodeInfoBusiness = new WeixinQrcodeInfoBusiness();
					String appSceneId = qrcodeInfoBusiness.getAppSceneIdByCondition(accountCode,
							Integer.valueOf(eventKey));
					// 替换为原来的EVENTKEY中去
					Method setMethod = baseBean.getClass().getMethod(ReflectKit.getSetterName("eventKey"),
							String.class);
					setMethod.invoke(baseBean, appSceneId);
					// 重新SET CONTENT
					sendContent.put(ReceiveMessageParser.CONTETNT_KEY, JsonUtil.toJson(baseBean));
					sendContentJson = JsonUtil.toJson(sendContent);
				}
			}
			// 判断是否需要分发
			if (isDispatch(accountCode, baseBean, parser)) {
				// 获取对应的Queue名
				String queueName = getQueueName(parser, accountId).trim();
				// 判断QUEUE名有效性，非空
				if (!StringKit.isValid(queueName)) {
					logger.error("[{}]需要分发,但分发队列名为空", accountCode + "|" + JsonUtil.toJson(baseBean));
					result.setCode(ReceiveDispatchConfig.DP_RESULT_DP_FAIL);
					result.setMessage("队列名为空,不作分发");
					return result;
				}
				// 把消息发送到队列
				WeixinMQFactory factory = new WeixinMQFactory();
				try {
					factory.getMQSender().send(queueName, sendContentJson);
					logger.info("发送消息[{}]到队列[{}]成功", sendContentJson, queueName);
				} catch (Exception e) {
					logger.error("发送消息[{}]到队列[{}]发生异常,异常原因[{}]", sendContentJson, queueName, e);
					result.setCode(ReceiveDispatchConfig.DP_RESULT_DP_FAIL);
					result.setMessage("分发消息发生异常," + e.getMessage());
				}
			} else {
				logger.info("[{}]不满足分发条件", accountCode + "|" + sendContentJson);
				result.setCode(ReceiveDispatchConfig.DP_RESULT_NOT_DP);
				result.setMessage("不满足分发条件不进行分发");
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}

	private String getQueueName(ReceiveMessageParser parser, int accountId) {
		// 获得默认队列名
		WeixinConfigCache cache = new WeixinConfigCache();
		WeixinConfigBean configBean = cache.getWeixinConfigBean(accountId);
		String defaultQueue = configBean.getQueueName();
		// 如果是二维码，就从数据库拿
		if (parser.isSubscribeBySceneQrcodeEvent() || parser.isScanSceneQrcodeEvent()) {
			String ticket = parser.get("ticket");
			if (!StringKit.isValid(ticket)) {
				return "";
			}
			WeixinQrcodeInfoBusiness qrcodeInfoBusiness = new WeixinQrcodeInfoBusiness();
			QrcodeInfoBean qrcodeInfoBean = qrcodeInfoBusiness.getQrcodeInfoByTicket(ticket);
			if (qrcodeInfoBean == null)
				return "";
			else {
				String callBackQueue = qrcodeInfoBean.getCallBackQueue();
				if (StringKit.isValid(callBackQueue)) {
					return callBackQueue;
				}
				return defaultQueue;
			}
		} else {
			// 否则从CONFIG拿
			return defaultQueue;
		}
	}
}
