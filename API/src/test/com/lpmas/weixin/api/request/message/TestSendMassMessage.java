package test.com.lpmas.weixin.api.request.message;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.message.mass.SendCardMassMessageRequestBean;

public class TestSendMassMessage {
	@Test
	public void testExecute() {
		// SendTextMassMessageRequestBean bean = new
		// SendTextMassMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));
		// ==========
//		SendImageMassMessageRequestBean bean = new SendImageMassMessageRequestBean();
//		System.out.println(JsonKit.toJson(bean));

		// ==========
//		SendVoiceMassMessageRequestBean bean = new SendVoiceMassMessageRequestBean();
//		System.out.println(JsonKit.toJson(bean));

		// ==========
		// SendVideoMassMessageRequestBean bean = new
		// SendVideoMassMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		SendCardMassMessageRequestBean bean = new SendCardMassMessageRequestBean();
		System.out.println(JsonKit.toJson(bean));

		// ==========
		// SendNewsMassMessageRequestBean bean = new
		// SendNewsMassMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));
	}
}
