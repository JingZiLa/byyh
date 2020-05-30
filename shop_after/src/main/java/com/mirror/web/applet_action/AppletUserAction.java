package com.mirror.web.applet_action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mirror.domain.JsonRequest;
import com.mirror.domain.Mdse;
import com.mirror.domain.PageBean;
import com.mirror.domain.User;
import com.mirror.service.UserService;
import com.mirror.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户Action类
 * 
 * @author JING
 *
 */
public class AppletUserAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动对象：
	private User user = new User();

	public User getModel() {
		return user;
	}

	// 注入Service
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

	// 接收数据:
	private Date user_end_time;

	public Date getUser_end_time() {
		return user_end_time;
	}

	public void setUser_end_time(Date user_end_time) {
		this.user_end_time = user_end_time;
	}

	/**
	 * 文件上传提供的三个属性:
	 */
	private String uploadFileName; // 文件名称
	private File upload; // 上传文件
	private String uploadContentType; // 文件类型

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	private JsonRequest<User> jsonRequest = new JsonRequest<User>();

	public JsonRequest<User> getJsonRequest() {
		return jsonRequest;
	}

	public void setJsonRequest(JsonRequest<User> jsonRequest) {
		this.jsonRequest = jsonRequest;
	}

	Logger log = Logger.getLogger(this.getClass().getName());
	/**
	 * 用户登陆的方法：login
	 */
	public String login() {
		System.out.println(user.getUser_code());
		User existUser = userService.login(user);
		if (existUser != null) {
			// 判断用户账号状态是否正常
			if ("0".equals(existUser.getUser_status())) {
				// 登陆成功
				jsonRequest.setT(existUser);
				log.info(jsonRequest.toString());
				return SUCCESS;
			} else {
				// 用户账号为冻结状态登陆失败
				// 设置回显信息
				jsonRequest.setStr("此用户账号已被冻结，请联系客服了解详情。");
				return LOGIN;
			}
		} else {
			// 登陆失败
			// 设置错误信息
			jsonRequest.setStr("找不到该用户：请确认用户账号密码。");
			return LOGIN;
		}

	}

	/**
	 * 查询所有用户方法
	 * 
	 * @return
	 */
	public String findAll() {
		// 接收参数：分页参数
		// 创建离线条件查询对象:
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		// 设置条件:
		if (user.getUser_addtime() != null) {
			System.out.println(user.getUser_addtime());
			detachedCriteria.add(Restrictions.ge("user_addtime", user.getUser_addtime()));
		}
		if (user_end_time != null) {
			detachedCriteria.add(Restrictions.le("user_addtime", user_end_time));
		}
		if (user.getUser_name() != null && !"".equals(user.getUser_name())) {
			detachedCriteria.add(Restrictions.ilike("user_name", "%" + user.getUser_name() + "%"));
		}

		PageBean<User> page = userService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(page);
		return "findAllSuccess";
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 * @throws IOException
	 */
	public String addUser() throws IOException {
		// 上传图片:
		if (upload != null) {
			// 文件上传：
			// 设置文件上传路径:
			String path = "E:/upload";
			// 一个目录下存放的相同文件名：随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			/*
			 * // 一个目录下存放的文件过多：目录分离 String realPath = UploadUtils.getPath(uuidFileName);
			 */
			// 创建目录:
//			String url = path + realPath;
			String url = path;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 文件上传:
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			// 设置image的属性的值:
			user.setUser_headimge(uuidFileName);
			System.out.println(uuidFileName);
		}
		userService.addUser(user);

		return "addUserSuccess";
	}

	/**
	 * 添加用户跳转
	 * 
	 * @return
	 */
	public String addUserUI() {
		return "addUserUI";
	}

	/**
	 * 根据ID删除用户功能
	 * 
	 * @return
	 */
	public String deleteUser() {
		User users = userService.findById(user.getUser_id());
		userService.deleteUser(users);

		return "deleteUserSuccess";

	}

	/**
	 * 修改用户页面跳转
	 * 
	 * @return
	 */
	public String editUI() {
		User users = userService.findById(user.getUser_id());
		ActionContext.getContext().getValueStack().push(users);
		return "editUI";
	}

	/**
	 * 修改用户信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String editUser() throws IOException {
		User users = userService.findById(user.getUser_id());
		System.out.println("ssssssss");
		if (user.getUser_city() == null && "".equals(user.getUser_city())) {
			System.out.println("ssssssss");
			user.setUser_city(users.getUser_city());
		}
		if (user.getUser_area() == null && "".equals(user.getUser_area())) {
			System.out.println("ssssssss");
			user.setUser_area(users.getUser_area());
		}
		if (user.getUser_province() == null && "".equals(user.getUser_province())) {
			System.out.println("ssssssss");
			user.setUser_province(users.getUser_province());
		}
		System.out.println("ssssssss");
		// 上传图片:
		if (upload != null) {
			System.out.println("ssssssss");
			if (users.getUser_headimge() != null) {
				System.out.println("ssssssss");
				File file = new File(users.getUser_headimge());
				if (file.exists()) {
					file.delete();
				}
			}

			// 文件上传：
			// 设置文件上传路径:
			String path = "E:/upload";
			// 一个目录下存放的相同文件名：随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			/*
			 * // 一个目录下存放的文件过多：目录分离 String realPath = UploadUtils.getPath(uuidFileName);
			 */
			// 创建目录:
//					String url = path + realPath;
			String url = path;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 文件上传:
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			// 设置image的属性的值:
			user.setUser_headimge(uuidFileName);
			System.out.println(uuidFileName);
		}
		System.out.println("ssssssss");
		userService.editUser(user);
		System.out.println("ssssssss");
		return "editUserSuccess";
	}

	/**
	 * 查询所有用户角色
	 * 
	 * @return
	 */
	public String userRole() {
		// 接收参数：分页参数
		// 创建离线条件查询对象:
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		// 设置条件:
//				if (user.getUser_addtime() != null) {
//					System.out.println(user.getUser_addtime());
//					detachedCriteria.add(Restrictions.ge("user_addtime", user.getUser_addtime()));
//				}
//				if (user_end_time != null) {
//					detachedCriteria.add(Restrictions.le("user_addtime", user_end_time));
//				}
//				if (user.getUser_name() != null && !"".equals(user.getUser_name())) {
//					detachedCriteria.add(Restrictions.ilike("user_name", "%" + user.getUser_name() + "%"));
//				}

		PageBean<User> page = userService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(page);
		return "userRoleSuccess";
	}
}
