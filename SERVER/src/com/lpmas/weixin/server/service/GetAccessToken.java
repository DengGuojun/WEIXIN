package com.lpmas.weixin.server.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpRequestKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.server.cache.WeixinAccessTokenCache;
import com.lpmas.weixin.server.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.server.config.AccessTokenIPConfig;

/**
 * Servlet implementation class GetAccessToken
 */
@WebServlet("/weixin/GetAccessToken.action")
public class GetAccessToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(GetAccessToken.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAccessToken() {
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

		ReturnMessageBean returnMessageBean = new ReturnMessageBean();
		String appCode = ParamKit.getParameter(request, "appCode", "");
		String storeCode = ParamKit.getParameter(request, "storeCode", "");

		String ip = HttpRequestKit.getIpAddress(request);
		if (!AccessTokenIPConfig.allowIpSet.contains(ip)) {
			returnMessageBean.setMessage("非法IP");
			logger.error(ip + "|非法IP|appCode:" + appCode + "|storeCode:" + storeCode);
			HttpResponseKit.printJson(request, response, returnMessageBean, "");
			return;
		}

		if (!StringKit.isValid(appCode)) {
			returnMessageBean.setMessage("appCode非法");
			logger.error(ip + "|appCode非法|appCode:" + appCode + "|storeCode:" + storeCode);
			HttpResponseKit.printJson(request, response, returnMessageBean, "");
			return;
		}

		WeixinAccessTokenCache cache = new WeixinAccessTokenCache();
		try {
			// 获取ACCOUNTID
			WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
			int accountId = accountInfoCache.getWeixinAccountInfoByCondition(appCode, storeCode).getAccountId();
			String token = cache.getAccessToken(accountId);
			if (StringKit.isValid(token)) {
				logger.info(ip + "|获取ACCESSTOKEN成功,accesstoken:" + token + "|appCode:" + appCode + "|storeCode:"
						+ storeCode);
				returnMessageBean.setCode(Constants.STATUS_VALID);
				returnMessageBean.setMessage("获取ACCESSTOKEN成功");
				returnMessageBean.setContent(token);
				HttpResponseKit.printJson(request, response, returnMessageBean, "");
				return;
			} else {
				logger.error(ip + "|获取ACCESSTOKEN失败|appCode:" + appCode + "|storeCode:" + storeCode);
				returnMessageBean.setMessage("获取ACCESSTOKEN失败");
				HttpResponseKit.printJson(request, response, returnMessageBean, "");
				return;
			}
		} catch (Exception e) {
			logger.error(ip + "|appCode:" + appCode + "|storeCode:" + storeCode, e);
			returnMessageBean.setMessage(e.getMessage());
			HttpResponseKit.printJson(request, response, returnMessageBean, "");
			return;
		}
	}

}
