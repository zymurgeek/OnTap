package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.R;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

public class EventListActivity extends Activity 
{
	public EventListActivity() {
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Specify implementation class names in Bundle and use classForName to load them 
        try {
        	EventDatabaseImpl.create();
        } catch (DatabaseAlreadyInstantiatedException e) {}
        try {
        	Context context = this.getApplicationContext();
        	ConnectivityManager connMgr = (ConnectivityManager) 
        	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
        	NetworkConnectivity netConn = new AndroidNetworkConnectivity(connMgr);
        	EventDatabaseLoaderFactory.createBetaSiteEventDatabaseLoader(netConn);
        } catch (DatabaseLoaderAlreadyInstantiatedException e) {}
        setContentView(R.layout.event_list_fragment);
    }
    
}