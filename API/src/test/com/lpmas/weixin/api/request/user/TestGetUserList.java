package test.com.lpmas.weixin.api.request.user;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.user.GetUserListRequestBean;
import com.lpmas.weixin.api.bean.response.user.GetUserListResponseBean;
import com.lpmas.weixin.api.request.user.GetUserList;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestGetUserList {

	@Test
	public void testExecute() {
		GetUserListRequestBean requestBean = new GetUserListRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setNextOpenId("");
		
		GetUserList getUserList = new GetUserList();
		GetUserListResponseBean bean = (GetUserListResponseBean)getUserList.execute(requestBean);
		System.out.println(JsonKit.toJson(bean.getOpenIdList()));
	}

}
