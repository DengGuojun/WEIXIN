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
import com.lpmas.weixin.bean.WeixinConfigInfoBean;
import com.lpmas.weixin.console.factory.WeixinDBFactory;

public class WeixinConfigInfoDao {
	private static Logger log = LoggerFactory.getLogger(WeixinConfigInfoDao.class);

	public int insertWeixinConfigInfo(WeixinConfigInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into weixin_config_info ( config_code, config_name, config_value, memo) value( ?, ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getConfigCode());
			ps.setString(2, bean.getConfigName());
			ps.setString(3, bean.getConfigValue());
			ps.setString(4, bean.getMemo());

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

	public int updateWeixinConfigInfo(WeixinConfigInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update weixin_config_info set config_code = ?, config_name = ?, config_value = ?, memo = ? where config_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getConfigCode());
			ps.setString(2, bean.getConfigName());
			ps.setString(3, bean.getConfigValue());
			ps.setString(4, bean.getMemo());

			ps.setInt(5, bean.getConfigId());

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

	public WeixinConfigInfoBean getWeixinConfigInfoByKey(int configId) {
		WeixinConfigInfoBean bean = null;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_config_info where config_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, configId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new WeixinConfigInfoBean();
				bean = BeanKit.resultSet2Bean(rs, WeixinConfigInfoBean.class);
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
	
	public WeixinConfigInfoBean getWeixinConfigInfoByCode(String configCode) {
		WeixinConfigInfoBean bean = null;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_config_info where config_code = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, configCode);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new WeixinConfigInfoBean();
				bean = BeanKit.resultSet2Bean(rs, WeixinConfigInfoBean.class);
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

	public PageResultBean<WeixinConfigInfoBean> getWeixinConfigInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<WeixinConfigInfoBean> result = new PageResultBean<WeixinConfigInfoBean>();
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_config_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by config_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, WeixinConfigInfoBean.class,
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
