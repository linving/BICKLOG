package com.tanerdiler.filter;

import java.io.Serializable;

import org.hibernate.criterion.Criterion;

public abstract class Predicate<T extends Serializable> {
	
	public abstract boolean apply(T input);
	
	public abstract Criterion[] getCriteria();
	
	
}
