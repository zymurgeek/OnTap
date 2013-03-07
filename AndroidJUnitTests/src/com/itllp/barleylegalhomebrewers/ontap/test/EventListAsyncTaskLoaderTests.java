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

import java.util.List;

import android.content.Context;
import android.test.AndroidTestCase;

import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactoryProvider;
import com.itllp.barleylegalhomebrewers.ontap.EventListAsyncTaskLoader;

public class EventListAsyncTaskLoaderTests extends
	AndroidTestCase {

	private Context context;
	private LocalEventDatabaseFactory localEdbFactory;
	private EventDatabase eventDb;
	private EventListAsyncTaskLoader loader;

	public EventListAsyncTaskLoaderTests() {
	}

	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        context = new MockContext();
        localEdbFactory = new LocalEventDatabaseFactory();
        eventDb = localEdbFactory.getEventDatabase();
    	eventDb.clearEventList();
    	TestEventDatabaseFactoryProvider.clearEventDatabaseFactory();
    	EventDatabaseFactoryProvider.setEventDatabaseFactory(localEdbFactory);
    	loader = new EventListAsyncTaskLoader(context);
    }
    
    
    public void testLoadInBackgroundWithNoItems() {
    	// Method under test
    	List<Event> actualEventList = loader.loadInBackground();
    	
    	// Postconditions
    	assertEquals(0, actualEventList.size());
    }
    
    
    public void testLoadInBackgroundWith17Items() {
    	// Preconditions
    	List<Event> expectedEventList = eventDb.getEventList();
    	for (int id=1; id<=17; ++id) {
    		Event event = new Event(id);
    		expectedEventList.add(event);
    	}
    	
    	// Method under test
    	List<Event> actualEventList = loader.loadInBackground();
    	
    	// Postconditions
    	assertTrue(expectedEventList.equals(actualEventList));
    }
}
