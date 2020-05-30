package com.mirror.web.action;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mirror.domain.Mdse;
import com.mirror.domain.Order;
import com.mirror.domain.PageBean;
import com.mirror.domain.User;
import com.mirror.service.MdseService;
import com.mirror.service.OrderService;
import com.mirror.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单Action
 * 
 * @author JING
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	// 模型驱动使用的对象:
	private Order order = new Order();

	@Override
	public Order getModel() {
		return order;
	}

	// 注入Service
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// 注入商品Service
	private MdseService mdseService;

	public void setMdseService(MdseService mdseService) {
		this.mdseService = mdseService;
	}

	// 注入用户Service
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 修改货物状态ID
	private int id = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// 商品信息mdses_id
	private String mdses_id;

	public String getMdses_id() {
		return mdses_id;
	}

	public void setMdses_id(String mdses_id) {
		this.mdses_id = mdses_id;
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

	// 查询订单状态变量
	private String findStatus;

	public String getFindStatus() {
		return findStatus;
	}

	public void setFindStatus(String findStatus) {
		this.findStatus = findStatus;
	}

	public String findAll() {
		/*
		 * // 接收参数：分页参数 // 创建离线条件查询对象: DetachedCriteria detachedCriteria =
		 * DetachedCriteria.forClass(Order.class); // 设置条件: if (order.getOrder_addtime()
		 * != null) { detachedCriteria.add(Restrictions.ge("order_addtime",
		 * order.getOrder_addtime())); } if (order_end_time != null) {
		 * detachedCriteria.add(Restrictions.le("order_addtime", order_end_time)); } if
		 * (order.getOrder_number() != null && !"".equals(order.getOrder_number())) {
		 * detachedCriteria.add(Restrictions.ilike("order_number", "%" +
		 * order.getOrder_number() + "%")); } System.out.println(findStatus);
		 * if(findStatus != null && !"".equals(findStatus)) { if("1".equals(findStatus))
		 * { detachedCriteria.add(Restrictions.eq("order_cargo_status", 0L)); }
		 * if("2".equals(findStatus)) {
		 * detachedCriteria.add(Restrictions.eq("order_status", 0L)); }
		 * if("3".equals(findStatus)) {
		 * detachedCriteria.add(Restrictions.eq("order_pay_status", 1L)); }
		 * 
		 * if(findStatus == "4") { detachedCriteria.add(Restrictions.eq("order_addtime",
		 * findStatus)); }
		 * 
		 * } PageBean<Order> page = orderService.findByPage(detachedCriteria, currPage,
		 * pageSize);
		 * 
		 * // List<Order> list = page.getList(); // Set<Mdse> mdse = null; // for (Order
		 * order : list) { // mdse = order.getMdse(); // } //
		 * ActionContext.getContext().getValueStack().set("mdse", mdse);
		 * ActionContext.getContext().getValueStack().push(page); */ 
		return "findAllSuccess";
		
	}

	/**
	 * 修根据ID修改订单状态
	 * 
	 * @return
	 */
	public String editOrder() {
//		System.out.println("ID= " + id);
//		Order orders = orderService.findById(order.getOrder_id());
//		Long status = 0L;
//		if (id == 1) {
//			status = orders.getOrder_status();
//			if (status != 1L) {
//				status = 1L;
//			} else {
//				status = 0L;
//			}
//			orders.setOrder_status(status);
//		} else if (id == 2) {
//			status = orders.getOrder_cargo_status();
//			if (status != 1L) {
//				status = 1L;
//			} else {
//				status = 0L;
//			}
//			orders.setOrder_cargo_status(status);
//		} else if (id == 3) {
//			status = orders.getOrder_pay_status();
//			if (status != 1L) {
//				status = 1L;
//			} else {
//				status = 0L;
//			}
//			orders.setOrder_pay_status(status);
//		}
//		orderService.editOrder(orders);
		return "editOrderSuccess";
	}

	/**
	 * 跳转到订单详情页面
	 * 
	 * @return
	 */
	public String orderDetails() {
		Order findById = orderService.findById(order.getOrder_id());
		ActionContext.getContext().getValueStack().push(findById);
		return "orderDetailsSuccess";
	}

	/**
	 * 创建订单跳转
	 * 
	 * @return
	 */
	public String createUI() {
		List<Mdse> list = mdseService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);

		return "createUISuccess";
	}

	/**
	 * 创建订单
	 * 
	 * @return
	 */
	public String createOrder() {
		User user = userService.findById(1L);
		String[] split = mdses_id.split(", ");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Mdse.class);
		if (split.length > 0) {
			for (int i = 0; i < split.length; i++) {
				detachedCriteria.add(Restrictions.eq("mdse_id", Long.parseLong(split[i])));
			}
		}
//		order.setMdse(mdseService.findByIds(detachedCriteria));
		order.setUser(user);
		orderService.createOrder(order);
		return "createOrderSuccess";
	}
}
