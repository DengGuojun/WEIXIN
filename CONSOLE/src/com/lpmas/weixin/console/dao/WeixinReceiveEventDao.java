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
import com.lpmas.weixin.bean.ReceiveEventBean;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.mongodb.client.MongoCollection;

public class WeixinReceiveEventDao {
	private static Logger logger = LoggerFactory.getLogger(WeixinReceiveEventDao.class);

	public PageResultBean<ReceiveEventBean> getWeixinReceiveEventPageList(Map<String, Object> condMap,
			PageBean pageBean, Map<String, Object> orderBy) {
		PageResultBean<ReceiveEventBean> resultBean = new PageResultBean<ReceiveEventBean>();
		MongoDB mongoDB = MongoDB.getInstance();
		try {
			resultBean = mongoDB.getPageListResult(WeixinMongoConfig.DB_NAME,
					WeixinMongoConfig.COLLECTION_RECEIVE_EVENT, condMap, ReceiveEventBean.class, pageBean, orderBy);
		} catch (Exception e) {
			logger.error("", e);
			resultBean.setRecordList(new ArrayList<ReceiveEventBean>(1));
		}
		return resultBean;
	}

	public ReceiveEventBean getWeixinReceiveEventBeanByKey(String _id) {
		ObjectId objectId = new ObjectId(_id);
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_RECEIVE_EVENT);
		try {
			return mongoDB.getRecordById(collection, objectId, ReceiveEventBean.class);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

}
