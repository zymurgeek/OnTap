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
import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.v4.app.FragmentManager;
import android.test.ActivityUnitTestCase;
import android.widget.ListView;

import com.itllp.barleylegalhomebrewers.ontap.EventListActivity;
import com.itllp.barleylegalhomebrewers.ontap.EventListFragment;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.OnTapContentProvider;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata;

public class EventListFragmentTests extends
	ActivityUnitTestCase<EventListActivity> {

	private Intent intent;
    ListView eventListView;
	private Instrumentation instrumentation;
	private Context context;
	private static final String[] COLUMN_NAMES = { EventTableMetadata.ID_COLUMN, EventTableMetadata.NAME_COLUMN,
			EventTableMetadata.START_LOCAL_TIME_COLUMN };
	private static final Object[] ROW1_COLUMN_VALUES = {Integer.valueOf(10), "Event10", "10/10/2013"};
	private static final Object[] ROW2_COLUMN_VALUES = {Integer.valueOf(20), "Event20", "10/20/2013"};
	private MatrixCursor mockCursor;
	private EventListActivity activity;
	private FragmentManager fragmentManager;
	private EventListFragment eventListFragment;
    
    
	public EventListFragmentTests() {
		super(EventListActivity.class);
	}

	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        /* These tests rely on a linkseam testing version of 
         * OnTapContentProvider */
        instrumentation = getInstrumentation();
    	context = getInstrumentation().getContext();
        intent = new Intent(context, EventListActivity.class);
    	instrumentation.waitForIdleSync();
        mockCursor = new MatrixCursor(COLUMN_NAMES);
    }
    
  
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		activity.finish();
	}

	
	private void startActivity() {
		activity = launchActivityWithIntent(
    			"com.itllp.barleylegalhomebrewers.ontap", 
				EventListActivity.class, intent);
    	instrumentation.waitForIdleSync();
    	fragmentManager = activity.getSupportFragmentManager();
    	eventListFragment = (EventListFragment)
    			fragmentManager.findFragmentById
    			(com.itllp.barleylegalhomebrewers.ontap.R.id.event_list_fragment);
        eventListView = (ListView)eventListFragment.getListView();
    	instrumentation.waitForIdleSync();
	}


    public void testEmptyList() {
    	// Set up preconditions
    	OnTapContentProvider.mockSetCursor(mockCursor);
    	
    	// Call method under test
    	startActivity();
    	
    	// Verify postconditions
        assertEquals("List should be empty", 0, 
        		eventListView.getCount());    	
    }


    public void testListWithOneItem() {
    	// Set up preconditions
    	mockCursor.addRow(ROW1_COLUMN_VALUES);
    	OnTapContentProvider.mockSetCursor(mockCursor);
    	
    	// Call method under test
    	startActivity();

    	// Verify postconditions
        assertEquals("List should have 1 item", 1, 
        		eventListView.getCount());    	
        Cursor row1 = (Cursor)eventListView.getItemAtPosition(0);
        int idColumnIndex = row1.getColumnIndex(EventTableMetadata.ID_COLUMN);
        assertEquals("First event ID should be 10", 10, row1.getInt(idColumnIndex));
    }


	public void testListWithTwoItems() {
    	// Set up preconditions
		mockCursor.addRow(ROW1_COLUMN_VALUES);
    	mockCursor.addRow(ROW2_COLUMN_VALUES);
    	OnTapContentProvider.mockSetCursor(mockCursor);

    	// Call method under test
    	startActivity();
    	
    	// Verify postconditions
        assertEquals("List should have 2 items", 2, eventListView.getCount());
        Cursor row1 = (Cursor)eventListView.getItemAtPosition(0);
        int idColumnIndex = row1.getColumnIndex(EventTableMetadata.ID_COLUMN);
        assertEquals("First event ID should be 10", 10, row1.getInt(idColumnIndex));
        Cursor row2 = (Cursor)eventListView.getItemAtPosition(1);
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
