package com.lpmas.weixin.server.dao;

import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.weixin.bean.WeixinUserInfoBean;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.mongodb.client.MongoCollection;

public class WeixinUserInfoDao {
	private static Logger logger = LoggerFactory.getLogger(WeixinUserInfoDao.class);

	public WeixinUserInfoBean getWeixinUserInfoByKey(String _id) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_USER_INFO);
		try {
			return mongoDB.getRecordById(collection, _id, WeixinUserInfoBean.class);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	public int insertWeixinUserInfo(WeixinUserInfoBean bean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_USER_INFO);
		return mongoDB.insert(collection, bean);
	}

	public int updateWeixinUserInfo(WeixinUserInfoBean userInfoBean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_USER_INFO);
		Map<String, Object> map = BeanKit.bean2Map(userInfoBean);
		map.remove("_id");
		return (int) mongoDB.update(collection, userInfoBean.get_id(), map);

	}

	public int updateWeixinUserInfoByMap(String id, Map<String, Object> map) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_USER_INFO);
		return (int) mongoDB.update(collection, id, map);
	}

}
