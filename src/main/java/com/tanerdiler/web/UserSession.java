package com.tanerdiler.web;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.protocol.http.WebSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tanerdiler.model.Person;

public class UserSession extends WebSession  {
	
	private static final Logger logger = LoggerFactory.getLogger(UserSession.class);

	private static final long serialVersionUID = -599019160572842265L;
	
	public UserSession(Request request) {
		super(request);	
		injectDependencies();
	}
	
	public static UserSession get(){
		return (UserSession) Session.get();
	}
	
    private void injectDependencies() {
        InjectorHolder.getInjector().inject(this);
    }

}
