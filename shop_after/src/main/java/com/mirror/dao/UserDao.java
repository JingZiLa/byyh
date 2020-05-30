package com.mirror.dao;

import com.mirror.domain.User;

/**
 * 用户Dao层接口
 * @author JING
 *
 */
public interface UserDao extends PublicDao<User>{

	/* void save(User user); */

	User login(User user);

}
