package com.mirror.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mirror.domain.Mdse;
import com.mirror.domain.PageBean;
import com.mirror.domain.Sort;
import com.mirror.service.MdseService;
import com.mirror.service.SortService;
import com.mirror.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MdseAction extends ActionSupport implements ModelDriven<Mdse> {
	// 模型驱动使用的对象：
	private Mdse mdse = new Mdse();

	@Override
	public Mdse getModel() {
		return mdse;
	}

	// 注入SortService
	private SortService sortService;

	public void setSortService(SortService sortService) {
		this.sortService = sortService;
	}

	// 注入MdseService
	private MdseService mdseService;

	public void setMdseService(MdseService mdseService) {
		this.mdseService = mdseService;
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
	private Date mdse_end_time;

	public Date getMdse_end_time() {
		return mdse_end_time;
	}

	public void setMdse_end_time(Date mdse_end_time) {
		this.mdse_end_time = mdse_end_time;
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
	/**
	 * 批量删除ID
	 */
	private String checkbox;
	
	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

	public String findAll() {
		System.out.println(pageSize+"sssssssssssssss"+ currPage);
		// 接收参数：分页参数
		// 创建离线条件查询对象:
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Mdse.class);
		// 设置条件:
		if (mdse.getMdse_addtime() != null) {
			detachedCriteria.add(Restrictions.ge("mdse_addtime", mdse.getMdse_addtime()));
		}
		if (mdse_end_time != null) {
			detachedCriteria.add(Restrictions.le("mdse_addtime", mdse_end_time));
		}
		
		if (mdse.getMdse_name() != null && !"".equals(mdse.getMdse_name())) {
			detachedCriteria.add(Restrictions.ilike("mdse_name", "%" + mdse.getMdse_name() + "%"));
		}
		/*
		 * if(mdse.getSort()!= null) { if(mdse.getSort().getSort_id()!= null && !
		 * "".equals(mdse.getSort().getSort_id())) {
		 * detachedCriteria.add(Restrictions.eq("sort.sort_id",
		 * mdse.getSort().getSort_id())); } }
		 */
		PageBean<Mdse> page = mdseService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(page);
		
		return"findAllSuccess";
	}
	
	/**
	 * 跳转添加商品页面方法
	 * 
	 * @return
	 */
	public String saveUI() {
		List<Sort> list = sortService.findAll();
		for (Sort sort : list) {
			System.out.println(sort.getSort_name());
		}
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUISuccess";
	}

	/**
	 * 保存商品方法
	 * 
	 * @return
	 * @throws IOException
	 */
	public String saveMdse() throws IOException {
		
		// 上传图片:
		if (upload != null) {
			// 文件上传：
			// 设置文件上传路径:
			String path = "E:/upload";
			// 一个目录下存放的相同文件名：随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// 一个目录下存放的文件过多：目录分离
//			String realPath = UploadUtils.getPath(uuidFileName);
			// 创建目录:
			String url = path; //+ realPath
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 文件上传:
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			// 设置image的属性的值:
			mdse.setMdse_imge(uuidFileName);
			System.out.println(uuidFileName);
		}
		// 保存商品
		mdseService.saveMdse(mdse);
		return "saveMdseSuccess";
	}

	/**
	 * 删除商品方法
	 * 
	 * @return
	 */
	public String deleteMdse() {
		
		// 先查询再删除
		mdse = mdseService.findById(mdse.getMdse_id());
		// 删除图片
		if (mdse.getMdse_imge() != null) {
			File file = new File(mdse.getMdse_imge());
			if (file.exists()) {
				file.delete();
			}
		}
		mdseService.deleteMdse(mdse);
		return "deleteMdseSuccess";
	}

	/**
	 * 修改商品
	 * 
	 * @return
	 * @throws IOException
	 */
	public String editMdse() throws IOException {
		Mdse mdses = mdseService.findById(mdse.getMdse_id());
		mdse.setMdse_sales(mdses.getMdse_sales());
		mdse.setMdse_imge(mdses.getMdse_imge());
		System.out.println("ssssssssssssssssss");
		// 上传图片:
		if (upload != null) {
			// 删除图片
			if (mdse.getMdse_imge() != null) {
				File file = new File(mdse.getMdse_imge());
				if (file.exists()) {
					file.delete();
				}
			}
			// 文件上传：
			// 设置文件上传路径:
			String path = "E:/upload";
			// 一个目录下存放的相同文件名：随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// 一个目录下存放的文件过多：目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			// 创建目录:
			String url = path + realPath;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 文件上传:
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			// 设置image的属性的值:
			mdse.setMdse_imge(url + "/" + uuidFileName);
			System.out.println(url + "/" + uuidFileName);
		}
		mdseService.editMdse(mdse);
		return "editMdseSuccess";
	}

	/**
	 * 修改商品页面跳转
	 * 
	 * @return
	 */
	public String editUI() {
		// 查询数据
		Mdse mdses = mdseService.findById(mdse.getMdse_id());
		List<Sort> list = sortService.findAll();
		// 数据回显
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(mdses);
		return "editUI";
	}
	/**
	 * 查询促销商品
	 * @return
	 */
	public String SalesMdse() {
		System.out.println("sssssssssssssssssssssss"+mdse.getMdse_id());
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Mdse.class);
		if(mdse.getMdse_id() != null) {
			Mdse mdses = mdseService.findById(mdse.getMdse_id());
			mdses.setMdse_status("2");
			mdseService.editMdse(mdses);
		}
		detachedCriteria.add(Restrictions.eq("mdse_status", "3"));
		
		PageBean<Mdse> findByPage = mdseService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(findByPage);
		return "SalesM1dse";
	}
	
}
