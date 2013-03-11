package com.itllp.barleylegalhomebrewers.ontap;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

public class JsonArrayEventDatabaseLoaderImpl implements JsonArrayEventDatabaseLoader {

	public static final String ID = "ID";
	public static final String EVENT_NAME = "EventName";
	public static final String EVENT_DATE = "EventDate";
	private NewEventDatabase eventDatabase;
	private StringToJavaDateConverter dateConverter;
	
	public JsonArrayEventDatabaseLoaderImpl(StringToJavaDateConverter dateConverter,
			NewEventDatabase eventDatabase) {
		if (null == dateConverter) {
			throw new NullPointerException();
		}
		if (null == eventDatabase) {
			throw new NullPointerException();
		}
		this.dateConverter = dateConverter;
		this.eventDatabase = eventDatabase;
	}

	@Override
	public void load(JSONArray jsonArray) {
		if (null == jsonArray) {
			return;
		}
		
    	for (int i = 0; i < jsonArray.length(); i++){
        	int id = -1;
        	String eventName = "";
        	Date eventDate = new Date();

    		try {
    			JSONObject jsonEvent = jsonArray.getJSONObject(i);
    			
    			try {
    				String idString = jsonEvent.getString(ID);
    				id = Integer.parseInt(idString);
        		} catch (JSONException e) {}
   
    			try {
    				eventName = jsonEvent.getString(EVENT_NAME);
        		} catch (JSONException e) {}

    			try {
    				String jsonEventDate = jsonEvent.getString(EVENT_DATE);
    				eventDate = dateConverter.getJavaDate(jsonEventDate);
        		} catch (JSONException e) {}

    			Event event = new Event(id);
    			event.setName(eventName);
    			event.setDate(eventDate);
    			eventDatabase.addOrUpdateEvent(event);
    		} catch (JSONException e) {}
    	}
	}

}