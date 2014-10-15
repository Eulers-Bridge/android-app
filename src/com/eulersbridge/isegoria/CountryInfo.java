package com.eulersbridge.isegoria;

import java.util.Vector;

public class CountryInfo {
	private String country;
	private Vector<String> institutions;
	
	public CountryInfo(String country) {
		this.country = country;
		this.institutions = new Vector<String>();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void addInstituion(String institution) {
		institutions.add(institution);
	}

	public Vector<String> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(Vector<String> institutions) {
		this.institutions = institutions;
	}
}
