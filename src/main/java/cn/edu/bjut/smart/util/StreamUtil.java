package cn.edu.bjut.smart.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 流操作工具类
 * 
 * @author sh
 *
 */
public final class StreamUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

	public static String getString(InputStream is) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			LOGGER.error("get string failure", e);
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
}
