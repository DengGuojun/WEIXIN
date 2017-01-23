package com.lpmas.weixin.server.business;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.web.UrlKit;
import com.lpmas.weixin.api.bean.request.qrcode.CreateTemporaryTicketRequestBean;
import com.lpmas.weixin.api.bean.request.qrcode.CreateTicketBySceneIdRequestBean;
import com.lpmas.weixin.api.bean.response.qrcode.CreateTicketResponseBean;
import com.lpmas.weixin.api.request.qrcode.CreateTemporaryTicket;
import com.lpmas.weixin.api.request.qrcode.CreateTicketBySceneId;
import com.lpmas.weixin.bean.QrcodeInfoBean;
import com.lpmas.weixin.business.WeixinUtil;
import com.lpmas.weixin.client.config.WeixinQrcodeConfig;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.lpmas.weixin.server.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.server.dao.WeixinQrcodeInfoDao;
import com.lpmas.weixin.server.factory.WeixinRequestFactory;

public class WeixinQrcodeInfoBusiness {
	private static Logger logger = LoggerFactory.getLogger(WeixinQrcodeInfoBusiness.class);
	private static Random random = new Random();
	private static int MAX_RAMDOM_ATTEMPT = 1000;
	private static int MAX_PERMANENT_TICKET_COUNT = 100000;

	public QrcodeInfoBean getQrcodeInfoByKey(String _id) {
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		return dao.getQrcodeInfoByKey(_id);
	}
	
	public QrcodeInfoBean getQrcodeInfoByTicket(String ticket){
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		Map<String,Object> condMap = new HashMap<String,Object>();
		condMap.put("ticket", ticket);
		
		return dao.getQrcodeInfoByMap(condMap);
	}

	public int addQrcodeInfo(QrcodeInfoBean bean) {
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		return dao.insertQrcodeInfo(bean);
	}

	public int updateQrcodeInfoByMap(String _id, Map<String, Object> condMap) {
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		return dao.updateQrcodeInfoByMap(_id, condMap);
	}

	public int updateQrcodeInfo(QrcodeInfoBean qrcodeInfoBean) {
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		return dao.updateQrcodeInfo(qrcodeInfoBean);
	}

	public long getTotalRecordCountByMap(Map<String, Object> condMap) {
		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		return dao.getTotalRecordCountByMap(condMap);
	}

	public String getAppSceneIdByCondition(String accountCode, int sceneId) {
		WeixinAccountInfoCache cache = new WeixinAccountInfoCache();
		int accountId = cache.getAccountIdByCode(accountCode);

		WeixinQrcodeInfoDao dao = new WeixinQrcodeInfoDao();
		String result = "";
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("accountId", accountId);
		condMap.put("sceneId", sceneId);
		QrcodeInfoBean qrcodeInfoBean = dao.getQrcodeInfoByMap(condMap);
		if (qrcodeInfoBean != null) {
			return qrcodeInfoBean.getAppSceneId();
		}
		return result;
	}

