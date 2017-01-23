package com.lpmas.weixin.api.receive;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import javax.crypto.spec.SecretKeySpec;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.crypto.AES;
import com.lpmas.framework.crypto.BASE64;
import com.lpmas.framework.crypto.SHA;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.util.XmlKit;
import com.lpmas.framework.web.UrlKit;
import com.lpmas.weixin.api.base.ApiErrorCode;
import com.lpmas.weixin.api.base.ApiException;
import com.lpmas.weixin.api.bean.receive.MessageConfigBean;
import com.lpmas.weixin.api.bean.receive.ReceiveBaseBean;
import com.lpmas.weixin.api.bean.receive.ReceiveDecryptBean;
import com.lpmas.weixin.api.bean.receive.ReplyMessageBaseBean;
import com.lpmas.weixin.api.receive.util.ByteList;
import com.lpmas.weixin.api.receive.util.ReceiveUtil;
import com.lpmas.weixin.api.receive.util.RecevieMessageParser;
import com.lpmas.weixin.api.receive.util.ReplyMessageProcessor;

public class ReceiveMessageProcessor {
	private final static String DEFAULT_CODE = Constants.ENCODING_UNICODE;
	private final static String CRYPTO_MODE = "AES";
	private final static String randomCharaters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public ReceiveBaseBean processReceiveMessage(MessageConfigBean configBean, String queryString, String receiveMsg)
			throws ApiException {

		Map<String, String> map = UrlKit.queryString2Map(queryString);
		String signature = map.get("signature");
		String timestamp = map.get("timestamp");
		String nonce = map.get("nonce");
		String encryptType = map.get("encrypt_type");
		String msgSignature = map.get("msg_signature");
		String result = "";

		boolean isValid = false;
		if (CRYPTO_MODE.equalsIgnoreCase(encryptType) && msgSignature != null) {// 加密的消息
			String encrypt = getEncryptContent(receiveMsg.trim());
			isValid = checkCipherSignature(configBean.getToken(), timestamp, nonce, encrypt, msgSignature);
			if (isValid) {
				ReceiveDecryptBean bean = decrypt(configBean.getCryptoKey(), encrypt);
				if (!bean.getAppId().equals(configBean.getAppId())) {
					throw new ApiException(ApiErrorCode.CONFING_APPID_ERROR,
							"receiveMsg_appId not equal configBean_appId");
				} else {
					result = bean.getDecryptXml();
				}
			} else {
				throw new ApiException(ApiErrorCode.RECEIVE_MSG_ERROR, "signature check fail");
			}
		} else {
			isValid = checkSignature(configBean.getToken(), timestamp, nonce, signature);
			if (isValid) {
				result = receiveMsg;
			} else {
				throw new ApiException(ApiErrorCode.RECEIVE_MSG_ERROR, "signature check fail");
			}
		}

		return RecevieMessageParser.parse(result);
	}

	public String processReplyMessage(MessageConfigBean configBean, ReplyMessageBaseBean replyBean, String queryString)
			throws ApiException {
		String result = ReplyMessageProcessor.process(replyBean);
		Map<String, String> map = UrlKit.queryString2Map(queryString);
		String timestamp = map.get("timestamp");
		String nonce = map.get("nonce");
		String encryptType = map.get("encrypt_type");
		String msgSignature = map.get("msg_signature");

		if (CRYPTO_MODE.equalsIgnoreCase(encryptType) && msgSignature != null) {
			return encrypt(configBean.getAppId(), configBean.getCryptoKey(), configBean.getToken(), result, timestamp,
					nonce);
		}

		return result;
	}

	public String getEncryptContent(String content) throws ApiException {
		return XmlKit.getValue(content, "Encrypt");
		// try {
		// Document document = XmlKit.getDocumentByString(content);
		// Element root = document.getRootElement();
		// return StringKit.validStr(root.elementText("Encrypt"));
		// } catch (Exception e) {
		// throw new ApiException(ApiErrorCode.DOCUMENT_ERROR,
		// "get Encrypt-Content form CipherXml fail:" + e.getMessage());
		// }
	}

