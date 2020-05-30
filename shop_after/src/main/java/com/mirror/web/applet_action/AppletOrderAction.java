package com.mirror.web.applet_action;

import java.util.logging.Logger;

import com.mirror.domain.JsonRequest;
import com.mirror.domain.Mdse;
import com.mirror.domain.Order;
import com.mirror.domain.User;
import com.mirror.service.MdseService;
import com.mirror.service.OrderService;
import com.mirror.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单Action
 * 
 * @author JING
 *
 */
public class AppletOrderAction extends ActionSupport implements ModelDriven<Order> {
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
	//注入用户Service
	private UserService userService;
	
 	public void setUserService(UserService userService) {
		this.userService = userService;
	}
 	//返回数据封装类
	private JsonRequest<Mdse> jsonRequest = new JsonRequest<Mdse>();

	public JsonRequest<Mdse> getJsonRequest() {
		return jsonRequest;
	}

	public void setJsonRequest(JsonRequest<Mdse> jsonRequest) {
		this.jsonRequest = jsonRequest;
	}

	Logger log = Logger.getLogger(this.getClass().getName());

	// 注入商品Service
	private MdseService mdseService;

	public void setMdseService(MdseService mdseService) {
		this.mdseService = mdseService;
	}

	/**
	 * 创建商品订单
	 * 
	 * @return
	 */
	public String createOrder() {
		System.out.println("开始创建商品订单"+order.getMdse().getMdse_id());
		Mdse findById = mdseService.findById(order.getMdse().getMdse_id());
		User findById2 = userService.findById(order.getUser().getUser_id());
		orderService.createOrder(findById , order ,findById2);
		return SUCCESS;
	}
}
