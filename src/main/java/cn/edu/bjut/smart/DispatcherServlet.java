package cn.edu.bjut.smart;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;

import cn.edu.bjut.smart.bean.Data;
import cn.edu.bjut.smart.bean.Handler;
import cn.edu.bjut.smart.bean.Param;
import cn.edu.bjut.smart.bean.View;
import cn.edu.bjut.smart.helper.BeanHelper;
import cn.edu.bjut.smart.helper.ConfigHelper;
import cn.edu.bjut.smart.helper.ControllerHelper;
import cn.edu.bjut.smart.util.ArrayUtil;
import cn.edu.bjut.smart.util.CodecUtil;
import cn.edu.bjut.smart.util.JsonUtil;
import cn.edu.bjut.smart.util.ReflectionUtil;
import cn.edu.bjut.smart.util.StreamUtil;
import cn.edu.bjut.smart.util.StringUtil;

/**
 * 请求转发器
 * 
 * @author sh
 *
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3692604199291416199L;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		// 初始化相关Helper
		HelperLoader.init();
		// 获取 ServletContext 对象（用于注册Servlet）
		ServletContext servletContext = servletConfig.getServletContext();
		// 注册处理JSP的Servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
		// 注册处理静态资源的默认 Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");

	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取请求方法和请求路径
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		// 获取 Action处理器
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if (handler != null) {
			// 获取Controller 类及其实例
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			// 创建请求参数对象
			HashedMap<String, Object> paramMap = new HashedMap<String, Object>();
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
			if (StringUtil.isNotEmpty(body)) {
				String[] params = StringUtil.splitString(body, "&");
				if (ArrayUtil.isNotEmpty(params)) {
					for (String param : params) {
						String[] array = StringUtil.splitString(param, "=");
						if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
							String paramName = array[0];
							String paramValue = array[1];
							paramMap.put(paramName, paramValue);
						}
					}
				}
			}
			Param param = new Param(paramMap);
			// 调用 Action方法
			Method actionMethod = handler.getActionMethod();
			Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
			// 处理 Action 返回值
			if (result instanceof View) {
				// 返回 JSP 页面
				View view = (View) request;
				String path = view.getPath();
				if (StringUtil.isNotEmpty(path)) {
					if (path.startsWith("/")) {
						response.sendRedirect(request.getContextPath() + path);
					} else {
						Map<String, Object> model = view.getModel();
						for (Map.Entry<String, Object> entry : model.entrySet()) {
							request.setAttribute(entry.getKey(), entry.getValue());
						}
						request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
					}
				}
			} else if (result instanceof Data) {
				// 返回JSON数据
				Data data = (Data) result;
				Object model = data.getModel();
				if (model != null) {
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter writer = response.getWriter();
					JsonUtil.toJson(model);
					writer.flush();
					writer.close();
				}
			}
		}
	}
}
