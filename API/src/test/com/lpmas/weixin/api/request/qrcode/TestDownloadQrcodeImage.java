package test.com.lpmas.weixin.api.request.qrcode;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.qrcode.DownloadQrcodeImageRequestBean;
import com.lpmas.weixin.api.bean.response.qrcode.DownloadQrcodeImageResponseBean;
import com.lpmas.weixin.api.request.qrcode.DownloadQrcodeImage;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestDownloadQrcodeImage {

	@Test
	public void testExecute() {
		DownloadQrcodeImageRequestBean requestBean = new DownloadQrcodeImageRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setTicket("gQFu7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3BFVlE5RWZrdjFURzVyaXd1Mms4AAIEMl57VgMEAAAAAA==");
		requestBean.setFilePath("F:/testA");
		requestBean.setFileName("qrcode.jpg");
		
		DownloadQrcodeImage downloadQrcodeImage = new DownloadQrcodeImage();
		DownloadQrcodeImageResponseBean bean = (DownloadQrcodeImageResponseBean) downloadQrcodeImage.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
