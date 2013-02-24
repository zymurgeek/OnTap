package com.itllp.barleylegalhomebrewers.ontap.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itllp.barleylegalhomebrewers.ontap.OldEventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringConverter;

public class OldEventDatabaseFromJsonArray  extends OldEventDatabase {

	// Hashmap for ListView
	private ArrayList<HashMap<String, String>> eventList = new ArrayList<HashMap<String, String>>();
	private StringConverter dateConverter = null;

	public OldEventDatabaseFromJsonArray(JSONArray jsonArray, StringConverter dateConverter) {

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
    				id = jsonEvent.getString(OldEventDatabase.ID);
        		} catch (JSONException e) {}
    			try {
    				eventName = jsonEvent.getString(OldEventDatabase.EVENT_NAME);
        		} catch (JSONException e) {}
    			try {
    				eventDate = jsonEvent.getString(OldEventDatabase.EVENT_DATE);
        		} catch (JSONException e) {}
    			if (!id.contentEquals("") || !eventName.contentEquals("") || !eventDate.contentEquals("")) {
    				HashMap<String, String> map = null;
    				map = new HashMap<String, String>();

    				map.put(OldEventDatabase.ID, id);
    				map.put(OldEventDatabase.EVENT_NAME, eventName);
    				map.put(OldEventDatabase.EVENT_DATE, getDateString(eventDate));

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
