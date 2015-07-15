package com.radio.bean;

import java.util.ArrayList;

public class Preferences {

	private String region = null;
	
	private ArrayList<String> languages = null;
	
	boolean registered = false;
	
	private ArrayList<Float> favourites = null;
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public ArrayList<String> getLanguages() {
		return languages;
	}

	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public ArrayList<Float> getFavourites() {
		return favourites;
	}

	public void setFavourites(ArrayList<Float> favourites) {
		this.favourites = favourites;
	}
	
}
