package com.tanerdiler.web.category.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.tanerdiler.dao.CategoryDAO;
import com.tanerdiler.model.Category;

public class CategoryAddPanel extends Panel {
	private static final long serialVersionUID = 9042417741272954076L;

	@SpringBean
	CategoryDAO catDAO;

	private class Pair implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer key;
		private String value;
		private Category category;

		public Pair(Integer key, String value, Category category) {
			this.key = key;
			this.value = value;
			this.category = category;
		}

		public Integer getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
		
		public Category getCategory() {
			return category;
		}
	}

	public CategoryAddPanel(String id) {
		super(id);
		add(new CategoryForm());
	}

	public CategoryAddPanel(String id, Category category) {
		super(id);
		add(new CategoryForm(category));
	}

	private class CategoryForm extends Form<Category> {
		@Override
		protected void onSubmit() {
			super.onSubmit();
		}

		private static final long serialVersionUID = 1L;

		public CategoryForm() {
			this(new Category());
		}
		
		public CategoryForm(Category category) {
			super("category-form", new Model<Category>(category));
			List<Category> cats = catDAO.getParents();
			List<Pair> pairs = makePair(cats);
			IChoiceRenderer<Pair> choiceRenderer = new IChoiceRenderer<Pair>() {
				private static final long serialVersionUID = 1L;

				public Object getDisplayValue(Pair pair) {
					return pair.getValue();
				}

				public String getIdValue(Pair pair, int index) {
					if(pair.getKey() == null){
						return "NULL";
					} 
					return pair.getKey().toString();
				}
			};
			DropDownChoice<Pair> categories = new DropDownChoice<Pair>(
					"categories", new Model<Pair>(new Pair(category.getId(), category.getName(), category)) , pairs, choiceRenderer);
			TextField<String> catName = new TextField<String>("category", new PropertyModel<String>(category, "name"));
			add(catName);
			add(categories);
			add(new AjaxButton("cat-form-submit", this){
				private static final long serialVersionUID = 1L;
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					Category  cat = (Category) getForm().getModelObject();
					Pair pair = (Pair) getForm().get("categories").getDefaultModelObject();
					Category parent = pair.getCategory();
					parent.addChild(cat);
					catDAO.saveOrUpdate(cat);
				}
				
			});
		}
	}

	private void makeBreadCrumb(List<Pair> pairs,
			Category category, String breadCrumb) {
		String separator = ">";
		if (breadCrumb == null) {
			breadCrumb = "";
			separator = "";
		}
		breadCrumb += separator + category.getName();
		pairs.add(new Pair(category.getId(), breadCrumb, category));
		if (category.hasChild()) {
			for (Category child : category.getChilds()) {
				makeBreadCrumb(pairs, child, new String(breadCrumb));
			}
		}

	}

	private List<Pair> makePair(List<Category> cats) {
		List<Pair> pairs = new ArrayList<Pair>();
		for (Category parent : cats) {
			makeBreadCrumb(pairs, parent, null);
		}
		return pairs;
	}
}
