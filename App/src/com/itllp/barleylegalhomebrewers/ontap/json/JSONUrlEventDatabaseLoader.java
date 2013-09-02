package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONArray;

import android.content.Context;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.JSONArrayEventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;


public class JSONUrlEventDatabaseLoader extends EventDatabaseLoader {

	private NetworkConnectivity networkConnectivity;
	private String url;
	private JSONArrayRetriever jsonArrayRetriever;
	private JSONArrayEventDatabaseLoader jsonArrayLoader;
	
	private JSONUrlEventDatabaseLoader(NetworkConnectivity networkConnectivity, 
			String url, JSONArrayRetriever jsonArrayRetriever,
			JSONArrayEventDatabaseLoader jsonArrayLoader) {
		this.networkConnectivity = networkConnectivity;
		this.url = url;
		this.jsonArrayRetriever = jsonArrayRetriever;
		this.jsonArrayLoader = jsonArrayLoader;
	}
	
	public void load(Context context) {
		if (networkConnectivity.isConnected()) {
			JSONArray jsonArray = jsonArrayRetriever.getJSONArray();
			jsonArrayLoader.load(jsonArray);
		}
	}

	public String getUrl() {
		return url;
	}

	public static void create(NetworkConnectivity networkConnectivity, 
			String url, JSONArrayRetriever jsonArrayRetriever, 
			JSONArrayEventDatabaseLoader jsonArrayLoader) {
		setInstance(new JSONUrlEventDatabaseLoader(networkConnectivity, url, 
				jsonArrayRetriever, jsonArrayLoader));
	}
}
