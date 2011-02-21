package com.tanerdiler.web.comment.view;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTarget.IListener;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.tanerdiler.bpo.UserBPO;
import com.tanerdiler.exception.SystemException;
import com.tanerdiler.model.Comment;
import com.tanerdiler.model.CommentAuthor;
import com.tanerdiler.model.Commentable;

public class CommentFormView<T extends Comment> extends Panel {

	private static final long serialVersionUID = -709479619287120478L;
	
	 @SpringBean 
	 private UserBPO userBPO;
	 
	 private Class<T> commentClass;
	 
	 private final List<IListener> formSendListeners = new ArrayList<IListener>();
	 
	 public void addListener(IListener listener) {
		 formSendListeners.add(listener);
	 }	
	 
	 private T createModelIstance() {
		 try {
			return this.commentClass.newInstance();
		} catch (InstantiationException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new SystemException(e.getMessage(), e);
		}
	 }
//	@SuppressWarnings("unchecked")
//	public CommentFormView(String id, Class<T> clazz) {
//		super(id);
//		init();
//		try {
//			add(new CommentForm(id, new Model<T>( clazz.newInstance())));
//		} catch (InstantiationException e) {
//			throw new SystemException(e.getMessage(), e);
//		} catch (IllegalAccessException e) {
//			throw new SystemException(e.getMessage(), e);
//		}
//	}

//	public CommentFormView(String id, IModel<T> model) {
//		super(id);
//		init();
//		add(new CommentForm<T>(id, model));
//	}
	
	public CommentFormView(String id, Commentable<T> commentableObject){
		super(id, new Model<Commentable<T>>(commentableObject));
		init(id, commentableObject);
	}
	
	@SuppressWarnings("unchecked")
	private void init(String id, Commentable<T> commentableObject){
		Class<T> commentType = null;
		Type[] interfaces = commentableObject.getClass().getGenericInterfaces();
		for(Type type : interfaces){
			System.out.println("GENERICINTERFACE CLASS NAME :"+((ParameterizedType) type).getRawType().toString());
			if(((ParameterizedType) type).getRawType().equals(Commentable.class)){
				commentType = (Class<T>)((ParameterizedType) type).getActualTypeArguments()[0];
			}
		}
		if(commentType == null){
			throw new IllegalArgumentException("GenericType of commentableObject parameter is not compatible.");
		}
		this.commentClass = commentType;
		add(new CommentForm(id, new Model<T>(this.createModelIstance())));
		add(CSSPackageResource.getHeaderContribution(this.getClass(), "CommentFormView.css"));
	}
	
	private class CommentForm extends Form<T>{

		private static final long serialVersionUID = -6931339006930917955L;

		public CommentForm(String id, IModel<T> model) {
			super(id, model);
			final FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackpanel");
			feedbackPanel.setOutputMarkupId(true);
			add(feedbackPanel);
			add(new RequiredTextField<String>("comment-firstname", new Model<String>()));
			add(new RequiredTextField<String>("comment-lastname", new Model<String>()));
			add(new TextField<String>("comment-web", new Model<String>()));
			add(new RequiredTextField<String>("comment-email", new Model<String>()));
			add(new TextArea<String>("comment-comment", new Model<String>()).setRequired(true));
			add(new AjaxButton("comment-submit", CommentForm.this){
				@Override
				protected void onError(AjaxRequestTarget target, Form<?> form) {
					target.addComponent(feedbackPanel);
				}

				private static final long serialVersionUID = -2106284878669167289L;
				
				@SuppressWarnings("unchecked")
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> arg1) {
					Commentable<T> commentableObject = (Commentable<T>) getParent().getParent().getDefaultModelObject();
					//Create Person
						// 1. if authenticated user, get person over session
						// 2. if not, create person by using entered infos to comment form
					
					//2.
					String firstName = getForm().get("comment-firstname").getDefaultModelObjectAsString();
					String lastName = getForm().get("comment-lastname").getDefaultModelObjectAsString();
					String web = getForm().get("comment-web").getDefaultModelObjectAsString();
					String email = getForm().get("comment-email").getDefaultModelObjectAsString();
					String comment = getForm().get("comment-comment").getDefaultModelObjectAsString();
					
					T commentBean = (T)getForm().getModelObject();
					commentBean.setComment(comment);
					commentBean.setCreatedTime(Calendar.getInstance());
					
					CommentAuthor commentAuthor = new CommentAuthor();
					commentAuthor.setFirstName(firstName);
					commentAuthor.setLastName(lastName);
					commentAuthor.setEmail(email);
					commentAuthor.setWebSite(web);
					System.out.println(commentAuthor.toString());
					commentBean.setUnknownAuthor(commentAuthor);
					commentableObject.addComment(commentBean);
					System.out.println(commentBean.toString());
					userBPO.writeComment(commentBean);
					target.addComponent(this.getParent());
					for(IListener listener : formSendListeners){
						target.addListener(listener);
					}
					CommentForm.this.setModel(new Model<T>(CommentFormView.this.createModelIstance()));
					this.clearFormComponents();
					super.onSubmit();
				}

				private void clearFormComponents() {
					getForm().get("comment-firstname").setDefaultModel(new Model<String>());
					getForm().get("comment-lastname").setDefaultModel(new Model<String>());
					getForm().get("comment-web").setDefaultModel(new Model<String>());
					getForm().get("comment-email").setDefaultModel(new Model<String>());
					getForm().get("comment-comment").setDefaultModel(new Model<String>());
				}
				
			}.add(new AttributeModifier("value", new StringResourceModel("submit.comment", new Model<String>("Submit Comment")))));
		}
		
	}


}
