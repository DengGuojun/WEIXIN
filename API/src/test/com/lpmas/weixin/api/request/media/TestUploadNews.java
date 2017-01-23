package test.com.lpmas.weixin.api.request.media;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.material.ArticleInfoBean;
import com.lpmas.weixin.api.bean.request.media.UploadNewsRequestBean;

public class TestUploadNews {
	@Test
	public void testExecute() {
		UploadNewsRequestBean requestBean = new UploadNewsRequestBean();

		List<ArticleInfoBean> articleList = new ArrayList<ArticleInfoBean>();
		ArticleInfoBean bean = new ArticleInfoBean();
		bean.setAuthor("abc");
		articleList.add(bean);

		requestBean.setArticleList(articleList);

		System.out.println(JsonKit.toJson(requestBean));
	}
}
