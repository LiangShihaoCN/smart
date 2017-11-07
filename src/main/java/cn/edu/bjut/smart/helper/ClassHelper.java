package cn.edu.bjut.smart.helper;

import java.util.HashSet;
import java.util.Set;

import cn.edu.bjut.smart.annotation.Controller;
import cn.edu.bjut.smart.annotation.Service;
import cn.edu.bjut.smart.util.ClassUtil;

/**
 * 类操作助手类
 * 
 * @author sh
 *
 */
public class ClassHelper {

	/**
	 * 定义类集合
	 */
	private static final Set<Class<?>> CLASS_SET;

	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}

	/**
	 * 获取应用包名下的所有类
	 * 
	 * @return
	 */
	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}

	/**
	 * 获取应用报名下的所有 Service 类
	 * 
	 * @return
	 */
	public static Set<Class<?>> getServiceClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> class1 : CLASS_SET) {
			if (class1.isAnnotationPresent(Service.class)) {
				classSet.add(class1);
			}
		}
		return classSet;
	}

	/**
	 * 获取应用报名下的所有 Controller 类
	 * 
	 * @return
	 */
	public static Set<Class<?>> getControllerClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> class1 : CLASS_SET) {
			if (class1.isAnnotationPresent(Controller.class)) {
				classSet.add(class1);
			}
		}
		return classSet;
	}

	/**
	 * 获取所有Bean 类
	 * 
	 * @return
	 */
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		classSet.addAll(getServiceClassSet());
		classSet.addAll(getControllerClassSet());
		return classSet;
	}
}
