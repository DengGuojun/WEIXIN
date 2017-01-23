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
import com.lpmas.weixin.bean.WeixinUserInfoBean;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.mongodb.client.MongoCollection;

public class WeixinUserInfoDao {
	private static Logger logger = LoggerFactory.getLogger(WeixinUserInfoDao.class);

	public PageResultBean<WeixinUserInfoBean> getWeixinUserInfoPageList(PageBean pageBean, Map<String, Object> condMap,
			Map<String, Object> orderBy) {
		PageResultBean<WeixinUserInfoBean> resultBean = new PageResultBean<WeixinUserInfoBean>();
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_USER_INFO);
		try {
			List<WeixinUserInfoBean> list = mongoDB.getRecordListResult(collection, condMap, WeixinUserInfoBean.class,
					pageBean, orderBy);
			int total = mongoDB.getTotalRecordResult(collection, condMap);
			resultBean.setTotalRecordNumber(total);
			resultBean.setRecordList(list);
		} catch (Exception e) {
			logger.error("", e);
			resultBean.setRecordList(new ArrayList<WeixinUserInfoBean>(1));
		}
		return resultBean;
	}

	public List<WeixinUserInfoBean> getWeixinUserInfoListByMap(Map<String, Object> condMap,
			Map<String, Object> orderBy) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_USER_INFO);
		try {
			return mongoDB.getRecordListResult(collection, condMap, WeixinUserInfoBean.class, null, orderBy);
		} catch (Exception e) {
			logger.error("", e);
			return new ArrayList<WeixinUserInfoBean>(1);
		}
	}

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
		return (int) mongoDB.update(collection, userInfoBean);
	}

	public int updateWeixinUserInfoByMap(String id, Map<String, Object> map) {
		MongoDB mongoDB = MongoDB.getInstance();
		MongoCollection<Document> collection = mongoDB.getCollection(WeixinMongoConfig.DB_NAME,
				WeixinMongoConfig.COLLECTION_USER_INFO);
		return (int) mongoDB.update(collection, id, map);
	}

}
