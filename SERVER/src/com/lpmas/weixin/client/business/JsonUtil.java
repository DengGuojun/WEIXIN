package com.lpmas.weixin.client.business;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static JsonFactory jsonFactory = new JsonFactory();

	private static ObjectMapper mapper = null;

	static {
		jsonFactory.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		jsonFactory.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper = new ObjectMapper(jsonFactory);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(MapperFeature.USE_ANNOTATIONS, false);
	}

	/**
	 * 获取jackson json lib的ObjectMapper对象
	 * 
	 * @return ObjectMapper对象
	 */
	public static ObjectMapper getObjectMapper() {
		return mapper;
	}

	/**
	 * 获取jackson json lib的JsonFactory对象
	 * 
	 * @return JsonFactory对象
	 */
	public static JsonFactory getJsonFactory() {
		return jsonFactory;
	}

	/**
	 * 将json转成bean
	 * 
	 * @param <T>
	 *            多态类型
	 * @param json
	 *            json字符串
	 * @param clazz
	 *            java bean类型(Class)
	 * @return java bean对象
	 */
	public static <T> T toBean(String json, Class<T> clazz) {
		T bean = null;
		try {
			bean = mapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return bean;
	}

	public static <T> T toBean(String json, TypeReference<?> typeReference) {
		T bean = null;
		try {
			bean = mapper.readValue(json, typeReference);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return bean;
	}

	/**
	 * 将json转换成List
	 * 
	 * @param json
	 *            json字符串
	 * @param clazz
	 *            java bean类型(Class)
	 * @return
	 */
	public static <T> List<T> toList(String json, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		try {
			JavaType javaType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
			list = mapper.readValue(json, javaType);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return list;
	}

	/**
	 * 将bean转成json
	 * 
	 * @param bean
	 *            java bean
	 * @return json 字符串
	 */
	public static String toJson(Object obj) {
		String result = null;
		try {
			result = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return result;
	}

}
