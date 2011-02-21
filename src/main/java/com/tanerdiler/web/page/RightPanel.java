package com.tanerdiler.web.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

public class RightPanel extends Panel {
	private static final long serialVersionUID = -5158890859836751993L;
	
	private final List<Component> views = new ArrayList<Component>();
	
	public RightPanel(){
		super("rightpanel");
	}
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		ListView<Component> listView = new ListView<Component>("rightpanelcontainer", views) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Component> item) {
				Component component = (Component)item.getDefaultModelObject();
				item.add(component);
			}
		};
		add(listView);
	}
	
	public void addView(Component view){
		views.add(view);
	}

}
