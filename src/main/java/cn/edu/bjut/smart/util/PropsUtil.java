package cn.edu.bjut.smart.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性文件工具类
 * 
 * @author sh
 *
 */
public class PropsUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

	/**
	 * 加载属性文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties loadProps(String fileName) {
		InputStream rs = null;
		Properties props = null;
		try {
			rs = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (rs == null) {
				throw new FileNotFoundException(fileName + "file is not found");
			}
			props = new Properties();
			props.load(rs);
		} catch (IOException e) {
			LOGGER.error("load properties file failure", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (IOException e) {
					LOGGER.error("close input stream failure", e);
				}
			}
		}
		return props;
	}

	/**
	 * 获取字符型属性（默认值为空字符串）
	 * 
	 * @param props
	 * @param key
	 * @return
	 */
	public static String getString(Properties props, String key) {

		return getString(props, key, "");

	}

	/**
	 * 获取字符型属性（可指定默认值）
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	private static String getString(Properties props, String key, String defaultValue) {
		String result = defaultValue;
		if (props.contains(key)) {
			defaultValue = props.getProperty(key);
		}
		return result;
	}

	/**
	 * 获取数值型属性（默认值为 0）
	 * 
	 * @param props
	 * @param key
	 * @return
	 */
	public static int getInt(Properties props, String key) {
		return getInt(props, key, 0);
	}

	/**
	 * 获取数值型属性（可指定默认值）
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	private static int getInt(Properties props, String key, int defaultValue) {
		int result = defaultValue;
		if (props.contains(key)) {
			result = CaseUtil.caseInt(props.getProperty(key));
		}
		return result;
	}

	/**
	 * 获取布尔型属性（默认值为 false）
	 * 
	 * @param props
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Properties props, String key) {
		return getBoolean(props, key, false);
	}

	/**
	 * 获取布尔型属性（可指定默认值）
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	private static boolean getBoolean(Properties props, String key, boolean defaultValue) {
		boolean result = defaultValue;
		if (props.contains(key)) {
			result = CaseUtil.caseBoolen(props.getProperty(key));
		}
		return result;
	}
}
