package com.lpmas.weixin.console.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.nosql.mongodb.MongoDBConfig;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.weixin.api.bean.material.MaterialInfoBean;
import com.lpmas.weixin.api.bean.request.material.GetMaterialListRequestBean;
import com.lpmas.weixin.api.bean.response.material.GetMaterialListResponseBean;
import com.lpmas.weixin.api.bean.response.material.NewsListResponseBean;
import com.lpmas.weixin.api.config.MaterialTypeConfig;
import com.lpmas.weixin.api.request.material.GetMaterialList;
import com.lpmas.weixin.bean.WeixinMaterialInfoBean;
import com.lpmas.weixin.business.WeixinUtil;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.lpmas.weixin.console.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.console.dao.WeixinMaterialInfoDao;
import com.lpmas.weixin.console.factory.WeixinRequestFactory;

public class WeixinMaterialInfoBusiness {
	private static Logger logger = LoggerFactory.getLogger(WeixinMaterialInfoBusiness.class);
	private static int MAX_RATERIAL_REQUEST_COUNT = 20;

	public PageResultBean<WeixinMaterialInfoBean> getWeixinMaterialInfoPageList(PageBean pageBean,
			Map<String, Object> condMap) {
		WeixinMaterialInfoDao dao = new WeixinMaterialInfoDao();
		Map<String, Object> orderBy = new HashMap<String, Object>();
		orderBy.put("createTime", MongoDBConfig.SORT_ORDER_DESC);
		return dao.getWeixinMaterialPageListByMap(condMap, pageBean, orderBy);
	}

	public List<WeixinMaterialInfoBean> getWeixinMaterialInfoListByMap(Map<String, Object> condMap) {
		WeixinMaterialInfoDao dao = new WeixinMaterialInfoDao();
		Map<String, Object> orderBy = new HashMap<String, Object>();
		orderBy.put("createTime", MongoDBConfig.SORT_ORDER_DESC);
		return dao.getWeixinMaterialInfoListByMap(condMap, orderBy);
	}

	public WeixinMaterialInfoBean getWeixinMaterialInfoByKey(String _id) {
		WeixinMaterialInfoDao dao = new WeixinMaterialInfoDao();
		return dao.getWeixinMaterilInfoByKey(_id);
	}

	public int addWeixinMaterialInfo(WeixinMaterialInfoBean bean) {
		WeixinMaterialInfoDao dao = new WeixinMaterialInfoDao();
		return dao.insertWeixinMaterialInfo(bean);
	}

	public int updateWeixinMaterialInfo(WeixinMaterialInfoBean bean) {
		WeixinMaterialInfoDao dao = new WeixinMaterialInfoDao();
		return dao.updateWeixinMaterialInfo(bean);
	}

	public int deleteWeixinMaterialInfo(int accountId, String mediaType, String mediaId) {
		// 查出对应的素材BEAN
		WeixinMaterialInfoBean bean = null;
		String _id = WeixinMongoConfig.getMaterilInfoKey(accountId, mediaType, mediaId);
		bean = getWeixinMaterialInfoByKey(_id);
		// 判断列表大小
		if (bean == null) {
			return -1;// 没有这个素材
		} else {
			WeixinMaterialInfoDao dao = new WeixinMaterialInfoDao();
			return dao.deleteWeixinMaterialInfo(bean);
		}
	}

	public int deleteWeixinMaterialInfo(WeixinMaterialInfoBean bean) {
		WeixinMaterialInfoDao dao = new WeixinMaterialInfoDao();
		return dao.deleteWeixinMaterialInfo(bean);
	}

