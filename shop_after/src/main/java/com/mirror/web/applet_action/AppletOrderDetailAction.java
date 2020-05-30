package com.mirror.web.applet_action;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mirror.domain.JsonRequest;
import com.mirror.domain.Mdse;
import com.mirror.domain.Order;
import com.mirror.domain.OrderDetail;
import com.mirror.domain.PageBean;
import com.mirror.service.OrderDetailService;
import com.mirror.service.OrderService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单详情Action
 * 
 * @author JING
 *
 */
public class AppletOrderDetailAction extends ActionSupport implements ModelDriven<OrderDetail> {

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

	// 注入订单Service
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

//	订单实体
	private Order order = new Order();

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	private JsonRequest<OrderDetail> jsonRequest = new JsonRequest<OrderDetail>();

	

	public JsonRequest<OrderDetail> getJsonRequest() {
		return jsonRequest;
	}

	public void setJsonRequest(JsonRequest<OrderDetail> jsonRequest) {
		this.jsonRequest = jsonRequest;
	}

	Logger log = Logger.getLogger(this.getClass().getName());
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
				detachedCriteria.add(
						Restrictions.ilike("order_detail_number", "%" + orderDetail.getOrder_detail_number() + "%"));
			}
		}

		PageBean<OrderDetail> page = orderDetailService.findByPage(detachedCriteria, currPage, pageSize);
		jsonRequest.setPageBean(page);
		log.info(jsonRequest.toString());
		return SUCCESS;
	}

	/**
	 * 根据订单ID查询订单详情信息后跳转
	 * 
	 * @return
	 */
	public String DetailsUI() {
		orderDetail = orderDetailService.findById(orderDetail.getOrder_detail_id());

		ActionContext.getContext().getValueStack().push(orderDetail);
		return "DetailsUISuccess";

	}

	/**
	 * 根据ID删除订单
	 * 
	 * @return
	 */
	public String deleteOrder() {
		// 先查询订单信息
		orderDetail = orderDetailService.findById(orderDetail.getOrder_detail_id());
		// 删除订单信息
		orderDetailService.deleteOrder(orderDetail);
		return "deleteOrderSuccess";
	}

	public String createOrderDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String parameter = request.getParameter("orderArr");
		parameter = "9, 10, ";
		String[] split = parameter.split(", ");
		System.out.println(split);
		Long[] orderArr = new Long[split.length];
		Set<Order> set = new HashSet<Order>();
		for (int i = 0; i < split.length; i++) {
			orderArr[i] = Long.parseLong(split[i]);
			System.out.println(orderArr[i]);
			set.add(orderService.findById(orderArr[i]));
		}
		for (Order order : set) {
			System.out.println(order.getOrder_id());
		}
		orderDetail.setDue_money(order.getOrder_money());
		orderDetailService.createOrderDetail(orderDetail, set);
		return SUCCESS;
	}

}
