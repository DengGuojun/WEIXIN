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
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.api.config.MaterialTypeConfig;
import com.lpmas.weixin.bean.WeixinMaterialInfoBean;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinMaterialInfoBusiness;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;

/**
 * Servlet implementation class WeixinMaterialInfoList
 */
@WebServlet("/weixin/WeixinMaterialInfoList.do")
public class WeixinMaterialInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinMaterialInfoList() {
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
		// 分页信息
		int pageNum = ParamKit.getIntParameter(request, "pageNum", WeixinConsoleConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", WeixinConsoleConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MATERIAL_INFO, OperationConfig.SEARCH)) {
			return;
		}

		// 权鉴
		WeixinAccountHelper helper = new WeixinAccountHelper(adminUserHelper);
		int accountId = ParamKit.getIntParameter(request, "accountId", helper.getDefaultAccount());
		ReturnMessageBean returnMessage = helper.hasPermission(accountId);
		if (returnMessage.getCode() == Constants.STATUS_NOT_VALID) {
			HttpResponseKit.alertMessage(response, returnMessage.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		String mediaType = ParamKit.getParameter(request, "mediaType", "").trim();
		if (!MaterialTypeConfig.MATERIAL_TYPE_MAP.containsKey(mediaType)) {
			HttpResponseKit.alertMessage(response, "素材类型错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		String path = "Weixin" + StringKit.upperFirstChar(mediaType) + "MaterialInfoList.jsp";

		// 查询条件参数
		HashMap<String, Object> condMap = new HashMap<String, Object>();

		condMap.put("accountId", accountId);
		condMap.put("mediaType", mediaType);
		WeixinMaterialInfoBusiness weixinMaterialInfoBusiness = new WeixinMaterialInfoBusiness();
		PageResultBean<WeixinMaterialInfoBean> result = weixinMaterialInfoBusiness
				.getWeixinMaterialInfoPageList(pageBean, condMap);
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("AdminUserHelper", adminUserHelper);
		request.setAttribute("accountList", helper.getAccountList());
		request.setAttribute("WeixinMaterialInfoList", result.getRecordList());
		RequestDispatcher rd = request.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + path);
		rd.forward(request, response);
	}

}
