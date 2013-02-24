package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.json.OldEventDatabaseFromJsonUrl;

import android.content.Context;
import android.net.ConnectivityManager;

public class OldJsonEventDatabaseFactory implements OldEventDatabaseFactory {
	private Context context;
	private static String eventsUrl = "http://misdb.com/barleylegalapp/getevent.aspx";
	
	public OldJsonEventDatabaseFactory(Context context) {
		this.context = context;
	}
	
	/* (non-Javadoc)
	 * @see com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactoryIF#getEventDatabase()
	 */
	@Override
	public OldEventDatabase getEventDatabase() {
    	ConnectivityManager connMgr = (ConnectivityManager) 
    	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkConnectivity networkConnectivity
    	= new AndroidNetworkConnectivity(connMgr);
    	OldEventDatabase eventDb = new OldEventDatabaseFromJsonUrl(networkConnectivity, eventsUrl);
    	return eventDb;
	}
	
}
