package com.lpmas.weixin.server.dao;

import org.bson.Document;

import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.weixin.bean.ReceiveMessageBean;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.mongodb.client.MongoCollection;

public class WeixinReceiveMessageDao {

	public int insertReceiveMessageBean(ReceiveMessageBean bean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_RECEIVE_MESSAGE);
		return mongoDB.insert(collection, bean);
	}

}
