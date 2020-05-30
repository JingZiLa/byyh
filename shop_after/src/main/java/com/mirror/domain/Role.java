package com.mirror.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色表实体类
 * @author JING
 *
 */
public class Role {
	private Long user_role_id; //角色ID
	private String user_role_name;	//角色名称
	
	private Set<User> user = new HashSet<User>(); //用户信息
	public Long getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(Long user_role_id) {
		this.user_role_id = user_role_id;
	}
	public String getUser_role_name() {
		return user_role_name;
	}
	public void setUser_role_name(String user_role_name) {
		this.user_role_name = user_role_name;
	}
	public Set<User> getUser() {
		return user;
	}
	public void setUser(Set<User> user) {
		this.user = user;
	}
}
