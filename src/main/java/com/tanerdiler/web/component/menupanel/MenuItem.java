package com.tanerdiler.web.component.menupanel;

import org.apache.wicket.Page;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class MenuItem extends BookmarkablePageLink<String>{

	public <C extends Page> MenuItem(Class<C> pageClass, String labelKey, boolean isHomePage) {
		super("menu-link", pageClass);
		this.homePage = isHomePage;
		if(isHomePage){
			this.add(new SimpleAttributeModifier("class", "home"));
		}
		this.add(new Label("menu-label", labelKey));
	}
	
	public <C extends Page> MenuItem(Class<C> pageClass, String labelKey) {
		this(pageClass, labelKey, false);
	}

	private static final long serialVersionUID = -5048782699087837847L;
	
	private boolean homePage;
	
	public boolean isHomePage() {
		return homePage;
	}
	
}
