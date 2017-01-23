package com.lpmas.weixin.console.business;

import java.util.HashMap;
import java.util.Map;

import com.lpmas.framework.nosql.mongodb.MongoDBConfig;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.weixin.bean.QrcodeInfoBean;
import com.lpmas.weixin.console.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.console.dao.WeixinQrcodeInfoDao;

public class WeixinQrcodeInfoBusiness {

	public QrcodeInfoBean getQrcodeInfoByKey(String _id) {
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		return dao.getQrcodeInfoByKey(_id);
	}

	public int addQrcodeInfo(QrcodeInfoBean bean) {
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		return dao.insertQrcodeInfo(bean);
	}

	public int updateQrcodeInfoByMap(String _id, Map<String, Object> condMap) {
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		return dao.updateQrcodeInfoByMap(_id, condMap);
	}

	public int updateQrcodeInfo(QrcodeInfoBean bean) {
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		return dao.updateQrcodeInfo(bean);
	}

	public PageResultBean<QrcodeInfoBean> getQrcodeInfoPageListByMap(Map<String, Object> condMap, PageBean pageBean) {
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		Map<String, Object> orderBy = new HashMap<String, Object>();
		orderBy.put("createTime", MongoDBConfig.SORT_ORDER_DESC);
		return dao.getQrcodeInfoPageListByMap(condMap, pageBean, orderBy);
	}

	public long getTotalRecordCountByMap(Map<String, Object> condMap) {
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		return dao.getTotalRecordCountByMap(condMap);
	}

	public String getAppSceneIdByCondition(String accountCode, int sceneId, String qrcodeType) {
		// 获取ACCOUNTID
		WeixinAccountInfoCache cache = new WeixinAccountInfoCache();
		int accountId = cache.getAccountIdByCode(accountCode);
		// 查询条件
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("accountId", accountId);
		condMap.put("sceneId", sceneId);
		condMap.put("qrcodeType", qrcodeType);
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		String result = "";
		QrcodeInfoBean qrcodeInfoBean = dao.getQrcodeInfoByMap(condMap);
		if (qrcodeInfoBean != null) {
			return qrcodeInfoBean.getAppSceneId();
		}
		return result;
	}

}
