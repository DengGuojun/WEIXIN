package com.lpmas.weixin.console.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.bean.AutoReplyMessageInfoBean;
import com.lpmas.weixin.config.AutoReplyMessageConfig;
import com.lpmas.weixin.console.cache.AutoReplyMessageInfoCache;
import com.lpmas.weixin.console.dao.AutoReplyMessageInfoDao;

public class AutoReplyMessageInfoBusiness {
	public int addAutoReplyMessageInfo(AutoReplyMessageInfoBean bean) {
		AutoReplyMessageInfoDao dao = new AutoReplyMessageInfoDao();
		int result = dao.insertAutoReplyMessageInfo(bean);
		if (result > 0) {
			AutoReplyMessageInfoCache cache = new AutoReplyMessageInfoCache();
			cache.refreshAutoReplyMessageInfoListByType(result, bean.getReplyType());
		}
		return result;
	}

	public int updateAutoReplyMessageInfo(AutoReplyMessageInfoBean bean) {
		AutoReplyMessageInfoDao dao = new AutoReplyMessageInfoDao();
		int result = dao.updateAutoReplyMessageInfo(bean);
		if (result > 0) {
			AutoReplyMessageInfoCache cache = new AutoReplyMessageInfoCache();
			cache.refreshAutoReplyMessageInfoListByType(bean.getAccountId(), bean.getReplyType());
		}
		return result;
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

	public ReturnMessageBean verifyAutoReplyMessageInfo(AutoReplyMessageInfoBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (bean.getReplyType() != AutoReplyMessageConfig.AR_TYPE_DEFAULT) {
			if (bean.getMessageMatchRule().isEmpty()) {
				result.setMessage("消息匹配规则不允许为空");
			}
		}
		return result;
	}
}
