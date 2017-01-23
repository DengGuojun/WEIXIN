package test.com.lpmas.weixin.api.receive;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiException;
import com.lpmas.weixin.api.bean.receive.message.ReceiveImageBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveLinkBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveLocationBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveShortVideoBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveTextBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveVideoBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveVoiceBean;
import com.lpmas.weixin.api.receive.util.RecevieMessageParser;

public class TestRecevieMessageParser {

	@Test
	public void test() throws ApiException {
		String textMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
		ReceiveTextBean textBean = (ReceiveTextBean) RecevieMessageParser.parse(textMessage);
		System.out.println(JsonKit.toJson(textBean));

		String imageMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[this is a url]]></PicUrl><MediaId><![CDATA[media_id]]></MediaId><MsgId>1234567890123456</MsgId></xml>";
		ReceiveImageBean imageBean = (ReceiveImageBean) RecevieMessageParser.parse(imageMessage);
		System.out.println(JsonKit.toJson(imageBean));

		String voiceMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1357290913</CreateTime><MsgType><![CDATA[voice]]></MsgType><MediaId><![CDATA[media_id]]></MediaId><Format><![CDATA[Format]]></Format><MsgId>1234567890123456</MsgId></xml>";
		ReceiveVoiceBean voiceBean = (ReceiveVoiceBean) RecevieMessageParser.parse(voiceMessage);
		System.out.println(JsonKit.toJson(voiceBean));
		
		String videoMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1357290913</CreateTime><MsgType><![CDATA[video]]></MsgType><MediaId><![CDATA[media_id]]></MediaId><ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId><MsgId>1234567890123456</MsgId></xml>";
		ReceiveVideoBean videoBean = (ReceiveVideoBean) RecevieMessageParser.parse(videoMessage);
		System.out.println(JsonKit.toJson(videoBean));
		
		String shortVideoMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1357290913</CreateTime><MsgType><![CDATA[shortvideo]]></MsgType><MediaId><![CDATA[media_id]]></MediaId><ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId><MsgId>1234567890123456</MsgId></xml>";
		ReceiveShortVideoBean shortVideoBean = (ReceiveShortVideoBean) RecevieMessageParser.parse(shortVideoMessage);
		System.out.println(JsonKit.toJson(shortVideoBean));
		
		String locationMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1351776360</CreateTime><MsgType><![CDATA[location]]></MsgType><Location_X>23.134521</Location_X><Location_Y>113.358803</Location_Y><Scale>20</Scale><Label><![CDATA[位置信息]]></Label><MsgId>1234567890123456</MsgId></xml>";
		ReceiveLocationBean locationBean = (ReceiveLocationBean) RecevieMessageParser.parse(locationMessage);
		System.out.println(JsonKit.toJson(locationBean));

		String linkMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1351776360</CreateTime><MsgType><![CDATA[link]]></MsgType><Title><![CDATA[公众平台官网链接]]></Title><Description><![CDATA[公众平台官网链接]]></Description><Url><![CDATA[url]]></Url><MsgId>1234567890123456</MsgId></xml>";
		ReceiveLinkBean linkBean = (ReceiveLinkBean) RecevieMessageParser.parse(linkMessage);
		System.out.println(JsonKit.toJson(linkBean));
	}
	
}
