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
import java.util.HashMap;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.ListView;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactoryImpl;
import com.itllp.barleylegalhomebrewers.ontap.EventListActivity;

public class EventListActivityTests extends
	/*ActivityInstrumentationTestCase2<EventListActivity>*/
	ActivityUnitTestCase<EventListActivity>{

	private Intent mIntent;
    ListView eventListView;
	private LocalEventDatabaseFactory mLocalEventDbFactory;
	private EventDatabase mEventDb;
	private ArrayList<HashMap<String, String>> mEventList;
    
    
	public EventListActivityTests() {
		super(EventListActivity.class);
	}

	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mIntent = new Intent();
		mLocalEventDbFactory = new LocalEventDatabaseFactory();
		mEventDb = mLocalEventDbFactory.getEventDatabase();
		mEventList = mEventDb.getEventList();
    	EventDatabaseFactoryImpl.setEventDatabaseFactory(mLocalEventDbFactory);
    }
    

    public void testInitialization() {
    	// Method under test
    	EventListActivity activity = startActivity(mIntent, null, null);
    	
    	// Postconditions
    	assertNotNull(activity);
    }
    

    public void testEmptyList() {
    	// Preconditions
    	
    	// Method under test
    	EventListActivity activity = startActivity(mIntent, null, null);
    	
    	// Postconditions
        eventListView = (ListView)activity.findViewById
        		(com.itllp.barleylegalhomebrewers.ontap.R.id.list);
        assertEquals("List should be empty", 0, 
        		eventListView.getCount());    	
    }



    public void testListWithOneItem() {
    	// Preconditions
    	HashMap<String, String> map = new HashMap<String, String>();
		map.put(EventDatabase.ID, "1");
		mEventList.add(map);
    	
    	// Method under test
    	EventListActivity activity = startActivity(mIntent, null, null);
    	
    	// Postconditions
        eventListView = (ListView)activity.findViewById
        		(com.itllp.barleylegalhomebrewers.ontap.R.id.list);
        assertEquals("List should have one item", 1, 
        		eventListView.getCount());    	
    }



}
