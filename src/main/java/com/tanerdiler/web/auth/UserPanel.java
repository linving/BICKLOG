package com.tanerdiler.web.auth;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.tanerdiler.bpo.UserBPO;

public class UserPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private UserBPO userbpo;
	
	private UserInfoPanel userInfoPanel;
	
	public UserPanel(String id) {
		super(id);
		if(userbpo.signedIn()){
			if(userInfoPanel == null){
				userInfoPanel = new UserInfoPanel("container");
			}
			add(userInfoPanel);
		} else {
			add(new SignInForm("container"));
		}
	}

}
