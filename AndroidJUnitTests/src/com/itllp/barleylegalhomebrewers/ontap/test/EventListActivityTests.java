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

import android.support.v4.app.FragmentManager;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.ListView;

import com.itllp.barleylegalhomebrewers.ontap.DatabaseAlreadyInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderAlreadyInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoaderFactory;
import com.itllp.barleylegalhomebrewers.ontap.EventListActivity;
import com.itllp.barleylegalhomebrewers.ontap.EventListFragment;
import com.itllp.barleylegalhomebrewers.ontap.EventListLoaderFactory;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseImpl;
import com.itllp.barleylegalhomebrewers.ontap.json.JsonUrlEventDatabaseLoader;

public class EventListActivityTests extends
	ActivityUnitTestCase<EventListActivity> {

	private Intent intent;
    ListView eventListView;
	private Instrumentation instrumentation;
	private EventListLoaderFactory elFactory;
	private Context context;
	private MockEventListAsyncTaskLoader mockLoader;
    
    
	public EventListActivityTests() {
		super(EventListActivity.class);
	}

	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        instrumentation = getInstrumentation();
    	
    	FakeEventDatabase.clearInstance();
    	EventDatabaseImpl.create();
    	
    	elFactory = new EventListLoaderFactory();
    	context = getInstrumentation().getContext();
    	mockLoader = new MockEventListAsyncTaskLoader(context);
    	elFactory.setLoader(mockLoader);
    	
        intent = new Intent(context, EventListActivity.class);
		intent.putExtra(EventListActivity.SKIP_INSTANTIATION_FOR_TESTING, true);
    }
    

    public void testInitialization() {
    	// Preconditions
    	FakeEventDatabase.clearInstance();
    	FakeEventDatabaseLoader.clearInstance();
		intent.putExtra(EventListActivity.SKIP_INSTANTIATION_FOR_TESTING, false);

    	// Method under test
    	EventListActivity activity = startActivity(intent, null, null);
    	instrumentation.waitForIdleSync();
    	
    	// Postconditions
    	assertNotNull(activity);
    	assertTrue(EventDatabase.getInstance() instanceof EventDatabaseImpl);
    	assertTrue(EventDatabaseLoader.getInstance() instanceof JsonUrlEventDatabaseLoader);
    	JsonUrlEventDatabaseLoader loader = (JsonUrlEventDatabaseLoader)EventDatabaseLoader.getInstance();
    	String expectedUrl = EventDatabaseLoaderFactory.productionSiteUrl;
    	String actualUrl = loader.getUrl();
    	assertEquals(expectedUrl, actualUrl);
    }
    
    public void testInitializationWhenAlreadyInitializedWithRightClasses() {
    	// Preconditions
		intent.putExtra(EventListActivity.SKIP_INSTANTIATION_FOR_TESTING, false);

    	// Method under test
    	EventListActivity activity = startActivity(intent, null, null);
    	instrumentation.waitForIdleSync();
    	
    	// Postconditions
    	assertNotNull(activity);
    	assertTrue(EventDatabase.getInstance() instanceof EventDatabaseImpl);
    	assertTrue(EventDatabaseLoader.getInstance() instanceof JsonUrlEventDatabaseLoader);
    	JsonUrlEventDatabaseLoader loader = (JsonUrlEventDatabaseLoader)EventDatabaseLoader.getInstance();
    	String expectedUrl = EventDatabaseLoaderFactory.productionSiteUrl;
    	String actualUrl = loader.getUrl();
    	assertEquals(expectedUrl, actualUrl);
    }
    
    public void testInitializationWhenAlreadyInitializedWithWrongEventDatabase() {
    	// Preconditions
    	EventDatabase.clearInstance();
    	FakeEventDatabase.create();
		intent.putExtra(EventListActivity.SKIP_INSTANTIATION_FOR_TESTING, false);

    	// Method under test and postconditions
		try {
			startActivity(intent, null, null);
			fail("Should throw exception");
		} catch (DatabaseAlreadyInstantiatedException e) {}
    }
    
    public void testInitializationWhenAlreadyInitializedWithWrongEventDatabaseLoader() {
    	// Preconditions
    	EventDatabaseLoader.clearInstance();
    	FakeEventDatabaseLoader.create();
		intent.putExtra(EventListActivity.SKIP_INSTANTIATION_FOR_TESTING, false);

    	// Method under test and postconditions
		try {
			startActivity(intent, null, null);
			fail("Should throw exception");
		} catch (DatabaseLoaderAlreadyInstantiatedException e) {}
    }

}
