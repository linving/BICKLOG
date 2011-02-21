/**
 * 
 */
package com.tanerdiler.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * @author tdiler
 *
 */
@MappedSuperclass
public abstract class GenericPK<T extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5015364268441716598L;
	
	public abstract T getPK();
	
	public abstract void setPK(T pk);
}
