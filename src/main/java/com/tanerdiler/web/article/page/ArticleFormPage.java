package com.tanerdiler.web.article.page;

import org.apache.wicket.markup.html.CSSPackageResource;

import admin.tanerdiler.page.AdminPage;

import com.tanerdiler.model.Article;
import com.tanerdiler.web.article.view.ArticleFormView;

public class ArticleFormPage extends AdminPage {

	public ArticleFormPage() {
		add(new ArticleFormView("article-form"));
		add(CSSPackageResource.getHeaderContribution(this.getClass(), "article.css"));
	}

	public ArticleFormPage(Article article) {
		add(new ArticleFormView("article-form", article));
		add(CSSPackageResource.getHeaderContribution(this.getClass(), "article.css"));
	}

}
