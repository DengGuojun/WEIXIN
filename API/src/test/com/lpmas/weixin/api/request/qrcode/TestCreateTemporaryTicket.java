package test.com.lpmas.weixin.api.request.qrcode;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.qrcode.CreateTemporaryTicketRequestBean;
import com.lpmas.weixin.api.bean.response.qrcode.CreateTicketResponseBean;
import com.lpmas.weixin.api.request.qrcode.CreateTemporaryTicket;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestCreateTemporaryTicket {

	@Test
	public void testExecute() {
		CreateTemporaryTicketRequestBean requestBean = new CreateTemporaryTicketRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);

		requestBean.setExpireSeconds(60 * 60 * 1000);
		requestBean.setSceneId(12345678);
		
		CreateTemporaryTicket createTemporaryTicket = new CreateTemporaryTicket();
		CreateTicketResponseBean bean = (CreateTicketResponseBean) createTemporaryTicket.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
