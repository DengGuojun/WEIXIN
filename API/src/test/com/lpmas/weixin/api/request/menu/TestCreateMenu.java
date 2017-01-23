package test.com.lpmas.weixin.api.request.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.menu.CreateMenuRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.request.menu.CreateMenu;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestCreateMenu {

	@Test
	public void testExecute() {
		CreateMenuRequestBean requestBean = new CreateMenuRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);

		Map<String, List<Map<String, Object>>> rootMap = new HashMap<String, List<Map<String, Object>>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "view");
		map.put("name", "menu1");
		map.put("url", "http://www.lpmas.com");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		
		rootMap.put("button", list);
		requestBean.setMenuContent(JsonKit.toJson(rootMap));

		CreateMenu createMenu = new CreateMenu();
		WxResponseBaseBean bean = createMenu.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
