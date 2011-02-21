package com.tanerdiler.web.article.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.calldecorator.AjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IAutoCompleteRenderer;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator.MaximumLengthValidator;

import wicket.contrib.tinymce.TinyMceBehavior;

import com.tanerdiler.BlogConstants;
import com.tanerdiler.DataHelper;
import com.tanerdiler.bpo.TagBPO;
import com.tanerdiler.bpo.UserBPO;
import com.tanerdiler.dao.TagValueDAO;
import com.tanerdiler.model.Article;
import com.tanerdiler.model.Category;
import com.tanerdiler.web.UserSession;
import com.tanerdiler.web.component.MyDropDownChoice;
import com.tanerdiler.web.component.tinymce.SettingManager;

public class ArticleFormView extends Panel {

	private static final long serialVersionUID = 1138591094211559188L;
	
	 @SpringBean 
	 private UserBPO userBPO;
	 
	 @SpringBean
	 private TagValueDAO tagDAO;
	
	public ArticleFormView(String id) {
		super(id);
		add(new ArticleForm(id, new Model<Article>(new Article())));
	}
	
	public ArticleFormView(String id, Article article) {
		super(id);
		add(new ArticleForm(id, new Model<Article>(article)));
	}
	
	private class ArticleForm extends Form<Article> {

		private static final long serialVersionUID = -5509967222467012920L;
		
		public ArticleForm(String id, IModel<Article> articleModel) {
			super(id, articleModel);
			add(new FeedbackPanel("feedbackpanel").setOutputMarkupId(true));
			add(CSSPackageResource.getHeaderContribution(this.getClass(), "autocompletetextfield.css"));
			add(new TextArea<String>("article-form-intro", 
					new PropertyModel<String>(articleModel.getObject(), "introText"))
					.add(new MaximumLengthValidator(500)).setRequired(true));
			TextArea<String> textArea = new TextArea<String>("article-form-full", new Model<String>(articleModel.getObject().getFullText()));
			textArea.add(new TinyMceBehavior(SettingManager.getFullConf()));
			textArea.setRequired(true);
			textArea.setEscapeModelStrings(false);
			add(textArea);
			add(new TextField<String>("article-form-title", new PropertyModel<String>(articleModel.getObject(), "title")).setRequired(true));
			class InnerAutoCompleteTextField extends AutoCompleteTextField<String> {
				
				private List<String> results = TagBPO.getTagLabelsWithEntityCount(tagDAO.getTagValuesWithArticleCount());
				
				public InnerAutoCompleteTextField(String id, IModel<String> object) {
					super(id, object);
				}
				@Override
				protected AutoCompleteBehavior<String> newAutoCompleteBehavior(
						IAutoCompleteRenderer<String> renderer,
						AutoCompleteSettings settings) {
					return new AutoCompleteBehavior<String>(renderer, settings) {
						private static final long serialVersionUID = -790268605499633265L;

						@Override
						public void renderHead(IHeaderResponse response) {
							super.renderHead(response);
							response.renderJavascriptReference(new JavascriptResourceReference(
									 		ArticleFormView.class, "autocompletetextfield.js"));
						}

						@Override
						protected Iterator<String> getChoices(String arg0) {
							return InnerAutoCompleteTextField.this.getChoices(arg0);
						}
						
					};
				}
				private static final long serialVersionUID = 4915154515432572657L;
				@Override
				public Iterator<String> getChoices(String input) {
					input = input.toLowerCase();
					int lastIndex = input.lastIndexOf(",");
					String lastEntered = "";
					if(lastIndex < 0){
						lastEntered = input;
					} else if(lastIndex != input.length()){
						lastEntered = input.substring(input.lastIndexOf(",")+1);
					}
					
					List<String> matchedList = new ArrayList<String>();
					String lowerResult = null;
					for(String result : results){
						lowerResult = result.toLowerCase().replaceAll(BlogConstants.RGX_TAG_COUNT, "");
						if(lowerResult.indexOf(lastEntered)>-1){
							if(input.indexOf(lowerResult)>-1){
								continue;
							}
							matchedList.add(result);
						}
					}
					return matchedList.iterator();
				}
				
				
			};
			add(new InnerAutoCompleteTextField("article-form-tags", new PropertyModel<String>(articleModel, "tagString")));
			add(new MyDropDownChoice<Category>(
					"article-form-category", new PropertyModel<Category>(articleModel,
							"category"), DataHelper.categories, new ChoiceRenderer<Category>(
							"name", "id")).setRequired(true));
			add(new AjaxButton("article-form-submit", ArticleForm.this) {
				
				@Override
				protected void onError(AjaxRequestTarget target, Form<?> form) {
					target.addComponent(ArticleForm.this.get("feedbackpanel"));
				}


				@Override
				protected IAjaxCallDecorator getAjaxCallDecorator() {
					return new AjaxCallDecorator() {
						private static final long serialVersionUID = -9177462398872956453L;

						@Override
						public CharSequence decorateScript(CharSequence script) {
							return "tinyMCE.triggerSave(true, true);"+script;
						}
						
					};
				}


				private static final long serialVersionUID = 1690454896310216317L;
				
				
				@Override
				protected void onSubmit(AjaxRequestTarget arg0, Form<?> arg1) {
					Article article = (Article)getForm().getModelObject();
					article.setFullText(getForm().get("article-form-full").getDefaultModelObjectAsString());
					userBPO.writeArticle(article);
					System.out.println(article.toString());
				}
			}.setDefaultFormProcessing(true));
		}
	}

}
