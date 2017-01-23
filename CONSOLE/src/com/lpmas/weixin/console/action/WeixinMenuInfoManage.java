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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.admin.bean.AdminGroupInfoBean;
import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.bean.WeixinMenuInfoBean;
import com.lpmas.weixin.config.WeixinMenuConfig;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinMenuInfoBusiness;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;

@WebServlet("/weixin/WeixinMenuInfoManage.do")
public class WeixinMenuInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(WeixinMenuInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinMenuInfoManage() {
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
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MENU_INFO, OperationConfig.SEARCH)) {
			return;
		}
		int menuId = ParamKit.getIntParameter(request, "menuId", 0);
		int accountId = ParamKit.getIntParameter(request, "accountId", 0);
		if (accountId <= 0) {
			HttpResponseKit.alertMessage(response, "公众号ID不能为空", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 获得这个用户的用户组
		List<AdminGroupInfoBean> groupList = adminUserHelper.getUserGroupList();
		if (groupList.isEmpty()) {
			HttpResponseKit.alertMessage(response, "对不起您不属于任何用户组,无权查看", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 权限验证
		WeixinAccountHelper helper = new WeixinAccountHelper(adminUserHelper);
		ReturnMessageBean returnMessage = helper.hasPermission(accountId);
		if (returnMessage.getCode() == Constants.STATUS_NOT_VALID) {
			HttpResponseKit.alertMessage(response, returnMessage.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		String accountName = helper.getAccountInfoMap().get(accountId).getAccountName();

		WeixinMenuInfoBusiness meunBusiness = new WeixinMenuInfoBusiness();
		WeixinMenuInfoBean bean = meunBusiness.getWeixinMenuInfoByKey(menuId);

		if (bean != null) {
			// 存在的话检验一下公众号信息是否对应
			if (bean.getAccountId() != accountId) {
				HttpResponseKit.alertMessage(response, "这个菜单不存在于这个公众号", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		} else {
			bean = new WeixinMenuInfoBean();
			bean.setStatus(Constants.STATUS_VALID);
		}
		request.setAttribute("accountName", accountName);
		request.setAttribute("WeixinMenuInfoBean", bean);
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "WeixinMenuInfoManage.jsp");
		rd.forward(request, response);
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

		WeixinMenuInfoBean bean = new WeixinMenuInfoBean();
		try {
			WeixinMenuInfoBusiness business = new WeixinMenuInfoBusiness();
			bean = BeanKit.request2Bean(request, WeixinMenuInfoBean.class);
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("menuType", String.valueOf(WeixinMenuConfig.MENU_TYPE_DEFAULT));
			condMap.put("accountId", String.valueOf(accountId));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			// 每个公众号默认类型菜单只能有一个
			List<WeixinMenuInfoBean> weixinMenuInfoList = business.getWeixinMenuInfoListByMap(condMap);
			if (bean.getMenuId() > 0) {
				for (WeixinMenuInfoBean weixinMenuInfoBean : weixinMenuInfoList) {
					if (weixinMenuInfoBean.getMenuId() != bean.getMenuId()) {
						HttpResponseKit.alertMessage(response, "一个公众号只允许一个默认菜单", HttpResponseKit.ACTION_HISTORY_BACK);
						return;
					}
				}
			} else {
				if (!weixinMenuInfoList.isEmpty() && weixinMenuInfoList.size() > 0) {
					HttpResponseKit.alertMessage(response, "一个公众号只允许一个默认菜单", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
			}
			int result = 0;
			WeixinMenuInfoBean existBean = business.getWeixinMenuInfoByKey(bean.getMenuId());
			if (existBean != null) {
				// 检验一下公众号信息是否对应
				if (bean.getAccountId() != accountId || existBean.getAccountId() != accountId) {
					HttpResponseKit.alertMessage(response, "这个菜单不存在于这个公众号", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				// 修改
				if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MENU_INFO, OperationConfig.UPDATE)) {
					return;
				}
				bean.setAccountId(accountId);
				bean.setMenuType(WeixinMenuConfig.MENU_TYPE_DEFAULT);
				bean.setModifyUser(adminUserHelper.getAdminUserId());
				result = business.updateWeixinMenuInfo(bean);
			} else {
				// 检验一下公众号信息是否对应
				if (bean.getAccountId() != accountId) {
					HttpResponseKit.alertMessage(response, "这个菜单不存在于这个公众号", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				// 新增
				if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MENU_INFO, OperationConfig.CREATE)) {
					return;
				}
				bean.setAccountId(accountId);
				bean.setMenuType(WeixinMenuConfig.MENU_TYPE_DEFAULT);
				bean.setCreateUser(adminUserHelper.getAdminUserId());
				result = business.addWeixinMenuInfo(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "WeixinMenuInfoList.do?accountId=" + accountId);
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
