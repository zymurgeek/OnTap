package com.itllp.barleylegalhomebrewers.ontap.database.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.EventContentProvider;
import com.itllp.barleylegalhomebrewers.ontap.database.CursorConverter;
import com.itllp.barleylegalhomebrewers.ontap.database.EventTable;
import com.itllp.barleylegalhomebrewers.ontap.database.OnTapDatabaseHelper;
import com.itllp.barleylegalhomebrewers.ontap.database.SQLiteEventTable;

@RunWith(RobolectricTestRunner.class)
public class SQLiteEventTableTests {
	private SQLiteDatabase mockDatabase;
	private SQLiteOpenHelper mockOpenHelper;
	private Cursor mockCursor;
	private Integer eventId42 = 42;
	final private Integer expectedId1 = 2;
	final private Integer expectedId2 = 3;
	private CursorConverter mockCursorConverter;
	private ContentValues expectedEvent1;
	private ContentValues expectedEvent2;
	private SQLiteEventTable sqliteEventTable;


	/*
	private StubEventTable stubEventTable;
	private StubJSONArrayToContentValuesConverter stubConverter;
	private SQLiteEventTableFromJSONArrayUpdaterImpl cut;
	private final String inputJSONString = "[ { } ]";
	private final org.json.JSONArray emptyJSONArray = new org.json.JSONArray();
	private org.json.JSONArray inputJSONArray;	
	private ContentValues contentValues1;
	private ContentValues contentValues2;
	private ContentValues updatedContentValues1;
	private List<ContentValues> contentValuesInTableList;
	private List<ContentValues> inputContentValuesList;
	*/
	
	@Before
	public void setUp() throws Exception {
		mockDatabase = mock(SQLiteDatabase.class);
		mockOpenHelper = mock(SQLiteOpenHelper.class);
		OnTapDatabaseHelper.setInstance(mockOpenHelper);
		when(mockOpenHelper.getReadableDatabase()).thenReturn(mockDatabase);
		when(mockOpenHelper.getWritableDatabase()).thenReturn(mockDatabase);
		String selection = SQLiteEventTable.ID_COLUMN + "=?";
		String[] selectionArgs = { eventId42.toString() };
		mockCursor = mock(Cursor.class);
		when(mockDatabase.query(SQLiteEventTable.TABLE_NAME, null, 
				selection, selectionArgs, null, null, null))
				.thenReturn(mockCursor);
		when(mockDatabase.query(SQLiteEventTable.TABLE_NAME, null, 
				null, null, null, null, null))
				.thenReturn(mockCursor);
		mockCursorConverter = mock(CursorConverter.class);
		expectedEvent1 = new ContentValues();
		expectedEvent1.put(EventTable.ID_COLUMN, expectedId1.intValue());
		expectedEvent2 = new ContentValues();
		expectedEvent2.put(EventTable.ID_COLUMN, expectedId2.intValue());
		when(mockCursorConverter.getContentValues(mockCursor))
		.thenReturn(expectedEvent1)
		.thenReturn(expectedEvent2);
		sqliteEventTable = new SQLiteEventTable(mockCursorConverter);
		/*
		inputJSONArray = null;
		try {
			inputJSONArray = new org.json.JSONArray(inputJSONString);
		} catch (org.json.JSONException x) {
			fail("Failed to parse JSON string" + x);
		}
		contentValues1 = new ContentValues();
		contentValues1.put(EventTable.ID_COLUMN, 1);
		contentValues1.put(EventTable.NAME_COLUMN, "event 1");
		contentValues2 = new ContentValues();
		contentValues2.put(EventTable.ID_COLUMN, 2);
		contentValues2.put(EventTable.NAME_COLUMN, "event 2");
		updatedContentValues1 = new ContentValues();
		updatedContentValues1.put(EventTable.ID_COLUMN, 1);
		updatedContentValues1.put(EventTable.NAME_COLUMN, "updated event 1");
		contentValuesInTableList = new ArrayList<ContentValues>();
		inputContentValuesList = new ArrayList<ContentValues>();

		stubEventTable = new StubEventTable();
		stubConverter = new StubJSONArrayToContentValuesConverter();
		cut = new SQLiteEventTableFromJSONArrayUpdaterImpl(stubConverter, 
				stubEventTable);
		*/
	}

	
	@After
	public void tearDown() throws Exception {
		if (null != OnTapDatabaseHelper.getInstance()) {
			OnTapDatabaseHelper.setInstance(null);
		}
	}

	
	@Test
	public void testOnCreate() {
		// Call method under test
		SQLiteEventTable.onCreate(mockDatabase);
		
		// Verify postconditions
		verify(mockDatabase).execSQL(SQLiteEventTable.DATABASE_CREATE);
	}


