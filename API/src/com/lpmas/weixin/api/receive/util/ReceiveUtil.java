package com.lpmas.weixin.api.receive.util;

import com.lpmas.weixin.api.base.ApiErrorCode;
import com.lpmas.weixin.api.base.ApiException;

public class ReceiveUtil {
	/**
	 * 检查必要参数是否为null，如果是Null立马抛出异常
	 * 
	 * @param args
	 * @throws ApiException
	 */
	public static void checkParameters(String errorStr, Object... args) throws ApiException {
		if (null != args) {
			int length = args.length;
			for (int i = 0; i < length; i++) {
				if (null == args[i]) {
					throw new ApiException(ApiErrorCode.PARAMETER_EMPTY, errorStr + "参数校验args[" + i + "]不能为null");
				}
			}
		}
	}

	public static String getReplyMessage(String encrypt, String signature, String timestamp, String nonce) {
		String format = "<xml>\n" + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n"
				+ "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n" + "<TimeStamp>%3$s</TimeStamp>\n"
				+ "<Nonce><![CDATA[%4$s]]></Nonce>\n" + "</xml>";
		return String.format(format, encrypt, signature, timestamp, nonce);
	}
}
