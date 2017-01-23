package test.com.lpmas.weixin.api.request.menu;

import org.junit.Test;

import com.lpmas.weixin.api.bean.request.menu.DeleteMenuRequestBean;
import com.lpmas.weixin.api.request.menu.DeleteMenu;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestDeleteMenu {

	@Test
	public void testExecute() {
		DeleteMenuRequestBean requestBean = new DeleteMenuRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		DeleteMenu deleteMenu = new DeleteMenu();
		deleteMenu.execute(requestBean);
	}

}
