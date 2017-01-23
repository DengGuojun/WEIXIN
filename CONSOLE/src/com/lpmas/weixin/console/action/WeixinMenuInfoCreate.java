package com.lpmas.weixin.console.action;

import java.io.IOException;

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
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.api.bean.request.menu.CreateMenuRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.request.menu.CreateMenu;
import com.lpmas.weixin.bean.WeixinMenuInfoBean;
import com.lpmas.weixin.business.WeixinUtil;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinMenuInfoBusiness;
import com.lpmas.weixin.console.config.WeixinResource;
import com.lpmas.weixin.console.factory.WeixinRequestFactory;

@WebServlet("/weixin/WeixinMenuInfoCreate.do")
public class WeixinMenuInfoCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(WeixinMenuInfoCreate.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinMenuInfoCreate() {
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
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MENU_INFO, OperationConfig.CREATE)) {
			return;
		}
		int menuId = ParamKit.getIntParameter(request, "menuId", 0);
		if (menuId <= 0) {
			HttpResponseKit.alertMessage(response, "菜单ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
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

		String accountCode = helper.getAccountCodeMap().get(accountId);

		// 根据菜单ID查询菜单
		WeixinMenuInfoBusiness weixinMenuInfoBusiness = new WeixinMenuInfoBusiness();
		WeixinMenuInfoBean weixinMenuInfoBean = weixinMenuInfoBusiness.getWeixinMenuInfoByKey(menuId);
		if (weixinMenuInfoBean == null) {
			HttpResponseKit.alertMessage(response, "菜单不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		} else {
			// 检验ACCOUNTID是否对应
			if (weixinMenuInfoBean.getAccountId() != accountId) {
				HttpResponseKit.alertMessage(response, "菜单不存在于这个公众号", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			// 请求微信
			CreateMenuRequestBean createMenuRequestBean = WeixinRequestFactory.getRequestBean(accountCode,
					CreateMenuRequestBean.class);
			createMenuRequestBean.setMenuContent(weixinMenuInfoBean.getMenuContent());
			CreateMenu createMenu = new CreateMenu();
			WxResponseBaseBean wxResponseBaseBean = createMenu.execute(createMenuRequestBean);
			ReturnMessageBean messageBean = new ReturnMessageBean();
			if (!WeixinUtil.isSuccess(wxResponseBaseBean)) {
				log.error("模板创建失败,errMsg:" + wxResponseBaseBean.getErrMsg());
				messageBean.setMessage(wxResponseBaseBean.getErrMsg());
			} else {
				messageBean.setMessage("处理成功");
			}
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}

	}
}
