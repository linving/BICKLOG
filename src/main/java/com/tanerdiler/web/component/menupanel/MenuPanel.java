package com.tanerdiler.web.component.menupanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.SimpleAttributeSet;

import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class MenuPanel extends Panel {

	private static final long serialVersionUID = -3365504968976468206L;
	
	private HeaderContributor css = CSSPackageResource.getHeaderContribution(MenuPanel.class, "MenuPanel.css");

	private List<MenuItem> items = new ArrayList<MenuItem>();

	private MenuItem homePageItem;
	
	private boolean displayHomePage;
	
	public MenuPanel(String id) {
		super(id);
	}
	
	public MenuPanel(String id, List<MenuItem> items) {
		super(id);
		this.items = items;
	}
	
	public void addItem(MenuItem item){
		this.items.add(item);
	}
	
	public void removeItem(MenuItem item){
		this.items.remove(item);
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		add(css);

		WebMarkupContainer homeMenuItem = new WebMarkupContainer("menu-home");
		if(displayHomePage){
			homeMenuItem.add(this.homePageItem);
		} else {
			homeMenuItem.add(new EmptyMenuItem());
		}
		add(homeMenuItem);
		
		MenuPanel menuPanel = (MenuPanel) get("menu-item");
		if(menuPanel == null){
			add(new ListView<MenuItem>("menu-item", items) {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void populateItem(ListItem<MenuItem> item) {
					MenuItem menuItem = (MenuItem) item.getDefaultModelObject();
					item.add(menuItem);
				}
				
			});
		}
	}

	public void addHomePage(MenuItem menuItem) {
		if(menuItem == null){
			throw new IllegalStateException("Menu item cannot be null.");
		} else if(!menuItem.isHomePage()){
			throw new IllegalStateException("Menu item must be signed as Home Page");
		}
		this.displayHomePage = true;
		this.homePageItem = menuItem;
	}

}
