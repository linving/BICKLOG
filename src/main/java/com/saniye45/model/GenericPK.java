/**
 * 
 */
package com.saniye45.model;

import java.io.Serializable;

/**
 * @author tdiler
 *
 */
public abstract class GenericPK<T extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5015364268441716598L;
	
	public abstract T getPK();
}
