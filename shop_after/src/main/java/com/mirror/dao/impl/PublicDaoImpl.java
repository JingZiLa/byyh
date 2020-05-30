package com.mirror.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.mirror.dao.PublicDao;

/**
 * 公共的Dao接口的实现类
 * 
 * @author JING
 *
 */
public class PublicDaoImpl<T> extends HibernateDaoSupport implements PublicDao<T> {

	private Class clazz;

	// 提供构造方法：在构造方法中传入具体类型的class
	/**
	 * 不想子类上有构造方法，必须在父类中提供无参数的构造，在无参构造中获得具体类型的Class。 具体类型的Class是参数类型中的实际类型参数。
	 */
	public PublicDaoImpl() {
		// 反射：第一步获得Class
		Class clazz = this.getClass();// 正在被调用那个类的Class,CustomerDaoImpl或者LinkManDaoImpl。
		// 查看JDK的API
		Type type = clazz.getGenericSuperclass();// 参数化类型：BaseDaoImpl<Customer>，BaseDaoImpl<LinkMan>
		System.out.println(type);
		// 得到这个type就是一个参数化的类型， 将type强转成参数化的类型:
		ParameterizedType pType = (ParameterizedType) type;
		// 通过参数化类型获得实际类型参数:得到一个实际类型参数的数组？Map<String,Integer>.
		Type[] types = pType.getActualTypeArguments();
		// 只获得第一个实际类型参数即可。
		this.clazz = (Class) types[0];// 得到Customer、LinkMan、User
	}
	//保存方法
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}
	//更新/修改方法
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	//删除方法
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}
	//根据ID查询信息方法
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}
	//查询所有方法
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}
	//统计个数方法
	public Integer findCount(DetachedCriteria detachedCriteria) {
		//设置条件
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0) {
			return list.get(0).intValue();
		}
		return null;
	}
	//分页查询
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
			detachedCriteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
	}

}
