/**
 * 
 */
package com.tanerdiler.web;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.tanerdiler.DataHelper;
import com.tanerdiler.web.article.page.ArticleFeedListPage;
import com.tanerdiler.web.article.page.ArticleViewPage;

/**
 * @author tdiler
 *
 */
public class BlogApplication extends WebApplication {

	/* (non-Javadoc)
	 * @see org.apache.wicket.protocol.http.WebApplication#init()
	 */
	@Override
	protected void init() {
		addComponentInstantiationListener(new SpringComponentInjector(this));
        getMarkupSettings().setCompressWhitespace(true);
        getMarkupSettings().setStripComments(true);
        getMarkupSettings().setStripWicketTags(true);
        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
        getMarkupSettings().setDefaultBeforeDisabledLink("");
        getMarkupSettings().setDefaultAfterDisabledLink("");
        
        mountBookmarkablePage("articles", ArticleFeedListPage.class);
        mountBookmarkablePage("article", ArticleViewPage.class);
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.protocol.http.WebApplication#newSession(org.apache.wicket.Request, org.apache.wicket.Response)
	 */
	@Override
	public Session newSession(Request request, Response response) {
		UserSession session = new UserSession(request);
		return session;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends Page> getHomePage() {
		return ArticleFeedListPage.class;
	}

}
