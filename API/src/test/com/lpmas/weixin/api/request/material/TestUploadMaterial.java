package test.com.lpmas.weixin.api.request.material;

import java.io.File;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.material.AddMaterialRequestBean;
import com.lpmas.weixin.api.bean.response.material.GetMaterialCountResponseBean;
import com.lpmas.weixin.api.request.material.AddMaterial;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestUploadMaterial {

	@Test
	public void testExecute() {
		AddMaterialRequestBean requestBean = new AddMaterialRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setFileTitle("图片1");
		
		
		File file = new File("F:\\testA\\img\\APP19-151105-1.jpg");
		requestBean.setMediaFile(file);

		AddMaterial getMaterialCount = new AddMaterial();
		GetMaterialCountResponseBean bean = (GetMaterialCountResponseBean) getMaterialCount.execute(requestBean);

		System.out.println(JsonKit.toJson(bean));
	}

}