	@Test
	public void testOnUpgrade() {
		// Call method under test
		SQLiteEventTable.onUpgrade(mockDatabase, 0, 0);
		
		// Verify postconditions
		verify(mockDatabase).execSQL(SQLiteEventTable.DATABASE_CREATE);
		String DROP_TABLE_COMMAND = SQLiteEventTable.DROP_TABLE 
				+ SQLiteEventTable.TABLE_NAME;
		verify(mockDatabase).execSQL(DROP_TABLE_COMMAND);
	}


	@Test
	public void testGetEventWhenEventExists() {
		// Set up preconditions
		when(mockCursor.moveToFirst()).thenReturn(true).thenReturn(false);
		
		// Call method under test
		ContentValues actualEvent = sqliteEventTable.getEvent(eventId42);
		
		// Verify postconditions
		verify(mockCursor).close();
		assertEquals(expectedEvent1, actualEvent);
	}
	
	
	@Test
	public void testGetEventWhenEventDoesNotExist() {
		// Set up preconditions
		when(mockCursor.moveToFirst()).thenReturn(false);
		
		// Call method under test
		ContentValues actualEvent = sqliteEventTable.getEvent(eventId42);
		
		// Verify postconditions
		verify(mockCursor).close();
		assertEquals(0, actualEvent.size());
	}
	
	
	@Test
	public void testGetAllEventsWhenNoEventsExist() {
		// Set up preconditions
		when(mockCursor.moveToFirst()).thenReturn(false);
		
		// Call method under test
		List<ContentValues> actualEvents = sqliteEventTable.getAllEvents();
		
		// Verify postconditions
		verify(mockCursor).close();
		assertEquals(0, actualEvents.size());
	}
	
	
	@Test
	public void testGetAllEventsWhenTwoEventsExist() {
		// Set up preconditions
		when(mockCursor.moveToFirst()).thenReturn(true);
		when(mockCursor.moveToNext()).thenReturn(true).thenReturn(false);
		
		// Call method under test
		List<ContentValues> actualEvents = sqliteEventTable.getAllEvents();
		
		// Verify postconditions
		verify(mockCursor).close();
		assertEquals(2, actualEvents.size());
		assertEquals(expectedEvent1, actualEvents.get(0));
		assertEquals(expectedEvent2, actualEvents.get(1));
	}
	
	
	@Test
	public void testGetAllIdsWhenNoEventsExist() {
		// Set up preconditions
		when(mockCursor.moveToFirst()).thenReturn(false);
		
		// Call method under test
		List<Integer> actualIds = sqliteEventTable.getAllIds();
		
		// Verify postconditions
		assertEquals(0, actualIds.size());
	}
	
	
	@Test
	public void testGetAllIdsWhenTwoEventsExist() {
		// Set up preconditions
		when(mockCursor.moveToFirst()).thenReturn(true);
		when(mockCursor.moveToNext()).thenReturn(true).thenReturn(false);
		
		// Call method under test
		List<Integer> actualIds = sqliteEventTable.getAllIds();
		
		// Verify postconditions
		assertEquals(2, actualIds.size());
		assertEquals(expectedId1, actualIds.get(0));
		assertEquals(expectedId2, actualIds.get(1));
	}

	
	@Test
	public void testInsert() {
		// Set up preconditions
		ContentResolver mockContentResolver = mock(ContentResolver.class);
		Context mockContext = mock(Context.class);
		when(mockContext.getContentResolver()).thenReturn(mockContentResolver);
		EventContentProvider mockEventCP = mock(EventContentProvider.class);
		when(mockEventCP.getContext()).thenReturn(mockContext);
		EventContentProvider.setInstance(mockEventCP);
		
		// Call method under test
		sqliteEventTable.insert(expectedEvent1);
		
		// Verify postconditions
		verify(mockDatabase).insert(SQLiteEventTable.TABLE_NAME, null, expectedEvent1);
		verify(mockContentResolver).notifyChange(EventContentProvider.CONTENT_URI, null);
	}
	
