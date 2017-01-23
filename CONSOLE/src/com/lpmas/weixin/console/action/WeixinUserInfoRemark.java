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
import com.lpmas.weixin.bean.WeixinUserInfoBean;
import com.lpmas.weixin.console.business.FancyBoxUtil;
import com.lpmas.weixin.console.business.WeixinAccountInfoBusiness;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinUserInfoBusiness;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;

/**
 * Servlet implementation class WeixinUserInfoRemark
 */
@WebServlet("/weixin/WeixinUserInfoRemark.do")
public class WeixinUserInfoRemark extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinUserInfoRemark() {
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
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_USER_INFO, OperationConfig.SEARCH)) {
			return;
		}

		int accountId = ParamKit.getIntParameter(request, "accountId", 0);
		if (accountId <= 0) {
			FancyBoxUtil.alertAndClose(response, "公众号ID不能为空");
			return;
		}
		String openId = ParamKit.getParameter(request, "openId", "");
		if (!StringKit.isValid(openId)) {
			FancyBoxUtil.alertAndClose(response, "openId缺失");
			return;
		}

		// 权限验证
		WeixinAccountHelper helper = new WeixinAccountHelper(adminUserHelper);
		ReturnMessageBean returnMessage = helper.hasPermission(accountId);
		if (returnMessage.getCode() == Constants.STATUS_NOT_VALID) {
			HttpResponseKit.alertMessage(response, returnMessage.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 根据参数查找用户
		WeixinUserInfoBusiness userInfoBusiness = new WeixinUserInfoBusiness();
		WeixinUserInfoBean userInfoBean = userInfoBusiness.getWeixinUserInfoByKey(accountId, openId);
		if (userInfoBean == null) {
			FancyBoxUtil.alertAndClose(response, "该用户不存在");
			return;
		}

		// 检查用户是否关注
		if (Constants.STATUS_NOT_VALID == userInfoBean.getIsSubscribe()) {
			FancyBoxUtil.alertAndClose(response, "该用户不是本公众号关注者");
			return;
		}

		WeixinAccountInfoBusiness accountInfoBusiness = new WeixinAccountInfoBusiness();
		String accountName = accountInfoBusiness.getWeixinAccountInfoByKey(accountId).getAccountName();

		request.setAttribute("accountName", accountName);
		request.setAttribute("WeixinUserInfoBean", userInfoBean);
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "WeixinUserInfoRemark.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_USER_INFO, OperationConfig.UPDATE)) {
			return;
		}

		int accountId = ParamKit.getIntParameter(request, "accountId", 0);
		if (accountId <= 0) {
			FancyBoxUtil.alertAndClose(response, "公众号ID不能为空");
			return;
		}
		String openId = ParamKit.getParameter(request, "openId", "");
		if (!StringKit.isValid(openId)) {
			FancyBoxUtil.alertAndClose(response, "openId缺失");
			return;
		}

		// 权限验证
		WeixinAccountHelper helper = new WeixinAccountHelper(adminUserHelper);
		ReturnMessageBean returnMessage = helper.hasPermission(accountId);
		if (returnMessage.getCode() == Constants.STATUS_NOT_VALID) {
			HttpResponseKit.alertMessage(response, returnMessage.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		String remark = ParamKit.getParameter(request, "remark", "").trim();
		int result = 0;
		// 根据参数查找用户
		WeixinUserInfoBusiness userInfoBusiness = new WeixinUserInfoBusiness();
		WeixinUserInfoBean userInfoBean = userInfoBusiness.getWeixinUserInfoByKey(accountId, openId);
		if (userInfoBean == null) {
			FancyBoxUtil.alertAndClose(response, "该用户不存在");
			return;
		}

		// 检查用户是否关注
		if (Constants.STATUS_NOT_VALID == userInfoBean.getIsSubscribe()) {
			FancyBoxUtil.alertAndClose(response, "该用户不是本公众号关注者");
			return;
		}

		userInfoBean.setRemark(remark);
		// 通知微信更改
		result = userInfoBusiness.updateWeixinUserInfoRemark(userInfoBean);
		if (result <= 0) {
			FancyBoxUtil.alertAndClose(response, "调用接口更新备注失败");
			return;
		}
		// 重新拉取用户信息
		result = userInfoBusiness.refreshWeixinUserInfo(userInfoBean);
		if (result <= 0) {
			FancyBoxUtil.alertAndClose(response, "调用接口更新备注成功，刷新用户信息失败");
			return;
		}

		FancyBoxUtil.alertAndCloseAndOperateParent(response, "调用接口更新备注成功，刷新用户信息成功", FancyBoxUtil.ACTION_WINDOW_REFRESH);

	}

}
