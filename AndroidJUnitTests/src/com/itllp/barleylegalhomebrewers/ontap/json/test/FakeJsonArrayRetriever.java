package com.itllp.barleylegalhomebrewers.ontap.json.test;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.json.JsonArrayRetriever;

public class FakeJsonArrayRetriever implements JsonArrayRetriever {
	private String whenUrl;
	private JSONArray returnJsonArray;
	
	void FAKE_setWhenUrl(String url) {
		whenUrl = url;
	}

	public void FAKE_setReturnArray(JSONArray jsonArray) {
		returnJsonArray = jsonArray;
	}

	@Override
	public JSONArray getJsonArray(String url) {
		if (url.equals(whenUrl)) {
			return returnJsonArray;
		}
		return null;
	}

}
