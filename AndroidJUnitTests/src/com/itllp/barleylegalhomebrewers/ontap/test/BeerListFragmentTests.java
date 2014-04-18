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

import com.itllp.barleylegalhomebrewers.ontap.BeerListActivity;
import com.itllp.barleylegalhomebrewers.ontap.BeerListFragment;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata;

public class BeerListFragmentTests extends
	ActivityUnitTestCase<BeerListActivity> {

	private static final String[] COLUMN_NAMES = { 
		BeerTableMetadata.ID_COLUMN, 
		BeerTableMetadata.NAME_COLUMN,
		BeerTableMetadata.BREWER_NAME_COLUMN,
		BeerTableMetadata.STYLE_CODE_COLUMN,
		BeerTableMetadata.STYLE_NAME_COLUMN,
		BeerTableMetadata.STYLE_OVERRIDE_COLUMN};
	private static final Object[] ROW1_COLUMN_VALUES = {Integer.valueOf(10), 
		"Beer #10", "Joe", "1A", "Lite American Lager", "Extra light"};
	private static final Object[] ROW2_COLUMN_VALUES = {Integer.valueOf(20), 
		"Beer #20", "Steve", "2B", "Bohemian Pilsner", "New Zealand swing"};
	private Intent intent;
    ListView beerListView;
	private Instrumentation instrumentation;
	private Context context;
	private BeerListActivity activity;
	private FragmentManager fragmentManager;
	private BeerListFragment beerListFragment;
	private MatrixCursor mockCursor;

    
	public BeerListFragmentTests() {
		super(BeerListActivity.class);
	}

	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        instrumentation = getInstrumentation();
    	context = getInstrumentation().getContext();
        intent = new Intent(context, BeerListActivity.class);
    	activity = startActivity(intent, null, null);
    	instrumentation.waitForIdleSync();
    	fragmentManager = activity.getSupportFragmentManager();
    	beerListFragment = (BeerListFragment)
    			fragmentManager.findFragmentById
    			(com.itllp.barleylegalhomebrewers.ontap.R.id.beer_list_fragment);
        beerListView = (ListView)beerListFragment.getListView();
        beerListFragment.onActivityCreated(null);
        mockCursor = new MatrixCursor(COLUMN_NAMES);
    }

    
    public void testEmptyList() {
    	// Verify Postconditions
        assertEquals("List should be empty", 0, 
        		beerListView.getCount());    	
    }

    
    public void testListWithOneItem() {
    	// Set up preconditions
    	mockCursor.addRow(ROW1_COLUMN_VALUES);

    	// Call method under test
    	beerListFragment.onLoadFinished(null, mockCursor);
    	
    	// Verify postconditions
        assertEquals("List should have 1 item", 1, 
        		beerListView.getCount());    	
    }

    
    public void testListWithTwoItems() {
    	// Set up preconditions
		mockCursor.addRow(ROW1_COLUMN_VALUES);
    	mockCursor.addRow(ROW2_COLUMN_VALUES);

    	// Call method under test
    	beerListFragment.onLoadFinished(null, mockCursor);
    	
    	// Verify postconditions
        assertEquals("List should have 2 items", 2, 
        		beerListView.getCount());
        MatrixCursor row1 = (MatrixCursor)beerListView.getItemAtPosition(0);
        int idColumnIndex = row1.getColumnIndex(EventTableMetadata.ID_COLUMN);
        assertEquals("First beer ID should be 10", 10, row1.getInt(idColumnIndex));
        MatrixCursor row2 = (MatrixCursor)beerListView.getItemAtPosition(1);
        assertEquals("Second beer ID should be 20", 20, row2.getInt(idColumnIndex));
    }
}
