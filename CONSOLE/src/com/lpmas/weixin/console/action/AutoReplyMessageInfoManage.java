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

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.bean.AutoReplyMessageInfoBean;
import com.lpmas.weixin.config.AutoReplyMessageConfig;
import com.lpmas.weixin.console.business.AutoReplyMessageInfoBusiness;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;

@WebServlet("/weixin/AutoReplyMessageInfoManage.do")
public class AutoReplyMessageInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(AutoReplyMessageInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AutoReplyMessageInfoManage() {
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
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_AUTO_REPLY, OperationConfig.SEARCH)) {
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

		int replyId = ParamKit.getIntParameter(request, "replyId", 0);
		AutoReplyMessageInfoBusiness autoReplyBusniess = new AutoReplyMessageInfoBusiness();
		AutoReplyMessageInfoBean bean = autoReplyBusniess.getAutoReplyMessageInfoByKey(replyId);
		if (bean == null) {
			bean = new AutoReplyMessageInfoBean();
			bean.setStatus(Constants.STATUS_VALID);
		} else {
			// 检验这个自动回复是否属于这个ACCOUNT的
			if (bean.getAccountId() != accountId) {
				HttpResponseKit.alertMessage(response, "这个自动回复不属于这个公众号", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		}

		request.setAttribute("AutoReplyMessageInfoBean", bean);
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "AutoReplyMessageInfoManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_AUTO_REPLY, OperationConfig.SEARCH)) {
			return;
		}
		Integer accountId = ParamKit.getIntParameter(request, "accountId", 0);
		if (accountId == 0) {
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

		AutoReplyMessageInfoBean bean = null;
		try {
			bean = BeanKit.request2Bean(request, AutoReplyMessageInfoBean.class);
			AutoReplyMessageInfoBusiness business = new AutoReplyMessageInfoBusiness();
			// 表单数据验证
			ReturnMessageBean messageBean = business.verifyAutoReplyMessageInfo(bean);
			if (StringKit.isValid(messageBean.getMessage())) {
				HttpResponseKit.alertMessage(response, messageBean.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			// 检验每个公众号默认回复的唯一性
			if (bean.getReplyType() == AutoReplyMessageConfig.AR_TYPE_DEFAULT) {
				HashMap<String, String> condMap = new HashMap<String, String>();
				condMap.put("accountId", String.valueOf(accountId));
				condMap.put("replyType", String.valueOf(AutoReplyMessageConfig.AR_TYPE_DEFAULT));
				condMap.put("status", String.valueOf(Constants.STATUS_VALID));
				// 每个公众号默认类型只能有一个
				List<AutoReplyMessageInfoBean> autoReplyMessageInfoList = business
						.getAutoReplyMessageInfoListByMap(condMap);
				if (autoReplyMessageInfoList.size() > 0) {
					// 取出第一个看看是否与表单时同一个ID
					if (bean.getReplyId() != autoReplyMessageInfoList.get(0).getReplyId()) {
						HttpResponseKit.alertMessage(response, "该公众号默认回复不允许新建多次", HttpResponseKit.ACTION_HISTORY_BACK);
						return;
					}
				}
			}
			int result = 0;
			if (bean.getReplyId() > 0) {
				// 修改
				if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_AUTO_REPLY, OperationConfig.UPDATE)) {
					return;
				}
				bean.setModifyUser(adminUserHelper.getAdminUserId());
				result = business.updateAutoReplyMessageInfo(bean);
			} else {
				// 新增
				if (!adminUserHelper.checkPermission(WeixinResource.WEIXIN_AUTO_REPLY, OperationConfig.CREATE)) {
					return;
				}
				bean.setAccountId(accountId);
				bean.setCreateUser(adminUserHelper.getAdminUserId());
				result = business.addAutoReplyMessageInfo(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "AutoReplyMessageInfoList.do?accountId=" + accountId);
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
