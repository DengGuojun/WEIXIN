package test.com.lpmas.weixin.api.request.group;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.group.UpdateBatchMemberToGroupRequestBean;
import com.lpmas.weixin.api.bean.response.group.UpdateBatchMemberToGroupResponseBean;
import com.lpmas.weixin.api.request.group.UpdateBatchMemberToGroup;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestUpdateBatchMemberToGroup {

	@Test
	public void testExecute() {
		UpdateBatchMemberToGroupRequestBean requestBean = new UpdateBatchMemberToGroupRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);

		List<String> openIdList = new ArrayList<String>();
		openIdList.add("o-MDTvsR3RMaTb0zOgpNtDHykDLk");
		requestBean.setOpenIdList(openIdList);
		requestBean.setToGroupId(100);

		UpdateBatchMemberToGroup updateBatchMemberToGroup = new UpdateBatchMemberToGroup();
		UpdateBatchMemberToGroupResponseBean bean = (UpdateBatchMemberToGroupResponseBean) updateBatchMemberToGroup
				.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
