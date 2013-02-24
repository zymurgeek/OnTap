package com.itllp.barleylegalhomebrewers.ontap.json;

import java.util.List;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToHumanReadableDate;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToString;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JsonDateToJavaDate;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDate;

public class EventDatabaseFromJsonUrl extends EventDatabase {
	private EventDatabaseFromJsonArray eventDatabase = null;
	
    public EventDatabaseFromJsonUrl(NetworkConnectivity networkConnectivity, String url) {
	    if (networkConnectivity.isConnected()) {
	    	JSONParser jParser = new JSONParser();
	    	JSONArray jsonArray = jParser.getJsonArrayFromUrl(url);
	    	StringToJavaDate jsonDateConverter = new JsonDateToJavaDate();
	    	JavaDateToString javaDateConverter = new JavaDateToHumanReadableDate(); 
	    	StringConverter dateConverter = new JsonDateToHumanReadableDate(jsonDateConverter,
	    			javaDateConverter);
	    	eventDatabase = new EventDatabaseFromJsonArray(jsonArray, dateConverter);
	    }
	}
	

	@Override
	public void clearEventList() {
		eventDatabase.clearEventList();
	}
	
	/* (non-Javadoc)
	 * @see com.itllp.barleylegalhomebrewers.ontap.EventList#getEvents()
	 */
	@Override
	public List<Event> getEventList() {
		return eventDatabase.getEventList(); 
	}

	public boolean isEmpty() {
		return getEventList().isEmpty();
	}

}
