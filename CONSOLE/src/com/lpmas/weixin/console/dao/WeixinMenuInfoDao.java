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
import com.lpmas.weixin.bean.WeixinMenuInfoBean;
import com.lpmas.weixin.console.factory.WeixinDBFactory;

public class WeixinMenuInfoDao {
	private static Logger log = LoggerFactory.getLogger(WeixinMenuInfoDao.class);

	public int insertWeixinMenuInfo(WeixinMenuInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into weixin_menu_info ( menu_name, menu_type, account_id, menu_condition, menu_content, menu_desc, use_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getMenuName());
			ps.setInt(2, bean.getMenuType());
			ps.setInt(3, bean.getAccountId());
			ps.setString(4, bean.getMenuCondition());
			ps.setString(5, bean.getMenuContent());
			ps.setString(6, bean.getMenuDesc());
			ps.setInt(7, bean.getUseStatus());
			ps.setInt(8, bean.getStatus());
			ps.setInt(9, bean.getCreateUser());
			ps.setString(10, bean.getMemo());

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

	public int updateWeixinMenuInfo(WeixinMenuInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update weixin_menu_info set menu_name = ?, menu_type = ?, account_id = ?, menu_condition = ?, menu_content = ?, menu_desc = ?, use_status = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where menu_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getMenuName());
			ps.setInt(2, bean.getMenuType());
			ps.setInt(3, bean.getAccountId());
			ps.setString(4, bean.getMenuCondition());
			ps.setString(5, bean.getMenuContent());
			ps.setString(6, bean.getMenuDesc());
			ps.setInt(7, bean.getUseStatus());
			ps.setInt(8, bean.getStatus());
			ps.setInt(9, bean.getModifyUser());
			ps.setString(10, bean.getMemo());

			ps.setInt(11, bean.getMenuId());

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

	public WeixinMenuInfoBean getWeixinMenuInfoByKey(int menuId) {
		WeixinMenuInfoBean bean = null;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_menu_info where menu_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, menuId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new WeixinMenuInfoBean();
				bean = BeanKit.resultSet2Bean(rs, WeixinMenuInfoBean.class);
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

	public PageResultBean<WeixinMenuInfoBean> getWeixinMenuInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<WeixinMenuInfoBean> result = new PageResultBean<WeixinMenuInfoBean>();
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_menu_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String menuType = condMap.get("menuType");
			if (StringKit.isValid(menuType)) {
				condList.add("menu_type = ?");
				paramList.add(menuType);
			}
			String accountId = condMap.get("accountId");
			if (StringKit.isValid(accountId)) {
				condList.add("account_id = ?");
				paramList.add(accountId);
			}
			String menuName = condMap.get("menuName");
			if (StringKit.isValid(menuName)) {
				condList.add("menu_name like ?");
				paramList.add("%" + menuName + "%");
			}
			String orderQuery = "order by menu_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, WeixinMenuInfoBean.class, pageBean,
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
		return result;
	}

	public List<WeixinMenuInfoBean> getWeixinMenuInfoListByMap(HashMap<String, String> condMap) {
		List<WeixinMenuInfoBean> list = new ArrayList<WeixinMenuInfoBean>();
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from weixin_menu_info";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String menuType = condMap.get("menuType");
			if (StringKit.isValid(menuType)) {
				condList.add("menu_type = ?");
				paramList.add(menuType);
			}
			String accountId = condMap.get("accountId");
			if (StringKit.isValid(accountId)) {
				condList.add("account_id = ?");
				paramList.add(accountId);
			}
			String orderQuery = "order by menu_id desc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			list = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, WeixinMenuInfoBean.class, db);
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
