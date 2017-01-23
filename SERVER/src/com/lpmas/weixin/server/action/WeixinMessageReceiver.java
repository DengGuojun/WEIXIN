package com.lpmas.weixin.server.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.util.XmlKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.api.base.ApiException;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.bean.receive.MessageBaseBean;
import com.lpmas.weixin.api.bean.receive.event.ClickEventBean;
import com.lpmas.weixin.api.bean.receive.event.LocationEventBean;
import com.lpmas.weixin.api.bean.receive.event.LocationSelectEventBean;
import com.lpmas.weixin.api.bean.receive.event.ScanCodePushEventBean;
import com.lpmas.weixin.api.bean.receive.event.ScanCodeWaitMessageEventBean;
import com.lpmas.weixin.api.bean.receive.event.ScanEventBean;
import com.lpmas.weixin.api.bean.receive.event.SendMessageJobFinishBean;
import com.lpmas.weixin.api.bean.receive.event.SendPictureFromAlbumEventBean;
import com.lpmas.weixin.api.bean.receive.event.SendPictureFromCameraEventBean;
import com.lpmas.weixin.api.bean.receive.event.SendPictureFromWeixinEventBean;
import com.lpmas.weixin.api.bean.receive.event.SendTemplateJobFinishBean;
import com.lpmas.weixin.api.bean.receive.event.SubscribeEventBean;
import com.lpmas.weixin.api.bean.receive.event.UnsubscribeEventBean;
import com.lpmas.weixin.api.bean.receive.event.ViewEventBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveImageBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveLinkBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveLocationBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveShortVideoBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveTextBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveVideoBean;
import com.lpmas.weixin.api.bean.receive.message.ReceiveVoiceBean;
import com.lpmas.weixin.api.config.ReceiveEventConfig;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;
import com.lpmas.weixin.api.receive.ReceiveMessageProcessor;
import com.lpmas.weixin.api.receive.util.ReceiveEventParser;
import com.lpmas.weixin.api.receive.util.RecevieMessageParser;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.business.WeixinConfigUtil;
import com.lpmas.weixin.server.business.AutoReplyMessageProcessor;
import com.lpmas.weixin.server.business.WeixinReceiveEventBusiness;
import com.lpmas.weixin.server.business.WeixinReceiveMessageBusiness;
import com.lpmas.weixin.server.business.WeixinReceiveDispatcher;
import com.lpmas.weixin.server.business.WeixinUserInfoBusiness;
import com.lpmas.weixin.server.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.server.cache.WeixinConfigCache;

