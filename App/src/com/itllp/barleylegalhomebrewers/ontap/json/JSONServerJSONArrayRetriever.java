package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONArray;

public class JSONServerJSONArrayRetriever implements JSONArrayRetriever {

	public JSONServerJSONArrayRetriever(String serverURL) {
		this.serverUrlTemplate = serverURL;
	}
	
	
	@Override
	public JSONArray getJSONArray() {
    	JSONParser jParser = new JSONParser();
    	JSONArray jsonArray = jParser.getJSONArrayFromUrl(serverUrlTemplate);
		return jsonArray;
	}

	
	@Override
	public String getDataSource() {
		return this.serverUrlTemplate;
	}
	
	
	@Override
	public JSONArray getJSONArray(String id) {
		if (id == null) {
			return getJSONArray();
		}
		String serverURL = serverUrlTemplate.replace("#", id);
    	JSONParser jParser = new JSONParser();
    	JSONArray jsonArray = jParser.getJSONArrayFromUrl(serverURL);
		return jsonArray;
	}


	private String serverUrlTemplate = null;
}
