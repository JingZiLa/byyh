package com.mirror.web.applet_action;

import org.hibernate.criterion.DetachedCriteria;

import com.mirror.domain.PageBean;
import com.mirror.domain.Role;
import com.mirror.domain.User;
import com.mirror.service.RoleService;
import com.mirror.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户角色Action类
 * 
 * @author JING
 *
 */
public class AppletRoleAction extends ActionSupport implements ModelDriven<Role> {
	// 模型驱动使用的对象
	private Role role = new Role();

	@Override
	public Role getModel() {
		return role;
	}

	// 注入RoleService
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	/**
	 * 
	 * 注入用户Service
	 */
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 使用set方法的方式接收数据:
	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	// 使用set方法接受每页显示记录数
	private Integer pageSize = 5;

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 5;
		}
		this.pageSize = pageSize;
	}

	/**
	 * 查询所有管理员
	 * 
	 * @return
	 */
	public String findAll() {
		// 接收参数：分页参数
		// 创建离线条件查询对象:
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		// 设置条件:
//		if (user.getUser_addtime() != null) {
//			System.out.println(user.getUser_addtime());
//			detachedCriteria.add(Restrictions.ge("user_addtime", user.getUser_addtime()));
//		}
//		if (user_end_time != null) {
//			detachedCriteria.add(Restrictions.le("user_addtime", user_end_time));
//		}
//		if (user.getUser_name() != null && !"".equals(user.getUser_name())) {
//			detachedCriteria.add(Restrictions.ilike("user_name", "%" + user.getUser_name() + "%"));
//		}

		PageBean<User> page = userService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(page);
		return "findAllSuccess";
	}
}
