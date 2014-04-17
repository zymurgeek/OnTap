package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONArray;

public interface JSONArrayRetriever {

	JSONArray getJSONArray();
	JSONArray getJSONArray(int id);
	void setServerUrl(String serverUrl);

}