	@SuppressWarnings("unchecked")
	public int refreshMaterialInfo(int total, String mediaType, int accountId) {
		// 获取CCOUNTCODE
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		String accountCode = accountInfoCache.getAccountCodeByKey(accountId);

		if (!MaterialTypeConfig.MATERIAL_TYPE_MAP.containsKey(mediaType)) {
			return -1;
		}
		if (total <= 0)
			return 0;
		int successCount = 0;
		GetMaterialListRequestBean getMaterialListRequestBean = null;
		GetMaterialListResponseBean<MaterialInfoBean> getMaterialListResponseBean = null;
		GetMaterialListResponseBean<NewsListResponseBean> getNewsListResponseBean = null;
		GetMaterialList getMaterialList = new GetMaterialList();
		WeixinMaterialInfoBean materialInfoBean = null;
		if (MaterialTypeConfig.MT_NEWS.equals(mediaType)) {
			// 图文
			// 计算offset和COUNT
			int offSet = 0;
			int remain = 0;
			int count = 0;
			if (total <= MAX_RATERIAL_REQUEST_COUNT)
				count = total;
			else
				count = MAX_RATERIAL_REQUEST_COUNT;
			while (true) {
				getMaterialListRequestBean = WeixinRequestFactory.getRequestBean(accountCode,
						GetMaterialListRequestBean.class);
				getMaterialListRequestBean.setOffset(offSet);
				getMaterialListRequestBean.setCount(count);
				getMaterialListRequestBean.setMediaType(mediaType);
				getNewsListResponseBean = (GetMaterialListResponseBean<NewsListResponseBean>) getMaterialList
						.execute(getMaterialListRequestBean);
				if (WeixinUtil.isSuccess(getNewsListResponseBean)) {
					for (NewsListResponseBean bean : getNewsListResponseBean.getItemList()) {
						materialInfoBean = new WeixinMaterialInfoBean();
						materialInfoBean
								.set_id(WeixinMongoConfig.getMaterilInfoKey(accountId, mediaType, bean.getMediaId()));
						materialInfoBean.setAccountId(accountId);
						materialInfoBean.setMediaId(bean.getMediaId());
						materialInfoBean.setMediaType(mediaType);
						materialInfoBean.setUpdateTime(bean.getUpdateTime());
						materialInfoBean.setCreateTime(bean.getUpdateTime());
						materialInfoBean.setArticleList(bean.getContent().getArticleList());
						// 判断是要ADD还是UPDATE
						WeixinMaterialInfoBean exitBean = getWeixinMaterialInfoByKey(materialInfoBean.get_id());
						int result = -1;
						if (exitBean == null) {
							// ADD
							result = addWeixinMaterialInfo(materialInfoBean);
						} else {
							// UPDATE
							result = updateWeixinMaterialInfo(materialInfoBean);
						}
						if (result >= 0)
							successCount++;
					}
				} else {
					logger.error("获取素材失败:errMsg:" + getNewsListResponseBean.getErrMsg());
				}
				// 计算REMAIN
				remain = total - count;
				if (remain <= 0)
					break;// 没有剩余了就跳出循环
				else {
					// 计算offset和COUNT
					offSet = count++;
					if (remain <= MAX_RATERIAL_REQUEST_COUNT) {
						count = remain;
					} else {
						count = MAX_RATERIAL_REQUEST_COUNT;
					}
				}
			}
		} else {
			// 其他
			// 计算offset和COUNT
			int offSet = 0;
			int remain = 0;
			int count = 0;
			if (total <= MAX_RATERIAL_REQUEST_COUNT)
				count = total;
			else
				count = MAX_RATERIAL_REQUEST_COUNT;
			while (true) {
				getMaterialListRequestBean = WeixinRequestFactory.getRequestBean(accountCode,
						GetMaterialListRequestBean.class);
				getMaterialListRequestBean.setOffset(offSet);
				getMaterialListRequestBean.setCount(count);
				getMaterialListRequestBean.setMediaType(mediaType);
				getMaterialListResponseBean = (GetMaterialListResponseBean<MaterialInfoBean>) getMaterialList
						.execute(getMaterialListRequestBean);
				if (WeixinUtil.isSuccess(getMaterialListResponseBean)) {
					for (MaterialInfoBean bean : getMaterialListResponseBean.getItemList()) {
						materialInfoBean = new WeixinMaterialInfoBean();
						materialInfoBean
								.set_id(WeixinMongoConfig.getMaterilInfoKey(accountId, mediaType, bean.getMediaId()));
						materialInfoBean.setAccountId(accountId);
						materialInfoBean.setMediaId(bean.getMediaId());
						materialInfoBean.setMediaType(mediaType);
						materialInfoBean.setUpdateTime(bean.getUpdateTime());
						materialInfoBean.setUrl(bean.getMediaUrl());
						materialInfoBean.setName(bean.getMediaName());
						// 判断是要ADD还是UPDATE
						WeixinMaterialInfoBean exitBean = getWeixinMaterialInfoByKey(materialInfoBean.get_id());
						int result = -1;
						if (exitBean == null) {
							// ADD
							result = addWeixinMaterialInfo(materialInfoBean);
						} else {
							// UPDATE
							result = updateWeixinMaterialInfo(materialInfoBean);
						}
						if (result >= 0)
							successCount++;
					}
				} else {
					logger.error("获取素材失败:errMsg:" + getMaterialListResponseBean.getErrMsg());
				}
				// 计算REMAIN
				remain = total - count;
				if (remain <= 0)
					break;// 没有剩余了就跳出循环
				else {
					// 计算offset和COUNT
					offSet = count++;
					if (remain <= MAX_RATERIAL_REQUEST_COUNT) {
						count = remain;
					} else {
						count = MAX_RATERIAL_REQUEST_COUNT;
					}
				}
			}
		}
		return successCount;
	}
}
