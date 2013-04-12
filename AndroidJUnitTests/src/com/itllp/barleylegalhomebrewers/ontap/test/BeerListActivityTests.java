// Copyright 2013 David A. Greenbaum
/*
This file is part of On Tap.

On Tap is free software: you can redistribute it and/or
modify it under the terms of the GNU General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

On Tap is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with On Tap.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.itllp.barleylegalhomebrewers.ontap.test;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.ListView;

import com.itllp.barleylegalhomebrewers.ontap.BeerDatabase;
import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseImpl;
import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseLoaderFactory;
import com.itllp.barleylegalhomebrewers.ontap.BeerListActivity;
import com.itllp.barleylegalhomebrewers.ontap.BeerListLoaderFactory;
import com.itllp.barleylegalhomebrewers.ontap.json.JsonUrlBeerDatabaseLoader;

public class BeerListActivityTests extends
	ActivityUnitTestCase<BeerListActivity> {

	private Intent intent;
    ListView beerListView;
	private BeerListLoaderFactory blFactory;
	private Context context;
	private MockBeerListAsyncTaskLoader mockLoader;
    
    
	public BeerListActivityTests() {
		super(BeerListActivity.class);
	}

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        getInstrumentation();
    	
    	FakeBeerDatabase.clearInstance();
    	BeerDatabaseImpl.create();
    	
    	blFactory = new BeerListLoaderFactory();
    	context = getInstrumentation().getContext();
    	mockLoader = new MockBeerListAsyncTaskLoader(context);
    	blFactory.setLoader(mockLoader);
    	
        intent = new Intent(context, BeerListActivity.class);
		intent.putExtra(BeerListActivity.SKIP_INSTANTIATION_FOR_TESTING, true);
    }
    
    public void testInitialization() {
    	// Preconditions
    	FakeBeerDatabase.clearInstance();
    	FakeBeerDatabaseLoader.clearInstance();
		intent.putExtra(BeerListActivity.SKIP_INSTANTIATION_FOR_TESTING, false);

    	// Method under test
    	BeerListActivity activity = startActivity(intent, null, null);
    	
    	// Postconditions
    	assertNotNull(activity);
    	assertTrue(BeerDatabase.getInstance() instanceof BeerDatabaseImpl);
    	assertTrue(BeerDatabaseLoader.getInstance() instanceof JsonUrlBeerDatabaseLoader);
    	JsonUrlBeerDatabaseLoader loader = (JsonUrlBeerDatabaseLoader)BeerDatabaseLoader.getInstance();
    	String expectedUrl = BeerDatabaseLoaderFactory.productionSiteUrl;
    	String actualUrl = loader.getUrl();
    	assertEquals(expectedUrl, actualUrl);
    }

    // TODO Create other initialization tests to mirror EventListActivityTests
}
