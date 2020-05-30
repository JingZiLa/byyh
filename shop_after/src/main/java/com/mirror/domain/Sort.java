package com.mirror.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 商品分类实体
 * @author JING
 *
 */
public class Sort {
	private Long sort_id;	//商品分类ID
	private String sort_name;	//商品分类名称
	private Date sort_addtime;	//商品分类添加时间
	private String sort_status;	//商品分类状态
	private User user;			//添加商品分类用户
	private Set<Mdse> mdse = new HashSet<Mdse>();	//分类下的商品集合
	
	public Set<Mdse> getMdse() {
		return mdse;
	}
	public void setMdse(Set<Mdse> mdse) {
		this.mdse = mdse;
	}
	public Long getSort_id() {
		return sort_id;
	}
	public void setSort_id(Long sort_id) {
		this.sort_id = sort_id;
	}
	public String getSort_name() {
		return sort_name;
	}
	public void setSort_name(String sort_name) {
		this.sort_name = sort_name;
	}
	public Date getSort_addtime() {
		return sort_addtime;
	}
	public void setSort_addtime(Date sort_addtime) {
		this.sort_addtime = sort_addtime;
	}
	public String getSort_status() {
		return sort_status;
	}
	public void setSort_status(String sort_status) {
		this.sort_status = sort_status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
