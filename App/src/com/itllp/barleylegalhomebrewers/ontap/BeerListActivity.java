package com.itllp.barleylegalhomebrewers.ontap;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

// TODO:  Should BeerListActivity be a fragment instead?  
public class BeerListActivity  extends FragmentActivity {
	public static final String SKIP_INSTANTIATION_FOR_TESTING 
	= "com.itllp.barleylegalhomebrewers.ontap.skipInstantiation_FOR_TESTING";
	//private ListView beerListView;
	//private BeerDatabase beerDb = null;
	public static final String EVENT_ID = "EVENT_ID";
	
	public BeerListActivity() {
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        
        boolean skipInstantiation = intent.getBooleanExtra(SKIP_INSTANTIATION_FOR_TESTING, false);
        if (!skipInstantiation) {
        	BeerDatabaseImpl.create();

        	Context context = this.getApplicationContext();
        	ConnectivityManager connMgr = (ConnectivityManager) 
        	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
        	NetworkConnectivity netConn = new AndroidNetworkConnectivity(connMgr);
        	BeerDatabaseLoaderFactory.createProductionSiteBeerDatabaseLoader(netConn);
        }
        setContentView(R.layout.beer_list_fragment);
        
        //String eventId = intent.getStringExtra(EVENT_ID);
    }
}
