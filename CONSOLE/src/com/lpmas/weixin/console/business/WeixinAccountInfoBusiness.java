package com.lpmas.weixin.console.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.admin.bean.AdminGroupInfoBean;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.system.client.cache.StoreInfoClientCache;
import com.lpmas.system.client.cache.SysApplicationInfoClientCache;
import com.lpmas.weixin.bean.WeixinAccountInfoBean;
import com.lpmas.weixin.console.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.console.cache.WeixinConfigCache;
import com.lpmas.weixin.console.dao.WeixinAccountInfoDao;

public class WeixinAccountInfoBusiness {
	public int addWeixinAccountInfo(WeixinAccountInfoBean bean) {
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		int result = dao.insertWeixinAccountInfo(bean);
		if (result > 0) {
			// 刷新缓存
			refreshCache(bean.getAppId(), bean.getStoreId(), result, bean.getAccountCode());
		}
		return result;
	}

	public int updateWeixinAccountInfo(WeixinAccountInfoBean bean) {
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		int result = dao.updateWeixinAccountInfo(bean);
		if (result > 0) {
			// 刷新缓存
			refreshCache(bean.getAppId(), bean.getStoreId(), bean.getAccountId(), bean.getAccountCode());
		}
		return result;
	}

	private void refreshCache(int appId, int storeId, int accountId, String accountCode) {
		SysApplicationInfoClientCache sysCache = new SysApplicationInfoClientCache();
		StoreInfoClientCache storeCache = new StoreInfoClientCache();

		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		accountInfoCache.refreshWeixinAccountInfoByCondition(sysCache.getSysApplicationCodeByKey(appId),
				storeCache.getStoreCodeByKey(storeId));
		accountInfoCache.refreshWeixinAccountCode(accountId);
		accountInfoCache.refreshWeixinAccountId(accountCode);
		accountInfoCache.refreshWeixinAccountInfoByCode(accountCode);

		WeixinConfigCache configCache = new WeixinConfigCache();
		configCache.refreshWeixinConfigBean(accountId);
	}

	public WeixinAccountInfoBean getWeixinAccountInfoByKey(int accountId) {
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		return dao.getWeixinAccountInfoByKey(accountId);
	}

	public PageResultBean<WeixinAccountInfoBean> getWeixinAccountInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		return dao.getWeixinAccountInfoPageListByMap(condMap, pageBean);
	}

	public WeixinAccountInfoBean getWeixinAccountInfoByCondition(String appCode, String storeCode) {
		SysApplicationInfoClientCache sysCache = new SysApplicationInfoClientCache();
		StoreInfoClientCache storeCache = new StoreInfoClientCache();

		int appId = sysCache.getSysApplicationIdByCode(appCode);
		int storeId = storeCache.getStoreIdByCode(storeCode);
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		return dao.getWeixinAccountInfoByKey(appId, storeId);
	}

	public WeixinAccountInfoBean getWeixinAccountInfoByKey(int appId, int storeId) {
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		return dao.getWeixinAccountInfoByKey(appId, storeId);
	}

	public List<WeixinAccountInfoBean> getWeixinAccountInfoListByGroupList(List<AdminGroupInfoBean> groupList) {
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		List<WeixinAccountInfoBean> queryResult = dao.getWeixinAccountInfoListByGroupList(groupList);
		return queryResult;
	}

	public WeixinAccountInfoBean getWeixinAccountInfoByCode(String accountCode) {
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		return dao.getWeixinAccountInfoByCode(accountCode);
	}

	public ReturnMessageBean verifyWeixinAccountCode(WeixinAccountInfoBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("accountCode", bean.getAccountCode());
		List<WeixinAccountInfoBean> weixinAccountCodeList = dao.getWeixinAccountInfoListByMap(condMap);
		if (weixinAccountCodeList.size() > 0) {
			result.setMessage("公众号代码重复");
		}
		return result;
	}

	public ReturnMessageBean verifyWeixinAccountId(WeixinAccountInfoBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (bean.getAppId() != 0) {
			WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("storeId", String.valueOf(bean.getStoreId()));
			condMap.put("appId", String.valueOf(bean.getAppId()));
			List<WeixinAccountInfoBean> weixinAccountInfoList = dao.getWeixinAccountInfoListByMap(condMap);
			if (weixinAccountInfoList.size() > 0) {
				result.setMessage("应用及商店Id重复");
			}
		} else {
			if (bean.getStoreId() != 0) {
				result.setMessage("应用及商店Id非法");
			}
		}
		return result;
	}
}
