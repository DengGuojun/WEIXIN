package test.com.lpmas.weixin.api.request.message;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.message.preview.PreviewNewsMessageRequestBean;

public class TestPreviewMessage {
	@Test
	public void testExecute() {

		// PreviewTextMessageRequestBean bean = new
		// PreviewTextMessageRequestBean();
		// bean.setOpenId("adsf");
		// bean.setContent("5566");
		// System.out.println(JsonKit.toJson(bean));
		// ==========
		// PreviewImageMessageRequestBean bean = new
		// PreviewImageMessageRequestBean();
		// bean.setOpenId("123456");
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		// PreviewVoiceMessageRequestBean bean = new
		// PreviewVoiceMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		// PreviewVideoMessageRequestBean bean = new
		// PreviewVideoMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		// PreviewCardMessageRequestBean bean = new
		// PreviewCardMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		// PreviewNewsMessageRequestBean bean = new
		// PreviewNewsMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));

		test("a", "b");
	}

	private void test(String x, Object... str) {
		for (Object s : str)
			System.out.println(s);
	}
}
