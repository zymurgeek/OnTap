package com.itllp.beerfestival.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itllp.beerfestival.EventDatabase;
import com.itllp.beerfestival.dateconverter.StringConverter;

public class EventDatabaseFromJsonArray  extends EventDatabase {

	// Hashmap for ListView
	private ArrayList<HashMap<String, String>> eventList = new ArrayList<HashMap<String, String>>();
	private StringConverter dateConverter = null;

	public EventDatabaseFromJsonArray(JSONArray jsonArray, StringConverter dateConverter) {

		if (dateConverter == null) {
			throw new NullPointerException("dateConverter may not be null");
		}
		this.dateConverter = dateConverter;
		if (jsonArray == null) {
			return;
		}
		
    	for (int i = 0; i < jsonArray.length(); i++){
        	String id = "";
        	String eventName = "";
        	String eventDate = "";

    		try {
    			JSONObject jsonEvent = jsonArray.getJSONObject(i);

    			try {
    				id = jsonEvent.getString(EventDatabase.ID);
        		} catch (JSONException e) {}
    			try {
    				eventName = jsonEvent.getString(EventDatabase.EVENT_NAME);
        		} catch (JSONException e) {}
    			try {
    				eventDate = jsonEvent.getString(EventDatabase.EVENT_DATE);
        		} catch (JSONException e) {}
    			if (!id.contentEquals("") || !eventName.contentEquals("") || !eventDate.contentEquals("")) {
    				HashMap<String, String> map = null;
    				map = new HashMap<String, String>();

    				map.put(EventDatabase.ID, id);
    				map.put(EventDatabase.EVENT_NAME, eventName);
    				map.put(EventDatabase.EVENT_DATE, getDateString(eventDate));

    				eventList.add(map);
    			}
    		} catch (JSONException e) {}
    	}
	}

	private String getDateString(String inputDateString)	{
		return this.dateConverter.convertString(inputDateString);
	}
	
	
	@Override
	public ArrayList<HashMap<String, String>> getEventList() {
		return eventList;
	}

	@Override
	public boolean isEmpty() {
		return getEventList().isEmpty();
	}

}
