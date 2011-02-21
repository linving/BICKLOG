package com.tanerdiler.model;

import java.io.Serializable;

public interface Commentable<T extends Comment> extends Serializable {
	public void addComment(T comment);
}
