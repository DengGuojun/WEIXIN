package com.lpmas.weixin.console.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import com.lpmas.weixin.api.bean.receive.MessageBaseBean;
import com.lpmas.weixin.bean.ReceiveMessageBean;
import com.lpmas.weixin.client.business.ClassUtil;
import com.lpmas.weixin.client.business.JsonUtil;
import com.lpmas.weixin.console.business.WeixinAccountInfoBusiness;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinReceiveMessageBusiness;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;

/**
 * Servlet implementation class WeixinReceiveMessageManage
 */
@WebServlet("/weixin/WeixinReceiveMessageManage.do")
public class WeixinReceiveMessageManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(WeixinReceiveMessageManage.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinReceiveMessageManage() {
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
		String _id = ParamKit.getParameter(request, "_id", "");
		if (!StringKit.isValid(_id)) {
			HttpResponseKit.alertMessage(response, "_id不能为空", HttpResponseKit.ACTION_HISTORY_BACK);
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

		// 查询MONGO
		WeixinReceiveMessageBusiness messageBusiness = new WeixinReceiveMessageBusiness();
		ReceiveMessageBean messageBean = messageBusiness.getWeixinReceiveMessageBeanByKey(_id);
		if (messageBean == null) {
			HttpResponseKit.alertMessage(response, "该消息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		Map<String, Object> contentMap = new HashMap<String, Object>();

		try {
			// 重现MESSAGECONTENT
			Object contentBean = JsonUtil.toBean(messageBean.getMessageContent(),
					ClassUtil.getClass(messageBean.getMessageContentClassName()));
			contentMap = messageBusiness.getDisplayMapByMessageBean(accountId, (MessageBaseBean) contentBean);

		} catch (Exception e) {
			logger.error("", e);
		}

		WeixinAccountInfoBusiness business = new WeixinAccountInfoBusiness();
		String accountName = business.getWeixinAccountInfoByKey(accountId).getAccountName();

		request.setAttribute("contentMap", contentMap);
		request.setAttribute("accountName", accountName);
		request.setAttribute("ReceiveMessageBean", messageBean);
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "WeixinReceiveMessageManage.jsp");
		rd.forward(request, response);
	}

}
