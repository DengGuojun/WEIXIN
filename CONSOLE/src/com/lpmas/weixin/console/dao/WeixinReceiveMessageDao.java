package com.lpmas.weixin.console.dao;

import java.util.ArrayList;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.weixin.bean.ReceiveMessageBean;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.mongodb.client.MongoCollection;

public class WeixinReceiveMessageDao {
	private static Logger logger = LoggerFactory.getLogger(WeixinReceiveMessageDao.class);

	public PageResultBean<ReceiveMessageBean> getWeixinReceiveMessagePageList(Map<String, Object> condMap,
			PageBean pageBean, Map<String, Object> orderBy) {
		MongoDB mongoDB = MongoDB.getInstance();
		PageResultBean<ReceiveMessageBean> resultBean = new PageResultBean<ReceiveMessageBean>();
		try {
			resultBean = mongoDB.getPageListResult(WeixinMongoConfig.DB_NAME,
					WeixinMongoConfig.COLLECTION_RECEIVE_MESSAGE, condMap, ReceiveMessageBean.class, pageBean, orderBy);
		} catch (Exception e) {
			logger.error("", e);
			resultBean.setRecordList(new ArrayList<ReceiveMessageBean>(1));
		}
		return resultBean;
	}

	public ReceiveMessageBean getWeixinReceiveMessageBeanByKey(String _id) {
		ObjectId objectId = new ObjectId(_id);
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_RECEIVE_MESSAGE);
		try {
			return mongoDB.getRecordById(collection, objectId, ReceiveMessageBean.class);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

}
