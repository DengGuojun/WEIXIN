package test.com.lpmas.weixin.api.request.message;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.message.user.SendNewsMessageRequestBean;

public class TestSendMessage {
	@Test
	public void testExecute() {
//		SendTextMessageRequestBean bean = new SendTextMessageRequestBean();
//		bean.setOpenId("9988");
//		bean.setOpenId("6655");
//		System.out.println(JsonKit.toJson(bean));
		// ==========
//		SendImageMessageRequestBean bean = new SendImageMessageRequestBean();
//		System.out.println(JsonKit.toJson(bean));

		// ==========
//		SendVoiceMessageRequestBean bean = new SendVoiceMessageRequestBean();
//		System.out.println(JsonKit.toJson(bean));

		// ==========
//		SendVideoMessageRequestBean bean = new SendVideoMessageRequestBean();
//		System.out.println(JsonKit.toJson(bean));

		// ==========
		// SendCardMessageRequestBean bean = new SendCardMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		SendNewsMessageRequestBean bean = new SendNewsMessageRequestBean();
		System.out.println(JsonKit.toJson(bean));
	}
}