	public String encrypt(String appId, String cryptoKey, String token, String replyMsg, String timestamp, String nonce)
			throws ApiException {
		String result = null;
		ReceiveUtil.checkParameters("encrypt", appId, cryptoKey, token, nonce, replyMsg);
		try {
			// 加密
			ByteList byteCollector = new ByteList();
			String randomStr = getRandomStr();

			byte[] randomStrBytes = randomStr.getBytes(DEFAULT_CODE);
			byte[] replyXmlBytes = replyMsg.getBytes(DEFAULT_CODE);
			byte[] networkBytesOrder = getNetworkBytesOrder(replyXmlBytes.length);
			byte[] appidBytes = appId.getBytes(DEFAULT_CODE);

			// randomStr + networkBytesOrder + text + appid
			byteCollector.addBytes(randomStrBytes);
			byteCollector.addBytes(networkBytesOrder);
			byteCollector.addBytes(replyXmlBytes);
			byteCollector.addBytes(appidBytes);

			// 设置加密模式为AES的CBC模式
			byte[] aesKey = BASE64.decodeBase64(cryptoKey + "=");
			SecretKeySpec keySpec = new SecretKeySpec(aesKey, CRYPTO_MODE);
			byte[] encrypted = AES.encryptByCBCNoPadding(byteCollector.toBytes(), keySpec, aesKey);
			String encryptContent = BASE64.encodeBase64(encrypted);
			// 生成安全签名
			if (StringKit.isValid(timestamp)) {
				timestamp = Long.toString(System.currentTimeMillis());
			}
			String signature = getSHA1(token, timestamp, nonce, encryptContent);
			// "发送给平台的签名是: " + signature[1].toString();
			// 生成发送的xml
			result = ReceiveUtil.getReplyMessage(encryptContent, signature, timestamp, nonce);
		} catch (Exception e) {
			throw new ApiException(ApiErrorCode.AES_ERROR, "msg encrypt error:" + e.getMessage());
		}
		return result;
	}

	public ReceiveDecryptBean decrypt(String cryptoKey, String encrypt) throws ApiException {
		ReceiveUtil.checkParameters("decrypt", cryptoKey, encrypt);
		try {
			byte[] aesKey = BASE64.decodeBase64(cryptoKey + "=");
			SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
			byte[] bytes = BASE64.decodeBase64(encrypt);
			byte[] decrypted = AES.decryptByCBCNoPadding(bytes, key, aesKey);
			// 分离16位随机字符串,网络字节序和AppId
			byte[] networkOrder = Arrays.copyOfRange(decrypted, 16, 20);
			int xmlLength = recoverNetworkBytesOrder(networkOrder);
			String xmlContent = new String(Arrays.copyOfRange(decrypted, 20, 20 + xmlLength), DEFAULT_CODE);
			String from_appid = new String(Arrays.copyOfRange(decrypted, 20 + xmlLength, decrypted.length),
					DEFAULT_CODE);
			return new ReceiveDecryptBean(from_appid, xmlContent);
		} catch (Exception e) {
			throw new ApiException(ApiErrorCode.AES_ERROR, "msg decrypt error:" + e.getMessage());
		}
	}

	public boolean checkCipherSignature(String token, String timestamp, String nonce, String encrypt, String signature)
			throws ApiException {
		ReceiveUtil.checkParameters("checkCipherSignature", token, timestamp, nonce, signature);
		try {
			String sha = getSHA1(token, timestamp, nonce, encrypt);
			return sha.equals(signature);
		} catch (NoSuchAlgorithmException e) {
			throw new ApiException(ApiErrorCode.AES_ERROR, "msg(Ciphertext) signature fail:" + e.getMessage());
		}
	}

	public boolean checkSignature(String token, String timestamp, String nonce, String signature) throws ApiException {
		ReceiveUtil.checkParameters("checkSignature", token, timestamp, nonce, signature);
		try {
			String sha = getSHA1(token, timestamp, nonce);
			return sha.equals(signature);
		} catch (NoSuchAlgorithmException e) {
			throw new ApiException(ApiErrorCode.AES_ERROR, "msg(Plaintext) signature fail:" + e.getMessage());
		}
	}

	// 还原4个字节的网络字节序
	private int recoverNetworkBytesOrder(byte[] orderBytes) {
		int sourceNumber = 0;
		for (int i = 0; i < 4; i++) {
			sourceNumber <<= 8;
			sourceNumber |= orderBytes[i] & 0xff;
		}
		return sourceNumber;
	}

	// 生成4个字节的网络字节序
	private byte[] getNetworkBytesOrder(int sourceNumber) {
		byte[] orderBytes = new byte[4];
		orderBytes[3] = (byte) (sourceNumber & 0xFF);
		orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
		orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
		orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
		return orderBytes;
	}

	// 随机生成16位字符串
	private String getRandomStr() {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			sb.append(randomCharaters.charAt(random.nextInt(randomCharaters.length())));
		}
		return sb.toString();
	}

	private String getSHA1(String... array) throws NoSuchAlgorithmException {
		Arrays.sort(array);
		return SHA.getSHA(SHA.SHA, StringKit.array2String(array, "").getBytes());
	}

}
