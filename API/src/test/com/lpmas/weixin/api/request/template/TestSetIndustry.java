package test.com.lpmas.weixin.api.request.template;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.template.SetIndustryRequestBean;
import com.lpmas.weixin.api.bean.response.template.SetIndustryResponseBean;
import com.lpmas.weixin.api.request.template.SetIndustry;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestSetIndustry {

	@Test
	public void testExecute() {
		SetIndustryRequestBean requestBean = new SetIndustryRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		requestBean.setIndustryId1("1");
		requestBean.setIndustryId2("31");
		
		SetIndustry setIndustry = new SetIndustry();
		SetIndustryResponseBean bean = (SetIndustryResponseBean)setIndustry.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
