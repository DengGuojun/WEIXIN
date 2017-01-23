package com.lpmas.weixin.api.receive.util;

import com.lpmas.framework.util.XmlKit;
import com.lpmas.weixin.api.base.ApiException;
import com.lpmas.weixin.api.bean.receive.ReplyMessageBaseBean;

public class ReplyMessageProcessor {

	public static String process(ReplyMessageBaseBean bean) throws ApiException {
		return XmlKit.toXml(bean);
	}
	// public static String process(ReplyMessageBaseBean bean) throws
	// ApiException {
	// String msgType = bean.getMsgType();
	// ReceiveUtil.checkParameters("Common paramters", bean.getToUserName(),
	// bean.getFromUserName(),
	// bean.getCreateTime(), msgType);
	// StringBuilder sb = new StringBuilder("<xml>");
	// sb.append(getNodeRow("ToUserName", bean.getToUserName()));
	// sb.append(getNodeRow("FromUserName", bean.getFromUserName()));
	// sb.append(getNodeRow("CreateTime",
	// String.valueOf(bean.getCreateTime())));
	// sb.append(getNodeRow("MsgType", msgType));
	//
	// if (ReplyMessageTypeConfig.PMT_VOICE.equalsIgnoreCase(msgType)) {
	// ReplyVoiceBean replyBean = (ReplyVoiceBean) bean;
	// ReceiveUtil.checkParameters("Voice paramters", replyBean.getMediaId());
	// sb.append("<Voice>");
	// sb.append(getNodeRow("MediaId", replyBean.getMediaId()));
	// sb.append("</Voice>");
	//
	// } else if (ReplyMessageTypeConfig.PMT_VIDEO.equalsIgnoreCase(msgType)) {
	// ReplyVideoBean replyBean = (ReplyVideoBean) bean;
	// ReceiveUtil.checkParameters("Video paramters", replyBean.getMediaId());
	// sb.append("<Video>");
	// sb.append(getNodeRow("MediaId", replyBean.getMediaId()));
	// sb.append(getNodeRow("Title", replyBean.getTitle()));
	// sb.append(getNodeRow("Description", replyBean.getDescription()));
	// sb.append("</Video>");
	//
	// } else if ("transfer_customer_service".equalsIgnoreCase(msgType)) {
	// ReplyTransferCustomerServiceBean replyBean =
	// (ReplyTransferCustomerServiceBean) bean;
	// sb.append("<TransInfo>");
	// sb.append(getNodeRow("KfAccount", replyBean.getAccount()));
	// sb.append("<TransInfo>");
	//
	// } else if (ReplyMessageTypeConfig.PMT_TEXT.equalsIgnoreCase(msgType)) {
	// ReplyTextBean replyBean = (ReplyTextBean) bean;
	// ReceiveUtil.checkParameters("Text paramters", replyBean.getContent());
	// sb.append(getNodeRow("Content", replyBean.getContent()));
	//
	// } else if (ReplyMessageTypeConfig.PMT_NEWS.equalsIgnoreCase(msgType)) {
	// ReplyNewsBean replyBean = (ReplyNewsBean) bean;
	// ReceiveUtil.checkParameters("News paramters",
	// replyBean.getArticleCount(), replyBean.getArticleList());
	// sb.append(getNodeRow("ArticleCount", replyBean.getArticleCount()));
	// sb.append("<Articles>");
	// for (NewsInfoBean item : replyBean.getArticleList()) {
	// sb.append("<item>");
	// sb.append(getNodeRow("Title", item.getTitle()));
	// sb.append(getNodeRow("Description", item.getDescription()));
	// sb.append(getNodeRow("PicUrl", item.getPicUrl()));
	// sb.append(getNodeRow("Url", item.getUrl()));
	// sb.append("</item>");
	// }
	// sb.append("</Articles>");
	//
	// } else if (ReplyMessageTypeConfig.PMT_MUSIC.equalsIgnoreCase(msgType)) {
	// ReplyMusicBean replyBean = (ReplyMusicBean) bean;
	// ReceiveUtil.checkParameters("Music paramters",
	// replyBean.getThumbMediaId());
	// sb.append(getNodeRow("Title", replyBean.getTitle()));
	// sb.append(getNodeRow("Description", replyBean.getDescription()));
	// sb.append(getNodeRow("MusicURL", replyBean.getMusicUrl()));
	// sb.append(getNodeRow("HQMusicUrl", replyBean.getHqMusicUrl()));
	// sb.append(getNodeRow("ThumbMediaId", replyBean.getThumbMediaId()));
	//
	// } else if (ReplyMessageTypeConfig.PMT_IMAGE.equalsIgnoreCase(msgType)) {
	// ReplyImageBean replyBean = (ReplyImageBean) bean;
	// ReceiveUtil.checkParameters("Image paramters", replyBean.getMediaId());
	// sb.append("<Image>");
	// sb.append(getNodeRow("MediaId", replyBean.getMediaId()));
	// sb.append("<Image>");
	// } else {
	// throw new ApiException(ApiErrorCode.REPLY_MSG_ERROR, "Invalid reply
	// msgBean:msgType=" + msgType);
	// }
	// sb.append("</xml>");
	// return sb.toString();
	// }
	//
	// private static String getNodeRow(String tag, String value) {
	// value = StringKit.validStr(value);
	// return "<" + tag + "><![CDATA[" + value + "]]></" + tag + ">";
	// }

}
