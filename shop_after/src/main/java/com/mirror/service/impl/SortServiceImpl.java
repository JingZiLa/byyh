package com.mirror.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.mirror.dao.SortDao;
import com.mirror.domain.PageBean;
import com.mirror.domain.Sort;
import com.mirror.service.SortService;

/**
 * 商品分类业务层接口实现
 * @author JING
 *
 */
@Transactional
public class SortServiceImpl implements SortService {
	
	//注入Dao
	private SortDao sortDao;
	
	public void setSortDao(SortDao sortDao) {
		this.sortDao = sortDao;
	}
	
	@Override
	/**
	 * 业务层查询所有商品分类方法
	 */
	public List<Sort> findAll() {
		return sortDao.findAll();
	}

	@Override
	/**
	 * 业务层查询所有商品--带条件(分页)
	 */
	public PageBean<Sort> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Sort> pageBean = new PageBean<Sort>();
		// 封装当前页数:
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数:
		pageBean.setPageSize(pageSize);
		// 封装总记录数:
		// 调用DAO:
		Integer totalCount = sortDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 封装总页数:
		Double ceil = Math.ceil(totalCount.doubleValue() / pageSize);
		pageBean.setTotalPage(ceil.intValue());
		// 封装每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<Sort> list = sortDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	/**
	 * 业务层添加分类方法
	 */
	@Override
	public void saveMdse(Sort sort) {
		Date date = new Date();
		sort.setSort_addtime(date);
		sortDao.save(sort);
	}

	@Override
	/**
	 * 根据ID查询分类方法
	 */
	public Sort findById(Long sort_id) {
		return sortDao.findById(sort_id);
	}

	@Override
	/**
	 * 删除分类方法
	 */
	public void deleteSort(Sort sort) {
		sortDao.delete(sort);
	}

	
	
}
