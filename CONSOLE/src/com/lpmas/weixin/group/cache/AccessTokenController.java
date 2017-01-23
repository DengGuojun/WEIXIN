package com.lpmas.weixin.group.cache;

import java.util.HashMap;
import java.util.Map;

import com.lpmas.console.group.MessageGroupInitializer;
import com.lpmas.console.group.MessageGroupMember;
import com.lpmas.console.group.MessageGroupUtil;
import com.lpmas.console.group.bean.MessageBean;
import com.lpmas.console.group.bean.MessageContentBean;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.config.WeixinConfig;

public class AccessTokenController {
	private static AccessTokenController instance = null;

	private AccessTokenController() {
	}

	public static AccessTokenController getInstance() {
		if (instance == null) {
			synchronized (AccessTokenController.class) {
				if (instance == null) {
					instance = new AccessTokenController();
				}
			}
		}
		return instance;
	}

	public void refreshAccessToken(int accountId) {
		MessageBean bean = new MessageBean();
		MessageContentBean contentBean = new MessageContentBean();

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("accountId", String.valueOf(accountId));

		contentBean.setCommand("refreshAccessToken");
		contentBean.setContent(JsonKit.toJson(paramMap));
		bean.setMessageObject(JsonKit.toJson(contentBean));

		MessageGroupInitializer initializer = new MessageGroupInitializer();
		MessageGroupMember member = initializer.getMessageGroupMemberByAppId(WeixinConfig.APP_ID);

		bean.setSrcMemberName(
				MessageGroupUtil.getMemberName(initializer.getMessageGroupConfig(WeixinConfig.APP_ID).getGroupId()));
		member.send(bean);
	}

}
