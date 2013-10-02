package com.itllp.barleylegalhomebrewers.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BeerListActivity  extends FragmentActivity {
	public static final String SKIP_INSTANTIATION_FOR_TESTING 
	= "com.itllp.barleylegalhomebrewers.ontap.skipInstantiation_FOR_TESTING";
	public int eventId = -1;
	public static final String EVENT_ID = "EVENT_ID";
	
	public BeerListActivity() {
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        
        //TODO Delete me 
        //createBeerDatabaseAndLoader(intent);
        
        setContentView(R.layout.beer_list_fragment);
        
        String eventIdString = intent.getStringExtra(EVENT_ID);
        try {
        	eventId = Integer.parseInt(eventIdString);
        } catch (NumberFormatException e) {
        	eventId = -1;
        }
        
        // TODO pass event ID via fragment arguments
		//BeerDatabaseLoader loader = BeerDatabaseLoader.getInstance();
		//loader.setEventId(eventId);

    }

	//TODO Delete me
	/*
	private void createBeerDatabaseAndLoader(Intent intent) {
        
        boolean skipInstantiation = intent.getBooleanExtra(SKIP_INSTANTIATION_FOR_TESTING, false);
        if (!skipInstantiation) {
        	if (null == BeerDatabase.getInstance()) {
        		BeerDatabaseImpl.create();
        	} else {
        		if (! (BeerDatabase.getInstance() instanceof BeerDatabaseImpl)) {
        			throw (new DatabaseAlreadyInstantiatedException());
        		}
        	}
        	
        	if (null == BeerDatabaseLoader.getInstance()) {
	        	Context context = this.getApplicationContext();
	        	ConnectivityManager connMgr = (ConnectivityManager) 
	        	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        	NetworkConnectivity netConn = new AndroidNetworkConnectivity(connMgr);
	        	BeerDatabaseLoaderFactory.createProductionSiteBeerDatabaseLoader(netConn);
        	} else {
        		if (!(BeerDatabaseLoader.getInstance() instanceof JSONUrlBeerDatabaseLoader)) {
        			throw (new DatabaseLoaderAlreadyInstantiatedException());
        		}
        	}
        }
	}
	*/
}
