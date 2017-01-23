package test.com.lpmas.weixin.api.request.user;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.user.GetUserInfoRequestBean;
import com.lpmas.weixin.api.bean.response.user.GetUserInfoResponseBean;
import com.lpmas.weixin.api.request.user.GetUserInfo;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestGetUserInfo {

	@Test
	public void testExecute() {
		GetUserInfoRequestBean requestBean = new GetUserInfoRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);

		requestBean.setOpenId("o-MDTvsR3RMaTb0zOgpNtDHykDLk");

		GetUserInfo getUserInfo = new GetUserInfo();
		GetUserInfoResponseBean bean = (GetUserInfoResponseBean) getUserInfo.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
