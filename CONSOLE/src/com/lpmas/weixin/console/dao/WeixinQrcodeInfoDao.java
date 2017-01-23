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
import com.lpmas.weixin.bean.QrcodeInfoBean;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.mongodb.client.MongoCollection;

public class WeixinQrcodeInfoDao {
	private static Logger logger = LoggerFactory.getLogger(WeixinQrcodeInfoDao.class);

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

	public QrcodeInfoBean getQrcodeInfoByMap(Map<String, Object> condMap) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_QRCODE_INFO);
		try {
			List<QrcodeInfoBean> list = mongoDB.getRecordListResult(collection, condMap, QrcodeInfoBean.class, null);
			if (!list.isEmpty()) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	public PageResultBean<QrcodeInfoBean> getQrcodeInfoPageListByMap(Map<String, Object> condMap, PageBean pageBean,
			Map<String, Object> orderBy) {
		MongoDB mongoDB = MongoDB.getInstance();
		PageResultBean<QrcodeInfoBean> resultBean = new PageResultBean<QrcodeInfoBean>();
		try {
			resultBean = mongoDB.getPageListResult(WeixinMongoConfig.DB_NAME, WeixinMongoConfig.COLLECTION_QRCODE_INFO,
					condMap, QrcodeInfoBean.class, pageBean, orderBy);
		} catch (Exception e) {
			logger.error("", e);
			resultBean.setRecordList(new ArrayList<QrcodeInfoBean>(1));
		}
		return resultBean;
	}

	public int insertQrcodeInfo(QrcodeInfoBean bean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_QRCODE_INFO);
		return mongoDB.insert(collection, bean);
	}

	public int updateQrcodeInfo(QrcodeInfoBean bean) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_QRCODE_INFO);
		return (int) mongoDB.update(collection, bean);
	}

	public int updateQrcodeInfoByMap(String _id, Map<String, Object> condMap) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_QRCODE_INFO);
		return (int) mongoDB.update(collection, _id, condMap);
	}

	public long getTotalRecordCountByMap(Map<String, Object> condMap) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_QRCODE_INFO);
		return mongoDB.getTotalRecordResult(collection, condMap);
	}

}
