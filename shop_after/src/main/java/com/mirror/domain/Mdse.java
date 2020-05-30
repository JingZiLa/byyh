package com.mirror.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 商品实体类
 * @author JING
 *
 */
public class Mdse {
	private Long mdse_id;			//商品ID
	private String mdse_name;		//商品名称
	private String mdse_status;		//商品状态
	private Date mdse_addtime;	//商品添加时间
	private String mdse_mome;		//商品详情
	private Double mdse_price;		//商品价格
	private String mdse_imge;		//商品图片
	private String mdse_quantity;	//商品数量
	private Long mdse_sales;	//商品销量
	
	private User user;				//添加商品用户
	private Sort sort;				//商品分类
	
	private Set<Order> order = new HashSet<Order>();
	
	
	public Set<Order> getOrder() {
		return order;
	}
	public void setOrder(Set<Order> order) {
		this.order = order;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public Long getMdse_id() {
		return mdse_id;
	}
	public void setMdse_id(Long mdse_id) {
		this.mdse_id = mdse_id;
	}
	public String getMdse_name() {
		return mdse_name;
	}
	public void setMdse_name(String mdse_name) {
		this.mdse_name = mdse_name;
	}
	public String getMdse_status() {
		return mdse_status;
	}
	public void setMdse_status(String mdse_status) {
		this.mdse_status = mdse_status;
	}
	
	public Date getMdse_addtime() {
		return mdse_addtime;
	}
	public void setMdse_addtime(Date mdse_addtime) {
		this.mdse_addtime = mdse_addtime;
	}
	public String getMdse_mome() {
		return mdse_mome;
	}
	public void setMdse_mome(String mdse_mome) {
		this.mdse_mome = mdse_mome;
	}
	public Double getMdse_price() {
		return mdse_price;
	}
	public void setMdse_price(Double mdse_price) {
		this.mdse_price = mdse_price;
	}
	public String getMdse_imge() {
		return mdse_imge;
	}
	public void setMdse_imge(String mdse_imge) {
		this.mdse_imge = mdse_imge;
	}
	public String getMdse_quantity() {
		return mdse_quantity;
	}
	public void setMdse_quantity(String mdse_quantity) {
		this.mdse_quantity = mdse_quantity;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getMdse_sales() {
		return mdse_sales;
	}
	public void setMdse_sales(Long mdse_sales) {
		this.mdse_sales = mdse_sales;
	}
	
}
