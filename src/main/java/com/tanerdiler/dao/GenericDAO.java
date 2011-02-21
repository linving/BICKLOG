/**
 * 
 */
package com.tanerdiler.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tanerdiler.model.GenericModel;

/**
 * @author tdiler
 *
 */
@Repository
@Transactional
public abstract class GenericDAO<PK extends Serializable, M extends GenericModel<PK>> implements IGenericDAO<PK, M> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2252797124185810388L;
	
	@Autowired
	SessionFactory sessionFactory;
	
	private Class<M> clazz;
	
	/**
	 * @return the clazz
	 */
	protected Class<M> getClazz() {
		return clazz;
	}

	@SuppressWarnings("unchecked")
	public GenericDAO(){
		this.clazz = (Class<M>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public M find(PK id) {
		Session session = this.getSession();
		return (M) session.get(this.clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<M> getList(Criterion[] criterions, String ...orderFields) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(this.clazz);
		if(criterions != null && criterions.length > 0){
			for(Criterion criterion : criterions){
				criteria.add(criterion);
			}
		}
		if(orderFields != null && orderFields.length > 0){
			for(String orderField : orderFields){
				criteria.addOrder(Order.asc(orderField));
			}
		}
		List<M> result = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return result;
	}

	public void remove(M model) {
		this.getSession().delete(model);
	}

	private static final Set<Class<?>> classesNotIncludeCreatedTimeField = new HashSet<Class<?>>();
	private static final Set<Class<?>> classesIncludeCreatedTimeField = new HashSet<Class<?>>();

	public PK saveOrUpdate(M model) {
		Session session = this.getSession();
		session.saveOrUpdate(model);
		return model.getId();
	}

	private void setCreatedTime(M model) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		boolean setCreatedTime = false;
		if(classesIncludeCreatedTimeField.contains(this.clazz)){
			setCreatedTime = true;
		} else if(!classesNotIncludeCreatedTimeField.contains(this.clazz)){
			Field[] fields = this.clazz.getFields();
			for(Field field: fields){
				System.out.println("Field Name --->"+field.getName().toLowerCase());
				if(field.getName().toLowerCase().indexOf("createdtime")>=0){
					classesIncludeCreatedTimeField.add(this.clazz);
					System.out.println("Set CreatedTime");
					setCreatedTime = true;
				} else {
					classesNotIncludeCreatedTimeField.add(this.clazz);
				}
			}
		}
		if(setCreatedTime){
			Method method = this.clazz.getMethod("setCreatedTime", Calendar.class);
			method.invoke(model, Calendar.getInstance());
			System.out.println("Created Time Setted");
		}
	}

}
