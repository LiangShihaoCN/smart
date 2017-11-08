package cn.edu.bjut.smart;

import cn.edu.bjut.smart.helper.BeanHelper;
import cn.edu.bjut.smart.helper.ClassHelper;
import cn.edu.bjut.smart.helper.ControllerHelper;
import cn.edu.bjut.smart.helper.IocHelper;
import cn.edu.bjut.smart.util.ClassUtil;

/**
 * 统一加载相应的 Helper 类
 * 
 * @author sh
 *
 */
public final class HelperLoader {

	public static void init() {
		Class<?>[] classList = { ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class };
		for (Class<?> clazz : classList) {
			ClassUtil.loadClass(clazz.getName());
		}
	}

}
