package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONArray;

public class JSONServerJSONArrayRetriever implements JSONArrayRetriever {

	public JSONServerJSONArrayRetriever(String serverURL) {
		this.serverURL = serverURL;
	}
	
	
	@Override
	public JSONArray getJSONArray() {
    	JSONParser jParser = new JSONParser();
    	JSONArray jsonArray = jParser.getJSONArrayFromUrl(serverURL);
		return jsonArray;
	}

	
	private String serverURL = null;


	@Override
	public JSONArray getJSONArray(int id) {
		String eventServerURL = serverURL.replace("#", Integer.toString(id));
    	JSONParser jParser = new JSONParser();
    	JSONArray jsonArray = jParser.getJSONArrayFromUrl(eventServerURL);
		return jsonArray;
	}
}
