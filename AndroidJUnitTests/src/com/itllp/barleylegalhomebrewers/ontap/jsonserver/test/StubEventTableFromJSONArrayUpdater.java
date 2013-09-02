package com.itllp.barleylegalhomebrewers.ontap.jsonserver.test;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.TableFromJSONArrayUpdater;

public class StubEventTableFromJSONArrayUpdater implements
		TableFromJSONArrayUpdater {

	@Override
	public void updateTable(JSONArray jsonArray) {
		++loadCount;
		lastLoadedJSONArray = jsonArray;
	}

	public int stub_getLoadCount() {
		return loadCount;
	}

	public void stub_resetLoadCount() {
		loadCount = 0;
	}

	public JSONArray stub_getLastLoadedJSONArray() {
		return lastLoadedJSONArray;
	}
	
	public void stub_resetLastLoadedJSONArray() {
		lastLoadedJSONArray = null;
	}
	
	
	private int loadCount = 0;
	private JSONArray lastLoadedJSONArray = null;
	
}
