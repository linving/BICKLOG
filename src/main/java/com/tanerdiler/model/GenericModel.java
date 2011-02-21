/**
 * 
 */
package com.tanerdiler.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;

import javax.persistence.MappedSuperclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tanerdiler.annotation.ToString;


/**
 * @author tdiler
 *
 */
@MappedSuperclass
public abstract class GenericModel<PK extends Serializable> implements Serializable {
	
	private static final Log logger = LogFactory.getLog(GenericModel.class);
	
	private static final long serialVersionUID = 1259364458603422171L;
	
	/**
	 * default constructor
	 */
	public GenericModel(){
		// DO NOTHING
	}

	/**
	 * @param id
	 */
	public GenericModel(PK id){
		this.setId(id);
	}

	/**
	 * abstract setter method for primary key. It is been calling from constructor with id parameter.
	 * to bypass usage of setter method.
	 * @param id
	 */
	public abstract void setId(PK id);

	/**
	 * @return
	 */
	public abstract PK getId();
	
	public abstract Object getEqualityCriterian();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append(this.getClass().getSimpleName()+"{\n");
		Field[] fields = this.getClass().getFields();
		for(Field field : fields){
			try {
				field.setAccessible(true);
				ToString toString = field.getAnnotation(ToString.class);
				if(toString == null || !toString.value()){
					continue;
				}
				String type = toString.type();
				if(type == null || ToString.TYPE_SIMPLE.equals(toString.type())){
					strb.append(field.getName()+":");
					strb.append(field.get(this).toString());
				} else if(type != null && ToString.TYPE_COLLECTION.equals(toString.type())) {
					Collection collection = (Collection)field.get(this);
					for(Object item : collection){
						strb.append(item.toString());
					}
				}
			} catch (Exception e) {
				logger.warn(e);
			}
			strb.append("\n");
		}
		strb.append(this.getClass().getSimpleName()+"}");
		return strb.toString();
	}
	
//	/* (non-Javadoc)
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if(this == obj){
//			return true;
//		}
//		if(obj instanceof GenericModel<?>){
//			GenericModel<?> __obj = (GenericModel<?>) obj;
//			if(this.getEqualityCriterian() != null && this.getEqualityCriterian().equals(__obj.getEqualityCriterian())){
//				return true;
//			}
//		}
//		return false;
//	}
}
