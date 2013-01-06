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

package com.itllp.beerfestival;

import android.app.Instrumentation;
import android.test.SingleLaunchActivityTestCase;
import android.widget.ListView;
import android.widget.TextView;

import com.itllp.beerfestival.EventListActivity;

/* These tests cover the initial values displayed in each field.
 * 
 */
public class EventListActivityInitializationTests extends
		SingleLaunchActivityTestCase<EventListActivity> {

	private Instrumentation mInstrumentation;
    private EventListActivity mActivity;
    
    
	public EventListActivityInitializationTests() {
		super("com.itllp.beerfestival", EventListActivity.class);
	}

	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mInstrumentation = getInstrumentation();
        mActivity = this.getActivity();
    }
    
    
    public void testEmptyList() {
    	LocalEventDatabase eventDb = new LocalEventDatabase();

    	ListView eventListView = (ListView)mActivity.findViewById
    			(com.itllp.beerfestival.R.id.list);
        assertEquals("List should be empty", 0, 
        		eventListView.getCount());    	
    }



}
