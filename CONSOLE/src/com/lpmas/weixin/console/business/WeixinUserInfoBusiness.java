package com.lpmas.weixin.console.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.api.bean.request.user.GetUserInfoRequestBean;
import com.lpmas.weixin.api.bean.request.user.GetUserListRequestBean;
import com.lpmas.weixin.api.bean.request.user.UpdateUserRemarkRequestBean;
import com.lpmas.weixin.api.bean.response.user.GetUserInfoResponseBean;
import com.lpmas.weixin.api.bean.response.user.GetUserListResponseBean;
import com.lpmas.weixin.api.bean.response.user.UpdateUserRemarkResponseBean;
import com.lpmas.weixin.api.request.user.GetUserInfo;
import com.lpmas.weixin.api.request.user.GetUserList;
import com.lpmas.weixin.api.request.user.UpdateUserRemark;
import com.lpmas.weixin.bean.WeixinUserInfoBean;
import com.lpmas.weixin.business.WeixinUtil;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.lpmas.weixin.console.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.console.dao.WeixinUserInfoDao;
import com.lpmas.weixin.console.factory.WeixinRequestFactory;

public class WeixinUserInfoBusiness {

	private static Logger logger = LoggerFactory.getLogger(WeixinUserInfoBusiness.class);

	public PageResultBean<WeixinUserInfoBean> getWeixinUserInfoPageList(PageBean pageBean,
			Map<String, Object> condMap) {
		WeixinUserInfoDao weixinUserInfoDao = new WeixinUserInfoDao();
		Map<String, Object> orderBy = new HashMap<String, Object>();
		String isSubscribeStr = condMap.get("isSubscribe").toString();
		int isSubscribe = 0;
		try {
			isSubscribe = Integer.valueOf(isSubscribeStr);
			if (isSubscribe == Constants.STATUS_VALID) {
				orderBy.put("subscribeTime", -1);
			} else {
				orderBy.put("unsubscribeTime", -1);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return weixinUserInfoDao.getWeixinUserInfoPageList(pageBean, condMap, orderBy);
	}

	public List<WeixinUserInfoBean> getWeixinUserInfoListByMap(Map<String, Object> condMap) {
		WeixinUserInfoDao weixinUserInfoDao = new WeixinUserInfoDao();
		Map<String, Object> orderBy = new HashMap<String, Object>();
		String isSubscribeStr = condMap.get("isSubscribe").toString();
		int isSubscribe = 0;
		try {
			isSubscribe = Integer.valueOf(isSubscribeStr);
			if (isSubscribe == Constants.STATUS_VALID) {
				orderBy.put("subscribeTime", -1);
			} else {
				orderBy.put("unsubscribeTime", -1);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return weixinUserInfoDao.getWeixinUserInfoListByMap(condMap, orderBy);
	}

	public WeixinUserInfoBean getWeixinUserInfoByKey(int accountId, String openId) {
		String _id = WeixinMongoConfig.getWeixinUserInfoKey(openId, accountId);
		WeixinUserInfoDao weixinUserInfoDao = new WeixinUserInfoDao();
		return weixinUserInfoDao.getWeixinUserInfoByKey(_id);
	}

	public List<String> getOpenIdList(int accountId) {
		// 获取CCOUNTCODE
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		String accountCode = accountInfoCache.getAccountCodeByKey(accountId);
		int count = 0;
		// 获取所有关注者列表
		GetUserList getUserList = new GetUserList();
		// 先调用1次接口，尝试获取总关注数和所有OPENID
		GetUserListRequestBean request = null;
		GetUserListResponseBean response = null;
		request = WeixinRequestFactory.getRequestBean(accountCode, GetUserListRequestBean.class);
		response = (GetUserListResponseBean) getUserList.execute(request);
		if (!WeixinUtil.isSuccess(response)) {
			return null;
		}
		// 生成总关注数大小的LIST
		List<String> openIds = new ArrayList<String>(response.getTotal());
		openIds.addAll(response.getOpenIdList());
		count += response.getCount();
		// 判断是否所有关注者都拉完
		String nextOpenId = response.getNextOpenId();
		// 未拉完就循环拉
		while (count < response.getTotal()) {
			request = WeixinRequestFactory.getRequestBean(accountCode, GetUserListRequestBean.class);
			request.setNextOpenId(nextOpenId);
			response = (GetUserListResponseBean) getUserList.execute(request);
			if (!WeixinUtil.isSuccess(response)) {
				return null;
			}
			// 刷新NEXTOPENID
			nextOpenId = response.getNextOpenId();
			// 复制到OPENID列表
			openIds.addAll(response.getOpenIdList());
			count += response.getCount();
		}

		// 已经获取到所有的OPENID
		return openIds;
	}

	public int updateWeixinUserInfo(WeixinUserInfoBean userInfoBean) {
		WeixinUserInfoDao weixinUserInfoDao = new WeixinUserInfoDao();
		return weixinUserInfoDao.updateWeixinUserInfo(userInfoBean);
	}

	public int updateWeixinUserInfoRemark(WeixinUserInfoBean userInfoBean) {
		// 获取CCOUNTCODE
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		String accountCode = accountInfoCache.getAccountCodeByKey(userInfoBean.getAccountId());

		UpdateUserRemarkRequestBean userRemarkRequestBean = WeixinRequestFactory.getRequestBean(accountCode,
				UpdateUserRemarkRequestBean.class);
		userRemarkRequestBean.setRemark(userInfoBean.getRemark());
		userRemarkRequestBean.setOpenId(userInfoBean.getOpenId());
		UpdateUserRemark updateUserRemark = new UpdateUserRemark();
		UpdateUserRemarkResponseBean responseBean = (UpdateUserRemarkResponseBean) updateUserRemark
				.execute(userRemarkRequestBean);
		if (!WeixinUtil.isSuccess(responseBean)) {
			return -1;
		}
		return 1;
	}

	public int refreshWeixinUserInfo(WeixinUserInfoBean userInfoBean) {
		// 获取CCOUNTCODE
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		String accountCode = accountInfoCache.getAccountCodeByKey(userInfoBean.getAccountId());
		// 向微信拉去这个用户最新的资料并更新到MONGO
		GetUserInfo getUserInfo = new GetUserInfo();
		GetUserInfoRequestBean requestBean = WeixinRequestFactory.getRequestBean(accountCode,
				GetUserInfoRequestBean.class);
		requestBean.setOpenId(userInfoBean.getOpenId());
		GetUserInfoResponseBean responseBean = (GetUserInfoResponseBean) getUserInfo.execute(requestBean);
		if (!WeixinUtil.isSuccess(responseBean)) {
			return -1;
		}
		// 更新
		WeixinUserInfoDao weixinUserInfoDao = new WeixinUserInfoDao();
		BeanKit.copyBean(userInfoBean, responseBean);
		Map<String, Object> map = BeanKit.bean2Map(userInfoBean);
		map.put("subscribeTime", responseBean.getSubscribeTime() * 1000);
		map.remove("_id");
		map.remove("unsubscribeTime");
		return weixinUserInfoDao.updateWeixinUserInfoByMap(userInfoBean.get_id(), map);
	}

	public ReturnMessageBean refreshWeixinUserInfoList(int accountId, float percent) {
		ReturnMessageBean messageBean = new ReturnMessageBean();
		List<String> openIds = getOpenIdList(accountId);
		// 参数校验
		if (percent > 1) {
			messageBean.setMessage("刷新数量不能大于总关注数");
			return messageBean;
		}
		if (openIds == null) {
			messageBean.setMessage("openId列表不能为空");
			return messageBean;
		}
		// 对象准备
		Random random = new Random();
		Set<String> finishOpenIds = new HashSet<String>();
		WeixinUserInfoBean userInfoBean = null;
		WeixinUserInfoDao weixinUserInfoDao = new WeixinUserInfoDao();

		// 获取CCOUNTCODE
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		String accountCode = accountInfoCache.getAccountCodeByKey(accountId);

		// 获取数据库中已有的关注者
		HashMap<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("accountId", accountId);
		condMap.put("isSubscribe", Constants.STATUS_VALID);
		List<WeixinUserInfoBean> existUserList = getWeixinUserInfoListByMap(condMap);
		Map<String, WeixinUserInfoBean> existUserMap = ListKit.list2Map(existUserList, "_id");

		GetUserInfo getUserInfo = new GetUserInfo();
		GetUserInfoRequestBean requestBean = null;
		GetUserInfoResponseBean responseBean = null;
		// 遍历获取用户信息
		Iterator<String> iterator = openIds.iterator();
		while (iterator.hasNext()) {
			// 随机获取一个不在已完成列表中的OPENID
			String openId = iterator.next();
			// 获取这个关注者的_ID
			String _id = WeixinMongoConfig.getWeixinUserInfoKey(openId, accountId);
			// 移除已有的用户MAP中的元素
			existUserMap.remove(_id);
			if (random.nextFloat() > percent) {
				// 大于比例就跳出本次循环，从概率保证刷新比例是设定的比例
				continue;
			}
			// 发起请求获取用户信息
			requestBean = WeixinRequestFactory.getRequestBean(accountCode, GetUserInfoRequestBean.class);
			requestBean.setOpenId(openId);
			responseBean = (GetUserInfoResponseBean) getUserInfo.execute(requestBean);
			if (!WeixinUtil.isSuccess(responseBean)) {
				continue;
			}
			try {
				long result = -1;
				// 查出MONGO中有无这个OPENID
				userInfoBean = weixinUserInfoDao.getWeixinUserInfoByKey(_id);
				if (userInfoBean != null) {
					// 更新
					BeanKit.copyBean(userInfoBean, responseBean);
					Map<String, Object> map = BeanKit.bean2Map(userInfoBean);
					map.put("subscribeTime", responseBean.getSubscribeTime() * 1000);
					map.remove("_id");
					map.remove("unsubscribeTime");
					result = weixinUserInfoDao.updateWeixinUserInfoByMap(userInfoBean.get_id(), map);
				} else {
					// 新增
					userInfoBean = new WeixinUserInfoBean();
					BeanKit.copyBean(userInfoBean, responseBean);
					// 重新设置关注时间
					userInfoBean.setSubscribeTime(responseBean.getSubscribeTime() * 1000);
					userInfoBean.set_id(_id);
					userInfoBean.setAccountId(accountId);
					userInfoBean.setIsSubscribe(responseBean.getSubscribe());
					result = weixinUserInfoDao.insertWeixinUserInfo(userInfoBean);
				}
				// 判断结果
				if (result >= 0) {
					// 成功插入到成功列表
					finishOpenIds.add(openId);
				}
			} catch (Exception e) {
				logger.error("", e);
				continue;
			}
		}

		// 遍历Map把剩下的关注者设成未关注并填上当前时间
		condMap.clear();
		condMap.put("isSubscribe", Constants.STATUS_NOT_VALID);
		for (Entry<String, WeixinUserInfoBean> entry : existUserMap.entrySet()) {
			condMap.put("unsubscribeTime", System.currentTimeMillis());
			weixinUserInfoDao.updateWeixinUserInfoByMap(entry.getKey(), condMap);
		}

		if (finishOpenIds.isEmpty()) {
			messageBean.setMessage("无法刷新关注者信息");
		} else {
			messageBean.setCode(Constants.STATUS_VALID);
			messageBean.setMessage("成功刷新关注者" + finishOpenIds.size() + "人");
		}

		return messageBean;
	}

	public ReturnMessageBean refreshWeixinUserInfoAllList(int accountId) {
		return refreshWeixinUserInfoList(accountId, new Float(1));
	}

}
