package com.itllp.barleylegalhomebrewers.ontap.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.OldEventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

public class EventDatabaseFromJsonArray  extends EventDatabase {

	private List<Event> eventList = new ArrayList<Event>();

	
	public EventDatabaseFromJsonArray(JSONArray jsonArray, StringToJavaDateConverter dateConverter) {

		if (dateConverter == null) {
			throw new NullPointerException("dateConverter may not be null");
		}
		if (jsonArray == null) {
			return;
		}
		
    	for (int i = 0; i < jsonArray.length(); i++){
        	int id = -1;
        	String eventName = "";
        	Date eventDate = new Date();

    		try {
    			JSONObject jsonEvent = jsonArray.getJSONObject(i);

    			try {
    				String idString = jsonEvent.getString(OldEventDatabase.ID);
    				id = Integer.parseInt(idString);
        		} catch (JSONException e) {}
    			try {
    				eventName = jsonEvent.getString(OldEventDatabase.EVENT_NAME);
        		} catch (JSONException e) {}
    			try {
    				String jsonEventDate = jsonEvent.getString(OldEventDatabase.EVENT_DATE);
    				eventDate = dateConverter.getJavaDate(jsonEventDate);
        		} catch (JSONException e) {}
    			if (id != -1) {
    				Event event = new Event(id);
    				event.setName(eventName);
    				event.setDate(eventDate);
    				eventList.add(event);
    			}
    		} catch (JSONException e) {}
    	}
	}

	
	@Override
	public void clearEventList() {
		eventList.clear();
	}
	
	
	@Override
	public List<Event> getEventList() {
		return eventList;
	}

	
	@Override
	public boolean isEmpty() {
		return getEventList().isEmpty();
	}

}
