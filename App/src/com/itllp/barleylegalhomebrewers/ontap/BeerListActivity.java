package com.itllp.barleylegalhomebrewers.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;


public class BeerListActivity  extends ActionBarActivity {
	public static final String SKIP_INSTANTIATION_FOR_TESTING 
	= "com.itllp.barleylegalhomebrewers.ontap.skipInstantiation_FOR_TESTING";
	public static int eventId = -1;
	public static final String EVENT_ID = "EVENT_ID";

	
	public BeerListActivity() {
	}

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        
        setContentView(R.layout.beer_list_fragment);
        
        String eventIdString = intent.getStringExtra(EVENT_ID);
        try {
        	eventId = Integer.parseInt(eventIdString);
        } catch (NumberFormatException e) {
        	// Assume no eventId was given and leave the prior value
        }
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        // TODO pass event ID via fragment arguments
        BeerListFragment beerListFrag = (BeerListFragment)
        	    getSupportFragmentManager().findFragmentById(com.itllp
        	    		.barleylegalhomebrewers.ontap.R.id.beer_list_fragment);
        if (null != beerListFrag) {
        	beerListFrag.setEventId(eventId);
        }
    }
	
	
}
