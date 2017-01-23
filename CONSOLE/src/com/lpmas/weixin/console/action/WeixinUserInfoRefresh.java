package com.lpmas.weixin.console.action;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinUserInfoBusiness;
import com.lpmas.weixin.console.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.console.config.WeixinResource;

/**
 * Servlet implementation class WeixinUserInfoRefresh
 */
@WebServlet("/weixin/WeixinUserInfoRefresh.do")
public class WeixinUserInfoRefresh extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(WeixinUserInfoRefresh.class);
	private static Map<String, Thread> threadMap = new ConcurrentHashMap<String, Thread>();
	private static Object lock = new Object();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinUserInfoRefresh() {
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
		if (!adminUserHelper.hasPermission(WeixinResource.WEIXIN_USER_INFO, OperationConfig.UPDATE)) {
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

		// 获取CCOUNTCODE
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		String accountCode = accountInfoCache.getAccountCodeByKey(accountId);

		try {
			synchronized (lock) {
				Thread thread = threadMap.get(accountCode);
				if (thread == null || !thread.isAlive()) {
					thread = new Thread(new Runnable() {
						@Override
						public void run() {
							// 刷新用户列表
							WeixinUserInfoBusiness userInfoBusiness = new WeixinUserInfoBusiness();
							userInfoBusiness.refreshWeixinUserInfoAllList(accountId);
						}
					});
					threadMap.put(accountCode, thread);
					thread.start();
				} else {
					HttpResponseKit.alertMessage(response, "这个公众号已经启动了刷新关注者信息任务，请勿重复提交",
							"WeixinUserInfoList.do?accountId=" + accountId);
					return;
				}
			}
			HttpResponseKit.alertMessage(response, "成功启动刷新关注者信息任务", "WeixinUserInfoList.do?accountId=" + accountId);
			return;
		} catch (Exception e) {
			logger.error("", e);
			HttpResponseKit.alertMessage(response, "启动刷新关注者信息任务失败", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
	}

}
