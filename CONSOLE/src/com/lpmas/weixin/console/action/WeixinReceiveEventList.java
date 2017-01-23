package com.lpmas.weixin.console.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.nosql.mongodb.MongoDBConfig;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.bean.ReceiveEventBean;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinReceiveEventBusiness;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;
import com.mongodb.BasicDBObject;

/**
 * Servlet implementation class WeixinReceiveEventList
 */
@WebServlet("/weixin/WeixinReceiveEventList.do")
public class WeixinReceiveEventList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinReceiveEventList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MESSAGE_EVENT_MANAGE, OperationConfig.SEARCH)) {
			return;
		}
		// 分页信息
		int pageNum = ParamKit.getIntParameter(request, "pageNum", WeixinConsoleConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", WeixinConsoleConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		// 权鉴
		WeixinAccountHelper helper = new WeixinAccountHelper(adminUserHelper);
		int accountId = ParamKit.getIntParameter(request, "accountId", helper.getDefaultAccount());
		ReturnMessageBean returnMessage = helper.hasPermission(accountId);
		if (returnMessage.getCode() == Constants.STATUS_NOT_VALID) {
			HttpResponseKit.alertMessage(response, returnMessage.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 查询条件参数
		HashMap<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("accountId", accountId);
		String event = ParamKit.getParameter(request, "event", "").trim();
		if (StringKit.isValid(event)) {
			condMap.put("event", event);
		}
		BasicDBObject query = new BasicDBObject();
		String begDateTime = ParamKit.getParameter(request, "begDateTime", "");
		if (StringKit.isValid(begDateTime)) {
			try {
				long begTime = DateKit.str2Timestamp(begDateTime, DateKit.DEFAULT_DATE_TIME_FORMAT).getTime();
				query.append(MongoDBConfig.QOP_GTE, begTime);
			} catch (Exception e) {
			}
		}
		String endDateTime = ParamKit.getParameter(request, "endDateTime", "");
		if (StringKit.isValid(endDateTime)) {
			try {
				long endTime = DateKit.str2Timestamp(endDateTime, DateKit.DEFAULT_DATE_TIME_FORMAT).getTime();
				query.append(MongoDBConfig.QOP_LTE, endTime);
			} catch (Exception e) {
			}
		}
		if (!query.isEmpty()) {
			condMap.put("createTime", query);
		}
		// 查询数据库
		WeixinReceiveEventBusiness eventBusiness = new WeixinReceiveEventBusiness();
		PageResultBean<ReceiveEventBean> result = eventBusiness.getWeixinReceiveEventPageList(condMap, pageBean);

		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("accountList", helper.getAccountList());
		request.setAttribute("ReceiveEventList", result.getRecordList());
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "WeixinReceiveEventList.jsp");
		rd.forward(request, response);
	}

}
