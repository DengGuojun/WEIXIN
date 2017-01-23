package test.com.lpmas.weixin.api.request.group;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.group.GetGroupListRequestBean;
import com.lpmas.weixin.api.bean.response.group.GetGroupListResponseBean;
import com.lpmas.weixin.api.request.group.GetGroupList;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestGetGroupList {

	@Test
	public void test() {
		GetGroupListRequestBean requestBean = new GetGroupListRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		GetGroupList getGroupList = new GetGroupList();
		GetGroupListResponseBean bean = (GetGroupListResponseBean)getGroupList.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
