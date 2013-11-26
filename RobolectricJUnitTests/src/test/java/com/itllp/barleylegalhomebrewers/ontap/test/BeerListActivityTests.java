package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.itllp.barleylegalhomebrewers.ontap.BeerListActivity;
import com.itllp.barleylegalhomebrewers.ontap.BeerListFragment;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.OnTapContentProvider;

@RunWith(RobolectricTestRunner.class)
public class BeerListActivityTests {


	@Before
	public void setUp() throws Exception {
	}

	
	@After
	public void tearDown() throws Exception {
	}


	//FIXME this test throws a no resource exception
	@Ignore @Test
	public void testOnCreate() {
		// Set up preconditions
		OnTapContentProvider provider = new OnTapContentProvider();
		provider.onCreate();
		
		Intent intent = new Intent(Robolectric.getShadowApplication().getApplicationContext(),
                BeerListActivity.class);
		String expectedId = "42";
		intent.putExtra(BeerListActivity.EVENT_ID, expectedId);
		ActivityController<BeerListActivity> beerListActivityController =
				Robolectric.buildActivity(BeerListActivity.class);
		BeerListActivity beerListActivity = beerListActivityController.
				withIntent(intent).create().visible().get();
		Bundle savedInstanceState = new Bundle();
		
		// Call method under test
		beerListActivity.onCreate(savedInstanceState);
		
		// Verify postconditions
        BeerListFragment beerListFrag = (BeerListFragment)
        	    beerListActivity.getSupportFragmentManager().
        	    findFragmentById(com.itllp.barleylegalhomebrewers.ontap
        	    		.R.id.beer_list_fragment);
        String actualId = "0";
        if (null != beerListFrag) {
        	actualId = beerListFrag.getEventId();
        }
        assertEquals(expectedId, actualId);
	}


}
