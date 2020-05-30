package com.mirror.service;

import org.hibernate.criterion.DetachedCriteria;

import com.mirror.domain.PageBean;
import com.mirror.domain.Role;

/**
 * 角色Service接口
 * @author JING
 *
 */
public interface RoleService {

	PageBean<Role> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

}
