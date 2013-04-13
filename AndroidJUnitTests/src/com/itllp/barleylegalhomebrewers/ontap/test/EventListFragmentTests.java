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

import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseImpl;
import com.itllp.barleylegalhomebrewers.ontap.EventListActivity;
import com.itllp.barleylegalhomebrewers.ontap.EventListFragment;
import com.itllp.barleylegalhomebrewers.ontap.EventListLoaderFactory;

public class EventListFragmentTests extends
	ActivityUnitTestCase<EventListActivity> {

	private Intent intent;
    ListView eventListView;
	private Instrumentation instrumentation;
	private EventListLoaderFactory elFactory;
	private Context context;
	private MockEventListAsyncTaskLoader mockLoader;
    
    
	public EventListFragmentTests() {
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
    
    
    public void testEmptyList() {
    	
    	// Method under test
    	EventListActivity activity = startActivity(intent, null, null);
    	instrumentation.waitForIdleSync();
    	
    	// Postconditions
    	FragmentManager fragmentManager = activity.getSupportFragmentManager();
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
    	EventListActivity activity = startActivity(intent, null, null);
    	FragmentManager fragmentManager = activity.getSupportFragmentManager();
    	EventListFragment eventListFragment = (EventListFragment)
    		fragmentManager.findFragmentById
    		(com.itllp.barleylegalhomebrewers.ontap.R.id.event_list_fragment);
    	eventListFragment.onActivityCreated(null);

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
    	EventListActivity activity = startActivity(intent, null, null);
    	FragmentManager fragmentManager = activity.getSupportFragmentManager();
    	EventListFragment eventListFragment = (EventListFragment)
    		fragmentManager.findFragmentById
    		(com.itllp.barleylegalhomebrewers.ontap.R.id.event_list_fragment);
    	eventListFragment.onActivityCreated(null);

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

    
    // TODO Implement testOnListItemClick
    /*
    public void testOnListItemClick() {
    	List<Event> eventList = new ArrayList<Event>();
    	Event event = new Event(10);
    	eventList.add(event);
    	event = new Event(20);
    	eventList.add(event);
    	EventListActivity eventListActivity = startActivity(intent, null, null);
    	FragmentManager fragmentManager = eventListActivity.getSupportFragmentManager();
    	final EventListFragment eventListFragment = (EventListFragment)
    		fragmentManager.findFragmentById
    		(com.itllp.barleylegalhomebrewers.ontap.R.id.event_list_fragment);
    	final View eventListFragmentView = eventListFragment.getView();
    	final ListView eventListView = eventListFragment.getListView();
    	
    	// See http://stackoverflow.com/questions/9405561/test-if-a-button-starts-a-new-activity-in-android-junit-pref-without-robotium
    	ActivityMonitor activityMonitor = getInstrumentation().addMonitor(BeerListActivity.class.getName(), null, false);
    	eventListActivity.runOnUiThread(new Runnable() {
    	    @Override
    	    public void run() {
    	      eventListFragment.onListItemClick(eventListView, eventListFragmentView, 2, 
    	    		  (long)com.itllp.barleylegalhomebrewers.ontap.R.id.id);
    	    }
    	  });
    	
    	BeerListActivity beerListActivity = (BeerListActivity) instrumentation.waitForMonitorWithTimeout(activityMonitor, 5);
    	assertEquals(20, beerListActivity.eventId);
    }
	*/
}
