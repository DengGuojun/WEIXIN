package com.lpmas.weixin.server.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.admin.bean.AdminGroupInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.system.client.cache.StoreInfoClientCache;
import com.lpmas.system.client.cache.SysApplicationInfoClientCache;
import com.lpmas.weixin.bean.WeixinAccountInfoBean;
import com.lpmas.weixin.server.dao.WeixinAccountInfoDao;

public class WeixinAccountInfoBusiness {

	public WeixinAccountInfoBean getWeixinAccountInfoByCode(String accountCode) {
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		return dao.getWeixinAccountInfoByCode(accountCode);
	}

	public WeixinAccountInfoBean getWeixinAccountInfoByKey(int accountId) {
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		return dao.getWeixinAccountInfoByKey(accountId);
	}

	public WeixinAccountInfoBean getWeixinAccountInfoByCondition(String appCode, String storeCode) {
		SysApplicationInfoClientCache sysCache = new SysApplicationInfoClientCache();
		StoreInfoClientCache storeCache = new StoreInfoClientCache();

		int appId = sysCache.getSysApplicationIdByCode(appCode);
		int storeId = storeCache.getStoreIdByCode(storeCode);
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		return dao.getWeixinAccountInfoByCondition(appId, storeId);
	}

	public List<WeixinAccountInfoBean> getWeixinAccountInfoListByGroupList(List<AdminGroupInfoBean> groupList) {
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		return dao.getWeixinAccountInfoListByGroupList(groupList);
	}
	
	public List<WeixinAccountInfoBean> getWeixinAccountAllList(){
		Map<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		
		WeixinAccountInfoDao dao = new WeixinAccountInfoDao();
		return dao.getWeixinAccountInfoListByMap(condMap);
	}

}
