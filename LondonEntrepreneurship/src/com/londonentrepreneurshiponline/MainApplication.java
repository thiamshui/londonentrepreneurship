package com.londonentrepreneurshiponline;

import android.app.Application;

public class MainApplication extends Application {

	private int loggedOnUser = -1;

	public int getLoggedOnUser() {
		return loggedOnUser;
	}

	public void setLoggedOnUser(int loggedOnUser) {
		this.loggedOnUser = loggedOnUser;
	}

}