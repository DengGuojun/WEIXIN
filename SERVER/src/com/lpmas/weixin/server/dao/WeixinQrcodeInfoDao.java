package com.lpmas.weixin.server.dao;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.weixin.bean.QrcodeInfoBean;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.mongodb.client.MongoCollection;

public class WeixinQrcodeInfoDao {
	private static Logger logger = LoggerFactory.getLogger(WeixinQrcodeInfoDao.class);

	public QrcodeInfoBean getQrcodeInfoByMap(Map<String, Object> condMap) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_QRCODE_INFO);
		QrcodeInfoBean result = null;
		try {
			List<QrcodeInfoBean> list = mongoDB.getRecordListResult(collection, condMap, QrcodeInfoBean.class, null);
			if (list.size() > 0) {
				// 取出第一个
				result = list.get(0);
			}
		} catch (Exception e) {
			logger.error("", e);
			result = null;
		}
		return result;
	}

	public QrcodeInfoBean getQrcodeInfoByKey(String _id) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_QRCODE_INFO);
		try {
			return mongoDB.getRecordById(collection, _id, QrcodeInfoBean.class);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	public int insertQrcodeInfo(QrcodeInfoBean bean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_QRCODE_INFO);
		return mongoDB.insert(collection, bean);
	}

	public int updateQrcodeInfoByMap(String _id, Map<String, Object> condMap) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_QRCODE_INFO);
		return (int) mongoDB.update(collection, _id, condMap);
	}

	public int updateQrcodeInfo(QrcodeInfoBean qrcodeInfoBean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_QRCODE_INFO);
		return (int) mongoDB.update(collection, qrcodeInfoBean);
	}

	public long getTotalRecordCountByMap(Map<String, Object> condMap) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_QRCODE_INFO);
		return mongoDB.getTotalRecordResult(collection, condMap);
	}

}
