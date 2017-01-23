package com.lpmas.weixin.api.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.api.config.ApiConfig;

public class ApiInvoker {
	private static Logger log = LoggerFactory.getLogger(ApiInvoker.class);

	protected String charset = ApiConfig.DEFAULT_CHARSET;
	protected int connectionTimeout = ApiConfig.DEFAULT_CONNECTION_TIMEOUT;
	protected int socketTimeout = ApiConfig.DEFAULT_SOCKET_TIMEOUT;

	/**
	 * 通过get方法获取数据
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ApiException
	 */
	public String getRequest(String url, HashMap<String, String> paramMap) throws ApiException {
		StringBuilder sb = new StringBuilder(url);

		// 设置请求参数
		if (paramMap != null && !paramMap.isEmpty()) {
			sb.append(MapKit.map2String(paramMap, "&", "="));
		}
		log.info("getRequest url:{}", sb);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse httpResponse = null;

		try {
			httpGet.setConfig(getRequestConfig());// 处理配置信息
			httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();

			int statusCode = httpResponse.getStatusLine().getStatusCode();// 获取状态码
			if (statusCode != HttpStatus.SC_OK) {
				log.error("getRequest {} status: {}", url, httpResponse.getStatusLine().toString());
				throw new ApiException(ApiErrorCode.NETWORK_ERROR,
						"Request [" + url + "] failed:" + httpResponse.getStatusLine().toString());
			}

			return new String(EntityUtils.toByteArray(httpEntity), charset);
		} catch (IOException e) {// 发生网络异常
			log.error("getRequest url error:{}", url, e);
			throw new ApiException(ApiErrorCode.NETWORK_ERROR, "Request [" + url + "] failed:" + e.getMessage());
		} finally {// 释放链接
			close(httpClient, httpResponse);
		}
	}

	/**
	 * 通过post方法提交数据
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ApiException
	 */
	public <K, V> String postRequest(String url, HashMap<K, V> paramMap) throws ApiException {
		if (paramMap != null && !paramMap.isEmpty()) {
			List<NameValuePair> paramList = new ArrayList<NameValuePair>();
			for (Map.Entry<K, V> entry : paramMap.entrySet()) {
				paramList.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
			}

			try {
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(paramList));

				return postRequest(url, httpPost);
			} catch (UnsupportedEncodingException e) {
				log.error("UnsupportedEncodingException[{}]:", url, e);
			}

		}
		return null;
	}

	/**
	 * 通过post方法提交数据
	 * 
	 * @param url
	 * @param postText
	 * @return
	 * @throws ApiException
	 */
	public String postRequest(String url, String postText) throws ApiException {
		if (StringKit.isValid(postText)) {
			HttpPost httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(postText, charset);
			httpPost.setEntity(entity);

			return postRequest(url, httpPost);
		}
		return null;
	}

	/**
	 * 通过post方法提交数据
	 * 
	 * @param url
	 * @param postMethod
	 * @return
	 * @throws ApiException
	 */
	protected String postRequest(String url, HttpPost httpPost) throws ApiException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse httpResponse = null;
		try {
			httpPost.setConfig(getRequestConfig());// 处理配置信息
			httpResponse = httpClient.execute(httpPost);

			return processHttpResponse(url, httpResponse);
		} catch (IOException e) {
			// 发生网络异常
			log.error("postRequest url error:{}", url, e);
			throw new ApiException(ApiErrorCode.NETWORK_ERROR, "Request [" + url + "] failed:" + e.getMessage());
		} finally {
			close(httpClient, httpResponse);
		}
	}

	private RequestConfig getRequestConfig() {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectionTimeout).setConnectionRequestTimeout(connectionTimeout).build();
		return requestConfig;
	}

	private String processHttpResponse(String url, HttpResponse httpResponse) throws IOException, ApiException {
		HttpEntity httpEntity = httpResponse.getEntity();

		int statusCode = httpResponse.getStatusLine().getStatusCode();// 获取状态码
		if (statusCode != HttpStatus.SC_OK) {
			log.error("portRequest {} status: {}", url, httpResponse.getStatusLine());
			throw new ApiException(ApiErrorCode.NETWORK_ERROR,
					"Request [" + url + "] failed:" + httpResponse.getStatusLine());
		}
		return new String(EntityUtils.toByteArray(httpEntity), charset);
	}

	/**
	 * 关闭method
	 * 
	 * @param method
	 */
	protected void close(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
		if (null != httpResponse) {
			try {
				httpResponse.close();
			} catch (IOException e) {
			}
		}
		if (null != httpClient) {
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}
}
