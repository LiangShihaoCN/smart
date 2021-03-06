package cn.edu.bjut.smart.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.edu.bjut.smart.util.ReflectionUtil;

/**
 * Bean 助手类(Bean 容器)
 * 
 * @author sh
 *
 */
public class BeanHelper {

	/**
	 * 定义 Bean 映射（用于存放 Bean 类与 Bean 实例对应关系）
	 */
	private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

	static {
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for (Class<?> beanClass : beanClassSet) {
			Object obj = ReflectionUtil.newInstance(beanClass);
			BEAN_MAP.put(beanClass, obj);
		}
	}

	/**
	 * 获取 Bean 映射
	 * 
	 * @return
	 */
	public static Map<Class<?>, Object> getBeanMap() {
		return BEAN_MAP;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> cls) {
		if (!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("can not get bean by class:" + cls);
		}
		return (T) BEAN_MAP.get(cls);
	}
}
