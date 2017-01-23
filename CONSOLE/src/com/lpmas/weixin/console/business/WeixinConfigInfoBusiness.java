package com.lpmas.weixin.console.business;

import java.util.HashMap;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.weixin.bean.WeixinConfigInfoBean;
import com.lpmas.weixin.console.dao.WeixinConfigInfoDao;

public class WeixinConfigInfoBusiness {
	public int addWeixinConfigInfo(WeixinConfigInfoBean bean) {
		WeixinConfigInfoDao dao = new WeixinConfigInfoDao();
		return dao.insertWeixinConfigInfo(bean);
	}

	public int updateWeixinConfigInfo(WeixinConfigInfoBean bean) {
		WeixinConfigInfoDao dao = new WeixinConfigInfoDao();
		return dao.updateWeixinConfigInfo(bean);
	}

	public WeixinConfigInfoBean getWeixinConfigInfoByKey(int configId) {
		WeixinConfigInfoDao dao = new WeixinConfigInfoDao();
		return dao.getWeixinConfigInfoByKey(configId);
	}
	
	public WeixinConfigInfoBean getWeixinConfigInfoByCode(String configCode) {
		WeixinConfigInfoDao dao = new WeixinConfigInfoDao();
		return dao.getWeixinConfigInfoByCode(configCode);
	}

	public PageResultBean<WeixinConfigInfoBean> getWeixinConfigInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		WeixinConfigInfoDao dao = new WeixinConfigInfoDao();
		return dao.getWeixinConfigInfoPageListByMap(condMap, pageBean);
	}
	
}