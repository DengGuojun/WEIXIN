package test.com.lpmas.weixin.api.request.media;

import java.io.File;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.media.UploadImageRequestBean;
import com.lpmas.weixin.api.bean.response.media.UploadImageResponseBean;
import com.lpmas.weixin.api.request.media.UploadImage;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestUploadImage {

	@Test
	public void testExecute() {
		UploadImageRequestBean requestBean = new UploadImageRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);				
		
		File file = new File("F:\\testA\\img\\APP19-151105-1.jpg");
		requestBean.setMediaFile(file);

		UploadImage uploadImage = new UploadImage();
		UploadImageResponseBean bean = (UploadImageResponseBean) uploadImage.execute(requestBean);

		System.out.println(JsonKit.toJson(bean));
	}

}
