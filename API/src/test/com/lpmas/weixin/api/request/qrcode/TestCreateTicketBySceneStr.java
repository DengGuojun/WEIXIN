package test.com.lpmas.weixin.api.request.qrcode;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.qrcode.CreateTicketBySceneStrRequestBean;
import com.lpmas.weixin.api.bean.response.qrcode.CreateTicketResponseBean;
import com.lpmas.weixin.api.request.qrcode.CreateTicketBySceneStr;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestCreateTicketBySceneStr {

	@Test
	public void testExecute() {
		CreateTicketBySceneStrRequestBean requestBean = new CreateTicketBySceneStrRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setSceneStr("abc");
		
		CreateTicketBySceneStr createTicketBySceneStr = new CreateTicketBySceneStr();
		CreateTicketResponseBean bean = (CreateTicketResponseBean) createTicketBySceneStr.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
