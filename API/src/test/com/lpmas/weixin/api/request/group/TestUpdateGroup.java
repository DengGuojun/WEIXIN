package test.com.lpmas.weixin.api.request.group;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.group.UpdateGroupRequestBean;
import com.lpmas.weixin.api.bean.response.group.UpdateGroupResponseBean;
import com.lpmas.weixin.api.request.group.UpdateGroup;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestUpdateGroup {

	@Test
	public void testExecute() {
		UpdateGroupRequestBean requestBean = new UpdateGroupRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setGroupId(100);
		requestBean.setGroupName("新分组2");
		
		UpdateGroup updateGroup = new UpdateGroup();
		UpdateGroupResponseBean bean = (UpdateGroupResponseBean)updateGroup.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
