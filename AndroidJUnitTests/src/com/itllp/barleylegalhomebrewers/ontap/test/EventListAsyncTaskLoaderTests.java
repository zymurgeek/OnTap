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
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.EventListAsyncTaskLoader;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;

public class EventListAsyncTaskLoaderTests extends
	AndroidTestCase {

	public EventListAsyncTaskLoaderTests() {
	}

	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    
    public void testLoadInBackground() {
    	// Preconditions
    	FakeEventDatabaseLoader.clearInstance();
    	FakeEventDatabaseLoader.create();
    	FakeEventDatabaseLoader fakeLoader = (FakeEventDatabaseLoader)EventDatabaseLoader.getInstance();

    	FakeEventDatabase.clearInstance();
    	FakeEventDatabase.create();
    	EventDatabase eventDatabase = EventDatabase.getInstance();
    	eventDatabase.addOrUpdateEvent(new Event(1));
    	List<Event> expectedEventList = eventDatabase.getEventList();
    	
    	Context context= new MockContext();
    	EventListAsyncTaskLoader loader = new EventListAsyncTaskLoader(context);
    	
    	// Method under test
    	List<Event> actualEventList = loader.loadInBackground();
    	
    	// Postconditions
    	assertEquals(1, fakeLoader.MOCK_getLoadCount());
    	assertEquals(expectedEventList, actualEventList);
    }
    
    
}
