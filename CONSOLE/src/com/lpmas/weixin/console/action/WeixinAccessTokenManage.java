package com.lpmas.weixin.console.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.client.WeixinServiceClient;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.cache.WeixinConfigCache;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;
import com.lpmas.weixin.group.cache.AccessTokenController;

/**
 * Servlet implementation class WeixinAccessTokenManage
 */
@WebServlet("/weixin/WeixinAccessTokenManage.do")
public class WeixinAccessTokenManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinAccessTokenManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_APP_CONFIG, OperationConfig.SEARCH)) {
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

		// 获取ACCOUNTCODE
		String accountCode = helper.getAccountCodeMap().get(accountId);

		WeixinServiceClient client = new WeixinServiceClient();
		String token = client.getAccessTokenByAccountCode(accountCode);
		if (!StringKit.isValid(token)) {
			token = "无法获取AccessToken";
		}

		WeixinConfigCache configCache = new WeixinConfigCache();
		WeixinConfigBean bean = configCache.getWeixinConfigBean(accountId);

		request.setAttribute("AccessToken", token);
		request.setAttribute("WeixinConfigBean", bean);
		request.setAttribute("AdminUserHelper", adminUserHelper);
		request.setAttribute("accountName", helper.getAccountInfoMap().get(accountId).getAccountName());
		request.setAttribute("accountList", helper.getAccountList());
		RequestDispatcher rd = request.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "WeixinAccessTokenManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_APP_CONFIG, OperationConfig.UPDATE)) {
			return;
		}
		// 权鉴
		WeixinAccountHelper helper = new WeixinAccountHelper(adminUserHelper);
		int accountId = ParamKit.getIntParameter(request, "accountId", 0);
		ReturnMessageBean returnMessage = helper.hasPermission(accountId);
		if (returnMessage.getCode() == Constants.STATUS_NOT_VALID) {
			HttpResponseKit.alertMessage(response, returnMessage.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		String accountCode = helper.getAccountCodeMap().get(accountId);

		// 缓存刷新后刷新页面
		AccessTokenController controller = AccessTokenController.getInstance();
		controller.refreshAccessToken(accountId);

		WeixinServiceClient client = new WeixinServiceClient();
		String token = client.getAccessTokenByAccountCode(accountCode);
		if (StringKit.isValid(token)) {
			HttpResponseKit.alertMessage(response, "AccessToken刷新成功",
					"WeixinAccessTokenManage.do?accountId=" + accountId);
			return;
		} else {
			HttpResponseKit.alertMessage(response, "AccessToken刷新失败",
					"WeixinAccessTokenManage.do?accountId=" + accountId);
			return;
		}
	}

}
