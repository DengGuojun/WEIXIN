package com.lpmas.weixin.server.business;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.SetKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.api.base.ApiException;
import com.lpmas.weixin.api.bean.receive.ReceiveBaseBean;
import com.lpmas.weixin.api.bean.receive.ReplyMessageBaseBean;
import com.lpmas.weixin.api.bean.receive.reply.ReplyTextBean;
import com.lpmas.weixin.api.receive.util.ReplyMessageProcessor;
import com.lpmas.weixin.bean.AutoReplyMessageInfoBean;
import com.lpmas.weixin.config.AutoReplyMessageConfig;
import com.lpmas.weixin.server.cache.AutoReplyMessageInfoCache;
import com.lpmas.weixin.server.cache.WeixinAccountInfoCache;

public abstract class AbstractAutoReplyMessageProcessor {
	private static Logger logger = LoggerFactory.getLogger(AbstractAutoReplyMessageProcessor.class);

	public ReturnMessageBean getReplyMessage(String accountCode, ReceiveBaseBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		//判断是否需要处理
		if(!isProcess(bean)) return result;
		
		// 根据ACCOUNTCODE获取ACCOOUNTID
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);

		List<AutoReplyMessageInfoBean> autoReplyMessageList = null;
		AutoReplyMessageInfoCache autoReplyCache = new AutoReplyMessageInfoCache();
		// 查出事件自动回复
		autoReplyMessageList = autoReplyCache.getAutoReplyMessageInfoListByType(accountId, getAutoReplyType());
		if (!autoReplyMessageList.isEmpty()) {
			// 看看是不是默认回复
			if (AutoReplyMessageConfig.AR_TYPE_DEFAULT == getAutoReplyType()) {
				// 因为只允许一个默认的，只取出第一个
				AutoReplyMessageInfoBean autoReplyBean = autoReplyMessageList.get(0);
				String replyMessage = getReply("", autoReplyBean.getMessageContent(), bean.getFromUserName(),
						bean.getToUserName());

				result.setMessage(replyMessage);
				result.setCode(Constants.STATUS_VALID);
				return result;
			}
			// 遍历看看是否满足规则
			for (AutoReplyMessageInfoBean autoReplyBean : autoReplyMessageList) {
				// 取出CONTENT
				String rules = autoReplyBean.getMessageMatchRule();
				// 用分号分隔转换成SET
				Set<String> ruleSet = SetKit.string2Set(rules, ";");
				// 遍历SET看看是否符合
				for (String rule : ruleSet) {
					// 判断是否符合这个事件
					if (isMatchRule(rule, bean)) {
						// 符合则组装返回消息BEAN
						String replayMessage = getReply(rule, autoReplyBean.getMessageContent(), bean.getFromUserName(),
								bean.getToUserName());
						result.setMessage(replayMessage);
						result.setCode(Constants.STATUS_VALID);
						return result;
					}
				}
			}
		}
		return result;
	}

	protected String getTextReply(String content, String toUser, String fromUser) {
		ReplyTextBean replyTextBean = getReplyBean(toUser, fromUser, ReplyTextBean.class);
		replyTextBean.setContent(content);
		try {
			return ReplyMessageProcessor.process(replyTextBean);
		} catch (ApiException e) {
			logger.error("", e);
			return "";
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> T getReplyBean(String toUser, String fromUser, Class<T> clazz) {
		if (!ReplyMessageBaseBean.class.isAssignableFrom(clazz)) {
			throw new IllegalArgumentException(clazz.getName() + "必须是ReplyMessageBaseBean的子类");
		}
		ReplyMessageBaseBean result = null;
		try {
			result = (ReplyMessageBaseBean) clazz.newInstance();
		} catch (Exception e) {
			logger.error("", e);
		}
		result.setCreateTime(System.currentTimeMillis() / 1000);
		result.setToUserName(toUser);
		result.setFromUserName(fromUser);
		return (T) result;
	}

	private String getValueFromRuleString(String rule, int index) {
		String[] array = rule.split(",");
		if (index > array.length - 1) {
			return "";
		}
		return array[index];
	}

	private static int INDEX_RULE = 0;
	private static int INDEX_FIELD = 1;
	private static int INDEX_RULE_VALUE = 2;

	protected String getRule(String rule) {
		return getValueFromRuleString(rule, INDEX_RULE);
	}

	protected String getField(String rule) {
		return getValueFromRuleString(rule, INDEX_FIELD);
	}

	protected String getRuleValue(String rule) {
		return getValueFromRuleString(rule, INDEX_RULE_VALUE);
	}

	protected abstract boolean isMatchRule(String rule, ReceiveBaseBean bean);

	protected abstract int getAutoReplyType();

	protected abstract String getReply(String rule, String content, String toUser, String fromUser);
	
	protected abstract boolean isProcess(ReceiveBaseBean bean);

}
