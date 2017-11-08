package cn.edu.bjut.smart.bean;

import java.util.Map;

import cn.edu.bjut.smart.util.CaseUtil;

/**
 * 请求参数对象
 * 
 * @author sh
 *
 */
public class Param {

	private Map<String, Object> paramMap;

	public Param(Map<String, Object> paramMap) {
		super();
		this.paramMap = paramMap;
	}

	/**
	 * 根据参数名获取long型参数
	 * 
	 * @param name
	 * @return
	 */
	public long getLong(String name) {
		return CaseUtil.caseLong(paramMap.get(name));
	}

	/**
	 * 获取所有字段信息
	 * 
	 * @return
	 */
	public Map<String, Object> getMap() {
		return paramMap;
	}

}
