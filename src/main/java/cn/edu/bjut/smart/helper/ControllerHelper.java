package cn.edu.bjut.smart.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.edu.bjut.smart.annotation.Action;
import cn.edu.bjut.smart.bean.Handler;
import cn.edu.bjut.smart.bean.Request;
import cn.edu.bjut.smart.util.ArrayUtil;
import cn.edu.bjut.smart.util.CollectionUtil;

/**
 * 控制器助手类
 * 
 * @author sh
 *
 */
public class ControllerHelper {

	/**
	 * 用于存放请求与处理器的关系
	 */
	private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();
	static {
		// 获取所有的Controller类
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if (CollectionUtil.isNotEmpty(controllerClassSet)) {
			// 遍历Controller类
			for (Class<?> controllerClass : controllerClassSet) {
				// 获取Controller类中定义的方法
				Method[] methods = controllerClass.getDeclaredMethods();
				if (ArrayUtil.isNotEmpty(methods)) {
					// 遍历Controller类中的方法
					for (Method method : methods) {
						// 判断当前方法是否带有 Action 注解
						if (method.isAnnotationPresent(Action.class)) {
							// 从 Action 注解中获取 URL 映射
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							// 验证URL映射规则
							if (mapping.matches("\\w+:/\\w*")) {
								String[] array = mapping.split(":");
								if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
									// 获取请求方法与请求路径
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									// 初始化 ACTION_MAP
									ACTION_MAP.put(request, handler);
								}
							}
						}

					}

				}

			}
		}
	}

	/**
	 * 获取Controller
	 * 
	 * @param requestMethod
	 * @param requestPath
	 * @return
	 */
	public static Handler getHandler(String requestMethod, String requestPath) {
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
}
