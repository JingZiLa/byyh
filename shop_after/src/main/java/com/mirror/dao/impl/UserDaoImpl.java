package com.mirror.dao.impl;

import java.util.List;

import com.mirror.dao.UserDao;
import com.mirror.domain.User;
/**
 * 用户Dao层实现类
 * @author JING
 *
 */
public class UserDaoImpl extends PublicDaoImpl<User> implements UserDao {
	/**
	 * Dao层登陆用户方法
	 */
	public User login(User user) {
		
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ? and user_password = ?", user.getUser_code(),user.getUser_password());
			if(list.size()> 0) {
				return list.get(0);
			}
		return null;
	}
}
