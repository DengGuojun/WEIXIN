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
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.api.bean.request.material.GetMaterialCountRequestBean;
import com.lpmas.weixin.api.bean.response.material.GetMaterialCountResponseBean;
import com.lpmas.weixin.api.config.MaterialTypeConfig;
import com.lpmas.weixin.api.request.material.GetMaterialCount;
import com.lpmas.weixin.business.WeixinUtil;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinMaterialInfoBusiness;
import com.lpmas.weixin.console.config.WeixinResource;
import com.lpmas.weixin.console.factory.WeixinRequestFactory;

@WebServlet("/weixin/WeixinMaterialInfoReFresh.do")
public class WeixinMaterialInfoReFresh extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(WeixinMaterialInfoReFresh.class);
	private static Map<String, Thread> threadMap = new ConcurrentHashMap<String, Thread>();
	private static Object lock = new Object();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinMaterialInfoReFresh() {
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
		int accountId = ParamKit.getIntParameter(request, "accountId", 0);
		if (accountId <= 0) {
			HttpResponseKit.alertMessage(response, "公众号ID不能为空", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		String mediaType = ParamKit.getParameter(request, "mediaType", "");
		if (!MaterialTypeConfig.MATERIAL_TYPE_MAP.containsKey(mediaType)) {
			HttpResponseKit.alertMessage(response, "素材类型错误", HttpResponseKit.ACTION_HISTORY_BACK);
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

		// 调用接口获取当前所有素材总数
		GetMaterialCountRequestBean getMaterialCountRequestBean = WeixinRequestFactory.getRequestBean(accountCode,
				GetMaterialCountRequestBean.class);
		GetMaterialCount getMaterialCount = new GetMaterialCount();
		GetMaterialCountResponseBean getMaterialCountResponseBean = (GetMaterialCountResponseBean) getMaterialCount
				.execute(getMaterialCountRequestBean);
		if (WeixinUtil.isSuccess(getMaterialCountResponseBean)) {
			try {
				synchronized (lock) {
					Thread thread = threadMap.get(accountCode);
					if (thread == null || !thread.isAlive()) {
						thread = new Thread(new Runnable() {
							@Override
							public void run() {
								// 获取各个类型的素材列表
								WeixinMaterialInfoBusiness materialInfoBusiness = new WeixinMaterialInfoBusiness();
								// 刷新图文
								materialInfoBusiness.refreshMaterialInfo(getMaterialCountResponseBean.getNewsCount(),
										MaterialTypeConfig.MT_NEWS, accountId);
								// 刷新图片
								materialInfoBusiness.refreshMaterialInfo(getMaterialCountResponseBean.getImageCount(),
										MaterialTypeConfig.MT_IMAGE, accountId);
								// 刷新音频
								materialInfoBusiness.refreshMaterialInfo(getMaterialCountResponseBean.getVoiceCount(),
										MaterialTypeConfig.MT_VOICE, accountId);
								// 刷新视频
								materialInfoBusiness.refreshMaterialInfo(getMaterialCountResponseBean.getVideoCount(),
										MaterialTypeConfig.MT_VIDEO, accountId);
							}
						});
						threadMap.put(accountCode, thread);
						thread.start();
					} else {
						HttpResponseKit.alertMessage(response, "这个公众号已经启动了刷新素材任务，请勿重复提交",
								"Weixin" + StringKit.upperFirstChar(mediaType.toLowerCase())
										+ "MaterialInfoList.do?accountId=" + accountId);
						return;
					}
				}
				HttpResponseKit.alertMessage(response, "成功启动刷新素材任务",
						"Weixin" + StringKit.upperFirstChar(mediaType.toLowerCase()) + "MaterialInfoList.do?accountId="
								+ accountId);
				return;
			} catch (Exception e) {
				logger.error("", e);
				HttpResponseKit.alertMessage(response, "启动刷新素材任务失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		} else {
			logger.error("素材数量获取失败,errMsg:" + getMaterialCountResponseBean.getErrMsg());
			HttpResponseKit.alertMessage(response, "素材数量获取失败,errMsg:" + getMaterialCountResponseBean.getErrMsg(),
					HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
	}
}
