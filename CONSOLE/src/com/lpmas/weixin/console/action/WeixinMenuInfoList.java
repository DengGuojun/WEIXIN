package com.lpmas.weixin.console.action;

import java.io.IOException;
import java.util.HashMap;
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
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.bean.WeixinMenuInfoBean;
import com.lpmas.weixin.config.WeixinMenuConfig;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinMenuInfoBusiness;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;

@WebServlet("/weixin/WeixinMenuInfoList.do")
public class WeixinMenuInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinMenuInfoList() {
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
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MENU_INFO, OperationConfig.SEARCH)) {
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
		HashMap<String, String> condMap = new HashMap<String, String>();

		condMap.put("accountId", String.valueOf(accountId));
		String status = ParamKit.getParameter(request, "status", String.valueOf(Constants.STATUS_VALID)).trim();
		if (StringKit.isValid(status)) {
			condMap.put("status", status);
		}
		condMap.put("menuType", String.valueOf(WeixinMenuConfig.MENU_TYPE_DEFAULT));
		String menuName = ParamKit.getParameter(request, "menuName", "").trim();
		if (StringKit.isValid(menuName)) {
			condMap.put("menuName", menuName);
		}

		WeixinMenuInfoBusiness business = new WeixinMenuInfoBusiness();
		// 检查是够已经存在默认菜单
		boolean hasDefaultMenu = false;
		HashMap<String, String> defaultCond = new HashMap<String, String>();
		defaultCond.put("accountId", StringKit.validStr(accountId));
		defaultCond.put("menuType", String.valueOf(WeixinMenuConfig.MENU_TYPE_DEFAULT));
		defaultCond.put("status", String.valueOf(Constants.STATUS_VALID));
		List<WeixinMenuInfoBean> defaultMenuList = business.getWeixinMenuInfoListByMap(defaultCond);
		if (!defaultMenuList.isEmpty())
			hasDefaultMenu = true;

		PageResultBean<WeixinMenuInfoBean> result = business.getWeixinMenuInfoPageListByMap(condMap, pageBean);
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("accountList", helper.getAccountList());
		request.setAttribute("WeixinMenuInfoList", result.getRecordList());
		request.setAttribute("AdminUserHelper", adminUserHelper);
		request.setAttribute("hasDefaultMenu", hasDefaultMenu);
		RequestDispatcher rd = request.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "WeixinMenuInfoList.jsp");
		rd.forward(request, response);

	}

}
