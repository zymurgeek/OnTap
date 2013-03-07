// Copyright 2013 David A. Greenbaum
/*
This file is part of Beer Festival.

Beer Festival is free software: you can redistribute it and/or
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

import android.app.FragmentManager;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.ListView;

import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactoryProvider;
import com.itllp.barleylegalhomebrewers.ontap.EventListActivityWithFragments;
import com.itllp.barleylegalhomebrewers.ontap.EventListFragment;
import com.itllp.barleylegalhomebrewers.ontap.EventListLoaderFactory;

public class EventListActivityWithFragmentsTests extends
	ActivityUnitTestCase<EventListActivityWithFragments> {

	private Intent mIntent;
    ListView eventListView;
	private LocalEventDatabaseFactory mLocalEventDbFactory;
	private Instrumentation mInstrumentation;
	private EventListLoaderFactory elFactory;
	private Context context;
	private MockEventListAsyncTaskLoader mockLoader;
    
    
	public EventListActivityWithFragmentsTests() {
		super(EventListActivityWithFragments.class);
	}

	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mIntent = new Intent();
        mInstrumentation = getInstrumentation();
		mLocalEventDbFactory = new LocalEventDatabaseFactory();
    	EventDatabaseFactoryProviderTestHelper.clearEventDatabaseFactory();
    	EventDatabaseFactoryProvider.setEventDatabaseFactory(mLocalEventDbFactory);
    	elFactory = new EventListLoaderFactory();
    	context = getInstrumentation().getContext();
    	mockLoader = new MockEventListAsyncTaskLoader(context);
    	elFactory.setLoader(mockLoader);
    }
    

    public void testInitialization() {
    	// Method under test
    	EventListActivityWithFragments activity = startActivity(mIntent, null, null);
    	
    	// Postconditions
    	assertNotNull(activity);
    }
    

    public void testEmptyList() {
    	
    	// Method under test
    	EventListActivityWithFragments activity = startActivity(mIntent, null, null);
    	mInstrumentation.waitForIdleSync();
    	
    	// Postconditions
    	FragmentManager fragmentManager = activity.getFragmentManager();
    	EventListFragment eventListFragment = (EventListFragment)
    		fragmentManager.findFragmentById
    		(com.itllp.barleylegalhomebrewers.ontap.R.id.event_list_fragment);
        eventListView = (ListView)eventListFragment.getListView();
        assertEquals("List should be empty", 0, 
        		eventListView.getCount());    	
    }



    public void testListWithOneItem() {
    	List<Event> eventList = new ArrayList<Event>();
    	Event event = new Event(1);
    	eventList.add(event);
    	EventListActivityWithFragments activity = startActivity(mIntent, null, null);
    	FragmentManager fragmentManager = activity.getFragmentManager();
    	EventListFragment eventListFragment = (EventListFragment)
    		fragmentManager.findFragmentById
    		(com.itllp.barleylegalhomebrewers.ontap.R.id.event_list_fragment);
    	
    	// Method under test
    	eventListFragment.onLoadFinished(null, eventList);
    	
    	// Postconditions
        eventListView = (ListView)eventListFragment.getListView();
        assertEquals("List should have 1 item", 1, 
        		eventListView.getCount());    	
    }


    public void testListWithTwoItems() {
    	List<Event> eventList = new ArrayList<Event>();
    	Event event = new Event(10);
    	eventList.add(event);
    	event = new Event(20);
    	eventList.add(event);
    	EventListActivityWithFragments activity = startActivity(mIntent, null, null);
    	FragmentManager fragmentManager = activity.getFragmentManager();
    	EventListFragment eventListFragment = (EventListFragment)
    		fragmentManager.findFragmentById
    		(com.itllp.barleylegalhomebrewers.ontap.R.id.event_list_fragment);
    	
    	// Method under test
    	eventListFragment.onLoadFinished(null, eventList);
    	
    	// Postconditions
        eventListView = (ListView)eventListFragment.getListView();
        assertEquals("List should have 2 items", 2, 
        		eventListView.getCount());
        event = (Event)eventListView.getItemAtPosition(0);
        assertEquals("First event ID should be 10", 10, event.getId());
        event = (Event)eventListView.getItemAtPosition(1);
        assertEquals("Second event ID should be 20", 20, event.getId());
    }



}
