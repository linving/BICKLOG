/**
 * 
 */
package com.tanerdiler.web.component;

import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

/**
 * @author tdiler
 *
 */
public class MyDropDownChoice<T> extends DropDownChoice<T> {

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.html.form.AbstractSingleSelectChoice#getNoSelectionValue()
	 */
	@Override
	protected Object getNoSelectionValue() {
		return "NULL";
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.html.form.AbstractSingleSelectChoice#getDefaultChoice(java.lang.Object)
	 */
	@Override
	protected CharSequence getDefaultChoice(Object selected) {
        String defaultChoice = "\n<option selected=\"selected\" value=\"NULL\">"+ getLocalizer().getString("combobox.select", this)+"</option>";
		return defaultChoice;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -986377883202958347L;

	/**
	 * @param id
	 * @param choices
	 * @param renderer
	 */
	public MyDropDownChoice(String id,
			IModel<? extends List<? extends T>> choices,
			IChoiceRenderer<? super T> renderer) {
		super(id, choices, renderer);
	}

	/**
	 * @param id
	 * @param choices
	 */
	public MyDropDownChoice(String id,
			IModel<? extends List<? extends T>> choices) {
		super(id, choices);
	}

	/**
	 * @param id
	 * @param model
	 * @param choices
	 * @param renderer
	 */
	public MyDropDownChoice(String id, IModel<T> model,
			IModel<? extends List<? extends T>> choices,
			IChoiceRenderer<? super T> renderer) {
		super(id, model, choices, renderer);
	}

	/**
	 * @param id
	 * @param model
	 * @param choices
	 */
	public MyDropDownChoice(String id, IModel<T> model,
			IModel<? extends List<? extends T>> choices) {
		super(id, model, choices);
	}

	/**
	 * @param id
	 * @param model
	 * @param data
	 * @param renderer
	 */
	public MyDropDownChoice(String id, IModel<T> model,
			List<? extends T> data, IChoiceRenderer<? super T> renderer) {
		super(id, model, data, renderer);
	}

	/**
	 * @param id
	 * @param model
	 * @param choices
	 */
	public MyDropDownChoice(String id, IModel<T> model,
			List<? extends T> choices) {
		super(id, model, choices);
	}

	/**
	 * @param id
	 * @param data
	 * @param renderer
	 */
	public MyDropDownChoice(String id, List<? extends T> data,
			IChoiceRenderer<? super T> renderer) {
		super(id, data, renderer);
	}

	/**
	 * @param id
	 * @param choices
	 */
	public MyDropDownChoice(String id, List<? extends T> choices) {
		super(id, choices);
	}

	/**
	 * @param id
	 */
	public MyDropDownChoice(String id) {
		super(id);
	}


}
