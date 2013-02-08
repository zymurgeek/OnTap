package com.itllp.beerfestival.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import com.itllp.beerfestival.EventDatabase;
import com.itllp.beerfestival.NetworkConnectivity;
import com.itllp.beerfestival.dateconverter.JavaDateToHumanReadableDate;
import com.itllp.beerfestival.dateconverter.JavaDateToString;
import com.itllp.beerfestival.dateconverter.JsonDateToJavaDate;
import com.itllp.beerfestival.dateconverter.StringConverter;
import com.itllp.beerfestival.dateconverter.StringToJavaDate;

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
	
	/* (non-Javadoc)
	 * @see com.itllp.beerfestival.EventList#getEvents()
	 */
	@Override
	public ArrayList<HashMap<String, String>> getEventList() {
		return eventDatabase.getEventList(); 
	}

	public boolean isEmpty() {
		return getEventList().isEmpty();
	}

}
