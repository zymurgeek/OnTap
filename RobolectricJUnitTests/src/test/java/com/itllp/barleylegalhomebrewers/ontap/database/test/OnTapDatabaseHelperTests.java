package com.itllp.barleylegalhomebrewers.ontap.database.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.database.sqlite.SQLiteDatabase;
import com.itllp.barleylegalhomebrewers.ontap.database.OnTapDatabaseHelper;
import com.itllp.barleylegalhomebrewers.ontap.database.SQLiteEventTable;

@RunWith(RobolectricTestRunner.class)
public class OnTapDatabaseHelperTests {

	private OnTapDatabaseHelper onTapDatabaseHelper;
	private SQLiteEventTable mockEventTable;
	private SQLiteDatabase sqliteDb;

	@Before
	public void setUp() throws Exception {
		onTapDatabaseHelper = new OnTapDatabaseHelper(null);
		mockEventTable = mock(SQLiteEventTable.class);
		sqliteDb = null;
	}

	
	@After
	public void tearDown() throws Exception {
		OnTapDatabaseHelper.setInstance(null);
	}

	
	@Test
	public void testInitialization() {
		// Call method under test
		OnTapDatabaseHelper actualHelper = OnTapDatabaseHelper.getInstance();
		
		// Verify postconditions
		assertEquals(onTapDatabaseHelper, actualHelper);
	}

	
	@Test
	public void testSecondInitialization() {
		// Call method under test
		try {
			new OnTapDatabaseHelper(null);
			fail("Second instantiation should throw an exception");
		} catch (UnsupportedOperationException e) {
			// pass
		}
	}


	@Test
	public void testOnCreateWhenTableRegistered() {
		// Set up preconditions
		onTapDatabaseHelper.registerTable(mockEventTable);
		
		// Call method under test
		onTapDatabaseHelper.onCreate(sqliteDb);
		
		// Verify postconditions
		verify(mockEventTable).onCreate(sqliteDb);
	}

	
	@Test
	public void testOnCreateWhenTableNotRegistered() {
		// Call method under test
		onTapDatabaseHelper.onCreate(sqliteDb);
		
		// Verify postconditions
		verify(mockEventTable, times(0)).onCreate(sqliteDb);
	}

	
	@Test
	public void testOnUpgradeWhenTableNotRegistered() {
		// Call method under test
		onTapDatabaseHelper.onUpgrade(sqliteDb, 0, 0);
		
		// Verify postconditions
		verify(mockEventTable, times(0)).onUpgrade(sqliteDb, 0, 0);
	}


	@Test
	public void testOnUpgradeWhenTableRegistered() {
		// Set up preconditions
		onTapDatabaseHelper.registerTable(mockEventTable);
		
		// Call method under test
		onTapDatabaseHelper.onUpgrade(sqliteDb, 1, 2);
		
		// Verify postconditions
		verify(mockEventTable).onUpgrade(sqliteDb, 1, 2);
	}


}
