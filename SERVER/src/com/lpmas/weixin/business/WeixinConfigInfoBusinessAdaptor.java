package com.lpmas.weixin.business;

import com.lpmas.weixin.bean.WeixinConfigInfoBean;
import com.lpmas.weixin.server.business.WeixinConfigInfoBusiness;

public class WeixinConfigInfoBusinessAdaptor {
	public WeixinConfigInfoBean getWeixinConfigInfoByCode(String configCode) {
		WeixinConfigInfoBusiness business = new WeixinConfigInfoBusiness();
		return business.getWeixinConfigInfoByCode(configCode);
	}
}
