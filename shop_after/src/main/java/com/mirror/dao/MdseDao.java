package com.mirror.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;

import com.mirror.domain.Mdse;
/**
 * 商品Dao接口
 * @author JING
 *
 */
public interface MdseDao extends PublicDao<Mdse>{

	Set<Mdse> QBCFindMdse(DetachedCriteria detachedCriteria);

	void editMdse(Mdse mdse);

}
