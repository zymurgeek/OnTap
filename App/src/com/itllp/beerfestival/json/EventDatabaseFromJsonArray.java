package com.itllp.beerfestival.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

import com.itllp.beerfestival.EventDatabase;

public class EventDatabaseFromJsonArray  extends EventDatabase {

	public EventDatabaseFromJsonArray(JSONArray jsonArray) {
	}

	@Override
	public ArrayList<HashMap<String, String>> getEventList() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

}
