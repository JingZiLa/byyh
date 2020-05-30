package com.mirror.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mirror.dao.RoleDao;
import com.mirror.domain.PageBean;
import com.mirror.domain.Role;
import com.mirror.service.RoleService;

/**
 * 角色Service接口实现
 * 
 * @author JING
 *
 */
public class RoleServiceImpl implements RoleService {
	// 注入RoleDao
	private RoleDao roleDao;

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	/**
	 * 查询所有管理员
	 */
	public PageBean<Role> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Role> pageBean = new PageBean<Role>();
		// 封装当前页数:
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数:
		pageBean.setPageSize(pageSize);
		// 封装总记录数:
		// 调用DAO:
		Integer totalCount = roleDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 封装总页数:
		Double ceil = Math.ceil(totalCount.doubleValue() / pageSize);
		pageBean.setTotalPage(ceil.intValue());
		// 封装每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<Role> list = roleDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);

		return pageBean;
	}

}
