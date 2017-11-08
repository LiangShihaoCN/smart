package cn.edu.bjut.smart.util;

/**
 * 字符串工具类
 * 
 * @author sh
 *
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str != null) {
			str = str.trim();
		}
		return StringUtil.isEmpty(str);
	}

	/**
	 * 判断字符串是否非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 切分字符串
	 * 
	 * @param string
	 * @param sp
	 * @return
	 */
	public static String[] splitString(String string, String sp) {
		return string.split(sp);
	}

}
