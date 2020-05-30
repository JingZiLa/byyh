package com.mirror.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 订单详情实体类
 * 
 * @author JING
 *
 */
public class OrderDetail {
	private Long order_detail_id; 
	private String order_detail_number; //订单编号
	private Double due_money;	//应付款
	private Double actual_money; //实际付款
	private Date order_addtime;	//创建时间
	private Integer pay_status;		//付款状态
	private Integer issue_cargo_status;	//发货状态
	private Integer put_cargo_status;	//收货状态
	
	private Date pay_time;		//付款时间
	private Date issue_cargo_time;	//发货时间
	private Date put_cargo_time;	//收货时间
	private Integer order_status; //订单状态
	private Integer order_payment; //支付方式
	private String order_mome; //订单备注
	
	private Integer order_logisticscorp; //订单物流公司
	private String order_logistics_odd;	////订单物流单号
	
	private User user;
	//订单集合
	private Set<Order> order = new HashSet<Order>();

	public Long getOrder_detail_id() {
		return order_detail_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setOrder_detail_id(Long order_detail_id) {
		this.order_detail_id = order_detail_id;
	}

	public String getOrder_detail_number() {
		return order_detail_number;
	}

	public void setOrder_detail_number(String order_detail_number) {
		this.order_detail_number = order_detail_number;
	}

	public Double getDue_money() {
		return due_money;
	}

	public void setDue_money(Double due_money) {
		this.due_money = due_money;
	}

	public Double getActual_money() {
		return actual_money;
	}

	public void setActual_money(Double actual_money) {
		this.actual_money = actual_money;
	}

	public Date getOrder_addtime() {
		return order_addtime;
	}

	public void setOrder_addtime(Date order_addtime) {
		this.order_addtime = order_addtime;
	}

	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

	public Integer getIssue_cargo_status() {
		return issue_cargo_status;
	}

	public void setIssue_cargo_status(Integer issue_cargo_status) {
		this.issue_cargo_status = issue_cargo_status;
	}

	public Integer getPut_cargo_status() {
		return put_cargo_status;
	}

	public void setPut_cargo_status(Integer put_cargo_status) {
		this.put_cargo_status = put_cargo_status;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public Date getIssue_cargo_time() {
		return issue_cargo_time;
	}

	public void setIssue_cargo_time(Date issue_cargo_time) {
		this.issue_cargo_time = issue_cargo_time;
	}

	public Date getPut_cargo_time() {
		return put_cargo_time;
	}

	public void setPut_cargo_time(Date put_cargo_time) {
		this.put_cargo_time = put_cargo_time;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	public Integer getOrder_payment() {
		return order_payment;
	}

	public void setOrder_payment(Integer order_payment) {
		this.order_payment = order_payment;
	}

	public String getOrder_mome() {
		return order_mome;
	}

	public void setOrder_mome(String order_mome) {
		this.order_mome = order_mome;
	}

	public Integer getOrder_logisticscorp() {
		return order_logisticscorp;
	}

	public void setOrder_logisticscorp(Integer order_logisticscorp) {
		this.order_logisticscorp = order_logisticscorp;
	}

	public String getOrder_logistics_odd() {
		return order_logistics_odd;
	}

	public void setOrder_logistics_odd(String order_logistics_odd) {
		this.order_logistics_odd = order_logistics_odd;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}
}