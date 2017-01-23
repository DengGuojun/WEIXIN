package test.com.lpmas.weixin.api.request.media;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.media.GetMediaRequestBean;
import com.lpmas.weixin.api.bean.response.media.GetMediaResponseBean;
import com.lpmas.weixin.api.request.media.GetMedia;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestGetMedia {

	@Test
	public void testExecute() {
		GetMediaRequestBean requestBean = new GetMediaRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setMediaId("GVY5f3X1a0mSZ0SrFbtLGxwV6KLeAMzUOSVt0dBQbEfrSpjrPaLob3rGlbc8V26l");
		requestBean.setMediaId("aaaa");
		
		String filePath = "F:\\testA\\";
		String fileName = "abc.jpg";
		requestBean.setFilePath(filePath);
		requestBean.setFileName(fileName);
		
		GetMedia getMedia = new GetMedia();
		GetMediaResponseBean bean = (GetMediaResponseBean)getMedia.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
