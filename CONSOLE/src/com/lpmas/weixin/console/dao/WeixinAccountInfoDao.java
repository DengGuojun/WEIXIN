package com.lpmas.weixin.console.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.admin.bean.AdminGroupInfoBean;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.bean.WeixinAccountInfoBean;
import com.lpmas.weixin.console.factory.WeixinDBFactory;

public class WeixinAccountInfoDao {
	private static Logger log = LoggerFactory.getLogger(WeixinAccountInfoDao.class);

	public int insertWeixinAccountInfo(WeixinAccountInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into weixin_account_info ( account_name, account_code, weixin_user_id, weixin_account_id, account_type, app_id, store_id, group_id, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getAccountName());
			ps.setString(2, bean.getAccountCode());
			ps.setString(3, bean.getWeixinUserId());
			ps.setString(4, bean.getWeixinAccountId());
			ps.setInt(5, bean.getAccountType());
			ps.setInt(6, bean.getAppId());
			ps.setInt(7, bean.getStoreId());
			ps.setInt(8, bean.getGroupId());
			ps.setInt(9, bean.getStatus());
			ps.setInt(10, bean.getCreateUser());
			ps.setString(11, bean.getMemo());

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

	public int updateWeixinAccountInfo(WeixinAccountInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update weixin_account_info set account_name = ?, account_code = ?, weixin_user_id = ?, weixin_account_id = ?, account_type = ?, app_id = ?, store_id = ?, group_id = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where account_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getAccountName());
			ps.setString(2, bean.getAccountCode());
			ps.setString(3, bean.getWeixinUserId());
			ps.setString(4, bean.getWeixinAccountId());
			ps.setInt(5, bean.getAccountType());
			ps.setInt(6, bean.getAppId());
			ps.setInt(7, bean.getStoreId());
			ps.setInt(8, bean.getGroupId());
			ps.setInt(9, bean.getStatus());
			ps.setInt(10, bean.getModifyUser());
			ps.setString(11, bean.getMemo());

			ps.setInt(12, bean.getAccountId());

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

	public WeixinAccountInfoBean getWeixinAccountInfoByKey(int accountId) {
		WeixinAccountInfoBean bean = null;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_account_info where account_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, accountId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new WeixinAccountInfoBean();
				bean = BeanKit.resultSet2Bean(rs, WeixinAccountInfoBean.class);
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

	public PageResultBean<WeixinAccountInfoBean> getWeixinAccountInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<WeixinAccountInfoBean> result = new PageResultBean<WeixinAccountInfoBean>();
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_account_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String appId = condMap.get("appId");
			if (StringKit.isValid(appId)) {
				condList.add("app_id = ?");
				paramList.add(appId);
			}
			String storeId = condMap.get("storeId");
			if (StringKit.isValid(storeId)) {
				condList.add("store_id = ?");
				paramList.add(storeId);
			}
			String orderQuery = "order by account_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, WeixinAccountInfoBean.class,
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

	public WeixinAccountInfoBean getWeixinAccountInfoByKey(int appId, int storeId) {
		WeixinAccountInfoBean bean = null;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_account_info where app_id = ? and store_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, appId);
			ps.setInt(2, storeId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new WeixinAccountInfoBean();
				bean = BeanKit.resultSet2Bean(rs, WeixinAccountInfoBean.class);
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

	public WeixinAccountInfoBean getWeixinAccountInfoByCode(String accountCode) {
		WeixinAccountInfoBean bean = null;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_account_info where account_code = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, accountCode);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new WeixinAccountInfoBean();
				bean = BeanKit.resultSet2Bean(rs, WeixinAccountInfoBean.class);
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

	public List<WeixinAccountInfoBean> getWeixinAccountInfoListByMap(HashMap<String, String> condMap) {
		List<WeixinAccountInfoBean> list = new ArrayList<WeixinAccountInfoBean>();
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_account_info";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String accountCode = condMap.get("accountCode");
			if (StringKit.isValid(accountCode)) {
				condList.add("account_code = ?");
				paramList.add(accountCode);
			}
			String appId = condMap.get("appId");
			if (StringKit.isValid(appId)) {
				condList.add("app_id = ?");
				paramList.add(appId);
			}
			String storeId = condMap.get("storeId");
			if (StringKit.isValid(storeId)) {
				condList.add("store_id = ?");
				paramList.add(storeId);
			}
			String orderQuery = "order by account_id desc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			list = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, WeixinAccountInfoBean.class,
					db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return list;
	}

	public List<WeixinAccountInfoBean> getWeixinAccountInfoListByGroupList(List<AdminGroupInfoBean> groupList) {
		List<WeixinAccountInfoBean> list = new ArrayList<WeixinAccountInfoBean>();
		WeixinAccountInfoBean bean = null;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;

		if (groupList.isEmpty())
			return list;

		String inSql = "";
		for (int i = 0; i < groupList.size(); i++) {
			inSql += "?,";
		}
		inSql = inSql.substring(0, inSql.length() - 1);

		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_account_info where group_id in (" + inSql + ")";
			PreparedStatement ps = db.getPreparedStatement(sql);
			for (int i = 0; i < groupList.size(); i++) {
				ps.setInt(i + 1, groupList.get(i).getGroupId());
			}

			ResultSet rs = db.executePstmtQuery();
			while (rs.next()) {
				bean = new WeixinAccountInfoBean();
				bean = BeanKit.resultSet2Bean(rs, WeixinAccountInfoBean.class);
				list.add(bean);
			}
			rs.close();

		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return list;
	}
}
