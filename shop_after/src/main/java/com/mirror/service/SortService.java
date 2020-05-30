package com.mirror.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mirror.domain.PageBean;
import com.mirror.domain.Sort;

/**
 * 商品分类业务层接口
 * @author JING
 *
 */
public interface SortService{

	List<Sort> findAll();

	PageBean<Sort> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void saveMdse(Sort sort);

	Sort findById(Long sort_id);

	void deleteSort(Sort sort);

}
