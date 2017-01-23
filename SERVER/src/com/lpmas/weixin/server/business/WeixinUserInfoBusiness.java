package com.lpmas.weixin.server.business;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.bean.request.user.GetUserInfoRequestBean;
import com.lpmas.weixin.api.bean.response.user.GetUserInfoResponseBean;
import com.lpmas.weixin.api.request.user.GetUserInfo;
import com.lpmas.weixin.bean.WeixinUserInfoBean;
import com.lpmas.weixin.business.WeixinUtil;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.lpmas.weixin.server.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.server.dao.WeixinUserInfoDao;
import com.lpmas.weixin.server.factory.WeixinRequestFactory;

public class WeixinUserInfoBusiness {

	private Logger logger = LoggerFactory.getLogger(WeixinUserInfoBusiness.class);

	public WeixinUserInfoBean getWeixinUserInfoByKey(String _id) {
		WeixinUserInfoDao dao = new WeixinUserInfoDao();
		return dao.getWeixinUserInfoByKey(_id);
	}

	public int updateWeixinUserInfo(WeixinUserInfoBean userInfoBean) {
		WeixinUserInfoDao dao = new WeixinUserInfoDao();
		return dao.updateWeixinUserInfo(userInfoBean);
	}

	public int updateWeixinUserInfoByMap(String id, Map<String, Object> map) {
		WeixinUserInfoDao dao = new WeixinUserInfoDao();
		return dao.updateWeixinUserInfoByMap(id, map);
	}

	public int addWeixinUserInfo(WeixinUserInfoBean bean) {
		WeixinUserInfoDao dao = new WeixinUserInfoDao();
		return dao.insertWeixinUserInfo(bean);
	}

	public int subscribe(String accountCode, EventBaseBean bean) {
		// 获取ACCOUNTID
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);
		// 新关注的用户从调用微信接口获取详细信息后，插入MONGO
		GetUserInfo getUserInfo = new GetUserInfo();
		GetUserInfoRequestBean requestBean = WeixinRequestFactory.getRequestBean(accountCode,
				GetUserInfoRequestBean.class);
		requestBean.setOpenId(bean.getFromUserName());
		GetUserInfoResponseBean responseBean = (GetUserInfoResponseBean) getUserInfo.execute(requestBean);
		if (!WeixinUtil.isSuccess(responseBean)) {
			logger.error("获取关注者信息失败.errMsg:" + responseBean.getErrMsg());
			return 0;
		}
		// 转换成USERINFOBEAN
		WeixinUserInfoBean userInfoBean = new WeixinUserInfoBean();
		BeanKit.copyBean(userInfoBean, responseBean);
		userInfoBean.setAccountId(accountId);
		userInfoBean.set_id(WeixinMongoConfig.getWeixinUserInfoKey(userInfoBean.getOpenId(), accountId));
		userInfoBean.setIsSubscribe(responseBean.getSubscribe());
		userInfoBean.setSubscribeTime(responseBean.getSubscribeTime() * 1000);
		// 判断要插入还是更新
		long result = 0;
		// 先查询这个用户是否曾经关注过，有就更新，没有就新增
		WeixinUserInfoBean existBean = null;
		try {
			// 查出MONGO中有无这个OPENID
			existBean = getWeixinUserInfoByKey(userInfoBean.get_id());
			if (existBean != null) {
				// 更新
				Map<String, Object> map = BeanKit.bean2Map(userInfoBean);
				map.remove("_id");
				map.remove("unsubscribeTime");
				result = updateWeixinUserInfoByMap(userInfoBean.get_id(), map);
			} else {
				// 新增
				result = addWeixinUserInfo(userInfoBean);
			}
		} catch (Exception e) {
			return 0;
		}
		return (int) result;
	}

	public int unsubscribe(String accountCode, EventBaseBean bean) {
		// 获取ACCOUNTID
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);
		String _id = WeixinMongoConfig.getWeixinUserInfoKey(bean.getFromUserName(), accountId);
		Map<String, Object> map = new HashMap<String, Object>();
		// 更新关注者
		map.put("isSubscribe", Constants.STATUS_NOT_VALID);
		map.put("unsubscribeTime", System.currentTimeMillis());

		return updateWeixinUserInfoByMap(_id, map);
	}

}
