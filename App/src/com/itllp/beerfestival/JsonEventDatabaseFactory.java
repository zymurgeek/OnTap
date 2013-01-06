package com.itllp.beerfestival;

import com.itllp.beerfestival.json.EventDatabaseFromJsonUrl;

import android.content.Context;
import android.net.ConnectivityManager;

public class JsonEventDatabaseFactory implements EventDatabaseFactory {
	private Context context;
	private static String eventsUrl = "http://misdb.com/barleylegalapp/getevent.aspx";
	
	public JsonEventDatabaseFactory(Context context) {
		this.context = context;
	}
	
	/* (non-Javadoc)
	 * @see com.itllp.beerfestival.EventDatabaseFactoryIF#getEventDatabase()
	 */
	@Override
	public EventDatabase getEventDatabase() {
    	ConnectivityManager connMgr = (ConnectivityManager) 
    	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkConnectivity networkConnectivity
    	= new AndroidNetworkConnectivity(connMgr);
    	EventDatabase eventDb = new EventDatabaseFromJsonUrl(networkConnectivity, eventsUrl);
    	return eventDb;
	}
	
}
