package com.radio.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.radio.bean.Preferences;
import com.radio.interfaces.RadioMainIntf;

public class RadioMainClass implements RadioMainIntf{
	
	final static String filePath = "D:/MyLab/Git/radioadblocker/data/data.json";
	
	final static String filePrefs = "D:/MyLab/Git/radioadblocker/data/prefs.json";
	
	static String prefLang = "Kannada";
	
	static String region = "Bangalore";
	
	public static void main(String[] args) throws IOException {
			
			RadioMainClass rm = new RadioMainClass();
			ArrayList<String> languages = new ArrayList<String>();
			ArrayList<Float> favourites = new ArrayList<Float>();
			Preferences prefs = new Preferences();
			
			languages.add(prefLang);
			
			favourites.add(new Float(92.7));
			favourites.add(new Float(93.5));
			boolean is_registered = true;
		
			prefs.setRegistered(is_registered);
			prefs.setRegion(region);
			prefs.setLanguages(languages);
			prefs.setFavourites(favourites);
			
			rm.writeDataToJSON(prefs);
		
			JSONArray channels = new RadioMainClass().fetchStations(region, prefLang, filePath);

			System.out.println("Radio Channels for "+prefLang+ " in "+region);
			System.out.println("-----------------------------");
			
			for (Object channel : channels){
				
				System.out.println(channel);
				
			}
			System.out.println("-----------------------------");
			
	}

	@Override
	public void writeDataToJSON(Preferences prefs) throws IOException{
		
		JSONObject obj = new JSONObject();
		obj.put("Region", prefs.getRegion());	
		obj.put("Registered", prefs.isRegistered());
		
		JSONArray languages = new JSONArray();
		for(String language : prefs.getLanguages()){
			
			languages.add(language);
		}
		
		obj.put("Languages", languages);
		
		JSONArray favourites = new JSONArray();
		for(Float f : prefs.getFavourites()){
		
			favourites.add(f);
		}
		
		obj.put("Favourites", favourites);
				
		
		FileWriter file = new FileWriter(filePrefs);
		
	        try {
	            file.write(obj.toJSONString());
	            System.out.println("Successfully Copied JSON Object to File...");
	            System.out.println("\nJSON Object: " + obj);
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	 
	        } finally {
	            file.flush();
	            file.close();
	        }
	        
	}

	@Override
	public JSONArray fetchStations(String region, String lang, String path) {
		
	JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(filePath));
			JSONObject jsonObj = (JSONObject) obj;
			
			System.out.println(jsonObj.get(region));
			
			/*
			 * Fetch the data based on region
			 */
			JSONArray channels = (JSONArray) jsonObj.get(region);
			
			JSONArray filteredChannels = null;
			for(int i=0; i<channels.size(); i++){
			
				JSONObject j = (JSONObject) channels.get(i);
				System.out.println(j.toJSONString());
				
				if(j.toJSONString().contains(prefLang)){
					
					filteredChannels = (JSONArray) j.get(prefLang);
				}
				System.out.println("-------------------------------");
			}
			
			return filteredChannels;	
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		catch (IOException e){
			e.printStackTrace();
		}
		
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
