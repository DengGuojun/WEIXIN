package com.lpmas.weixin.console.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.bean.WeixinMaterialInfoBean;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinMaterialInfoBusiness;
import com.lpmas.weixin.console.config.WeixinResource;

/**
 * Servlet implementation class WeixinMaterialInfoAjaxManage
 */
@WebServlet("/weixin/WeixinMaterialInfoAjaxManage.do")
public class WeixinMaterialInfoAjaxManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinMaterialInfoAjaxManage() {
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
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MATERIAL_INFO, OperationConfig.UPDATE)) {
			return;
		}
		ReturnMessageBean returnMessage = new ReturnMessageBean();
		int accountId = ParamKit.getIntParameter(request, "accountId", 0);
		if (accountId <= 0) {
			returnMessage.setMessage("公众号ID不能为空");
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		}

		// 权限验证
		WeixinAccountHelper helper = new WeixinAccountHelper(adminUserHelper);
		returnMessage = helper.hasPermission(accountId);
		if (returnMessage.getCode() == Constants.STATUS_NOT_VALID) {
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		}

		String mediaId = ParamKit.getParameter(request, "mediaId", "").trim();
		WeixinMaterialInfoBusiness business = new WeixinMaterialInfoBusiness();
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("mediaId", mediaId);
		condMap.put("accountId", accountId);

		List<WeixinMaterialInfoBean> materialInfoList = business.getWeixinMaterialInfoListByMap(condMap);

		// 判断是够找到这个素材
		if (materialInfoList.isEmpty()) {
			returnMessage.setMessage("素材ID不存在");
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		}

		// 返回第一个
		returnMessage.setCode(Constants.STATUS_VALID);
		returnMessage.setContent(materialInfoList.get(0));
		HttpResponseKit.printJson(request, response, returnMessage, "");
		return;
	}

}
