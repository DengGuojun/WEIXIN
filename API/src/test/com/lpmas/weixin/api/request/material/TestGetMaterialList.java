package test.com.lpmas.weixin.api.request.material;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.material.MaterialInfoBean;
import com.lpmas.weixin.api.bean.request.material.GetMaterialListRequestBean;
import com.lpmas.weixin.api.bean.response.material.GetMaterialListResponseBean;
import com.lpmas.weixin.api.config.MaterialTypeConfig;
import com.lpmas.weixin.api.request.material.GetMaterialList;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestGetMaterialList {

	@Test
	public void testExecute() {
		GetMaterialListRequestBean requestBean = new GetMaterialListRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setMediaType(MaterialTypeConfig.MT_IMAGE);
		requestBean.setCount(20);
		requestBean.setOffset(0);
		
		GetMaterialList getMaterialList = new GetMaterialList();
		GetMaterialListResponseBean<MaterialInfoBean> bean = (GetMaterialListResponseBean<MaterialInfoBean>) getMaterialList.execute(requestBean);

		System.out.println(JsonKit.toJson(bean));
	}

}
