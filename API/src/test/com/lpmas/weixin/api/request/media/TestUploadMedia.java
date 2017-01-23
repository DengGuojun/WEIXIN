package test.com.lpmas.weixin.api.request.media;

import java.io.File;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.media.UploadMediaRequestBean;
import com.lpmas.weixin.api.bean.response.media.UploadMediaResponseBean;
import com.lpmas.weixin.api.request.media.UploadMedia;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestUploadMedia {

	@Test
	public void testExecute() {
		UploadMediaRequestBean requestBean = new UploadMediaRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setMediaType("image");
		
		File file = new File("F:\\testA\\img\\APP19-151105-1.jpg");
		requestBean.setMediaFile(file);
		
		UploadMedia uploadMedia = new UploadMedia();
		UploadMediaResponseBean bean = (UploadMediaResponseBean)uploadMedia.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
