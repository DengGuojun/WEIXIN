package test.com.lpmas.weixin.api.request.group;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.group.GetGroupIdByOpenIdRequestBean;
import com.lpmas.weixin.api.bean.response.group.GetGroupIdByOpenIdResponseBean;
import com.lpmas.weixin.api.request.group.GetGroupIdByOpenId;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestGetGroupIdByOpenId {

	@Test
	public void testExecute() {
		GetGroupIdByOpenIdRequestBean requestBean = new GetGroupIdByOpenIdRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setOpenId("o-MDTvsR3RMaTb0zOgpNtDHykDLk");
		
		GetGroupIdByOpenId getGroupIdByOpenId = new GetGroupIdByOpenId();
		GetGroupIdByOpenIdResponseBean bean = (GetGroupIdByOpenIdResponseBean)getGroupIdByOpenId.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
