package cn.edu.bjut.smart.helper;

import java.lang.reflect.Field;
import java.util.Map;

import cn.edu.bjut.smart.annotation.Inject;
import cn.edu.bjut.smart.util.ArrayUtil;
import cn.edu.bjut.smart.util.CollectionUtil;
import cn.edu.bjut.smart.util.ReflectionUtil;

/**
 * 依赖注入助手类
 * 
 * @author sh
 *
 */
public final class IocHelper {

	static {
		// 获取所有Bean类与Bean实例之间的映射关系
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if (CollectionUtil.isNotEmpty(beanMap)) {
			// 遍历Bean
			for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
				// 从BeanMap中获取Bean类与实例
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				// 获取Bean类定义的所有成员变量
				Field[] beanFields = beanClass.getDeclaredFields();
				if (ArrayUtil.isNotEmpty(beanFields)) {
					// 遍历BeanFields
					for (Field field : beanFields) {
						if (field.isAnnotationPresent(Inject.class)) {
							Class<?> beanFieldClass = field.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if (beanFieldInstance != null) {
								ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}

}
