package com.tanerdiler.web.article.page;

import java.io.Serializable;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTarget.IJavascriptResponse;
import org.apache.wicket.ajax.AjaxRequestTarget.IListener;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.CSSPackageResource;

import com.tanerdiler.model.Article;
import com.tanerdiler.model.ArticleComment;
import com.tanerdiler.web.article.view.ArticleView;
import com.tanerdiler.web.comment.view.CommentFormView;
import com.tanerdiler.web.comment.view.CommentListView;
import com.tanerdiler.web.page.MainPage;

public class ArticleViewPage extends MainPage {
	private static final String COMMENT_FORM = "comment-form";
	private static final String ARTICLE_COMMENTS = "article-comments";
	private static final String ARTICLE_VIEW = "article-view";
	
	private static final HeaderContributor ARTICLE_CSS = CSSPackageResource.getHeaderContribution(ArticleViewPage.class, "article.css");
	public ArticleViewPage(Article article) {
		add(new ArticleView(ARTICLE_VIEW, article));
		add(new CommentListView<ArticleComment>(ARTICLE_COMMENTS, article.getComments()).setOutputMarkupId(true));
		CommentFormView<ArticleComment> commentForm = new CommentFormView<ArticleComment>(COMMENT_FORM, article);
		class CommentFormSubmitListener implements IListener, Serializable {

			private static final long serialVersionUID = 9110852941110463292L;

			public void onAfterRespond(Map<String, Component> arg0,
					IJavascriptResponse arg1) {
				
			}

			public void onBeforeRespond(Map<String, Component> arg0,
					AjaxRequestTarget arg1) {
				arg1.addComponent(ArticleViewPage.this.get(ARTICLE_COMMENTS));
			}
			
		}
		commentForm.addListener(new CommentFormSubmitListener());
		add(commentForm);
		add(ARTICLE_CSS);
	}

}
