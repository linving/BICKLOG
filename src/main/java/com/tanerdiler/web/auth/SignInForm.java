package com.tanerdiler.web.auth;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.tanerdiler.bpo.UserBPO;

public class SignInForm extends Panel {

	private static final long serialVersionUID = 9009595787642978536L;
	
	@SpringBean
	private UserBPO userbpo;
	
	public SignInForm(String id) {
		super(id);
		add(new LoginForm("loginform"));
	}
	
	class LoginForm extends Form {

	    private String username;

	    private String password;

	    public LoginForm(String id) {
	        super(id);
	        setModel(new CompoundPropertyModel(this));
	        add(new RequiredTextField("username"));
	        add(new PasswordTextField("password"));
	    }

	    @Override
	    protected void onSubmit() {
	    	boolean signedIn = userbpo.signIn(username, password);
	    }

	    private void setDefaultResponsePageIfNecessary() {
	        if(!continueToOriginalDestination()) {
	            setResponsePage(getApplication().getHomePage());
	        }
	    }

	}

}
