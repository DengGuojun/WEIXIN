package test.com.lpmas.weixin.api.request.cgi;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.bean.request.cgi.ShortUrlRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.cgi.ShortUrlResponseBean;
import com.lpmas.weixin.api.request.cgi.ShortUrl;

import test.com.lpmas.weixin.api.config.TestAccessToken;

public class TestShortUrl {

	//@Test
	public void testExecute() {
		String longUrl = "http://bbs.csdn.net/topics/390457094";
		ShortUrlRequestBean requestBean = new ShortUrlRequestBean();
		requestBean.setAccessToken(TestAccessToken.ACCESS_TOKEN);
		requestBean.setLongUrl(longUrl);
		
		ShortUrl shortUrl = new ShortUrl();
		WxResponseBaseBean bean = shortUrl.execute(requestBean);
		System.out.println(JsonKit.toJson(bean));
	}
	
	@Test
	public void test(){
		//String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"short_url\":\"aaaaaa\"}";
		String json = "{\"errcode\":40013,\"errmsg\":\"invalid appid\"}";
		ShortUrlResponseBean bean = JsonKit.toBean(json, ShortUrlResponseBean.class);
		System.out.println(JsonKit.toJson(bean));
	}

}
