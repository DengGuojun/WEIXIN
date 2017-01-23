package com.lpmas.weixin.console.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.lpmas.weixin.api.bean.material.ArticleInfoBean;
import com.lpmas.weixin.api.bean.request.material.AddNewsRequestBean;
import com.lpmas.weixin.api.bean.response.material.AddNewsResponseBean;
import com.lpmas.weixin.api.bean.response.material.ArticleResponseBean;
import com.lpmas.weixin.api.config.MaterialTypeConfig;
import com.lpmas.weixin.api.request.material.AddNews;
import com.lpmas.weixin.bean.WeixinMaterialInfoBean;
import com.lpmas.weixin.business.WeixinUtil;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinMaterialInfoBusiness;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;
import com.lpmas.weixin.console.factory.WeixinRequestFactory;

/**
 * Servlet implementation class WeixinNewsMaterialInfoManage
 */
@WebServlet("/weixin/WeixinNewsMaterialInfoManage.do")
public class WeixinNewsMaterialInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(WeixinNewsMaterialInfoManage.class);
	private static final int MULTIPLE_NEWS_MAX_COUNT = 8;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinNewsMaterialInfoManage() {
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

		// 获取素材的_ID
		String _id = ParamKit.getParameter(request, "_id", "").trim();
		WeixinMaterialInfoBean bean = null;
		if (StringKit.isValid(_id)) {
			WeixinMaterialInfoBusiness business = new WeixinMaterialInfoBusiness();
			bean = business.getWeixinMaterialInfoByKey(_id);
		}
		if (bean == null) {
			bean = new WeixinMaterialInfoBean();
			bean.setMediaType(MaterialTypeConfig.MT_NEWS);
			List<ArticleResponseBean> articleList = new ArrayList<ArticleResponseBean>();
			articleList.add(new ArticleResponseBean());
			bean.setArticleList(articleList);
		}

		request.setAttribute("AdminUserHelper", adminUserHelper);
		request.setAttribute("WeixinMaterialInfoBean", bean);
		RequestDispatcher rd = request
				.getRequestDispatcher(WeixinConsoleConfig.PAGE_PATH + "WeixinNewsMaterialInfoManage.jsp");
		rd.forward(request, response);
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
			HttpResponseKit.alertMessage(response, returnMessage.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		String accountCode = helper.getAccountCodeMap().get(accountId);

		// 获取请求参数
		// 从0开始最多获取8个，任意一个的TITLE找不到停止
		List<ArticleInfoBean> articleInfoList = new ArrayList<ArticleInfoBean>();
		ArticleInfoBean articleInfoBean = null;
		for (int i = 0; i < MULTIPLE_NEWS_MAX_COUNT; i++) {
			String title = ParamKit.getParameter(request, "title_" + i, "").trim();
			if (!StringKit.isValid(title))
				break;
			String thumbMediaId = ParamKit.getParameter(request, "thumbMediaId_" + i, "").trim();
			String author = ParamKit.getParameter(request, "author_" + i, "").trim();
			String digest = ParamKit.getParameter(request, "digest_" + i, "").trim();
			int showCoverPic = ParamKit.getIntParameter(request, "showCoverPic_" + i, 0);
			String content = ParamKit.getParameter(request, "content_" + i, "").trim();
			String contentSourceUrl = ParamKit.getParameter(request, "contentSourceUrl_" + i, "").trim();
			articleInfoBean = new ArticleInfoBean();
			articleInfoBean.setTitle(title);
			articleInfoBean.setThumbMediaId(thumbMediaId);
			articleInfoBean.setAuthor(author);
			articleInfoBean.setDigest(digest);
			articleInfoBean.setShowCoverPic(showCoverPic);
			articleInfoBean.setContent(content);
			articleInfoBean.setContentSourceUrl(contentSourceUrl);
			articleInfoList.add(articleInfoBean);
		}
		// 拿到所有ARTICLE后开始请求微信
		AddNewsRequestBean addNewsRequestBean = WeixinRequestFactory.getRequestBean(accountCode,
				AddNewsRequestBean.class);
		addNewsRequestBean.setArticleList(articleInfoList);
		AddNews addNews = new AddNews();
		AddNewsResponseBean addNewsResponseBean = (AddNewsResponseBean) addNews.execute(addNewsRequestBean);
		if (WeixinUtil.isSuccess(addNewsResponseBean)) {
			// 记录到MONGO
			WeixinMaterialInfoBean materialInfoBean = new WeixinMaterialInfoBean();
			materialInfoBean.set_id(WeixinMongoConfig.getMaterilInfoKey(accountId, MaterialTypeConfig.MT_NEWS,
					addNewsResponseBean.getMediaId()));
			materialInfoBean.setAccountId(accountId);
			materialInfoBean.setMediaType(MaterialTypeConfig.MT_NEWS);
			materialInfoBean.setCreateTime((int) (System.currentTimeMillis() / 1000));
			materialInfoBean.setMediaId(addNewsResponseBean.getMediaId());
			// 转换成子类型的列表
			ArticleResponseBean bean = null;
			List<ArticleResponseBean> list = new ArrayList<ArticleResponseBean>();
			for (ArticleInfoBean article : articleInfoList) {
				bean = new ArticleResponseBean();
				BeanKit.copyBean(bean, article);
				list.add(bean);
			}
			materialInfoBean.setArticleList(list);

			WeixinMaterialInfoBusiness materialInfoBusiness = new WeixinMaterialInfoBusiness();
			int result = materialInfoBusiness.addWeixinMaterialInfo(materialInfoBean);
			if (result > 0) {
				returnMessage.setCode(Constants.STATUS_VALID);
				returnMessage.setMessage("操作成功");
				HttpResponseKit.printJson(request, response, returnMessage, "");
				return;
			} else {
				returnMessage.setMessage("操作失败");
				HttpResponseKit.printJson(request, response, returnMessage, "");
				return;
			}
		} else {
			log.error("创建图文素材失败,errMsg:" + addNewsResponseBean.getErrMsg());
			returnMessage.setMessage(addNewsResponseBean.getErrMsg());
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		}
	}

}
