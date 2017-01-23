package com.lpmas.weixin.console.business;

import java.util.HashMap;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.weixin.bean.WeixinPropertyConfigBean;
import com.lpmas.weixin.console.dao.WeixinPropertyConfigDao;

public class WeixinPropertyConfigBusiness {
	public int addWeixinPropertyConfig(WeixinPropertyConfigBean bean) {
		WeixinPropertyConfigDao dao = new WeixinPropertyConfigDao();
		return dao.insertWeixinPropertyConfig(bean);
	}

	public int updateWeixinPropertyConfig(WeixinPropertyConfigBean bean) {
		WeixinPropertyConfigDao dao = new WeixinPropertyConfigDao();
		return dao.updateWeixinPropertyConfig(bean);
	}

	public WeixinPropertyConfigBean getWeixinPropertyConfigByKey(int propertyId) {
		WeixinPropertyConfigDao dao = new WeixinPropertyConfigDao();
		return dao.getWeixinPropertyConfigByKey(propertyId);
	}

	public PageResultBean<WeixinPropertyConfigBean> getWeixinPropertyConfigPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		WeixinPropertyConfigDao dao = new WeixinPropertyConfigDao();
		return dao.getWeixinPropertyConfigPageListByMap(condMap, pageBean);
	}

}