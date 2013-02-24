package com.itllp.barleylegalhomebrewers.ontap.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.OldEventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToHumanReadableDate;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToString;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JsonDateToJavaDate;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

public class OldEventDatabaseFromJsonUrl extends OldEventDatabase {
	private OldEventDatabaseFromJsonArray eventDatabase = null;
	
    public OldEventDatabaseFromJsonUrl(NetworkConnectivity networkConnectivity, String url) {
	    if (networkConnectivity.isConnected()) {
	    	JSONParser jParser = new JSONParser();
	    	JSONArray jsonArray = jParser.getJsonArrayFromUrl(url);
	    	StringToJavaDateConverter jsonDateConverter = new JsonDateToJavaDate();
	    	JavaDateToString javaDateConverter = new JavaDateToHumanReadableDate(); 
	    	StringConverter dateConverter = new JsonDateToHumanReadableDate(jsonDateConverter,
	    			javaDateConverter);
	    	eventDatabase = new OldEventDatabaseFromJsonArray(jsonArray, dateConverter);
	    }
	}
	
	/* (non-Javadoc)
	 * @see com.itllp.barleylegalhomebrewers.ontap.EventList#getEvents()
	 */
	@Override
	public ArrayList<HashMap<String, String>> getEventList() {
		return eventDatabase.getEventList(); 
	}

	public boolean isEmpty() {
		return getEventList().isEmpty();
	}

}