	// FIXME 1: Continue here
	// TODO add test for registering table with database helper
/*	
	public void testUpdateTableWithNullJSONArray() {
		// Call method under test
		cut.updateTable(null);
		
		// Verify postconditions
		assertEquals(0, stubEventTable.stub_getInsertedContentValuesList().
				size());
		assertEquals(0, stubEventTable.stub_getUpdatedContentValuesList().
				size());
		assertEquals(0, stubEventTable.stub_getDeletedIdsList().size());
	}

	
	public void testUpdateEmptyTableWithEmptyJSONArray() {
		// Call method under test
		cut.updateTable(emptyJSONArray);

		// Verify postconditions
		assertEquals(0, stubEventTable.stub_getInsertedContentValuesList().
				size());
		assertEquals(0, stubEventTable.stub_getUpdatedContentValuesList().
				size());
		assertEquals(0, stubEventTable.stub_getDeletedIdsList().size());
	}

	
	public void testUpdateTableWithOneNewEvent() {
		// Set up Preconditions
		inputContentValuesList.add(contentValues1);
		stubConverter.stub_addConversion(inputJSONArray, 
				inputContentValuesList);

		// Call method under test
		cut.updateTable(inputJSONArray);
		
		// Verify postconditions
		assertEquals(inputContentValuesList, 
				stubEventTable.stub_getInsertedContentValuesList());
		assertEquals(0, stubEventTable.stub_getUpdatedContentValuesList().
				size());
		assertEquals(0, stubEventTable.stub_getDeletedIdsList().size());
	}

	
	public void testUpdateTableWithTwoNewEvents() {
		// Set up Preconditions
		inputContentValuesList.add(contentValues1);
		inputContentValuesList.add(contentValues2);
		stubConverter.stub_addConversion(inputJSONArray, 
				inputContentValuesList);

		// Call method under test
		cut.updateTable(inputJSONArray);
		
		// Verify postconditions
		assertEquals(inputContentValuesList, stubEventTable
				.stub_getInsertedContentValuesList());
		assertEquals(0, stubEventTable.stub_getUpdatedContentValuesList().
				size());
		assertEquals(0, stubEventTable.stub_getDeletedIdsList().size());
	}

	
	public void testUpdateTableWithOneUpdatedEvent() {
		// Set up Preconditions
		contentValuesInTableList.add(contentValues1);
		stubEventTable.stub_setContentValuesInTable(contentValuesInTableList);
		
		inputContentValuesList.add(updatedContentValues1);
		stubConverter.stub_addConversion(inputJSONArray, 
				inputContentValuesList);

		// Call method under test
		cut.updateTable(inputJSONArray);
		
		// Verify postconditions
		assertEquals(0, stubEventTable
				.stub_getInsertedContentValuesList().size());
		assertEquals(inputContentValuesList, 
				stubEventTable.stub_getUpdatedContentValuesList());
		assertEquals(0, stubEventTable.stub_getDeletedIdsList().size());

	}


	public void testUpdateTableWithSameEvent() {
		// Set up Preconditions
		contentValuesInTableList.add(contentValues1);
		stubEventTable.stub_setContentValuesInTable(contentValuesInTableList);
		
		inputContentValuesList.add(contentValues1);
		stubConverter.stub_addConversion(inputJSONArray, 
				inputContentValuesList);

		// Call method under test
		cut.updateTable(inputJSONArray);
		
		// Verify postconditions
		assertEquals(0, stubEventTable
				.stub_getInsertedContentValuesList().size());
		assertEquals(0,	stubEventTable.
				stub_getUpdatedContentValuesList().size());
		assertEquals(0, stubEventTable.stub_getDeletedIdsList().size());

	}


	public void testUpdateTableWithOneDeletedEvent() {
		// Set up Preconditions
		contentValuesInTableList.add(contentValues1);
		stubEventTable.stub_setContentValuesInTable(contentValuesInTableList);
		
		stubConverter.stub_addConversion(inputJSONArray, 
				inputContentValuesList);
		
		List<Integer> expectedDeletedIds = new ArrayList<Integer>();
		expectedDeletedIds.add(Integer.valueOf(1));

		// Call method under test
		cut.updateTable(inputJSONArray);
		
		// Verify postconditions
		assertEquals(0, stubEventTable
				.stub_getInsertedContentValuesList().size());
		assertEquals(0,	stubEventTable.stub_getUpdatedContentValuesList().
				size());
		assertEquals(expectedDeletedIds, stubEventTable.
				stub_getDeletedIdsList());

	}

*/
	
	// TODO Finish tests
}
