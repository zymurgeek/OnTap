// Copyright 2013 David A. Greenbaum
/*
This file is part of On Tap.

On Tap is free software: you can redistribute it and/or
modify it under the terms of the GNU General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

Tip On Discount is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with Tip On Discount.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.itllp.barleylegalhomebrewers.ontap.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.test.ActivityUnitTestCase;
import android.widget.ListView;

import com.itllp.barleylegalhomebrewers.ontap.Beer;
import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseImpl;
import com.itllp.barleylegalhomebrewers.ontap.BeerListActivity;
import com.itllp.barleylegalhomebrewers.ontap.BeerListFragment;
import com.itllp.barleylegalhomebrewers.ontap.BeerListLoaderFactory;

public class BeerListFragmentTests extends
	ActivityUnitTestCase<BeerListActivity> {

	private Intent intent;
    ListView beerListView;
	private Instrumentation mInstrumentation;
	private BeerListLoaderFactory blFactory;
	private Context context;
	private MockBeerListAsyncTaskLoader mockLoader;
    
    
	public BeerListFragmentTests() {
		super(BeerListActivity.class);
	}

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mInstrumentation = getInstrumentation();
    	
    	FakeBeerDatabase.clearInstance();
    	BeerDatabaseImpl.create();
    	
    	blFactory = new BeerListLoaderFactory();
    	context = getInstrumentation().getContext();
    	mockLoader = new MockBeerListAsyncTaskLoader(context);
    	blFactory.setLoader(mockLoader);
    	
        intent = new Intent(context, BeerListActivity.class);
		intent.putExtra(BeerListActivity.SKIP_INSTANTIATION_FOR_TESTING, true);
    }
    
    public void testEmptyList() {
    	
    	// Method under test
    	BeerListActivity activity = startActivity(intent, null, null);
    	mInstrumentation.waitForIdleSync();
    	
    	// Postconditions
    	FragmentManager fragmentManager = activity.getSupportFragmentManager();
    	BeerListFragment beerListFragment = (BeerListFragment)
    		fragmentManager.findFragmentById
    		(com.itllp.barleylegalhomebrewers.ontap.R.id.beer_list_fragment);
        beerListView = (ListView)beerListFragment.getListView();
        assertEquals("List should be empty", 0, 
        		beerListView.getCount());    	
    }

    public void testListWithOneItem() {
    	List<Beer> beerList = new ArrayList<Beer>();
    	Beer beer = new Beer(1);
    	beerList.add(beer);
    	BeerListActivity activity = startActivity(intent, null, null);
    	FragmentManager fragmentManager = activity.getSupportFragmentManager();
    	BeerListFragment beerListFragment = (BeerListFragment)
    		fragmentManager.findFragmentById
    		(com.itllp.barleylegalhomebrewers.ontap.R.id.beer_list_fragment);
    	beerListFragment.onActivityCreated(null);
    	
    	// Method under test
    	beerListFragment.onLoadFinished(null, beerList);
    	
    	// Postconditions
        beerListView = (ListView)beerListFragment.getListView();
        assertEquals("List should have 1 item", 1, 
        		beerListView.getCount());    	
    }
    
    public void testListWithTwoItems() {
    	List<Beer> beerList = new ArrayList<Beer>();
    	Beer beer = new Beer(10);
    	beerList.add(beer);
    	beer = new Beer(20);
    	beerList.add(beer);
    	BeerListActivity activity = startActivity(intent, null, null);
    	FragmentManager fragmentManager = activity.getSupportFragmentManager();
    	BeerListFragment beerListFragment = (BeerListFragment)
    		fragmentManager.findFragmentById
    		(com.itllp.barleylegalhomebrewers.ontap.R.id.beer_list_fragment);
    	beerListFragment.onActivityCreated(null);

    	// Method under test
    	beerListFragment.onLoadFinished(null, beerList);
    	
    	// Postconditions
        beerListView = (ListView)beerListFragment.getListView();
        assertEquals("List should have 2 items", 2, 
        		beerListView.getCount());
        beer = (Beer)beerListView.getItemAtPosition(0);
        assertEquals("First beer ID should be 10", 10, beer.getId());
        beer = (Beer)beerListView.getItemAtPosition(1);
        assertEquals("Second beer ID should be 20", 20, beer.getId());
    }

}
