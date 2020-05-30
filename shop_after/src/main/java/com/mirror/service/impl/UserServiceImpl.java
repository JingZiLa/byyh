package com.mirror.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.mirror.dao.UserDao;
import com.mirror.domain.PageBean;
import com.mirror.domain.User;
import com.mirror.service.UserService;
import com.mirror.utils.MD5Utils;
/**
 * 用户Service层实现类
 * @author JING
 *
 */
@Transactional
public class UserServiceImpl implements UserService {
	//注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 业务层：注册用户方法
	 */
	public void regist(User user) {
//		对用户密码进行加密处理
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
//		对用户状态设置为正常
		user.setUser_state("1");
		userDao.save(user);
	}
	@Override
	/**
	 * 业务层：用户登陆方法
	 */
	public User login(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		System.out.println(user.getUser_password());
		return userDao.login(user);
	}
	@Override
	/**
	 * 业务层：查询所有用户方法
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}
	@Override
	/**
	 * 业务层：根据ID查询用户具体信息方法
	 */
	public User findById(Long id) {
		return userDao.findById(id);
	}
	@Override
	/**
	 * 业务层：查询所有用户方法---带条件---分页
	 */
	public PageBean<User> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<User> pageBean = new PageBean<User>();
		// 封装当前页数:
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数:
		pageBean.setPageSize(pageSize);
		// 封装总记录数:
		// 调用DAO:
		Integer totalCount = userDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 封装总页数:
		Double ceil = Math.ceil(totalCount.doubleValue() / pageSize);
		pageBean.setTotalPage(ceil.intValue());
		// 封装每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<User> list = userDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	/**
	 * 业务层:添加用户
	 */
	public void addUser(User user) {
		user.setUser_status("0");
		user.setUser_addtime(new Date());
		userDao.save(user);
	}
	@Override
	public void deleteUser(User users) {
		if(users.getUser_headimge()!= null) {
			File file = new File(users.getUser_headimge());
			if(file.exists()) {
				file.delete();
			}
		}
		userDao.delete(users);
	}
	@Override
	/**
	 * 业务层:修改用户信息方法
	 */
	public void editUser(User user) {
		user.setUser_addtime(new Date());
		userDao.update(user);
	}
	
}
