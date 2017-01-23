package test.com.lpmas.weixin.api.request.user;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.user.UpdateUserRemarkRequestBean;
import com.lpmas.weixin.api.bean.response.user.UpdateUserRemarkResponseBean;
import com.lpmas.weixin.api.request.user.UpdateUserRemark;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestUpdateUserRemark {

	@Test
	public void testExecute() {
		UpdateUserRemarkRequestBean requestBean = new UpdateUserRemarkRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);

		requestBean.setOpenId("o-MDTvsR3RMaTb0zOgpNtDHykDLk");
		requestBean.setRemark("ABC");

		UpdateUserRemark updateUserRemark = new UpdateUserRemark();
		UpdateUserRemarkResponseBean bean = (UpdateUserRemarkResponseBean) updateUserRemark.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