@WebServlet("/weixin/ReceiveMessage.do")
public class WeixinMessageReceiver extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(WeixinMessageReceiver.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinMessageReceiver() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String signature = ParamKit.getParameter(request, "signature", "");
		String timestamp = ParamKit.getParameter(request, "timestamp", "");
		String nonce = ParamKit.getParameter(request, "nonce", "");
		String echostr = ParamKit.getParameter(request, "echostr", "");

		// 获取加密串
		String id = ParamKit.getParameter(request, "id", "");
		if (!StringKit.isValid(id)) {
			log.error("无法获取微信加密串");
			return;
		}
		// 获取APPCODE和STORECODE
		WeixinConfigUtil helper = new WeixinConfigUtil(id);
		String accountCode = helper.getAccountCode();

		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);

		WeixinConfigCache configCache = new WeixinConfigCache();
		boolean result = false;
		WeixinConfigBean bean = configCache.getWeixinConfigBean(accountId);
		ReceiveMessageProcessor processor = new ReceiveMessageProcessor();
		try {
			result = processor.checkSignature(bean.getToken(), timestamp, nonce, signature);
		} catch (ApiException e) {
			log.error("微信接入失败" + e.getMessage());
		}
		if (!result) {
			echostr = String.valueOf(Constants.STATUS_NOT_VALID);
		}
		// 接入成功，原样返回echostr参数内容
		HttpResponseKit.printMessage(response, echostr);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String signature = ParamKit.getParameter(request, "signature", "");
		String timestamp = ParamKit.getParameter(request, "timestamp", "");
		String nonce = ParamKit.getParameter(request, "nonce", "");

		// 获取加密串
		String id = ParamKit.getParameter(request, "id", "");
		if (!StringKit.isValid(id)) {
			log.error("无法获取微信加密串");
			return;
		}
		// 获取APPCODE和STORECODE
		WeixinConfigUtil helper = new WeixinConfigUtil(id);
		String accountCode = helper.getAccountCode();

		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);

		EventBaseBean eventBean = null;
		MessageBaseBean messageBean = null;
		WeixinConfigCache configCache = new WeixinConfigCache();
		WeixinConfigBean appConfigBean = configCache.getWeixinConfigBean(accountId);
		ReceiveMessageProcessor processor = new ReceiveMessageProcessor();
		WeixinReceiveDispatcher dispatcher = new WeixinReceiveDispatcher();
		ReturnMessageBean returnMessageBean = new ReturnMessageBean();
		try {
			// 校验消息
			if (processor.checkSignature(appConfigBean.getToken(), timestamp, nonce, signature)) {
				String message = ParamKit.getInputStream(request);

				int result = 0;
				WeixinReceiveMessageBusiness messageBusiness = new WeixinReceiveMessageBusiness();
				String msgType = RecevieMessageParser.getMessageType(message);
				// 消息处理
				if (ReceiveMessageTypeConfig.RMT_TEXT.equalsIgnoreCase(msgType)) {
					// 文本消息
					messageBean = XmlKit.toBean(message, ReceiveTextBean.class);
					returnMessageBean = AutoReplyMessageProcessor.process(accountCode, messageBean);
					result = messageBusiness.addReceiveMessageBean(accountCode, messageBean);
					dispatcher.doDispatch(accountCode, messageBean);
				} else if (ReceiveMessageTypeConfig.RMT_IMAGE.equalsIgnoreCase(msgType)) {
					// 图片消息
					messageBean = XmlKit.toBean(message, ReceiveImageBean.class);
					returnMessageBean = AutoReplyMessageProcessor.process(accountCode, messageBean);
					result = messageBusiness.addReceiveMessageBean(accountCode, messageBean);
					dispatcher.doDispatch(accountCode, messageBean);
				} else if (ReceiveMessageTypeConfig.RMT_LINK.equalsIgnoreCase(msgType)) {
					// 链接消息
					messageBean = XmlKit.toBean(message, ReceiveLinkBean.class);
					returnMessageBean = AutoReplyMessageProcessor.process(accountCode, messageBean);
					result = messageBusiness.addReceiveMessageBean(accountCode, messageBean);
					dispatcher.doDispatch(accountCode, messageBean);
				} else if (ReceiveMessageTypeConfig.RMT_VOICE.equalsIgnoreCase(msgType)) {
					// 音频消息
					messageBean = XmlKit.toBean(message, ReceiveVoiceBean.class);
					returnMessageBean = AutoReplyMessageProcessor.process(accountCode, messageBean);
					result = messageBusiness.addReceiveMessageBean(accountCode, messageBean);
					dispatcher.doDispatch(accountCode, messageBean);
				} else if (ReceiveMessageTypeConfig.RMT_VIDEO.equalsIgnoreCase(msgType)) {
					// 视频消息
					messageBean = XmlKit.toBean(message, ReceiveVideoBean.class);
					returnMessageBean = AutoReplyMessageProcessor.process(accountCode, messageBean);
					result = messageBusiness.addReceiveMessageBean(accountCode, messageBean);
					dispatcher.doDispatch(accountCode, messageBean);
				} else if (ReceiveMessageTypeConfig.RMT_SHORT_VIDEO.equalsIgnoreCase(msgType)) {
					// 小视频消息
					messageBean = XmlKit.toBean(message, ReceiveShortVideoBean.class);
					returnMessageBean = AutoReplyMessageProcessor.process(accountCode, messageBean);
					result = messageBusiness.addReceiveMessageBean(accountCode, messageBean);
					dispatcher.doDispatch(accountCode, messageBean);
				} else if (ReceiveMessageTypeConfig.RMT_LOCATION.equalsIgnoreCase(msgType)) {
					// 位置消息
					messageBean = XmlKit.toBean(message, ReceiveLocationBean.class);
					returnMessageBean = AutoReplyMessageProcessor.process(accountCode, messageBean);
					result = messageBusiness.addReceiveMessageBean(accountCode, messageBean);
					dispatcher.doDispatch(accountCode, messageBean);
				}
				// 事件处理
				else if (ReceiveMessageTypeConfig.RMT_EVENT.equalsIgnoreCase(msgType)) {
					WeixinReceiveEventBusiness eventBusiness = new WeixinReceiveEventBusiness();
					WeixinUserInfoBusiness userInfoBusiness = new WeixinUserInfoBusiness();
					String eventType = ReceiveEventParser.getEventType(message);
					if (ReceiveEventConfig.EVENT_SUBSCRIBE.equalsIgnoreCase(eventType)) {
						// 关注事件
						eventBean = XmlKit.toBean(message, SubscribeEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						result = userInfoBusiness.subscribe(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_UNSUBSCRIBE.equalsIgnoreCase(eventType)) {
						// 取消关注事件
						eventBean = XmlKit.toBean(message, UnsubscribeEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						result = userInfoBusiness.unsubscribe(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_SCAN.equalsIgnoreCase(eventType)) {
						// 扫码事件
						eventBean = XmlKit.toBean(message, ScanEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_LOCATION.equalsIgnoreCase(eventType)) {
						// 地理位置推送事件
						eventBean = XmlKit.toBean(message, LocationEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_CLICK.equalsIgnoreCase(eventType)) {
						// 菜单点击事件
						eventBean = XmlKit.toBean(message, ClickEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_VIEW.equalsIgnoreCase(eventType)) {
						// 菜单跳转链接事件
						eventBean = XmlKit.toBean(message, ViewEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_SEND_MESSAGE_JOB_FINISH.equalsIgnoreCase(eventType)) {
						// 群发消息发送结果推送
						eventBean = XmlKit.toBean(message, SendMessageJobFinishBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_SEND_TEMPLATE_JOB_FINISH.equalsIgnoreCase(eventType)) {
						// 模板消息发送结果推送
						eventBean = XmlKit.toBean(message, SendTemplateJobFinishBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_SCAN_CODE_PUSH.equalsIgnoreCase(eventType)) {
						// 公众号调起扫一扫（不等待回复）
						eventBean = XmlKit.toBean(message, ScanCodePushEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_SCAN_CODE_WAIT_MESSAGE.equalsIgnoreCase(eventType)) {
						// 公众号调起扫一扫（等待回复）
						eventBean = XmlKit.toBean(message, ScanCodeWaitMessageEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_SEND_PICTURE_FROM_CAMERA.equalsIgnoreCase(eventType)) {
						// 发送图片事件（从系统相机）
						eventBean = XmlKit.toBean(message, SendPictureFromCameraEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_SEND_PICTURE_FROM_ALBUM.equalsIgnoreCase(eventType)) {
						// 发送图片事件（从相册）
						eventBean = XmlKit.toBean(message, SendPictureFromAlbumEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_SEND_PICTURE_FROM_WEIXIN.equalsIgnoreCase(eventType)) {
						// 发送图片事件（从微信相机）
						eventBean = XmlKit.toBean(message, SendPictureFromWeixinEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else if (ReceiveEventConfig.EVENT_LOCATION_SELECT.equalsIgnoreCase(eventType)) {
						// 地理位置选择事件
						eventBean = XmlKit.toBean(message, LocationSelectEventBean.class);
						returnMessageBean = AutoReplyMessageProcessor.process(accountCode, eventBean);
						result = eventBusiness.addReceiveEventBean(accountCode, eventBean);
						dispatcher.doDispatch(accountCode, eventBean);
					} else {
						log.error("微信消息解析失败, 没有对应的Event Type:" + eventType);
					}
				} else {
					log.error("微信消息解析失败, 没有对应的Message Type:" + msgType);
				}

				if (result <= 0) {
					log.error("微信消息记录失败, Message:" + message);
				}
			}
		} catch (ApiException e) {
			log.error("微信消息解析失败: " + e.getMessage());
		}

		if (returnMessageBean.getCode() == Constants.STATUS_VALID) {
			HttpResponseKit.printMessage(response, returnMessageBean.getMessage());
			return;
		} else {
			HttpResponseKit.printMessage(response, "");
		}
	}

}
