package com.lpmas.weixin.server.business;

import com.lpmas.weixin.bean.WeixinConfigInfoBean;
import com.lpmas.weixin.server.dao.WeixinConfigInfoDao;

public class WeixinConfigInfoBusiness {
	
	public WeixinConfigInfoBean getWeixinConfigInfoByCode(String configCode) {
		WeixinConfigInfoDao dao = new WeixinConfigInfoDao();
		return dao.getWeixinConfigInfoByCode(configCode);
	}
	
}