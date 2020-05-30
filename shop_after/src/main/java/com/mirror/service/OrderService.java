package com.mirror.service;

import org.hibernate.criterion.DetachedCriteria;

import com.mirror.domain.Mdse;
import com.mirror.domain.Order;
import com.mirror.domain.PageBean;
import com.mirror.domain.User;

/**
 * 顶的Service接口
 * @author JING
 *
 */
public interface OrderService {

	PageBean<Order> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	Order findById(Long order_id);

	void editOrder(Order orders);

	void createOrder(Mdse mdse, Order order , User user);

}
