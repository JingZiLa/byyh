package com.mirror.web.applet_action;

import java.util.List;
import java.util.logging.Logger;

import com.mirror.domain.JsonRequest;
import com.mirror.domain.Mdse;
import com.mirror.domain.Sort;
import com.mirror.service.SortService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AppletSortAction extends ActionSupport implements ModelDriven<Sort> {
	//模型驱动使用的对象
	private Sort sort = new Sort();
	@Override
	public Sort getModel() {
		return sort;
	}
//	注入商品分类Service
	private SortService sortService;
	public void setSortService(SortService sortService) {
		this.sortService = sortService;
	}
//	Json数据封装类
	private JsonRequest<Sort> jsonRequest = new JsonRequest<Sort>();
	public JsonRequest<Sort> getJsonRequest() {
		return jsonRequest;
	}
	public void setJsonRequest(JsonRequest<Sort> jsonRequest) {
		this.jsonRequest = jsonRequest;
	}
	
	Logger log = Logger.getLogger(this.getClass().getName());
	/**
	 * 查询所有商品分类
	 * @return
	 */
	public String findAll() {
		List<Sort> list = sortService.findAll();
		jsonRequest.setList(list);
		log.info(jsonRequest.toString());
		return SUCCESS;
	}
	
	/**
	 * 根据ID查询商品详情
	 *  @return
	 */
	public String findByID() {
		System.out.println("sssssssssssssssssssss");
		Sort sorts = sortService.findById(sort.getSort_id());
		System.out.println("sssssssssssssssssssss");
		jsonRequest.setT(sorts);
		System.out.println("sssssssssssssssssssss");
		log.info(jsonRequest.toString());
		return SUCCESS;
	}
}
