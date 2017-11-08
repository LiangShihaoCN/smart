package cn.edu.bjut.smart.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 * 
 * @author sh
 *
 */
public final class ArrayUtil {

	/**
	 * 判断数组数组是否非空
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);

	}

	/**
	 * 判断数组数组是否为空
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object[] array) {
		return ArrayUtils.isEmpty(array);
	}

}
