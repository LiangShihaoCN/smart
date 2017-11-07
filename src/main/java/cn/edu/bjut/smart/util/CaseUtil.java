package cn.edu.bjut.smart.util;

/**
 * 转型操作工具类
 * 
 * @author sh
 *
 */
public class CaseUtil {

	/**
	 * 转为String
	 * 
	 * @param obj
	 * @return
	 */
	public static String castString(Object obj) {
		return CaseUtil.castString(obj, "");
	}

	/**
	 * 转为String（提供默认值）
	 * 
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	private static String castString(Object obj, String defaultValue) {
		return obj != null ? String.valueOf(obj) : defaultValue;
	}

	/**
	 * 转为Double
	 * 
	 * @param obj
	 * @return
	 */
	public static Double castDouble(Object obj) {
		return CaseUtil.castDouble(obj, 0.0);
	}

	/**
	 * 转为Double（提供默认值）
	 * 
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	private static Double castDouble(Object obj, Double defaultValue) {
		double parseDouble = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					parseDouble = Double.parseDouble(strValue);
				} catch (NumberFormatException e) {
					parseDouble = defaultValue;
				}
			}
		}
		return parseDouble;
	}

	/**
	 * 转为Long
	 * 
	 * @param obj
	 * @return
	 */
	public static long caseLong(Object obj) {
		return CaseUtil.caseLong(obj, 0L);
	}

	/**
	 * 转为Long（提供默认值）
	 * 
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	private static long caseLong(Object obj, long defaultValue) {
		long parseLong = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					parseLong = Long.parseLong(strValue);
				} catch (NumberFormatException e) {
					parseLong = defaultValue;
				}
			}
		}
		return parseLong;
	}

	/**
	 * 转为int
	 * 
	 * @param obj
	 * @return
	 */
	public static int caseInt(Object obj) {
		return CaseUtil.caseInt(obj, 0);
	}

	/**
	 * 转为int（提供默认值）
	 * 
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	private static int caseInt(Object obj, int defaultValue) {
		int parseInt = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					parseInt = Integer.parseInt(strValue);
				} catch (NumberFormatException e) {
					parseInt = defaultValue;
				}
			}
		}
		return parseInt;
	}

	/**
	 * 转为boolean
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean caseBoolen(Object obj) {
		return CaseUtil.caseBoolen(obj, false);
	}

	/**
	 * 转为Boolean（提供默认值）
	 * 
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	private static boolean caseBoolen(Object obj, boolean defaultValue) {
		boolean parseBoolean = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					parseBoolean = Boolean.parseBoolean(strValue);
				} catch (NumberFormatException e) {
					parseBoolean = defaultValue;
				}
			}
		}
		return parseBoolean;
	}
}
