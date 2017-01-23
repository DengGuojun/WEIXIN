package test.com.lpmas.weixin.api.request.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.menu.CreateConditionalMenuRequestBean;
import com.lpmas.weixin.api.bean.request.menu.MenuConditionalRuleBean;

public class TestCreateConditionalMenu {
	@Test
	public void testExecute() {
		CreateConditionalMenuRequestBean requestBean = new CreateConditionalMenuRequestBean();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		requestBean.setMenuContent(list);
		
		MenuConditionalRuleBean ruleBean = new MenuConditionalRuleBean();
		requestBean.setRuleContent(ruleBean);
		
		System.out.println(JsonKit.toJson(requestBean));
	}
}
