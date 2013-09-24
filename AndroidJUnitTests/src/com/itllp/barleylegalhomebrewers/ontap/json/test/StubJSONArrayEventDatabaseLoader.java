package com.itllp.barleylegalhomebrewers.ontap.json.test;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.JSONArrayEventDatabaseLoader;

public class StubJSONArrayEventDatabaseLoader implements
		JSONArrayEventDatabaseLoader {
	
	private int loadCount = 0;
	private JSONArray lastJSONArray;
	
	public int getLoadCount() {
		return loadCount;
	}
	
	@Override
	public void load(JSONArray jsonArray) {
		++loadCount;
		lastJSONArray = jsonArray;
	}

	public JSONArray getLastLoadArgument() {
		return lastJSONArray;
	}
}
