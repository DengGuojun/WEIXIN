package com.lpmas.weixin.server.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.weixin.bean.AutoReplyMessageInfoBean;
import com.lpmas.weixin.server.dao.AutoReplyMessageInfoDao;

public class AutoReplyMessageInfoBusiness {
	public int addAutoReplyMessageInfo(AutoReplyMessageInfoBean bean) {
		AutoReplyMessageInfoDao dao = new AutoReplyMessageInfoDao();
		return dao.insertAutoReplyMessageInfo(bean);
	}

	public int updateAutoReplyMessageInfo(AutoReplyMessageInfoBean bean) {
		AutoReplyMessageInfoDao dao = new AutoReplyMessageInfoDao();
		return dao.updateAutoReplyMessageInfo(bean);
	}

	public AutoReplyMessageInfoBean getAutoReplyMessageInfoByKey(int replyId) {
		AutoReplyMessageInfoDao dao = new AutoReplyMessageInfoDao();
		return dao.getAutoReplyMessageInfoByKey(replyId);
	}

	public PageResultBean<AutoReplyMessageInfoBean> getAutoReplyMessageInfoPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		AutoReplyMessageInfoDao dao = new AutoReplyMessageInfoDao();
		return dao.getAutoReplyMessageInfoPageListByMap(condMap, pageBean);
	}

	public List<AutoReplyMessageInfoBean> getAutoReplyMessageInfoListByMap(Map<String, String> condMap) {
		AutoReplyMessageInfoDao dao = new AutoReplyMessageInfoDao();
		return dao.getAutoReplyMessageInfoListByMap(condMap);
	}

	/**
	 * 获取自动回复列表
	 * 
	 * @param accountId
	 * @param replyType
	 * @return
	 */
	public List<AutoReplyMessageInfoBean> getAutoReplyMessageInfoListByType(int accountId, int replyType) {
		Map<String, String> condMap = new HashMap<String, String>();
		condMap.put("accountId", String.valueOf(accountId));
		condMap.put("replyType", String.valueOf(replyType));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		List<AutoReplyMessageInfoBean> result = getAutoReplyMessageInfoListByMap(condMap);
		return result;
	}
}
