package com.mirror.web.action;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mirror.domain.Mdse;
import com.mirror.domain.OrderDetail;
import com.mirror.domain.PageBean;
import com.mirror.service.MdseService;
import com.mirror.service.OrderDetailService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单详情Action
 * 
 * @author JING
 *
 */
public class OrderDetailAction extends ActionSupport implements ModelDriven<OrderDetail> {

	// 模型驱动使用的对象
	private OrderDetail orderDetail = new OrderDetail();

	@Override
	public OrderDetail getModel() {
		return orderDetail;
	}

	// 注入OrderDetailService
	private OrderDetailService orderDetailService;

	public void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}
	// 注入SortService
	private MdseService mdseService;
	
	public void setMdseService(MdseService mdseService) {
		this.mdseService = mdseService;
	}

	/**
	 * 查询订单状态条件
	 */
	private String findStatus;

	public void setFindStatus(String findStatus) {
		this.findStatus = findStatus;
	}

	public String getFindStatus() {
		return findStatus;
	}

	// 使用set方法的方式接收数据:
	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	// 使用set方法接受每页显示记录数
	private Integer pageSize = 5;

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 5;
		}
		this.pageSize = pageSize;
	}

	// 接收数据:
	private Date order_end_time;

	public Date getOrder_end_time() {
		return order_end_time;
	}

	public void setOrder_end_time(Date order_end_time) {
		this.order_end_time = order_end_time;
	}

	/**
	 * 查询所有订单方法
	 * 
	 * @return
	 */
	public String findAll() {
		// 接收参数：分页参数
		// 创建离线条件查询对象:
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderDetail.class);
		// 设置条件:
		System.out.println(findStatus);
		if (findStatus != null && !"".equals(findStatus)) {
			if ("1".equals(findStatus)) {
				detachedCriteria.add(Restrictions.eq("issue_cargo_status", 0));
			}
			if ("2".equals(findStatus)) {
				detachedCriteria.add(Restrictions.eq("put_cargo_status", 0));
			}
			if ("3".equals(findStatus)) {
				detachedCriteria.add(Restrictions.eq("pay_status", 0));
			}
			if ("4".equals(findStatus)) {
				detachedCriteria.add(Restrictions.eq("order_status", 1));
			}
			if ("5".equals(findStatus)) {
				detachedCriteria.add(Restrictions.eq("order_status", 2));
			}
			
			if (orderDetail.getOrder_detail_number() != null && !"".equals(orderDetail.getOrder_detail_number())) {
				detachedCriteria.add(Restrictions.ilike("order_detail_number", "%" + orderDetail.getOrder_detail_number() + "%"));
			}
			if (orderDetail.getOrder_addtime() != null) {
				detachedCriteria.add(Restrictions.ge("order_addtime", orderDetail.getOrder_addtime()));
			}
			if (order_end_time != null) {
				detachedCriteria.add(Restrictions.le("order_addtime", order_end_time));
			}
		}
		
		PageBean<OrderDetail> page = orderDetailService.findByPage(detachedCriteria, currPage, pageSize);

		ActionContext.getContext().getValueStack().push(page);
		return "findAllSuccess";
	}
	/**
	 * 根据订单ID查询订单详情信息后跳转
	 * @return
	 */
	public String DetailsUI() {
		orderDetail = orderDetailService.findById(orderDetail.getOrder_detail_id());
//		System.out.println(orderDetail.getOrder().size()+"sssssssssssssssssssssssssssssss");
//		Set<Order> order = orderDetail.getOrder();
//		for (Order order2 : order) {
//			System.out.println(order2.getUser().getUser_name()+"aaaaaaaaaaaaaaaaa");
//		}
		
		ActionContext.getContext().getValueStack().push(orderDetail);
		return "DetailsUISuccess";
		
	}
	/**
	 * 查询订单商品详情
	 * @return
	 */
	public String mdseDetail() {
		Mdse mdse = mdseService.findById(orderDetail.getOrder_detail_id());
		ActionContext.getContext().getValueStack().push(mdse);
		return "mdseDetailSuccess";
	}
	/**
	 * 根据ID删除订单
	 * @return
	 */
	public String deleteOrder() {
		//先查询订单信息
		orderDetail = orderDetailService.findById(orderDetail.getOrder_detail_id());
		//删除订单信息
		orderDetailService.deleteOrder(orderDetail);
		return "deleteOrderSuccess";
	}
}
