package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.BeerListActivity;
import com.itllp.barleylegalhomebrewers.ontap.BeerListFragment;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.test.ActivityUnitTestCase;

public class BeerListActivityTests extends ActivityUnitTestCase<BeerListActivity> {

	public BeerListActivityTests() {
		super(BeerListActivity.class);
	}
	
	public void testOnCreate() {
		// Set up preconditions
		Intent intent = new Intent();
		int expectedId = 42;
		String expectedIdString = String.valueOf(expectedId);
		
		intent.putExtra(BeerListActivity.EVENT_ID, expectedIdString);
		
		// Call method under test
		BeerListActivity activity = 
		launchActivityWithIntent("com.itllp.barleylegalhomebrewers.ontap", 
				BeerListActivity.class, intent);

		// Verify postconditions
		FragmentManager fragMgr = activity.getSupportFragmentManager();
        BeerListFragment beerListFrag = (BeerListFragment)fragMgr.
        	    findFragmentById(com.itllp.barleylegalhomebrewers.ontap
        	    		.R.id.beer_list_fragment);
        int actualId = 0;
        if (null != beerListFrag) {
        	actualId = beerListFrag.getEventId();
        }
        assertEquals(expectedId, actualId);
        activity.finish();
	}

}
