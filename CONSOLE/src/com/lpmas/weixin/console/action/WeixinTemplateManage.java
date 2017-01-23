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
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.bean.WeixinPropertyConfigBean;
import com.lpmas.weixin.config.WeixinPropertyConfig;
import com.lpmas.weixin.console.business.WeixinAccountInfoBusiness;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinPropertyConfigBusiness;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;

@WebServlet("/weixin/WeixinTemplateManage.do")
public class WeixinTemplateManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(WeixinTemplateManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinTemplateManage() {
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
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MATERIAL_INFO, OperationConfig.SEARCH)) {
			return;
		}
		int propertyId = ParamKit.getIntParameter(request, "propertyId", 0);
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

		WeixinAccountInfoBusiness accountInfoBusiness = new WeixinAccountInfoBusiness();
		String accountName = accountInfoBusiness.getWeixinAccountInfoByKey(accountId).getAccountName();

		WeixinPropertyConfigBusiness business = new WeixinPropertyConfigBusiness();
		WeixinPropertyConfigBean bean = business.getWeixinPropertyConfigByKey(propertyId);

		if (bean != null) {
			// 存在的话检验一下公众号信息是否对应
			if (bean.getAccountId() != accountId) {
				HttpResponseKit.alertMessage(response, "这个菜单不存在于这个公众号", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		} else {
			bean = new WeixinPropertyConfigBean();
			bean.setStatus(Constants.STATUS_VALID);
		}

		request.setAttribute("accountName", accountName);
		request.setAttribute("WeixinPropertyConfigBean", bean);
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "WeixinTemplateManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MATERIAL_INFO, OperationConfig.SEARCH)) {
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

		WeixinPropertyConfigBean bean = new WeixinPropertyConfigBean();
		try {
			bean = BeanKit.request2Bean(request, WeixinPropertyConfigBean.class);
			WeixinPropertyConfigBusiness business = new WeixinPropertyConfigBusiness();
			WeixinPropertyConfigBean existBean = business.getWeixinPropertyConfigByKey(bean.getPropertyId());
			int result = 0;
			if (existBean != null) {
				// 检验一下公众号信息是否对应
				if (bean.getAccountId() != accountId || existBean.getAccountId() != accountId) {
					HttpResponseKit.alertMessage(response, "这个菜单不存在于这个公众号", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				// 修改
				if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MATERIAL_INFO, OperationConfig.UPDATE)) {
					return;
				}
				bean.setAccountId(accountId);
				bean.setPropertyType(WeixinPropertyConfig.TEMPLATETYPE);
				bean.setModifyUser(adminUserHelper.getAdminUserId());
				result = business.updateWeixinPropertyConfig(bean);
			} else {
				// 检验一下公众号信息是否对应
				if (bean.getAccountId() != accountId) {
					HttpResponseKit.alertMessage(response, "这个菜单不存在于这个公众号", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				// 新增
				if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MATERIAL_INFO, OperationConfig.CREATE)) {
					return;
				}
				bean.setAccountId(accountId);
				bean.setPropertyType(WeixinPropertyConfig.TEMPLATETYPE);
				bean.setCreateUser(adminUserHelper.getAdminUserId());
				result = business.addWeixinPropertyConfig(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/weixin/WeixinTemplateList.do?accountId=" + accountId);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		} catch (Exception e) {
			log.error("", e);
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

	}

}
