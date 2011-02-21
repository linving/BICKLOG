package com.tanerdiler.web.comment.view;

import java.util.Calendar;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.tanerdiler.model.Comment;
import com.tanerdiler.web.model.HumanFriendlyTimeModel;

public class CommentView extends Panel {

	private static final long serialVersionUID = -2111145843910297444L;
	
	public CommentView(String id) {
		super(id);
		add(CSSPackageResource.getHeaderContribution(this.getClass(), "commentview.css"));
	}
	
	public CommentView(String id, Comment comment){
		super(id, new Model<Comment>(comment));
		add(CSSPackageResource.getHeaderContribution(this.getClass(), "commentview.css"));
		add(new Label("comment-writtenby", comment.getAuthorName()));
		add(new Label("comment-comment", comment.getComment()));
		add(new Label("comment-createdtime", new HumanFriendlyTimeModel(new PropertyModel<Calendar>(comment, "createdTime"))));
	}

}
