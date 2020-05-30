package com.mirror.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.mirror.dao.OrderDao;
import com.mirror.domain.Mdse;
import com.mirror.domain.Order;
import com.mirror.domain.PageBean;
import com.mirror.domain.User;
import com.mirror.service.OrderService;
/**
 * 顶的Service接口的实现
 * @author JING
 *
 */
@Transactional
public class OrderServiceImpl implements OrderService {
	
	//注入Dao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	/**
	 * 查询所有订单带条件--分页
	 */
	public PageBean<Order> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 封装当前页数:
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数:
		pageBean.setPageSize(pageSize);
		// 封装总记录数:
		// 调用DAO:
		Integer totalCount = orderDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 封装总页数:
		Double ceil = Math.ceil(totalCount.doubleValue() / pageSize);
		pageBean.setTotalPage(ceil.intValue());
		// 封装每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<Order> list = orderDao.findByPage(detachedCriteria, begin, pageSize);

		pageBean.setList(list);
		return pageBean;
	}

	@Override
	/**
	 * 根据ID修改订单状态
	 */
	public Order findById(Long order_id) {
		return orderDao.findById(order_id);
	}

	@Override
	/**
	 * 修改订单方法
	 */
	public void editOrder(Order orders) {
		orderDao.update(orders);
	}

	@Override
	/**
	 * 创建订单
	 */
	public void createOrder(Mdse mdse, Order order , User user) {
		//添加创建时间
		Date date = new Date();
		order.setOrder_addtime(date);
		//设置订单默认状态
		order.setOrder_status(0L);
		//创建订单编号
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String format = simpleDateFormat.format(date);
		int  random = new Random().nextInt(1000);
		int  randoms = new Random().nextInt(10000);
		order.setOrder_number(format+random+randoms);
		//默认添加商品为1
		order.setOrder_mdse_quantity(1L);
		//设置订单金额
		order.setOrder_money(mdse.getMdse_price());
		
		//设置订单所属用户
		order.setUser(user);
		//设置订单商品
		order.setMdse(mdse);
		//创建订单
		orderDao.save(order);
		
	}
	
}