	public QrcodeInfoBean getPermanentQrcodeInfoByAppSceneId(String accountCode, String appSceneId,
			String callBackQueue) {
		QrcodeInfoBean qrcodeInfoBean = null;
		CreateTicketResponseBean responseBean = null;
		// 获取ACCOUNTID
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);
		// 先找出在数据库有没有
		String _id = WeixinMongoConfig.getQrcodeInfoKey(accountId, appSceneId,
				WeixinQrcodeConfig.QRCODE_TYPE_PERMANENT);
		qrcodeInfoBean = getQrcodeInfoByKey(_id);
		if (qrcodeInfoBean != null) {
			// 有就直接返回
			return qrcodeInfoBean;
		} else {
			// 没有就问微信拿
			int sceneId = generateSceneId(accountId, WeixinQrcodeConfig.QRCODE_TYPE_PERMANENT);
			CreateTicketBySceneIdRequestBean requestBean = WeixinRequestFactory.getRequestBean(accountCode,
					CreateTicketBySceneIdRequestBean.class);
			requestBean.setSceneId(sceneId);
			CreateTicketBySceneId createTicketBySceneId = new CreateTicketBySceneId();
			responseBean = (CreateTicketResponseBean) createTicketBySceneId.execute(requestBean);
			if (!WeixinUtil.isSuccess(responseBean)) {
				logger.error("get qrcode fail,errMsg:" + responseBean.getErrMsg());
				return null;
			}
			// 插入MONGO
			qrcodeInfoBean = new QrcodeInfoBean();
			BeanKit.copyBean(qrcodeInfoBean, responseBean);
			qrcodeInfoBean.setAppSceneId(appSceneId);
			qrcodeInfoBean.setQrcodeType(WeixinQrcodeConfig.QRCODE_TYPE_PERMANENT);
			qrcodeInfoBean.set_id(_id);
			qrcodeInfoBean.setSceneId(sceneId);
			qrcodeInfoBean.setAccountId(accountId);
			qrcodeInfoBean.setCreateTime(new Timestamp(System.currentTimeMillis()));
			qrcodeInfoBean.setActionName(requestBean.getActionName());
			qrcodeInfoBean.setEncodeTicket(UrlKit.urlEncode(responseBean.getTicket(), "UTF-8"));
			qrcodeInfoBean.setCallBackQueue(callBackQueue);
			addQrcodeInfo(qrcodeInfoBean);
			// 保存后从数据库取出，保证结果与数据库一致
			return getQrcodeInfoByKey(_id);
		}
	}

	public QrcodeInfoBean getTemporaryQrcodeInfoByAppSceneId(String accountCode, String appSceneId, String callBackQueue,
			int expireSeconds) {
		QrcodeInfoBean qrcodeInfoBean = null;
		CreateTicketResponseBean responseBean = null;
		// 获取ACCOUNTID
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);
		// 先找出在数据库有没有
		String _id = WeixinMongoConfig.getQrcodeInfoKey(accountId, appSceneId,
				WeixinQrcodeConfig.QRCODE_TYPE_TEMPORARY);
		qrcodeInfoBean = getQrcodeInfoByKey(_id);
		if (qrcodeInfoBean == null || isOverExpire(qrcodeInfoBean)) {
			// 获得一个随机场景ID
			int sceneId = 0;
			if (qrcodeInfoBean == null)
				sceneId = generateSceneId(accountId, WeixinQrcodeConfig.QRCODE_TYPE_TEMPORARY);
			else {
				sceneId = qrcodeInfoBean.getSceneId();
			}
			// 数据库里面没有或者已经失效就重新获取并保存到数据库
			CreateTemporaryTicketRequestBean requestBean = WeixinRequestFactory.getRequestBean(accountCode,
					CreateTemporaryTicketRequestBean.class);
			requestBean.setSceneId(sceneId);
			requestBean.setExpireSeconds(expireSeconds);
			CreateTemporaryTicket createTemporaryTicket = new CreateTemporaryTicket();
			responseBean = (CreateTicketResponseBean) createTemporaryTicket.execute(requestBean);
			if (!WeixinUtil.isSuccess(responseBean)) {
				logger.error("get qrcode fail,errMsg:" + responseBean.getErrMsg());
				return null;
			}
			if (qrcodeInfoBean == null) {
				qrcodeInfoBean = new QrcodeInfoBean();
				BeanKit.copyBean(qrcodeInfoBean, responseBean);
				qrcodeInfoBean.setAppSceneId(appSceneId);
				qrcodeInfoBean.setQrcodeType(WeixinQrcodeConfig.QRCODE_TYPE_TEMPORARY);
				qrcodeInfoBean.set_id(_id);
				qrcodeInfoBean.setSceneId(sceneId);
				qrcodeInfoBean.setAccountId(accountId);
				qrcodeInfoBean.setCreateTime(DateKit.getCurrentTimestamp());
				qrcodeInfoBean.setActionName(requestBean.getActionName());
				qrcodeInfoBean.setEncodeTicket(UrlKit.urlEncode(responseBean.getTicket(), "UTF-8"));
				qrcodeInfoBean.setCallBackQueue(callBackQueue);
				addQrcodeInfo(qrcodeInfoBean);
			} else {
				// 有就更新
				qrcodeInfoBean.setExpireSeconds(responseBean.getExpireSeconds());
				qrcodeInfoBean.setTicket(responseBean.getTicket());
				qrcodeInfoBean.setEncodeTicket(UrlKit.urlEncode(responseBean.getTicket(), "UTF-8"));
				qrcodeInfoBean.setUrl(responseBean.getUrl());
				qrcodeInfoBean.setCreateTime(DateKit.getCurrentTimestamp());
				qrcodeInfoBean.setCallBackQueue(callBackQueue);
				updateQrcodeInfo(qrcodeInfoBean);
			}
			return getQrcodeInfoByKey(_id);
		}
		// 更新已有的QUEUENAME
		qrcodeInfoBean.setCallBackQueue(callBackQueue);
		updateQrcodeInfo(qrcodeInfoBean);
		return getQrcodeInfoByKey(_id);
	}

	private boolean isOverExpire(QrcodeInfoBean bean) {
		if (bean.getExpireSeconds() < WeixinQrcodeConfig.SECONDS_BEFORE_EXPIRE) {
			return true;
		}
		long current = System.currentTimeMillis() / 1000;
		if ((bean.getExpireSeconds() + (bean.getCreateTime().getTime() / 1000))
				- current < WeixinQrcodeConfig.SECONDS_BEFORE_EXPIRE) {
			return true;
		}
		return false;
	}

	private int generateSceneId(int accountId, String qrcodeType) {
		int sceneId = 0;
		synchronized (WeixinQrcodeInfoBusiness.class) {
			// 获得一个随机场景ID
			Map<String, Object> condMap = new HashMap<String, Object>();
			// 判断最大值
			int max_count = 0;
			if (qrcodeType.equals(WeixinQrcodeConfig.QRCODE_TYPE_TEMPORARY)) {
				max_count = Integer.MAX_VALUE;
			} else {
				max_count = MAX_PERMANENT_TICKET_COUNT;
			}
			sceneId = random.nextInt(max_count);
			int attempt = 0;
			while (MAX_RAMDOM_ATTEMPT >= attempt) {
				// 检验这个SCENEID是否存在于数据库，在就重新RAMDOM
				condMap.put("sceneId", sceneId);
				condMap.put("accountId", accountId);
				condMap.put("qrcodeType", qrcodeType);
				int count = (int) getTotalRecordCountByMap(condMap);
				if (count == 0)
					break;
				else {
					sceneId = random.nextInt(max_count);
					attempt++;
					if (attempt == MAX_RAMDOM_ATTEMPT) {
						logger.error("随机场景ID获取失败，超过最大尝试次数：" + MAX_RAMDOM_ATTEMPT);
						return -1;
					}
				}
			}
		}
		return sceneId;
	}

}
