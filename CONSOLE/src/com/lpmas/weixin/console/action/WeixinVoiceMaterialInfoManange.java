package com.lpmas.weixin.console.action;

import java.io.File;
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
import com.lpmas.framework.crypto.BASE64;
import com.lpmas.framework.util.FileKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.api.bean.request.material.AddMaterialRequestBean;
import com.lpmas.weixin.api.bean.response.material.AddMaterialResponseBean;
import com.lpmas.weixin.api.config.MaterialTypeConfig;
import com.lpmas.weixin.api.request.material.AddMaterial;
import com.lpmas.weixin.bean.WeixinMaterialInfoBean;
import com.lpmas.weixin.business.WeixinUtil;
import com.lpmas.weixin.config.WeixinMongoConfig;
import com.lpmas.weixin.console.business.WeixinAccountHelper;
import com.lpmas.weixin.console.business.WeixinMaterialInfoBusiness;
import com.lpmas.weixin.console.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.console.config.WeixinConsoleConfig;
import com.lpmas.weixin.console.config.WeixinResource;
import com.lpmas.weixin.console.factory.WeixinRequestFactory;

/**
 * Servlet implementation class WeixinVoiceMaterialInfoManange
 */
@WebServlet("/weixin/WeixinVoiceMaterialInfoManange.do")
public class WeixinVoiceMaterialInfoManange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(WeixinVoiceMaterialInfoManange.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinVoiceMaterialInfoManange() {
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

		// 获取请求参数
		String voiceType = ParamKit.getParameter(request, "voiceType", "").trim();
		String voiceData = ParamKit.getParameter(request, "voiceData", "").trim();
		if (!StringKit.isValid(voiceType) || !StringKit.isValid(voiceData)) {
			returnMessage.setMessage("参数错误");
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		}

		// 获取CCOUNTCODE
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		String accountCode = accountInfoCache.getAccountCodeByKey(accountId);

		// 获取文件后缀名
		String subFixx = "." + voiceType;
		// 设置临时文件名
		String tempFileName = accountCode + "_" + MaterialTypeConfig.MT_VOICE + "_" + System.currentTimeMillis();
		String localFilePath = WeixinConsoleConfig.FILE_PATH + tempFileName + subFixx;
		// 保存临时图片
		byte[] voice = BASE64.decodeBase64(voiceData);

		FileKit.bytes2File(voice, localFilePath);
		WeixinMaterialInfoBean bean = new WeixinMaterialInfoBean();

		try {
			File file = new File(localFilePath); // 获取本地文件

			AddMaterial addMaterial = new AddMaterial();
			AddMaterialRequestBean addMaterialRequestBean = WeixinRequestFactory.getRequestBean(accountCode,
					AddMaterialRequestBean.class);
			addMaterialRequestBean.setMediaFile(file);
			addMaterialRequestBean.setMediaType(MaterialTypeConfig.MT_VOICE);
			AddMaterialResponseBean addMaterialResponseBean = (AddMaterialResponseBean) addMaterial
					.execute(addMaterialRequestBean);
			if (WeixinUtil.isSuccess(addMaterialResponseBean)) {
				bean.setMediaId(addMaterialResponseBean.getMediaId());
			} else {
				log.error("素材创建失败,errMsg:" + addMaterialResponseBean.getErrMsg());
				returnMessage.setMessage(addMaterialResponseBean.getErrMsg());
				HttpResponseKit.printJson(request, response, returnMessage, "");
				return;
			}
			FileKit.deleteFile(localFilePath); // 删除本地文件

			WeixinMaterialInfoBusiness business = new WeixinMaterialInfoBusiness();
			int result = 0;
			if (StringKit.isValid(bean.getMediaId())) {
				bean.set_id(
						WeixinMongoConfig.getMaterilInfoKey(accountId, MaterialTypeConfig.MT_VOICE, bean.getMediaId()));
				bean.setCreateTime((int) (System.currentTimeMillis() / 1000));
				bean.setAccountId(accountId);
				bean.setMediaType(MaterialTypeConfig.MT_VOICE);
				result = business.addWeixinMaterialInfo(bean);

			}
			// 判断上传是否成功
			if (result > 0) {
				// 成功
				returnMessage.setCode(Constants.STATUS_VALID);
				returnMessage.setMessage("上传成功");
			} else {
				// 不成功
				returnMessage.setMessage("上传失败");
			}

			// 返回结果
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		} catch (Exception e) {
			log.error("", e);
			// 返回结果
			returnMessage.setMessage(e.getMessage());
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		}
	}

}
