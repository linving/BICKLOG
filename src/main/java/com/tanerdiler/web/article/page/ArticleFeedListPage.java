package com.tanerdiler.web.article.page;

import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.mcavallo.opencloud.Cloud.Case;

import com.tanerdiler.dao.ArticleDAO;
import com.tanerdiler.dao.TagValueDAO;
import com.tanerdiler.filter.Filter;
import com.tanerdiler.model.Article;
import com.tanerdiler.model.view.TagValueWithArticleCount;
import com.tanerdiler.web.article.view.ArticleFeedView;
import com.tanerdiler.web.auth.SignInForm;
import com.tanerdiler.web.auth.UserPanel;
import com.tanerdiler.web.page.MainPage;
import com.tanerdiler.web.page.RightPanel;
import com.wicketfaces.tagcloud.TagCloudPanel;
import com.wicketfaces.tagcloud.TagCloudPanel.FONT_SIZE_TYPE;

public class ArticleFeedListPage extends MainPage {

	@SpringBean
	ArticleDAO articleDAO;
	
	@SpringBean 
	TagValueDAO tagDAO;
	
	public ArticleFeedListPage() {
		add(CSSPackageResource.getHeaderContribution(this.getClass(), "article.css"));
		PageParameters parameters = getRequestCycle().getPageParameters();
		
		add(new ListView<Article>("ArticleFeedList", getArticleList(parameters)){
			private static final long serialVersionUID = 1L;

			protected void populateItem(ListItem<Article> item) {
				 	Article article = item.getModelObject();
				 	item.add(new ArticleFeedView("ArticleFeedView", article));
			 }

		});
	}

	private List<Article> getArticleList(PageParameters parameters) {
		if(parameters.containsKey("tag")){
			return Filter.filter(articleDAO, Filter.matchTag((String)parameters.getString("tag")));
		}
		return articleDAO.getList(null);
	}

	protected RightPanel createRightPanel(RightPanel rightPanel) {
		
		List<TagValueWithArticleCount> tags = tagDAO.getTagValuesWithArticleCount();
		rightPanel.addView(new TagCloudPanel("rightpanelitem", ArticleFeedListPage.class, tags, Case.UPPER){
			private static final long serialVersionUID = 1L;
			@Override
			public void populate(TagItem item) {
				TagValueWithArticleCount tag = (TagValueWithArticleCount) item.getDefaultModelObject();
				item.setName(tag.getText());
				item.setScore(tag.getCount());
			}
			
		}.setMaxWeight(1.5).setMinWeight(0).setSizeType(FONT_SIZE_TYPE.EM));
		return rightPanel;
	}

}
