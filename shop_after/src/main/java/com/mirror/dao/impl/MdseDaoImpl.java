package com.mirror.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;

import com.mirror.dao.MdseDao;
import com.mirror.domain.Mdse;
/**
 * 商品Dao接口实现
 * @author JING
 *
 */
public class MdseDaoImpl extends PublicDaoImpl<Mdse> implements MdseDao{

	@Override
	public Set<Mdse> QBCFindMdse(DetachedCriteria detachedCriteria) {
		List<Mdse> list = (List<Mdse>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Set<Mdse> set = new HashSet<Mdse>();
		set.addAll(list);
		
		return set ;
	}

	@Override
	public void editMdse(Mdse mdse) {
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(mdse);
	}

}
