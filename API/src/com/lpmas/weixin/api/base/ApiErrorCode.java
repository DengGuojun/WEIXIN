package com.lpmas.weixin.api.base;

public class ApiErrorCode {
	/** 必填参数为空 */
	public final static int PARAMETER_EMPTY = 2001;

	/** 必填参数无效 */
	public final static int PARAMETER_INVALID = 2002;

	/** 服务器响应数据无效 */
	public final static int RESPONSE_DATA_INVALID = 2003;

	/** 生成签名失败 */
	public final static int MAKE_SIGNATURE_ERROR = 2004;

	/** 网络错误 */
	public final static int NETWORK_ERROR = 3000;

	/** 微信推送信息验证失败 */
	public final static int RECEIVE_CHECK_ERROR = 3001;

	/** 被动回复信息加解密报错。 */
	public final static int AES_ERROR = 3002;

	/** 被动回复信息 post过来的xml流解析成Doucument出错。 */
	public final static int DOCUMENT_ERROR = 3003;

	/** 服务器收到消息体格式有问题 */
	public final static int RECEIVE_MSG_ERROR = 3004;
	
	/** 被动回复消息体格式有问题 */
	public final static int REPLY_MSG_ERROR = 3005;

	/** 微信推送信息验证失败 */
	public final static int UPLOAD_MEDIA_ERROR = 3006;

	/** 被动回复信息加解密报错 */
	public final static int CONFING_APPID_ERROR = 4000;

	/** encodeaeskey非法 */
	public final static int CONFING_ENCODEAESKEY_ERROR = 4001;
}
