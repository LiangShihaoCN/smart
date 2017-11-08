package cn.edu.bjut.smart.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action 方法注解
 * 
 * @author sh
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
	/**
	 * 请求类型与路径
	 * 
	 * @return
	 */
	String value();
}
