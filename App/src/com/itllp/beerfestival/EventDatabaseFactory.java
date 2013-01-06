package com.itllp.beerfestival;

import com.itllp.beerfestival.json.EventListFromJsonUrl;

import android.content.Context;
import android.net.ConnectivityManager;

public class EventDatabaseFactory {
	private Context context;
	private static String eventsUrl = "http://misdb.com/barleylegalapp/getevent.aspx";
	
	public EventDatabaseFactory(Context context) {
		this.context = context;
	}
	
	public EventDatabase getEventDatabase() {
    	ConnectivityManager connMgr = (ConnectivityManager) 
    	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkConnectivity networkConnectivity
    	= new AndroidNetworkConnectivity(connMgr);
    	EventDatabase eventList = new EventListFromJsonUrl(networkConnectivity, eventsUrl);
    	return eventList;
	}
	
}
