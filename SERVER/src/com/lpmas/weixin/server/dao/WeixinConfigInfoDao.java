package com.lpmas.weixin.server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.weixin.bean.WeixinConfigInfoBean;
import com.lpmas.weixin.server.factory.WeixinDBFactory;

public class WeixinConfigInfoDao {
	private static Logger log = LoggerFactory.getLogger(WeixinConfigInfoDao.class);

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

}
