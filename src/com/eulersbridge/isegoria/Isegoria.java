package com.eulersbridge.isegoria;

import android.app.Application;

public class Isegoria extends Application {
	protected Network network;
	
	public Isegoria() {
		network = new Network("greg.newitt@unimelb.edu.au", "test123");
	}
	
	public Network getNetwork() {
		return network;
	}
}
