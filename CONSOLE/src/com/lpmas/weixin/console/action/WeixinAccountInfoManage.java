package com.lpmas.weixin.console.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.admin.bean.AdminGroupInfoBean;
import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.system.bean.StoreInfoBean;
import com.lpmas.system.bean.SysApplicationInfoBean;
import com.lpmas.system.business.SystemInfoHelper;
import com.lpmas.weixin.bean.WeixinAccountInfoBean;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.bean.WeixinConfigInfoBean;
import com.lpmas.weixin.business.WeixinConfigUtil;
import com.lpmas.weixin.config.WeixinAppConfig;
import com.lpmas.weixin.console.business.WeixinAccountInfoBusiness;
import com.lpmas.weixin.console.business.WeixinConfigBusiness;
import com.lpmas.weixin.console.business.WeixinConfigInfoBusiness;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;

@WebServlet("/weixin/WeixinAccountInfoManage.do")
public class WeixinAccountInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(WeixinAccountInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinAccountInfoManage() {
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
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_ACCOUNT_INFO, OperationConfig.SEARCH)) {
			return;
		}
		int accountId = ParamKit.getIntParameter(request, "accountId", 0);
		WeixinAccountInfoBusiness business = new WeixinAccountInfoBusiness();
		WeixinAccountInfoBean bean = new WeixinAccountInfoBean();

		WeixinConfigBusiness configBusiness = new WeixinConfigBusiness();
		WeixinConfigBean weixinConfigBean = new WeixinConfigBean();
		if (accountId > 0) {
			bean = business.getWeixinAccountInfoByKey(accountId);
			weixinConfigBean = configBusiness.getWeixinConfigBean(accountId);
		} else {
			bean.setStatus(Constants.STATUS_VALID);
		}

		SystemInfoHelper helper = new SystemInfoHelper(adminUserHelper);
		// 获取APP列表
		List<SysApplicationInfoBean> appList = helper.getUserValidSysApplicationList();

		// 获取Store列表
		List<StoreInfoBean> storeList = helper.getUserValidStoreList();

		// 生成加密串
		String encodeId = "";
		if (StringKit.isValid(bean.getAccountCode())) {
			encodeId = WeixinConfigUtil.getidentityString(bean.getAccountCode());
		}

		// 获取用户组Id列表
		List<AdminGroupInfoBean> adminGroupInfoList = adminUserHelper.getUserGroupList();

		request.setAttribute("EncodeId", encodeId);
		request.setAttribute("AdminGroupInfoList", adminGroupInfoList);
		request.setAttribute("WeixinConfigBean", weixinConfigBean);
		request.setAttribute("WeixinAccountInfoBean", bean);
		request.setAttribute("AdminUserHelper", adminUserHelper);
		request.setAttribute("SysApplicationList", appList);
		request.setAttribute("StoreList", storeList);
		RequestDispatcher rd = request.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "WeixinAccountInfoManage.jsp");
		rd.forward(request, response);
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
		WeixinAccountInfoBean bean = new WeixinAccountInfoBean();
		try {
			// 获取微信账号信息
			String accountName = ParamKit.getParameter(request, "accountName", "");
			String accountCode = ParamKit.getParameter(request, "accountCode", "");
			String weixinUserId = ParamKit.getParameter(request, "weixinUserId", "");
			String weixinAccountId = ParamKit.getParameter(request, "weixinAccountId", "");
			int accountType = ParamKit.getIntParameter(request, "accountType", 0);
			int appInfoId = ParamKit.getIntParameter(request, "appInfoId", 0);
			int storeId = ParamKit.getIntParameter(request, "storeId", 0);
			int groupId = ParamKit.getIntParameter(request, "groupId", 0);
			int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_NOT_VALID);
			int accountId = ParamKit.getIntParameter(request, "accountId", 0);
			bean.setAccountName(accountName);
			bean.setAccountCode(accountCode);
			bean.setWeixinUserId(weixinUserId);
			bean.setWeixinAccountId(weixinAccountId);
			bean.setAccountType(accountType);
			bean.setAppId(appInfoId);
			bean.setStoreId(storeId);
			bean.setGroupId(groupId);
			bean.setStatus(status);
			bean.setAccountId(accountId);
			WeixinAccountInfoBusiness business = new WeixinAccountInfoBusiness();
			// 检验公众号代码，应用，商店是否重复
			if (accountId > 0) {
				WeixinAccountInfoBean weixinAccountInfoBean = business.getWeixinAccountInfoByKey(accountId);
				if (weixinAccountInfoBean != null) {
					if (!weixinAccountInfoBean.getAccountCode().equals(bean.getAccountCode())) {
						ReturnMessageBean messageBean = business.verifyWeixinAccountCode(bean);
						if (StringKit.isValid(messageBean.getMessage())) {
							HttpResponseKit.alertMessage(response, messageBean.getMessage(),
									HttpResponseKit.ACTION_HISTORY_BACK);
							return;
						}
					} else if (weixinAccountInfoBean.getAppId() != bean.getAppId()
							|| weixinAccountInfoBean.getStoreId() != bean.getStoreId()) {
						ReturnMessageBean messageBean = business.verifyWeixinAccountId(bean);
						if (StringKit.isValid(messageBean.getMessage())) {
							HttpResponseKit.alertMessage(response, messageBean.getMessage(),
									HttpResponseKit.ACTION_HISTORY_BACK);
							return;
						}
					}
				}
			} else {
				ReturnMessageBean messageBean = business.verifyWeixinAccountCode(bean);
				if (StringKit.isValid(messageBean.getMessage())) {
					HttpResponseKit.alertMessage(response, messageBean.getMessage(),
							HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				messageBean = business.verifyWeixinAccountId(bean);
				if (StringKit.isValid(messageBean.getMessage())) {
					HttpResponseKit.alertMessage(response, messageBean.getMessage(),
							HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
			}
			int result = 0;
			if (bean.getAccountId() > 0) {
				// 修改账号信息
				if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_ACCOUNT_INFO, OperationConfig.UPDATE)) {
					return;
				}
				bean.setModifyUser(adminUserHelper.getAdminUserId());
				result = business.updateWeixinAccountInfo(bean);
				accountId = bean.getAccountId();
			} else {
				// 新增账号信息
				if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_ACCOUNT_INFO, OperationConfig.CREATE)) {
					return;
				}
				bean.setCreateUser(adminUserHelper.getAdminUserId());
				result = business.addWeixinAccountInfo(bean);
				accountId = result;
			}

			if (result > 0) {
				// 设置信息配置
				int resultConfig = 0;
				WeixinConfigInfoBusiness weixinConfigInfoBusiness = new WeixinConfigInfoBusiness();
				WeixinConfigInfoBean weixinConfigInfoBean = null;
				for (StatusBean<String, String> statusBean : WeixinAppConfig.CONFIG_KEY_LIST) {
					String configCode = WeixinAppConfig.getWeixinConfigCode(accountId, statusBean.getStatus());
					String configValue = ParamKit
							.getParameter(request, StringKit.lowerFirstChar(statusBean.getStatus()), "").trim();

					weixinConfigInfoBean = weixinConfigInfoBusiness.getWeixinConfigInfoByCode(configCode);
					if (weixinConfigInfoBean == null) {
						// 新增
						weixinConfigInfoBean = new WeixinConfigInfoBean();
						weixinConfigInfoBean.setConfigCode(configCode);
						weixinConfigInfoBean.setConfigName(statusBean.getValue());
						weixinConfigInfoBean.setConfigValue(configValue);
						resultConfig = weixinConfigInfoBusiness.addWeixinConfigInfo(weixinConfigInfoBean);
					} else {
						// 修改
						weixinConfigInfoBean.setConfigCode(configCode);
						weixinConfigInfoBean.setConfigName(statusBean.getValue());
						weixinConfigInfoBean.setConfigValue(configValue);
						resultConfig = weixinConfigInfoBusiness.updateWeixinConfigInfo(weixinConfigInfoBean);
					}

					if (resultConfig <= 0) {
						HttpResponseKit.alertMessage(response, String.format("参数[{0}]处理失败", configCode),
								HttpResponseKit.ACTION_HISTORY_BACK);
						return;
					} else {
						HttpResponseKit.alertMessage(response, "处理成功", "/weixin/WeixinAccountInfoList.do");
					}
				}
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("", e);
		}

	}
}
