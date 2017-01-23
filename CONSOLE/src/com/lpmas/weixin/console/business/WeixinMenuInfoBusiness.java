package com.lpmas.weixin.console.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.weixin.bean.WeixinMenuInfoBean;
import com.lpmas.weixin.console.dao.WeixinMenuInfoDao;

public class WeixinMenuInfoBusiness {
	public int addWeixinMenuInfo(WeixinMenuInfoBean bean) {
		WeixinMenuInfoDao dao = new WeixinMenuInfoDao();
		return dao.insertWeixinMenuInfo(bean);
	}

	public int updateWeixinMenuInfo(WeixinMenuInfoBean bean) {
		WeixinMenuInfoDao dao = new WeixinMenuInfoDao();
		return dao.updateWeixinMenuInfo(bean);
	}

	public WeixinMenuInfoBean getWeixinMenuInfoByKey(int menuId) {
		WeixinMenuInfoDao dao = new WeixinMenuInfoDao();
		return dao.getWeixinMenuInfoByKey(menuId);
	}

	public PageResultBean<WeixinMenuInfoBean> getWeixinMenuInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		WeixinMenuInfoDao dao = new WeixinMenuInfoDao();
		return dao.getWeixinMenuInfoPageListByMap(condMap, pageBean);
	}

	public List<WeixinMenuInfoBean> getWeixinMenuInfoListByMap(HashMap<String, String> condMap) {
		WeixinMenuInfoDao dao = new WeixinMenuInfoDao();
		return dao.getWeixinMenuInfoListByMap(condMap);
	}

}