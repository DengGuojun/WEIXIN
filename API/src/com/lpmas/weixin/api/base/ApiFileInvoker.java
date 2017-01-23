package com.lpmas.weixin.api.base;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.FileKit;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.util.StringKit;

public class ApiFileInvoker extends ApiInvoker {
	private static Logger log = LoggerFactory.getLogger(ApiFileInvoker.class);

	/**
	 * 文件上传
	 * 
	 * @param url
	 * @param file
	 * @return
	 * @throws ApiException
	 */
	public String uploadFile(String url, String fieldName, File file) throws ApiException {
		HttpPost httpPost = new HttpPost(url);
		try {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addBinaryBody(fieldName, file, ContentType.DEFAULT_BINARY, file.getName());

			HttpEntity httpEntity = builder.build();
			httpPost.setEntity(httpEntity);

			return postRequest(url, httpPost);
		} catch (Exception e) {// 找不到文件
			log.error("uploadFile error:{}", file.getPath(), e);
			throw new ApiException(ApiErrorCode.UPLOAD_MEDIA_ERROR, "Request [" + url + "] failed:" + e.getMessage());
		}

	}

	public String uploadFile(String url, String fieldName, File file, Map<String, String> paramMap)
			throws ApiException {
		HttpPost httpPost = new HttpPost(url);
		try {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addBinaryBody(fieldName, file, ContentType.DEFAULT_BINARY, file.getName());

			if (null != paramMap && !paramMap.isEmpty()) {
				for (Entry<String, String> entry : paramMap.entrySet()) {
					builder.addTextBody(entry.getKey(), entry.getValue());
				}
			}

			HttpEntity httpEntity = builder.build();
			httpPost.setEntity(httpEntity);

			return postRequest(url, httpPost);
		} catch (Exception e) {// 找不到文件
			log.error("uploadFile error:{}", file.getPath(), e);
			throw new ApiException(ApiErrorCode.UPLOAD_MEDIA_ERROR, "Request [" + url + "] failed:" + e.getMessage());
		}

	}

	/**
	 * 
	 * @param url
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws ApiException
	 */
	public String downloadFile(String url, String filePath, String fileName) throws ApiException {
		if (!StringKit.isValid(filePath)) {
			throw new ApiException(ApiErrorCode.PARAMETER_EMPTY, "downloadFile [" + url + "] error: filePath is null");
		}

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse httpResponse = null;
		try {
			HttpGet httpGet = new HttpGet(url);

			httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				log.error("getRequest {} status: {}", url, httpResponse.getStatusLine());
				throw new ApiException(ApiErrorCode.NETWORK_ERROR,
						"Request [" + url + "] failed:" + httpResponse.getStatusLine());
			}

			// 出错时，微信返回文字信息
			Header contentTypeHeader = httpResponse.getFirstHeader("Content-Type");
			if (contentTypeHeader != null) {
				String contentType = contentTypeHeader.getValue();
				if ("text/plain".indexOf(contentType) > -1) {
					return new String(EntityUtils.toByteArray(httpEntity), charset);
				}
			}

			fileName = getFileName(httpResponse, fileName);
			File file = new File(filePath + "/" + fileName);
			FileKit.inputStream2File(httpEntity.getContent(), file);

			Map<String, String> map = new HashMap<String, String>();
			map.put("errcode", "0");
			map.put("errmsg", "ok");
			return JsonKit.toJson(map);
		} catch (IOException e) {
			// 发生网络异常
			log.error("getRequest url error:{}", url, e);
			throw new ApiException(ApiErrorCode.NETWORK_ERROR, "Request [" + url + "] failed:" + e.getMessage());
		} finally {// 释放链接
			close(httpClient, httpResponse);
		}
	}

	/**
	 * 获取文件名
	 * 
	 * @param getMethod
	 * @param fileName
	 * @return
	 */
	private String getFileName(HttpResponse httpResponse, String fileName) {
		if (!StringKit.isValid(fileName)) {// 没有指定文件名的从header中Content-disposition获取
			Header dispositionHeader = httpResponse.getFirstHeader("Content-disposition");
			if (dispositionHeader != null) {
				String dispositionStr = dispositionHeader.getValue();
				fileName = dispositionStr.split(";")[1].replace("filename", "").replace("\"", "").trim();
			} else {// 从Content-disposition获取不了的，按日期+3为随机数+后缀生成文件名
				fileName = DateKit.getCurrentDateTime("yyyyMMddHHmmss") + "_" + new Random().nextInt(999)
						+ getFileExtensionName(httpResponse);
			}
		}
		return fileName;
	}

	/**
	 * 获取文件的后缀名
	 * 
	 * @param getMethod
	 * @return
	 */
	private String getFileExtensionName(HttpResponse httpResponse) {
		// 从Content-Type中获取
		Header contentTypeHeader = httpResponse.getFirstHeader("Content-Type");
		if (contentTypeHeader != null) {
			String contentType = contentTypeHeader.getValue();
			if ("image/jpg".indexOf(contentType) > -1) {
				return ".jpg";
			} else if ("audio/x-speex-with-header-byte".indexOf(contentType) > -1) {
				return ".speex";
			}
		}
		return ".wxf";// 默认返回自定义的wxf
	}
}
