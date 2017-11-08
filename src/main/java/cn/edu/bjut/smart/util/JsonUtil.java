package cn.edu.bjut.smart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON工具类
 * 
 * @author sh
 *
 */
public class JsonUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * POJO 转为 JSON
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> String toJson(T obj) {
		String json;
		try {
			json = OBJECT_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			LOGGER.error("convent POJO to JSON failure", e);
			throw new RuntimeException(e);
		}
		return json;
	}

	/**
	 * JSON 转为 POJO
	 * 
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> type) {
		T pojo;
		try {
			pojo = OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			LOGGER.error("convent JSON to POJO failure", e);
			throw new RuntimeException(e);
		}
		return pojo;
	}

}
