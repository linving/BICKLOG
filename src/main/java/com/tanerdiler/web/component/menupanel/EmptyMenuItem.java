package com.tanerdiler.web.component.menupanel;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

public class EmptyMenuItem extends Link<String> {

	private static final long serialVersionUID = -6147486799559191126L;
	public EmptyMenuItem() {
		super("menu-link");
		this.add(new SimpleAttributeModifier("href", "#"));
		this.add(new SimpleAttributeModifier("class", "home"));
		this.add(new Label("menu-label", ""));
	}
	
	@Override
	public void onClick() {
		// DO NOTHING
	}
}
