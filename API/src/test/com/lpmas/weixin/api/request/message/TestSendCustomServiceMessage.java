package test.com.lpmas.weixin.api.request.message;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.message.cs.SendNewsCustemServerMessageRequestBean;

public class TestSendCustomServiceMessage {
	@Test
	public void testExecute() {
		// SendImageCustemServerMessageRequestBean bean = new
		// SendImageCustemServerMessageRequestBean();
		// bean.setAccout("123456");
		// bean.setMsgType("text");
		//
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		// SendTextCustemServerMessageRequestBean bean = new
		// SendTextCustemServerMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		// SendVoiceCustemServerMessageRequestBean bean = new
		// SendVoiceCustemServerMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		// SendVideoCustemServerMessageRequestBean bean = new
		// SendVideoCustemServerMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		// SendMusicCustemServerMessageRequestBean bean = new
		// SendMusicCustemServerMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		// SendCardCustemServerMessageRequestBean bean = new
		// SendCardCustemServerMessageRequestBean();
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		// SendLinkNewsCustemServerMessageRequestBean bean = new
		// SendLinkNewsCustemServerMessageRequestBean();
		//
		// List<SendArticleInfoBean> articleList = new
		// ArrayList<SendArticleInfoBean>();
		// SendArticleInfoBean articleBean = new SendArticleInfoBean();
		// articleBean.setTitle("abc");
		// articleList.add(articleBean);
		//
		// articleBean.setTitle("123");
		// articleList.add(articleBean);
		//
		// bean.setArticleList(articleList);
		//
		// System.out.println(JsonKit.toJson(bean));

		// ==========
		SendNewsCustemServerMessageRequestBean bean = new SendNewsCustemServerMessageRequestBean();
		System.out.println(JsonKit.toJson(bean));

	}
}
