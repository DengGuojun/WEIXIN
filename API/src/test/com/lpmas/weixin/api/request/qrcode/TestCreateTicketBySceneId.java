package test.com.lpmas.weixin.api.request.qrcode;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.qrcode.CreateTicketBySceneIdRequestBean;
import com.lpmas.weixin.api.bean.response.qrcode.CreateTicketResponseBean;
import com.lpmas.weixin.api.request.qrcode.CreateTicketBySceneId;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestCreateTicketBySceneId {

	@Test
	public void testExecute() {
		CreateTicketBySceneIdRequestBean requestBean = new CreateTicketBySceneIdRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setSceneId(123);
		
		CreateTicketBySceneId createTicketBySceneId = new CreateTicketBySceneId();
		CreateTicketResponseBean bean = (CreateTicketResponseBean) createTicketBySceneId.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
