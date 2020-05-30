package com.mirror.web.applet_action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mirror.domain.JsonRequest;
import com.mirror.domain.Mdse;
import com.mirror.domain.PageBean;
import com.mirror.service.MdseService;
import com.mirror.service.SortService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 前端小程序商品Action类
 * 
 * @author JING
 *
 */
public class AppletMdseAction extends ActionSupport implements ModelDriven<Mdse> {
	// 模型驱动使用的对象：
	private Mdse mdse = new Mdse();

	@Override
	public Mdse getModel() {
		return mdse;
	}

	// 注入商品Service
	private MdseService mdseService;

	public void setMdseService(MdseService mdseService) {
		this.mdseService = mdseService;
	}

	// 注入商品分类Service
	private SortService sortService;

	public void setSortService(SortService sortService) {
		this.sortService = sortService;
	}

	private JsonRequest<Mdse> jsonRequest = new JsonRequest<Mdse>();

	public JsonRequest<Mdse> getJsonRequest() {
		return jsonRequest;
	}

	public void setJsonRequest(JsonRequest<Mdse> jsonRequest) {
		this.jsonRequest = jsonRequest;
	}

	Logger log = Logger.getLogger(this.getClass().getName());

	// 使用set方法的方式接收数据:
	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	// 使用set方法接受每页显示记录数
	private Integer pageSize = 6;

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 6;
		}
		this.pageSize = pageSize;
	}

	/**
	 * 查询所有商品
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findAll() throws IOException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Mdse.class);
		System.out.println(mdse.getMdse_name());
		if (mdse.getMdse_name() != null && !"".equals(mdse.getMdse_name())) {
			detachedCriteria.add(Restrictions.ilike("mdse_name", "%" + mdse.getMdse_name() + "%"));
		}
//		查询商品
		List<Mdse> list = mdseService.findAll();
		jsonRequest.setList(list);
		log.info(jsonRequest.toString());
		return SUCCESS;
	}

	/**
	 * 根据ID查询商品详情
	 * 
	 * @return
	 */
	public String findByID() {
		Mdse mdses = mdseService.findById(mdse.getMdse_id());
//		String mdse_imge = mdses.getMdse_imge();
//		String[] split = mdse_imge.split(",");
//		jsonRequest.setArrString(split);
//		System.out.println(split.toString());
		jsonRequest.setT(mdses);
		log.info(jsonRequest.toString());
		return SUCCESS;
	}

	/**
	 * 查询部分商品
	 * 
	 * @return
	 */
	public String findSectMdse() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Mdse.class);
		
//		查询商品
		PageBean<Mdse> findByPage = mdseService.findByPage(detachedCriteria, currPage, pageSize);
		jsonRequest.setPageBean(findByPage);
		log.info(jsonRequest.toString());

		return SUCCESS;
	}
	
	/**
	 * 查询促销商品
	 * @return
	 */
	public String SalesMdse() {
		System.out.println("sssssssssssssssssssssss");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Mdse.class);
		
		detachedCriteria.add(Restrictions.eq("mdse_status", "3"));
		
		PageBean<Mdse> findByPage = mdseService.findByPage(detachedCriteria, currPage, pageSize);
		jsonRequest.setPageBean(findByPage);
		log.info(jsonRequest.toString());
		return SUCCESS;
	}
	/**
	 * 查询新品推荐
	 * @return
	 */
	public String newProducts() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Mdse.class);
		detachedCriteria.add(Restrictions.ge("mdse_addtime", new Date()));
		Set<Mdse> findByIds = mdseService.findByIds(detachedCriteria);
		for (Mdse mdse : findByIds) {
			System.out.println(mdse.getMdse_name());
		}
		jsonRequest.setSet(findByIds);
		return SUCCESS;
	}
	
	/**
	 * 查询新品推荐
	 * @return
	 */
	public String hotProducts() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Mdse.class);
		detachedCriteria.addOrder(Order.asc("mdse_sales"));
		Set<Mdse> findByIds = mdseService.findByIds(detachedCriteria);
		for (Mdse mdse : findByIds) {
			System.out.println(mdse.getMdse_name() +"ssssssss"+ mdse.getMdse_sales());
		}
		jsonRequest.setSet(findByIds);
		return SUCCESS;
	}
}
