package test.com.lpmas.weixin.api.request.cs;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.cs.AddAccountRequestBean;
import com.lpmas.weixin.api.bean.response.cs.AddAccountResponseBean;
import com.lpmas.weixin.api.request.cs.AddAccount;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestAddAccount {

	@Test
	public void testExecute() {
		AddAccountRequestBean requestBean = new AddAccountRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);

		requestBean.setAccount("CS001@DMZL-LP");
		requestBean.setNickName("客服001");
		requestBean.setPassword("123456");

		AddAccount addAccount = new AddAccount();
		AddAccountResponseBean bean = (AddAccountResponseBean)addAccount.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
