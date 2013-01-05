package com.itllp.beerfestival;

import com.itllp.beerfestival.json.EventListFromJsonUrl;

import android.content.Context;
import android.net.ConnectivityManager;

public class EventListFactory {
	private Context context;
	private static String eventsUrl = "http://misdb.com/barleylegalapp/getevent.aspx";
	
	public EventListFactory(Context context) {
		this.context = context;
	}
	
	public EventList getEventList() {
    	ConnectivityManager connMgr = (ConnectivityManager) 
    	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkConnectivity networkConnectivity
    	= new AndroidNetworkConnectivity(connMgr);
    	EventList eventList = new EventListFromJsonUrl(networkConnectivity, eventsUrl);
    	return eventList;
	}
	
}
