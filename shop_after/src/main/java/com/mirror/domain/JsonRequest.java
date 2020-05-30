package com.mirror.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 小程序响应请求返回数据的封装类
 * 
 * @author JING
 * @param <T>
 *
 */
public class JsonRequest<T> {
	//	字符串数据
	private String str;
	private String[] arrString;
	//对象类型数据
	private T t;
	//	set集合数据
	private Set<T> set = new HashSet<T>();
	//	List集合数据
	List<T> list = new ArrayList<T>();
	//Map集合数据	
	private Map<String, T> map = new HashMap<String, T>();
	// 分页数据
	private PageBean<T> pageBean;

	public PageBean<T> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<T> pageBean) {
		this.pageBean = pageBean;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Set<T> getSet() {
		return set;
	}

	public void setSet(Set<T> set) {
		this.set = set;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Map<String, T> getMap() {
		return map;
	}

	public void setMap(Map<String, T> map) {
		this.map = map;
	}
	public String[] getArrString() {
		return arrString;
	}

	public void setArrString(String[] arrString) {
		this.arrString = arrString;
	}

	/**
	 * 测试是否连接
	 * 
	 * @return
	 */
	public String testRequest() {
		System.out.println("小程序请求连接后端成功。。。");
		return "已成功连接后端";
	}

}
