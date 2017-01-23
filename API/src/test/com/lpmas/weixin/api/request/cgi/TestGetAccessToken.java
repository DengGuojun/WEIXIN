package test.com.lpmas.weixin.api.request.cgi;

import org.junit.Test;

import com.lpmas.weixin.api.bean.request.cgi.GetAccessTokenRequestBean;
import com.lpmas.weixin.api.bean.response.cgi.GetAccessTokenResponseBean;
import com.lpmas.weixin.api.request.cgi.GetAccessToken;

public class TestGetAccessToken {
	private String appId = "wx2d091d1e1b0c824d";
	private String appSecret = "e78b32b33c3ff12ecbaa4be7c44820c0";

	@Test
	public void testExecute() {
		GetAccessTokenRequestBean requestBean = new GetAccessTokenRequestBean();
		requestBean.setAppId(appId);
		requestBean.setAppSecret(appSecret);

		GetAccessToken getAccessToken = new GetAccessToken();
		GetAccessTokenResponseBean bean = (GetAccessTokenResponseBean) getAccessToken.execute(requestBean);
		System.out.println(bean.getAccessToken());
	}

}
