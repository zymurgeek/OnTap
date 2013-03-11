package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONArray;

public class JsonArrayRetrieverImpl implements JsonArrayRetriever {

	@Override
	public JSONArray getJsonArray(String url) {
    	JSONParser jParser = new JSONParser();
    	JSONArray jsonArray = jParser.getJsonArrayFromUrl(url);
		return jsonArray;
	}

}
