/**
 * 
 */
package com.tanerdiler.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

import com.tanerdiler.model.GenericModel;

/**
 * @author tdiler
 *
 */
public interface IGenericDAO<PK extends Serializable, M extends GenericModel<PK>> extends Serializable {
	public M find(PK id);
	public List<M> getList(Criterion[] criterions, String ...orderFields);
	public PK saveOrUpdate(M model);
	public void remove(M model);
	public void setSessionFactory(SessionFactory sessionFactory);
}
