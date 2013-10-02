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

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.support.v4.app.FragmentManager;
import android.test.ActivityUnitTestCase;
import android.widget.ListView;

import com.itllp.barleylegalhomebrewers.ontap.EventListFromProviderActivity;
import com.itllp.barleylegalhomebrewers.ontap.EventListFromProviderFragment;
import com.itllp.barleylegalhomebrewers.ontap.database.EventTable;
import com.itllp.barleylegalhomebrewers.ontap.database.SQLiteEventTable;

public class EventListFromProviderFragmentTests extends
	ActivityUnitTestCase<EventListFromProviderActivity> {

	private Intent intent;
    ListView eventListView;
	private Instrumentation instrumentation;
	private Context context;
	private static final String[] COLUMN_NAMES = { EventTable.ID_COLUMN, EventTable.NAME_COLUMN,
			SQLiteEventTable.START_LOCAL_TIME_COLUMN };
	private static final Object[] ROW1_COLUMN_VALUES = {Integer.valueOf(10), "Event10", "10/10/2013"};
	private static final Object[] ROW2_COLUMN_VALUES = {Integer.valueOf(20), "Event20", "10/20/2013"};
	private MatrixCursor mockCursor;
	private EventListFromProviderActivity activity;
	private FragmentManager fragmentManager;
	private EventListFromProviderFragment eventListFragment;
    
    
	public EventListFromProviderFragmentTests() {
		super(EventListFromProviderActivity.class);
	}

	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        instrumentation = getInstrumentation();
    	
    	context = getInstrumentation().getContext();
    	
        intent = new Intent(context, EventListFromProviderActivity.class);
        activity = startActivity(intent, null, null);
    	instrumentation.waitForIdleSync();
    	fragmentManager = activity.getSupportFragmentManager();
    	eventListFragment = (EventListFromProviderFragment)
    			fragmentManager.findFragmentById
    			(com.itllp.barleylegalhomebrewers.ontap.R.id.event_list_fragment);
        eventListView = (ListView)eventListFragment.getListView();
        eventListFragment.onActivityCreated(null);
        mockCursor = new MatrixCursor(COLUMN_NAMES);
    }
    
    
    public void testEmptyList() {
    	// Verify postconditions
        assertEquals("List should be empty", 0, 
        		eventListView.getCount());    	
    }



    public void testListWithOneItem() {
    	// Set up preconditions
    	mockCursor.addRow(ROW1_COLUMN_VALUES);

    	// Call method under test
    	eventListFragment.onLoadFinished(null, mockCursor);
    	
    	// Verify postconditions
        assertEquals("List should have 1 item", 1, 
        		eventListView.getCount());    	
    }


    public void testListWithTwoItems() {
    	// Set up preconditions
		mockCursor.addRow(ROW1_COLUMN_VALUES);
    	mockCursor.addRow(ROW2_COLUMN_VALUES);

    	// Call method under test
    	eventListFragment.onLoadFinished(null, mockCursor);
    	
    	// Verify postconditions
        assertEquals("List should have 2 items", 2, 
        		eventListView.getCount());
        MatrixCursor row1 = (MatrixCursor)eventListView.getItemAtPosition(0);
        int idColumnIndex = row1.getColumnIndex(EventTable.ID_COLUMN);
        assertEquals("First event ID should be 10", 10, row1.getInt(idColumnIndex));
        MatrixCursor row2 = (MatrixCursor)eventListView.getItemAtPosition(1);
        assertEquals("Second event ID should be 20", 20, row2.getInt(idColumnIndex));
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
