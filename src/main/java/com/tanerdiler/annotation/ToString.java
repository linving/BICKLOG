package com.tanerdiler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ToString {
	public final static String TYPE_SIMPLE = "simple";
	public final static String TYPE_COLLECTION = "collection";
	
	boolean value();
	String type();
}
