package com.itllp.barleylegalhomebrewers.ontap.json.test;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.JsonArrayEventDatabaseLoader;

public class FakeJsonArrayEventDatabaseLoader implements
		JsonArrayEventDatabaseLoader {
	
	private int loadCount = 0;
	private JSONArray lastJsonArray;
	
	public int getLoadCount() {
		return loadCount;
	}
	
	@Override
	public void load(JSONArray jsonArray) {
		++loadCount;
		lastJsonArray = jsonArray;
	}

	public JSONArray getLastLoadArgument() {
		return lastJsonArray;
	}
}
