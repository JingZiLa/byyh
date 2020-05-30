package com.mirror.service;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;

import com.mirror.domain.Mdse;
import com.mirror.domain.PageBean;
/**
 * 商品业务层接口
 * @author JING
 *
 */
public interface MdseService{

	/**
	 * 查询所有商品
	 * @return
	 */
	 PageBean<Mdse> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);
	/**
	 * 保存商品
	 * @param mdse
	 */
	void saveMdse(Mdse mdse);
	/**
	 * 删除商品
	 * @param mdse
	 */
	void deleteMdse(Mdse mdse);
	/**
	 * 根据ID查询商品
	 * @param mdse_id
	 * @return
	 */
	Mdse findById(Long mdse_id);
	/**
	 * 修改商品
	 * @param mdse
	 */
	void editMdse(Mdse mdse);
	
	Set<Mdse> findByIds(DetachedCriteria detachedCriteria);
	
	List<Mdse> findAll();
	
	void QBCFindMdse(DetachedCriteria detachedCriteria);

}
