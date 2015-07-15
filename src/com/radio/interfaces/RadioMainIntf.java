package com.radio.interfaces;

import java.io.IOException;

import org.json.simple.JSONArray;

import com.radio.bean.Preferences;

public interface RadioMainIntf {

	public JSONArray fetchStations(String region, String lang, String path);
	
	public void writeDataToJSON(Preferences prefs) throws IOException;

}
