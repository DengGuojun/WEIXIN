package com.lpmas.weixin.server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.bean.AutoReplyMessageInfoBean;
import com.lpmas.weixin.server.factory.WeixinDBFactory;

public class AutoReplyMessageInfoDao {
	private static Logger log = LoggerFactory.getLogger(AutoReplyMessageInfoDao.class);

	public int insertAutoReplyMessageInfo(AutoReplyMessageInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into auto_reply_message_info ( reply_name, reply_type, account_id, message_match_rule, message_content, message_desc, priority, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getReplyName());
			ps.setInt(2, bean.getReplyType());
			ps.setInt(3, bean.getAccountId());
			ps.setString(4, bean.getMessageMatchRule());
			ps.setString(5, bean.getMessageContent());
			ps.setString(6, bean.getMessageDesc());
			ps.setInt(7, bean.getPriority());
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

	public int updateAutoReplyMessageInfo(AutoReplyMessageInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update auto_reply_message_info set reply_name = ?, reply_type = ?, account_id = ?, message_match_rule = ?, message_content = ?, message_desc = ?, priority = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where reply_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getReplyName());
			ps.setInt(2, bean.getReplyType());
			ps.setInt(3, bean.getAccountId());
			ps.setString(4, bean.getMessageMatchRule());
			ps.setString(5, bean.getMessageContent());
			ps.setString(6, bean.getMessageDesc());
			ps.setInt(7, bean.getPriority());
			ps.setInt(8, bean.getStatus());
			ps.setInt(9, bean.getModifyUser());
			ps.setString(10, bean.getMemo());

			ps.setInt(11, bean.getReplyId());

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

	public AutoReplyMessageInfoBean getAutoReplyMessageInfoByKey(int replyId) {
		AutoReplyMessageInfoBean bean = null;
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from auto_reply_message_info where reply_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, replyId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new AutoReplyMessageInfoBean();
				bean = BeanKit.resultSet2Bean(rs, AutoReplyMessageInfoBean.class);
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

	public List<AutoReplyMessageInfoBean> getAutoReplyMessageInfoListByMap(Map<String, String> condMap) {
		List<AutoReplyMessageInfoBean> result = new ArrayList<AutoReplyMessageInfoBean>();
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from auto_reply_message_info";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String accountId = condMap.get("accountId");
			if (StringKit.isValid(accountId)) {
				condList.add("account_id = ?");
				paramList.add(accountId);
			}
			String replyType = condMap.get("replyType");
			if (StringKit.isValid(replyType)) {
				condList.add("reply_type = ?");
				paramList.add(replyType);
			}

			String orderQuery = "order by priority";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList,
					AutoReplyMessageInfoBean.class, db);
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

	public PageResultBean<AutoReplyMessageInfoBean> getAutoReplyMessageInfoPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<AutoReplyMessageInfoBean> result = new PageResultBean<AutoReplyMessageInfoBean>();
		DBFactory dbFactory = new WeixinDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from auto_reply_message_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String accountId = condMap.get("accountId");
			if (StringKit.isValid(accountId)) {
				condList.add("account_id = ?");
				paramList.add(accountId);
			}

			String orderQuery = "order by reply_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, AutoReplyMessageInfoBean.class,
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
