package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.JsonArrayBeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;


public class JsonUrlBeerDatabaseLoader extends BeerDatabaseLoader {

	private NetworkConnectivity networkConnectivity;
	private String url;
	private JsonArrayRetriever jsonArrayRetriever;
	private JsonArrayBeerDatabaseLoader jsonArrayLoader;
	
	private JsonUrlBeerDatabaseLoader(NetworkConnectivity networkConnectivity, 
			String url, JsonArrayRetriever jsonArrayRetriever,
			JsonArrayBeerDatabaseLoader jsonArrayLoader) {
		this.networkConnectivity = networkConnectivity;
		this.url = url;
		this.jsonArrayRetriever = jsonArrayRetriever;
		this.jsonArrayLoader = jsonArrayLoader;
	}
	
	public void load() {
		if (networkConnectivity.isConnected()) {
			JSONArray jsonArray = jsonArrayRetriever.getJsonArray(url);
			jsonArrayLoader.load(jsonArray);
		}
	}

	public String getUrl() {
		return url;
	}

	public static void create(NetworkConnectivity networkConnectivity, 
			String url, JsonArrayRetriever jsonArrayRetriever, 
			JsonArrayBeerDatabaseLoader jsonArrayLoader) {
		setInstance(new JsonUrlBeerDatabaseLoader(networkConnectivity, url, 
				jsonArrayRetriever, jsonArrayLoader));
	}
}
