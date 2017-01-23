package test.com.lpmas.weixin.api.request.group;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.group.CreateGroupRequestBean;
import com.lpmas.weixin.api.bean.response.group.CreateGroupResponseBean;
import com.lpmas.weixin.api.request.group.CreateGroup;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestCreateGroup {

	@Test
	public void testExecute() {
		CreateGroupRequestBean requestBean = new CreateGroupRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setGroupName("新分组1");
		
		CreateGroup createGroup = new CreateGroup();
		CreateGroupResponseBean bean = (CreateGroupResponseBean)createGroup.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
