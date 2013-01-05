package com.itllp.beerfestival.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itllp.beerfestival.EventList;
import com.itllp.beerfestival.NetworkConnectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

public class EventListFromJsonUrl extends EventList {
    // Hashmap for ListView
	public ArrayList<HashMap<String, String>> eventList = new ArrayList<HashMap<String, String>>();
	public JSONParser jParser = new JSONParser();
	public JSONArray jsonArray = null;
	public String id = null;
	public String eventName = null;
	public String eventDate = null;
	public JSONObject phone = null;
	public Boolean active = null;
	public HashMap<String, String> map = null;
	
	public EventListFromJsonUrl(NetworkConnectivity networkConnectivity, String url) {
	    if (networkConnectivity.isConnected()) {

	    	jsonArray = this.jParser.getJsonArrayFromUrl(url);

	    	try {
	    		for(int i = 0; i < jsonArray.length(); i++){
	    			JSONObject jsonEvent = jsonArray.getJSONObject(i);

	    			this.id = jsonEvent.getString(this.ID);
	    			this.eventName = jsonEvent.getString(this.EVENT_NAME);
	    			this.eventDate = jsonEvent.getString(this.EVENT_DATE);
	    			this.active = jsonEvent.getBoolean(this.ACTIVE);

	    			this.map = new HashMap<String, String>();

	    			this.map.put(this.ID, this.id);
	    			this.map.put(this.EVENT_NAME, this.eventName);
	    			this.map.put(this.EVENT_DATE, getDateStringFromJson(this.eventDate));
	    			//this.map.put(this.ACTIVE, this.active);

	    			this.eventList.add(this.map);
	    		}
	    	} catch (JSONException e) {
	    		e.printStackTrace();
	    	}
	    }
	}
	
	private String getDateStringFromJson(String jsonDateString)	{
	    // Expect date in this format "/Date(1268123281843-0500)/" which is milliseconds since 1970 followed
		// by a timezone offset in hhmm format.
		String dateString = jsonDateString;
		return dateString;
	}
	
	
	/* (non-Javadoc)
	 * @see com.itllp.beerfestival.EventList#getEvents()
	 */
	@Override
	public ArrayList<HashMap<String, String>> getEvents() {
		return eventList; 
	}

	public boolean isEmpty() {
		return getEvents().isEmpty();
	}

}
