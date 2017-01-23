package test.com.lpmas.weixin.api.receive;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiException;
import com.lpmas.weixin.api.bean.receive.event.SendPictureFromWeixinEventBean;
import com.lpmas.weixin.api.receive.util.ReceiveEventParser;

public class TestRecevieEventParser {

	@Test
	public void test() throws ApiException {
//		String subscribeMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[qrscene_123123]]></EventKey><Ticket><![CDATA[TICKET]]></Ticket></xml>";
//		SubscribeEventBean subscribeEventBean = toBean(subscribeMessage, SubscribeEventBean.class);
//		System.out.println(JsonKit.toJson(subscribeEventBean));
//
//		String unsubscribeMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[unsubscribe]]></Event></xml>";
//		UnsubscribeEventBean unsubscribeEventBean = toBean(unsubscribeMessage, UnsubscribeEventBean.class);
//		System.out.println(JsonKit.toJson(unsubscribeEventBean));
//
//		String scanMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[SCAN]]></Event><EventKey><![CDATA[SCENE_VALUE]]></EventKey><Ticket><![CDATA[TICKET]]></Ticket></xml>";
//		ScanEventBean scanEventBean = toBean(scanMessage, ScanEventBean.class);
//		System.out.println(JsonKit.toJson(scanEventBean));
//
//		String locationMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[LOCATION]]></Event><Latitude>23.137466</Latitude><Longitude>113.352425</Longitude><Precision>119.385040</Precision></xml>";
//		LocationEventBean locationEventBean = toBean(locationMessage, LocationEventBean.class);
//		System.out.println(JsonKit.toJson(locationEventBean));
//
//		String clickMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event><EventKey><![CDATA[EVENTKEY]]></EventKey></xml>";
//		ClickEventBean clickEventBean = toBean(clickMessage, ClickEventBean.class);
//		System.out.println(JsonKit.toJson(clickEventBean));
//
//		String viewMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[VIEW]]></Event><EventKey><![CDATA[www.qq.com]]></EventKey></xml>";
//		ViewEventBean viewEventBean = toBean(viewMessage, ViewEventBean.class);
//		System.out.println(JsonKit.toJson(viewEventBean));
//
//		String sendMessageFinishMessage = "<xml><ToUserName><![CDATA[gh_3e8adccde292]]></ToUserName><FromUserName><![CDATA[oR5Gjjl_eiZoUpGozMo7dbBJ362A]]></FromUserName><CreateTime>1394524295</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[MASSSENDJOBFINISH]]></Event><MsgID>1988</MsgID><Status><![CDATA[sendsuccess]]></Status><TotalCount>100</TotalCount><FilterCount>80</FilterCount><SentCount>75</SentCount><ErrorCount>5</ErrorCount></xml>";
//		SendMessageJobFinishBean sendMessageJobFinishBean = toBean(sendMessageFinishMessage,
//				SendMessageJobFinishBean.class);
//		System.out.println(JsonKit.toJson(sendMessageJobFinishBean));
//
//		String scanCodePushEventMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>1408090606</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[scancode_waitmsg]]></Event><EventKey><![CDATA[6]]></EventKey><ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType><ScanResult><![CDATA[2]]></ScanResult></ScanCodeInfo><AgentID>1</AgentID></xml>";
//		ScanCodeWaitMessageEventBean scanCodePushEventBean = toBean(scanCodePushEventMessage,
//				ScanCodeWaitMessageEventBean.class);
//		System.out.println(JsonKit.toJson(scanCodePushEventBean));

//		String sendPictureFromCameraEventMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>1408090651</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[pic_sysphoto]]></Event><EventKey><![CDATA[6]]></EventKey><SendPicsInfo><Count>1</Count><PicList><item><PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum></item></PicList></SendPicsInfo><AgentID>1</AgentID></xml>";
//		SendPictureFromCameraEventBean postPictureFromCameraEventBean = toBean(sendPictureFromCameraEventMessage,
//				SendPictureFromCameraEventBean.class);
//		System.out.println(JsonKit.toJson(postPictureFromCameraEventBean));
//
//		String sendPictureFromAlbumEventMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>1408090816</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[pic_photo_or_album]]></Event><EventKey><![CDATA[6]]></EventKey><SendPicsInfo><Count>1</Count><PicList><item><PicMd5Sum><![CDATA[5a75aaca956d97be686719218f275c6b]]></PicMd5Sum></item></PicList></SendPicsInfo><AgentID>1</AgentID></xml>";
//		SendPictureFromAlbumEventBean sendPictureFromAlbumEventBean = toBean(sendPictureFromAlbumEventMessage,
//				SendPictureFromAlbumEventBean.class);
//		System.out.println(JsonKit.toJson(sendPictureFromAlbumEventBean));

		String sendPictureFromWeixinEventMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>1408090816</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[pic_weixin]]></Event><EventKey><![CDATA[6]]></EventKey><SendPicsInfo><Count>1</Count><PicList><item><PicMd5Sum><![CDATA[5a75aaca956d97be686719218f275c6b]]></PicMd5Sum></item></PicList></SendPicsInfo><AgentID>1</AgentID></xml>";
		SendPictureFromWeixinEventBean sendPictureFromWeixinEventBean = toBean(sendPictureFromWeixinEventMessage,
				SendPictureFromWeixinEventBean.class);
		System.out.println(JsonKit.toJson(sendPictureFromWeixinEventBean));
		System.out.println(ReceiveEventParser.getEventType(sendPictureFromWeixinEventMessage));

//		String locationSelectEventMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>1408091189</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[location_select]]></Event><EventKey><![CDATA[6]]></EventKey><SendLocationInfo><Location_X><![CDATA[23]]></Location_X><Location_Y><![CDATA[113]]></Location_Y><Scale><![CDATA[15]]></Scale><Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label><Poiname><![CDATA[]]></Poiname></SendLocationInfo><AgentID>1</AgentID></xml>";
//		LocationSelectEventBean locationSelectEventBean = toBean(locationSelectEventMessage,
//				LocationSelectEventBean.class);
//		System.out.println(JsonKit.toJson(locationSelectEventBean));
	}

	public static <T> T toBean(String content, Class<T> clazz) {
		XmlMapper xmlMapper = new XmlMapper();
		T bean = null;
		try {
			bean = xmlMapper.readValue(content, clazz);
			JsonNode node = xmlMapper.readTree(content);
			String text = node.findValue("SendPicsInfo").findValue("Count").asText();
			System.out.println(text);
			
			List<String> list = node.findValue("SendPicsInfo").findValuesAsText("PicList");
			System.out.println(list);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
}
