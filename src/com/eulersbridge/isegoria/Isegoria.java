package com.eulersbridge.isegoria;

import android.app.Application;

public class Isegoria extends Application {
	protected Network network;
	private boolean loggedIn = false;
	private String username = "";
	private String password = "";
	
	public Isegoria() {
		
	}
	
	public Network getNetwork() {
		return network;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void login() {
		network = new Network(username, password);
		setLoggedIn(true);
	}
}
