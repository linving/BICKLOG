package com.tanerdiler.web.page;

import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;

import admin.tanerdiler.page.AdminPage;

import com.tanerdiler.web.auth.UserPanel;
import com.tanerdiler.web.component.menupanel.MenuItem;
import com.tanerdiler.web.component.menupanel.MenuPanel;

public abstract class MainPage extends WebPage{
	
	private MenuPanel menu;
	private HeaderContributor css = CSSPackageResource.getHeaderContribution(MainPage.class, "MainPage.css");
	
	public MainPage() {
		this.setMenu(createMenu());
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		RightPanel rightPanel = new RightPanel();
		add(menu);
		rightPanel.addView(new UserPanel("rightpanelitem"));
		add(createRightPanel(rightPanel));
		add(css);
	}
	
	public void setMenu(MenuPanel menu){
		this.menu = menu;
	}
	
	public void setCss(HeaderContributor css){
		this.css = css;
	}
	
	protected RightPanel createRightPanel(RightPanel rightPanel) {
		rightPanel.setVisible(false);
		return rightPanel;
	}
	
	public MenuPanel createMenu() {
		MenuPanel panel = new MenuPanel("menu-container");
		panel.addHomePage(new MenuItem(this.getApplication().getHomePage(), "menu.home", true));
		panel.addItem(new MenuItem(AdminPage.class, "menu.admin"));
		return panel;
	}
	
}
