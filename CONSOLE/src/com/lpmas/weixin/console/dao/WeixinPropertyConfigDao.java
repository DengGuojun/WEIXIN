package com.lpmas.weixin.console.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.bean.WeixinPropertyConfigBean;
import com.lpmas.weixin.console.factory.WeixinDBFactory;

public class WeixinPropertyConfigDao {
	private static Logger log = LoggerFactory.getLogger(WeixinPropertyConfigDao.class);

	public int insertWeixinPropertyConfig(WeixinPropertyConfigBean bean) {
		int result = -1;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into weixin_property_config ( property_type, account_id, property_key_1, property_key_2, property_key_3, property_key_4, property_key_5, property_value_1, property_value_2, property_value_3, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getPropertyType());
			ps.setInt(2, bean.getAccountId());
			ps.setString(3, bean.getPropertyKey1());
			ps.setString(4, bean.getPropertyKey2());
			ps.setString(5, bean.getPropertyKey3());
			ps.setString(6, bean.getPropertyKey4());
			ps.setString(7, bean.getPropertyKey5());
			ps.setString(8, bean.getPropertyValue1());
			ps.setString(9, bean.getPropertyValue2());
			ps.setString(10, bean.getPropertyValue3());
			ps.setInt(11, bean.getStatus());
			ps.setInt(12, bean.getCreateUser());
			ps.setString(13, bean.getMemo());

			result = db.executePstmtInsert();
		} catch (Exception e) {
			log.error("", e);
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public int updateWeixinPropertyConfig(WeixinPropertyConfigBean bean) {
		int result = -1;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update weixin_property_config set property_type = ?, account_id = ?, property_key_1 = ?, property_key_2 = ?, property_key_3 = ?, property_key_4 = ?, property_key_5 = ?, property_value_1 = ?, property_value_2 = ?, property_value_3 = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where property_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getPropertyType());
			ps.setInt(2, bean.getAccountId());
			ps.setString(3, bean.getPropertyKey1());
			ps.setString(4, bean.getPropertyKey2());
			ps.setString(5, bean.getPropertyKey3());
			ps.setString(6, bean.getPropertyKey4());
			ps.setString(7, bean.getPropertyKey5());
			ps.setString(8, bean.getPropertyValue1());
			ps.setString(9, bean.getPropertyValue2());
			ps.setString(10, bean.getPropertyValue3());
			ps.setInt(11, bean.getStatus());
			ps.setInt(12, bean.getModifyUser());
			ps.setString(13, bean.getMemo());

			ps.setInt(14, bean.getPropertyId());

			result = db.executePstmtUpdate();
		} catch (Exception e) {
			log.error("", e);
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public WeixinPropertyConfigBean getWeixinPropertyConfigByKey(int propertyId) {
		WeixinPropertyConfigBean bean = null;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_property_config where property_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, propertyId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new WeixinPropertyConfigBean();
				bean = BeanKit.resultSet2Bean(rs, WeixinPropertyConfigBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			bean = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return bean;
	}

	public PageResultBean<WeixinPropertyConfigBean> getWeixinPropertyConfigPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<WeixinPropertyConfigBean> result = new PageResultBean<WeixinPropertyConfigBean>();
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_property_config";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String propertyType = condMap.get("propertyType");
			if (StringKit.isValid(propertyType)) {
				condList.add("property_type = ?");
				paramList.add(propertyType);
			}
			String accountId = condMap.get("accountId");
			if (StringKit.isValid(accountId)) {
				condList.add("account_id = ?");
				paramList.add(accountId);
			}
			String propertyKey1 = condMap.get("propertyKey1");
			if (StringKit.isValid(propertyKey1)) {
				condList.add("property_key_1 like ?");
				paramList.add("%"+propertyKey1+"%");
			}
			String orderQuery = "order by property_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, WeixinPropertyConfigBean.class,
					pageBean, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

}
