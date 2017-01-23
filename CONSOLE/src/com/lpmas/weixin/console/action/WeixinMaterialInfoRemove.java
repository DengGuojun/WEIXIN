package com.lpmas.weixin.console.action;

import java.io.IOException;

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
import com.lpmas.weixin.api.bean.request.material.DeleteMaterialRequestBean;
import com.lpmas.weixin.api.bean.response.material.DeleteMaterialResponseBean;
import com.lpmas.weixin.api.request.material.DeleteMaterial;
import com.lpmas.weixin.bean.WeixinMaterialInfoBean;
import com.lpmas.weixin.business.WeixinUtil;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinMaterialInfoBusiness;
import com.lpmas.weixin.console.config.WeixinResource;
import com.lpmas.weixin.console.factory.WeixinRequestFactory;

@WebServlet("/weixin/WeixinMaterialInfoRemove.do")
public class WeixinMaterialInfoRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinMaterialInfoRemove() {
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
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_MATERIAL_INFO, OperationConfig.REMOVE)) {
			return;
		}
		String _id = ParamKit.getParameter(request, "_id", "").trim();
		int accountId = ParamKit.getIntParameter(request, "accountId", 0);
		if (accountId <= 0) {
			HttpResponseKit.alertMessage(response, "公众号ID不能为空", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		if (!StringKit.isValid(_id)) {
			HttpResponseKit.alertMessage(response, "素材ID不能为空", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 权限验证
		WeixinAccountHelper helper = new WeixinAccountHelper(adminUserHelper);
		ReturnMessageBean returnMessage = helper.hasPermission(accountId);
		if (returnMessage.getCode() == Constants.STATUS_NOT_VALID) {
			HttpResponseKit.alertMessage(response, returnMessage.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 先查询对应的素材
		WeixinMaterialInfoBusiness business = new WeixinMaterialInfoBusiness();
		WeixinMaterialInfoBean materialInfoBean = business.getWeixinMaterialInfoByKey(_id);
		if (materialInfoBean == null || !(materialInfoBean.getAccountId() == accountId)) {
			// 不存在或者不属于这个账户的都返回
			HttpResponseKit.alertMessage(response, "该素材不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		String accountCode = helper.getAccountCodeMap().get(accountId);

		// 发起请求删除
		DeleteMaterial deleteMaterial = new DeleteMaterial();
		DeleteMaterialRequestBean deleteMaterialRequestBean = WeixinRequestFactory.getRequestBean(accountCode,
				DeleteMaterialRequestBean.class);
		deleteMaterialRequestBean.setMediaId(materialInfoBean.getMediaId());
		DeleteMaterialResponseBean deleteMaterialResponseBean = (DeleteMaterialResponseBean) deleteMaterial
				.execute(deleteMaterialRequestBean);
		if (WeixinUtil.isSuccess(deleteMaterialResponseBean)) {
			// 请求成功
			// 操作数据库
			long result = 0;
			result = business.deleteWeixinMaterialInfo(materialInfoBean);
			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功",
						"Weixin" + StringKit.upperFirstChar(materialInfoBean.getMediaType().toLowerCase())
								+ "MaterialInfoList.do?accountCode=" + accountCode);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		} else {
			String message = "";
			if (deleteMaterialResponseBean == null || deleteMaterialResponseBean.getErrMsg().trim().equals("")) {
				message = "请求失败!";
			} else {
				message = deleteMaterialResponseBean.getErrMsg();
			}
			HttpResponseKit.alertMessage(response, message, HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

	}
}
