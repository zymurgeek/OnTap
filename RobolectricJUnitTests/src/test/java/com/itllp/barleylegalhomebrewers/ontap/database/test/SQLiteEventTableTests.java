package com.itllp.barleylegalhomebrewers.ontap.database.test;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

import android.database.sqlite.SQLiteDatabase;

import com.itllp.barleylegalhomebrewers.ontap.database.SQLiteEventTable;

public class SQLiteEventTableTests extends TestCase {
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
	
	public SQLiteEventTableTests(String name) {
		super(name);
	}

	
	protected void setUp() throws Exception {
		super.setUp();
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

	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreate() {
		// Call method under test
		SQLiteEventTable table = new SQLiteEventTable();
		
		// Verify postconditions
		assertNotNull(table);
		
	}
	
	public void testOnCreate() {
		// Set up preconditions
		SQLiteDatabase mockDatabase = mock(SQLiteDatabase.class);
		
		// Call method under test
		SQLiteEventTable.onCreate(mockDatabase);
		
		// Verify postconditions
		verify(mockDatabase).execSQL(SQLiteEventTable.DATABASE_CREATE);
	}

	
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
