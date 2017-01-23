package test.com.lpmas.weixin.api.request.cgi;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.cgi.GetCallbackIpRequestBean;
import com.lpmas.weixin.api.bean.response.cgi.GetCallbackIpResponseBean;
import com.lpmas.weixin.api.request.cgi.GetCallbackIp;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestGetCallbackIp {

	@Test
	public void testExecute() {
		GetCallbackIpRequestBean requestBean = new GetCallbackIpRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		
		GetCallbackIp getCallbackIp = new GetCallbackIp();
		GetCallbackIpResponseBean bean=(GetCallbackIpResponseBean)getCallbackIp.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}

}
