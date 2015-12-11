package com.njupt.kangaroo.utils;

import android.app.Application;

public class LoginingUseInfo extends Application {

	private String usernameLogin;
	private String passwordLogin;
	
	public String getUsernameLogin() {
		return usernameLogin;
	}

	public void setUsernameLogin(String usernameLogin) {
		this.usernameLogin = usernameLogin;
	}

	public String getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}
	

}
