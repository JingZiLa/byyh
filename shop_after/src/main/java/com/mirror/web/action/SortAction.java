package com.mirror.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mirror.domain.Mdse;
import com.mirror.domain.PageBean;
import com.mirror.domain.Sort;
import com.mirror.service.SortService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 商品分类Action
 * 
 * @author JING
 *
 */
public class SortAction extends ActionSupport implements ModelDriven<Sort> {
	// 模型驱动使用的对象
	private Sort sort = new Sort();

	@Override
	public Sort getModel() {
		return sort;
	}

	// 注入Service
	private SortService sortService;

	public void setSortService(SortService sortService) {
		this.sortService = sortService;
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
	private Integer pageSize = 10;

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 10;
		}
		this.pageSize = pageSize;
	}

	// 接收数据:
	private Date sort_end_time;

	public Date getSort_end_time() {
		return sort_end_time;
	}

	public void setSort_end_time(Date sort_end_time) {
		this.sort_end_time = sort_end_time;
	}

	/**
	 * 查询所有商品分类
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findAll() throws IOException {
		// 接收参数：分页参数
		// 创建离线条件查询对象:
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Sort.class);
		// 设置条件:
		if (sort.getSort_addtime() != null) {
			System.out.println(sort.getSort_addtime());
			detachedCriteria.add(Restrictions.ge("sort_addtime", sort.getSort_addtime()));
		}
		if (sort_end_time != null) {
			detachedCriteria.add(Restrictions.le("sort_addtime", sort_end_time));
		}
		if (sort.getSort_name() != null && !"".equals(sort.getSort_name())) {
			detachedCriteria.add(Restrictions.ilike("sort_name", "%" + sort.getSort_name() + "%"));
		}
		PageBean<Sort> page = sortService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(page);
		return "findAllSuccess";
	}
	/**
	 * 跳转添加分类方法
	 * @return
	 */
	public String saveUI() {
		return "saveUISuccess";
	}
	/**
	 * 添加分类方法
	 * @return
	 */
	public 	String saveMdse() {
		sortService.saveMdse(sort);
		return "saveMdSuccess";
	}
	/**
	 * 删除分类方法
	 * @return
	 */
	public String deleteSort() {
		// 先查询再删除
		sort = sortService.findById(sort.getSort_id());
		sortService.deleteSort(sort);
		return "deleteSortSuccess";
	}
}
