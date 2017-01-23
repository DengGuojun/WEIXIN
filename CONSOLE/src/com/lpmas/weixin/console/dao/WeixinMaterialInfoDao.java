package com.lpmas.weixin.console.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.weixin.bean.WeixinMaterialInfoBean;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.mongodb.client.MongoCollection;

public class WeixinMaterialInfoDao {

	private static Logger logger = LoggerFactory.getLogger(WeixinMaterialInfoDao.class);

	public int insertWeixinMaterialInfo(WeixinMaterialInfoBean bean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> materialCollection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_MATERIAL_INFO);
		return mongoDB.insert(materialCollection, bean);
	}

	public int updateWeixinMaterialInfo(WeixinMaterialInfoBean bean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> materialCollection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_MATERIAL_INFO);
		try {
			return (int) mongoDB.update(materialCollection, bean);
		} catch (Exception e) {
			logger.error("", e);
			return -1;
		}
	}

	public int deleteWeixinMaterialInfo(WeixinMaterialInfoBean bean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> materialCollection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_MATERIAL_INFO);
		return (int) mongoDB.delete(materialCollection, bean.get_id());
	}

	public WeixinMaterialInfoBean getWeixinMaterilInfoByKey(String _id) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> materialCollection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_MATERIAL_INFO);
		WeixinMaterialInfoBean result = null;
		try {
			result = mongoDB.getRecordById(materialCollection, _id, WeixinMaterialInfoBean.class);
		} catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}

	public PageResultBean<WeixinMaterialInfoBean> getWeixinMaterialPageListByMap(Map<String, Object> condMap,
			PageBean pageBean, Map<String, Object> orderBy) {
		MongoDB mongoDB = MongoDB.getInstance();
		PageResultBean<WeixinMaterialInfoBean> resultBean = new PageResultBean<WeixinMaterialInfoBean>();
		try {
			resultBean = mongoDB.getPageListResult(WeixinMongoConfig.DB_NAME,
					WeixinMongoConfig.COLLECTION_MATERIAL_INFO, condMap, WeixinMaterialInfoBean.class, pageBean,
					orderBy);
		} catch (Exception e) {
			logger.error("", e);
			resultBean.setRecordList(new ArrayList<WeixinMaterialInfoBean>(1));
		}
		return resultBean;
	}

	public List<WeixinMaterialInfoBean> getWeixinMaterialInfoListByMap(Map<String, Object> condMap,
			Map<String, Object> orderBy) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> materialCollection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_MATERIAL_INFO);
		try {
			return mongoDB.getRecordListResult(materialCollection, condMap, WeixinMaterialInfoBean.class, null,
					orderBy);
		} catch (Exception e) {
			logger.error("", e);
			return new ArrayList<WeixinMaterialInfoBean>(1);
		}
	}

}
