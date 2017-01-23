package test.com.lpmas.weixin.api.request.material;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.material.GetMaterialCountRequestBean;
import com.lpmas.weixin.api.bean.response.material.GetMaterialCountResponseBean;
import com.lpmas.weixin.api.request.material.GetMaterialCount;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestGetMaterialCount {

	@Test
	public void testExecute() {
		GetMaterialCountRequestBean requestBean = new GetMaterialCountRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);

		GetMaterialCount getMaterialCount = new GetMaterialCount();
		GetMaterialCountResponseBean bean = (GetMaterialCountResponseBean) getMaterialCount.execute(requestBean);

		System.out.println(JsonKit.toJson(bean));
	}

}
