package com.tanerdiler.web.comment.view;

import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.tanerdiler.model.Comment;

public class CommentListView<T extends Comment> extends Panel {

	private static final long serialVersionUID = -1559969179497014427L;

	public CommentListView(String id, List<T> comments) {
		super(id);
		add(new ListView<T>("comment-list", comments) {

			private static final long serialVersionUID = -7943834462430393865L;

			@Override
			protected void populateItem(ListItem<T> item) {
				Comment comment = item.getModelObject();
			 	item.add(new CommentView("comment-view", comment));
			}
		});
	}

}
