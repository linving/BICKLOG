package com.tanerdiler.web.article.view;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.tanerdiler.bpo.UserBPO;
import com.tanerdiler.model.Article;
import com.tanerdiler.web.article.page.ArticleFormPage;
import com.tanerdiler.web.model.HumanFriendlyTimeModel;
import com.wicketfaces.ratingpanel.RatingPanel;

public class ArticleView extends Panel {
	
	@SpringBean 
	private UserBPO userBPO;
 
	@Override
	protected void onRender(MarkupStream markupStream) {
		super.onRender(markupStream);
		HttpServletRequest request = getWebRequest().getHttpServletRequest();
		String referer = request.getHeader("referer");
		String ip = request.getRemoteHost();
		userBPO.viewArticle(ip, referer, (Article) this.getDefaultModelObject());
	}

	private static final long serialVersionUID = 1138591094211559188L;

	public ArticleView(String id) {
		super(id);
	}
	
	public ArticleView(String id, final Article article) {
		super(id, new Model<Article>(article));
		add(new Label("article-title", article.getTitle()));
		add(new Label("article-writtenby", article.getPerson().getFirstName()+" "+article.getPerson().getLastName()));
		add(new Label("article-writtenon", new HumanFriendlyTimeModel(new PropertyModel<Calendar>(article, "createdTime"))));
		add(new Label("article-fulltext", article.getFullText()).setEscapeModelStrings(false));
		add(new Label("article-viewcount", ""+article.getViewCount()));
		add(new RatingPanel("article-rating-panel", article.getRating(), 5, 1, 1){

			private static final long serialVersionUID = 1L;
			
			@Override
			public void onVote(Integer voteValue) {
				HttpServletRequest request = getWebRequest().getHttpServletRequest();
				String ip = request.getRemoteHost();
				String sessionId = request.getSession().getId();
				userBPO.voteArticle(ip, article, voteValue, sessionId);
			}
			
		});
		add(new Label("article-commentcount", ""+article.getComments().size()));
	}

}
