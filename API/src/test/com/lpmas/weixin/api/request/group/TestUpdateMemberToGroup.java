package test.com.lpmas.weixin.api.request.group;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.group.UpdateMemberToGroupRequestBean;
import com.lpmas.weixin.api.bean.response.group.UpdateMemberToGroupResponseBean;
import com.lpmas.weixin.api.request.group.UpdateMemberToGroup;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestUpdateMemberToGroup {

	@Test
	public void testExecute() {
		UpdateMemberToGroupRequestBean requestBean = new UpdateMemberToGroupRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setOpenId("o-MDTvsR3RMaTb0zOgpNtDHykDLk");
		requestBean.setToGroupId(100);
		
		UpdateMemberToGroup updateMemberToGroup = new UpdateMemberToGroup();
		UpdateMemberToGroupResponseBean bean = (UpdateMemberToGroupResponseBean)updateMemberToGroup.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
