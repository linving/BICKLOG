package com.tanerdiler.web.article.view;

import java.util.Calendar;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.tanerdiler.model.Article;
import com.tanerdiler.util.NumberUtils;
import com.tanerdiler.web.article.page.ArticleViewPage;
import com.tanerdiler.web.model.HumanFriendlyTimeModel;

public class ArticleFeedView extends Panel{

	private static final long serialVersionUID = 7501206672669149327L;
	
	public ArticleFeedView(String id) {
		super(id);
	}
	
	public ArticleFeedView(String id, Article article) {
		super(id, new Model<Article>(article));
		add(new Label("article-writtenby", article.getPerson().getFirstName()+" "+article.getPerson().getLastName()));
		add(new Label("article-writtenon", new HumanFriendlyTimeModel(new PropertyModel<Calendar>(article, "createdTime"))));
		add(new Label("article-intro", article.getIntroText()).setEscapeModelStrings(false));
		add(new Label("article-viewcount", ""+article.getViewCount()));
		add(new Label("article-rating", NumberUtils.formatNumber(article.getRating())));
		add(new Label("article-commentcount", article.getComments().size()+""));
		class ArticleViewLink extends Link<Article> {
			private static final long serialVersionUID = 4877909857750583066L;

			public ArticleViewLink(String id) {
				super(id);
			}

			@Override
			public void onClick() {
				Article article = (Article) getParent().getDefaultModelObject();
		        setResponsePage(new ArticleViewPage(article));
			}
		};
		Link<Article> articleTitleLink = new ArticleViewLink("article-viewarticle");
		Link<Article> articleReadmore = new ArticleViewLink("article-readmore");
		articleTitleLink.add(new Label("article-title", article.getTitle()));
		add(articleTitleLink);
		add(articleReadmore);
	}

}
