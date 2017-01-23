package com.lpmas.weixin.console.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.client.WeixinServiceClient;
import com.lpmas.weixin.client.config.WeixinQrcodeConfig;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;

/**
 * Servlet implementation class WeixinQrcodeInfoManage
 */
@WebServlet("/weixin/WeixinQrcodeInfoManage.do")
public class WeixinQrcodeInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(WeixinQrcodeInfoManage.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinQrcodeInfoManage() {
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
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_QRCODE_INFO, OperationConfig.SEARCH)) {
			return;
		}
		int accountId = ParamKit.getIntParameter(request, "accountId", 0);
		if (accountId <= 0) {
			HttpResponseKit.alertMessage(response, "公众号ID不能为空", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 权限验证
		WeixinAccountHelper helper = new WeixinAccountHelper(adminUserHelper);
		ReturnMessageBean returnMessage = helper.hasPermission(accountId);
		if (returnMessage.getCode() == Constants.STATUS_NOT_VALID) {
			HttpResponseKit.alertMessage(response, returnMessage.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request
				.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "WeixinQrcodeInfoManage.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_QRCODE_INFO, OperationConfig.CREATE)) {
			return;
		}

		int accountId = ParamKit.getIntParameter(request, "accountId", 0);
		if (accountId <= 0) {
			HttpResponseKit.alertMessage(response, "公众号ID不能为空", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 权限验证
		WeixinAccountHelper helper = new WeixinAccountHelper(adminUserHelper);
		ReturnMessageBean returnMessage = helper.hasPermission(accountId);
		if (returnMessage.getCode() == Constants.STATUS_NOT_VALID) {
			HttpResponseKit.alertMessage(response, returnMessage.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		String appSceneId = ParamKit.getParameter(request, "appSceneId", "").trim();
		if (!StringKit.isValid(appSceneId)) {
			HttpResponseKit.alertMessage(response, "应用场景ID不能为空", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		String qrcodeType = ParamKit.getParameter(request, "qrcodeType", "").trim();
		if (!StringKit.isValid(qrcodeType)) {
			HttpResponseKit.alertMessage(response, "二维码类型不能为空", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		String callBackQueue = ParamKit.getParameter(request, "callBackQueue", "").trim();
		int expireSeconds = ParamKit.getIntParameter(request, "expireSeconds", 0);

		// 获取ACCOUNTCODE
		WeixinAccountInfoCache cache = new WeixinAccountInfoCache();
		String accountCode = cache.getAccountCodeByKey(accountId);

		try {
			// 调用CLIENT获得二维码
			WeixinServiceClient client = new WeixinServiceClient();
			String ticket = "";
			if (qrcodeType.equals(WeixinQrcodeConfig.QRCODE_TYPE_TEMPORARY)) {
				ticket = client.getTemporaryQrcodeTicketByAccountCode(accountCode, appSceneId,callBackQueue,expireSeconds);
			} else if (qrcodeType.equals(WeixinQrcodeConfig.QRCODE_TYPE_PERMANENT)) {
				ticket = client.getPermanentTicketByAppSceneIdByAccountCode(accountCode, appSceneId,callBackQueue);
			} else {
				HttpResponseKit.alertMessage(response, "二维码类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			if (StringKit.isValid(ticket)) {
				HttpResponseKit.alertMessage(response, "创建成功",
						"WeixinQrcodeInfoList.do?accountId=" + accountId + "&qrcodeType=" + qrcodeType);
				return;
			} else {
				throw new Exception("二维码获取失败");
			}
		} catch (Exception e) {
			log.error("", e);
			HttpResponseKit.alertMessage(response, e.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

	}

}
