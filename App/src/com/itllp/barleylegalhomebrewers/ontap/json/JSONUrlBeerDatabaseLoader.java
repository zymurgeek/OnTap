package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.JSONArrayBeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;


public class JSONUrlBeerDatabaseLoader extends BeerDatabaseLoader {

	private NetworkConnectivity networkConnectivity;
	private String url;
	private JSONArrayRetriever jsonArrayRetriever;
	private JSONArrayBeerDatabaseLoader jsonArrayLoader;
	
	private JSONUrlBeerDatabaseLoader(NetworkConnectivity networkConnectivity, 
			String url, JSONArrayRetriever jsonArrayRetriever,
			JSONArrayBeerDatabaseLoader jsonArrayLoader) {
		this.networkConnectivity = networkConnectivity;
		this.url = url;
		this.jsonArrayRetriever = jsonArrayRetriever;
		this.jsonArrayLoader = jsonArrayLoader;
	}
	
	public void load(int eventId) {
		if (networkConnectivity.isConnected()) {
			JSONArray jsonArray = jsonArrayRetriever.getJSONArray(eventId);
			jsonArrayLoader.load(jsonArray);
		}
	}

	public String getUrl() {
		return url;
	}

	public static void create(NetworkConnectivity networkConnectivity, 
			String url, JSONArrayRetriever jsonArrayRetriever, 
			JSONArrayBeerDatabaseLoader jsonArrayLoader) {
		setInstance(new JSONUrlBeerDatabaseLoader(networkConnectivity, url, 
				jsonArrayRetriever, jsonArrayLoader));
	}
}
