package com.tanerdiler.web.auth;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

public class UserInfoPanel extends Panel {

	private static final long serialVersionUID = 9009595787642978536L;
	
	private static final ResourceReference profilePhoto = new ResourceReference(  
			    UserInfoPanel.class, "unknown-profile.jpg");  
			  
	
	public UserInfoPanel(String id) {
		super(id);
		add(new Image("profilePhoto", profilePhoto));
	}

}
