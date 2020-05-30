package com.mirror.service;

import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;

import com.mirror.domain.Order;
import com.mirror.domain.OrderDetail;
import com.mirror.domain.PageBean;

/**
 * 订单详情Service接口
 * @author JING
 *
 */
public interface OrderDetailService{

	PageBean<OrderDetail> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	OrderDetail findById(Long order_detail_id);

	void deleteOrder(OrderDetail orderDetail);

	void createOrderDetail(OrderDetail orderDetail,Set<Order> set);
	
}
