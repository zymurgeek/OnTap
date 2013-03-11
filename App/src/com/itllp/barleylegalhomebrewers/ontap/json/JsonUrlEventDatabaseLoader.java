package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.JsonArrayEventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;


public class JsonUrlEventDatabaseLoader extends EventDatabaseLoader {

	private NetworkConnectivity networkConnectivity;
	private String url;
	private JsonArrayRetriever jsonArrayRetriever;
	private JsonArrayEventDatabaseLoader jsonArrayLoader;
	
	private JsonUrlEventDatabaseLoader(NetworkConnectivity networkConnectivity, 
			String url, JsonArrayRetriever jsonArrayRetriever,
			JsonArrayEventDatabaseLoader jsonArrayLoader) {
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
			JsonArrayEventDatabaseLoader jsonArrayLoader) {
		setInstance(new JsonUrlEventDatabaseLoader(networkConnectivity, url, 
				jsonArrayRetriever, jsonArrayLoader));
	}
}
