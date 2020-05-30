package com.mirror.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 订单详情实体类
 * @author JING
 *
 */
public class Order {
	private Long order_id;	//订单ID
	private String order_number;	//订单编号
	private Long order_status;		//订单状态
	private Double order_money;		//订单金额	
	private Date order_addtime;		//订单创建时间
	private Long order_mdse_quantity;		//订单商品数量
	//订单所属用户
	private User user;
	
	//订单商品
	private Mdse mdse;
/**
 * 订单 详情
 */
	private OrderDetail orderDetail;
	public Mdse getMdse() {
		return mdse;
	}

	public void setMdse(Mdse mdse) {
		this.mdse = mdse;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

 
	public Long getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Long order_status) {
		this.order_status = order_status;
	}

	public Double getOrder_money() {
		return order_money;
	}

	public void setOrder_money(Double order_money) {
		this.order_money = order_money;
	}

	public Date getOrder_addtime() {
		return order_addtime;
	}

	public void setOrder_addtime(Date order_addtime) {
		this.order_addtime = order_addtime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getOrder_mdse_quantity() {
		return order_mdse_quantity;
	}

	public void setOrder_mdse_quantity(Long order_mdse_quantity) {
		this.order_mdse_quantity = order_mdse_quantity;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	
	
}
