package com.lpmas.weixin.console.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.weixin.bean.WeixinAccountInfoBean;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;

@WebServlet("/weixin/WeixinAccountInfoList.do")
public class WeixinAccountInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinAccountInfoList() {
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
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_ACCOUNT_INFO, OperationConfig.SEARCH)) {
			return;
		}
		// 分页信息
		int pageNum = ParamKit.getIntParameter(request, "pageNum", WeixinConsoleConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", WeixinConsoleConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		HashMap<String, String> condMap = new HashMap<String, String>();
		int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
		int accountType = ParamKit.getIntParameter(request, "accountType", 0);
		if (accountType > 0) {
			condMap.put("accountType", String.valueOf(accountType));
		}
		String accountName = ParamKit.getParameter(request, "accountName", "").trim();
		if (StringKit.isValid(accountName)) {
			condMap.put("accountName", accountName);
		}

		WeixinAccountHelper helper = new WeixinAccountHelper(adminUserHelper);
		List<WeixinAccountInfoBean> accountList = helper.getAccountList();

		// 计算并获取SUBLIST
		int remain = accountList.size() - (pageSize * (pageNum - 1));
		if ((accountList.size() - (pageSize * (pageNum - 1))) > pageSize) {
			remain = pageSize;
		}
		List<WeixinAccountInfoBean> accountPageList = ListKit.subList(accountList, pageSize * (pageNum - 1), remain);
		Iterator<WeixinAccountInfoBean> iterator = accountPageList.iterator();
		while (iterator.hasNext()) {
			WeixinAccountInfoBean bean = iterator.next();
			if (status != bean.getStatus()){
				iterator.remove();
				continue;
			}
			if (condMap.containsKey("accountType") && bean.getAccountType() != accountType) {
				iterator.remove();
				continue;
			}
			if (condMap.containsKey("accountName") && !bean.getAccountName().contains(accountName)) {
				iterator.remove();
				continue;
			}
		}

		pageBean.init(pageNum, pageSize, accountList.size());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("WeixinAccountInfoList", accountPageList);
		RequestDispatcher rd = request.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "WeixinAccountInfoList.jsp");
		rd.forward(request, response);
	}

}
