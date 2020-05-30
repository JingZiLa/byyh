package com.mirror.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 * 
 * @author JING
 */
public class User {
	private Long user_id; // 用户ID
	private String user_code; // 用户账号
	private String user_password; // 用户密码
	private String user_name; // 用户姓名
	private Integer user_sex; // 用户性别
	private String user_email; // 用户邮箱
	private String user_state; // 用户所在国家
	private String user_province; // 用户所在省份
	private String user_city; // 用户所在城市
	private String user_area; // 用户所在区域
	private String user_headimge; // 用户头像
	private String user_status; // 用户状态
	private String user_phone; // 用户电话
	private String user_address; // 用户收货地址
	private Date user_addtime; // 用户添加时间
	private String user_info; // 用户简介
	// 用户商品
	private Set<Mdse> mdse = new HashSet<Mdse>();

	// 用户订单
	private Set<Order> order = new HashSet<Order>();

	// 用户订单详情
	private Set<OrderDetail> orderDetail = new HashSet<OrderDetail>();
	//用户角色
	private Role role;
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Set<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}

	public String getUser_state() {
		return user_state;
	}

	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}

	public String getUser_province() {
		return user_province;
	}

	public void setUser_province(String user_province) {
		this.user_province = user_province;
	}

	public String getUser_city() {
		return user_city;
	}

	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}

	public String getUser_headimge() {
		return user_headimge;
	}

	public void setUser_headimge(String user_headimge) {
		this.user_headimge = user_headimge;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public Set<Mdse> getMdse() {
		return mdse;
	}

	public void setMdse(Set<Mdse> mdse) {
		this.mdse = mdse;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public Date getUser_addtime() {
		return user_addtime;
	}

	public void setUser_addtime(Date user_addtime) {
		this.user_addtime = user_addtime;
	}

	public String getUser_info() {
		return user_info;
	}

	public void setUser_info(String user_info) {
		this.user_info = user_info;
	}

	public String getUser_area() {
		return user_area;
	}

	public void setUser_area(String user_area) {
		this.user_area = user_area;
	}
	
}
