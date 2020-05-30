package com.mirror.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.mirror.dao.MdseDao;
import com.mirror.domain.Mdse;
import com.mirror.domain.PageBean;
import com.mirror.service.MdseService;

/**
 * 商品业务层实现
 * 
 * @author JING
 *
 */
@Transactional
public class MdseServiceImpl implements MdseService {

	// 注入Dao
	private MdseDao mdseDao;

	public void setMdseDao(MdseDao mdseDao) {
		this.mdseDao = mdseDao;
	}

	@Override
	/**
	 * 业务层查询所有商品--带条件(分页)
	 */
	public PageBean<Mdse> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Mdse> pageBean = new PageBean<Mdse>();
		// 封装当前页数:
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数:
		pageBean.setPageSize(pageSize);
		// 封装总记录数:
		// 调用DAO:
		Integer totalCount = mdseDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 封装总页数:
		Double ceil = Math.ceil(totalCount.doubleValue() / pageSize);
		pageBean.setTotalPage(ceil.intValue());
		// 封装每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<Mdse> list = mdseDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	/**
	 * 保存商品
	 * 
	 * @param mdse
	 */
	public void saveMdse(Mdse mdse) {
		Date date = new Date();
		mdse.setMdse_addtime(date);
		mdseDao.save(mdse);
	}
	
	@Override
	/**
	 * 删除商品方法
	 */
	public void deleteMdse(Mdse mdse) {
		mdseDao.delete(mdse);
	}

	@Override
	/**
	 * 根据ID查询商品方法：
	 */
	public Mdse findById(Long mdse_id) {
		return mdseDao.findById(mdse_id);
	}

	@Override
	/**
	 * 修改商品
	 */
	public void editMdse(Mdse mdse) {
		Date date = new Date();
		mdse.setMdse_addtime(date);
		
		mdseDao.editMdse(mdse);
	}

	/**
	 * 查询多个商品信息
	 */
	@Override
	public Set<Mdse> findByIds(DetachedCriteria detachedCriteria) {
		return mdseDao.QBCFindMdse(detachedCriteria);
	}
	/**
	 * 查询所有商品信息
	 */
	@Override
	public List<Mdse> findAll() {
		return mdseDao.findAll();
	}

	@Override
	/**
	 * 条件查询所有商品
	 */
	public void QBCFindMdse(DetachedCriteria detachedCriteria) {
		mdseDao.QBCFindMdse(detachedCriteria);
	}
}
