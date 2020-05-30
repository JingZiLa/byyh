package com.mirror.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.mirror.dao.OrderDetailDao;
import com.mirror.domain.Order;
import com.mirror.domain.OrderDetail;
import com.mirror.domain.PageBean;
import com.mirror.service.OrderDetailService;

/**
 * 订单详情Service接口实现
 * 
 * @author JING
 *
 */
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

	// 注入OrderDetailDao
	private OrderDetailDao orderDetailDao;

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	@Override
	/**
	 * 业务层查询订单方法---带条件---（分页）
	 */
	public PageBean<OrderDetail> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<OrderDetail> pageBean = new PageBean<OrderDetail>();
		// 封装当前页数:
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数:
		pageBean.setPageSize(pageSize);
		// 封装总记录数:
		// 调用DAO:
		Integer totalCount = orderDetailDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 封装总页数:
		Double ceil = Math.ceil(totalCount.doubleValue() / pageSize);
		pageBean.setTotalPage(ceil.intValue());
		// 封装每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<OrderDetail> list = orderDetailDao.findByPage(detachedCriteria, begin, pageSize);

		pageBean.setList(list);

		return pageBean;
	}

	@Override
	/**
	 * 业务层根据订单ID 查询订单信息方法
	 */
	public OrderDetail findById(Long order_detail_id) {
		return orderDetailDao.findById(order_detail_id);
	}

	@Override
	/**
	 * 删除订单详情
	 */
	public void deleteOrder(OrderDetail orderDetail) {
		orderDetailDao.delete(orderDetail);
	}

	@Override
	/**
	 * 创建订单详情
	 */
	public void createOrderDetail(OrderDetail orderDetail, Set<Order> set) {
		Date date = new Date();
		// 创建订单编号
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String format = simpleDateFormat.format(date);
		int random = new Random().nextInt(10000);
		int randoms = new Random().nextInt(100000);
		orderDetail.setOrder_detail_number(format+random+randoms);
		
		//设置订单创建时间
		orderDetail.setOrder_addtime(date);
		
		Set<Order> orders = orderDetail.getOrder();
		for (Order order : set) {
			System.out.println(order.getUser().getUser_name());
			orderDetail.setUser(order.getUser());
			orders.add(order);
		}
		orderDetail.setOrder(orders);
		System.out.println(orderDetail.getOrder().size()+"ssssssssssss");
		orderDetailDao.save(orderDetail);
	}


}
