package test.com.lpmas.weixin.api.request.menu;

import org.junit.Test;

import com.lpmas.weixin.api.bean.request.menu.GetCurrentSelfMenuInfoRequestBean;
import com.lpmas.weixin.api.bean.response.menu.GetCurrentSelfMenuInfoResponseBean;
import com.lpmas.weixin.api.request.menu.GetCurrentSelfMenuInfo;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestGetCurrentSelfMenuInfo {

	@Test
	public void testExecute() {
		GetCurrentSelfMenuInfoRequestBean requestBean = new GetCurrentSelfMenuInfoRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		GetCurrentSelfMenuInfo getCurrentSelfMenuInfo = new GetCurrentSelfMenuInfo();
		GetCurrentSelfMenuInfoResponseBean bean =(GetCurrentSelfMenuInfoResponseBean) getCurrentSelfMenuInfo.execute(requestBean);
		System.out.println(bean.getResultMap());
	}

}
