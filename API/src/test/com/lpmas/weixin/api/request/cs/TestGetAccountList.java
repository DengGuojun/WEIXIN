package test.com.lpmas.weixin.api.request.cs;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.cs.GetAccountListRequestBean;
import com.lpmas.weixin.api.bean.response.cs.GetAccountListResponseBean;
import com.lpmas.weixin.api.request.cs.GetAccountList;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestGetAccountList {

	@Test
	public void testExcute() {
		GetAccountListRequestBean requestBean = new GetAccountListRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);

		GetAccountList getAccountList = new GetAccountList();
		GetAccountListResponseBean bean = (GetAccountListResponseBean) getAccountList.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
