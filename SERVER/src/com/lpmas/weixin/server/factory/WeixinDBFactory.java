package com.lpmas.weixin.server.factory;

import java.sql.SQLException;

import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.db.MysqlDBExecutor;
import com.lpmas.framework.db.MysqlDBObject;
import com.lpmas.weixin.server.config.WeixinDBConfig;

public class WeixinDBFactory extends DBFactory {

	public DBObject getDBObjectR() throws SQLException {
		return new MysqlDBObject(WeixinDBConfig.DB_LINK_WEIXIN_R);
	}

	public DBObject getDBObjectW() throws SQLException {
		return new MysqlDBObject(WeixinDBConfig.DB_LINK_WEIXIN_W);
	}

	@Override
	public DBExecutor getDBExecutor() {
		return new MysqlDBExecutor();
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}
}
