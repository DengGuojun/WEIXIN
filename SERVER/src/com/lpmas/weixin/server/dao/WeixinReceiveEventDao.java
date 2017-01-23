package com.lpmas.weixin.server.dao;

import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.weixin.bean.ReceiveEventBean;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.mongodb.client.MongoCollection;

public class WeixinReceiveEventDao {

	private static Logger logger = LoggerFactory.getLogger(WeixinReceiveEventDao.class);

	public int insertReceiveEventBean(ReceiveEventBean bean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_RECEIVE_EVENT);
		return mongoDB.insert(collection, bean);
	}

	public ReceiveEventBean getReceiveEventByKey(ObjectId _id) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_RECEIVE_EVENT);
		try {
			return mongoDB.getRecordById(collection, _id, ReceiveEventBean.class);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	public int updateReceiveEvent(ObjectId _id, Map<String, Object> map) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_RECEIVE_EVENT);
		return (int) mongoDB.update(collection, _id, map);
	}

}
