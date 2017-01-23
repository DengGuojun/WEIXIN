package test.com.lpmas.weixin.api.request.menu;

import org.junit.Test;

import com.lpmas.weixin.api.bean.request.menu.GetMenuRequestBean;
import com.lpmas.weixin.api.bean.response.menu.GetMenuResponseBean;
import com.lpmas.weixin.api.request.menu.GetMenu;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestGetMenu {

	@Test
	public void testExecuteWxRequestBaseBean() {
		GetMenuRequestBean requestBean = new GetMenuRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		GetMenu getMenu = new GetMenu();
		GetMenuResponseBean bean = (GetMenuResponseBean)getMenu.execute(requestBean);
		System.out.println(bean.getResultMap());
	}

}
