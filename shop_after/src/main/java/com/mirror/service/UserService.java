package com.mirror.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mirror.domain.PageBean;
import com.mirror.domain.Sort;
import com.mirror.domain.User;

/**
 * 用户Service层接口
 * @author JING
 *
 */
public interface UserService {

	void regist(User user);

	User login(User user);

	List<User> findAll();

	User findById(Long id);

	PageBean<User> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);
	void addUser(User user);

	void deleteUser(User users);

	void editUser(User user);

}
