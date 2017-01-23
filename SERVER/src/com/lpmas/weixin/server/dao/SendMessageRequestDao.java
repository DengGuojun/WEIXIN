package com.lpmas.weixin.server.dao;

import org.bson.Document;

import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.weixin.client.bean.request.SendMessageRequestBean;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.mongodb.client.MongoCollection;

public class SendMessageRequestDao {

	public int insertSendMessageRequestBean(SendMessageRequestBean bean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> sendCollection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_SEND_MESSAGE);
		return mongoDB.insert(sendCollection, bean);
	}

	public int updateSendMessageRequestBean(SendMessageRequestBean bean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> sendCollection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_SEND_MESSAGE);
		return (int) mongoDB.update(sendCollection, bean);
	}

}
