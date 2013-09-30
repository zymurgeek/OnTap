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
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.EventContentProvider;
import com.itllp.barleylegalhomebrewers.ontap.database.CursorConverter;
import com.itllp.barleylegalhomebrewers.ontap.database.EventTable;
import com.itllp.barleylegalhomebrewers.ontap.database.OnTapDatabaseHelper;
import com.itllp.barleylegalhomebrewers.ontap.database.SQLiteEventTable;

@RunWith(RobolectricTestRunner.class)
public class SQLiteEventTableTests {
	private SQLiteDatabase mockDatabase;
	private OnTapDatabaseHelper mockDatabaseHelper;
	private Cursor mockCursor;
	private Integer eventId42 = 42;
	final private Integer expectedId1 = 2;
	final private Integer expectedId2 = 3;
	private CursorConverter mockCursorConverter;
	private ContentValues expectedEvent1;
	private ContentValues expectedEvent2;
	private SQLiteEventTable sqliteEventTable;
	private ContentResolver mockContentResolver;
	private Context mockContext;
	private EventContentProvider mockEventCP;


	@Before
	public void setUp() throws Exception {
		mockDatabase = mock(SQLiteDatabase.class);
		mockDatabaseHelper = mock(OnTapDatabaseHelper.class);
		OnTapDatabaseHelper.setInstance(mockDatabaseHelper);
		when(mockDatabaseHelper.getReadableDatabase()).thenReturn(mockDatabase);
		when(mockDatabaseHelper.getWritableDatabase()).thenReturn(mockDatabase);
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
		mockContentResolver = mock(ContentResolver.class);
		mockContext = mock(Context.class);
		when(mockContext.getContentResolver()).thenReturn(mockContentResolver);
		mockEventCP = mock(EventContentProvider.class);
		when(mockEventCP.getContext()).thenReturn(mockContext);
		EventContentProvider.setInstance(mockEventCP);
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
		sqliteEventTable.onCreate(mockDatabase);
		
		// Verify postconditions
		verify(mockDatabase).execSQL(SQLiteEventTable.DATABASE_CREATE);
	}


	@Test
	public void testOnUpgrade() {
		// Call method under test
		sqliteEventTable.onUpgrade(mockDatabase, 0, 0);
		
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
		// Call method under test
		sqliteEventTable.insert(expectedEvent1);
		
		// Verify postconditions
		verify(mockDatabase).insert(SQLiteEventTable.TABLE_NAME, null, expectedEvent1);
		verify(mockContentResolver).notifyChange(EventContentProvider.CONTENT_URI, null);
	}
	
	
	@Test
	public void testUpdate() {
		// Set up preconditions
		String whereClause = SQLiteEventTable.ID_COLUMN + "=?";
		Integer id = expectedEvent2.getAsInteger(SQLiteEventTable.ID_COLUMN);
		String[] whereArgs = { id.toString() };

		// Call method under test
		sqliteEventTable.update(expectedEvent2);
		
		// Verify postconditions
		verify(mockDatabase).update(SQLiteEventTable.TABLE_NAME, expectedEvent2,
				whereClause, whereArgs);
		verify(mockContentResolver).notifyChange(EventContentProvider.CONTENT_URI, null);
	}
	
	
	@Test
	public void testDelete() {
		// Set up preconditions
		String whereClause = SQLiteEventTable.ID_COLUMN + "=?";
		String[] whereArgs = { eventId42.toString() };
		
		// Call method under test
		sqliteEventTable.delete(eventId42);
		
		// Verify postconditions
		verify(mockDatabase).delete(SQLiteEventTable.TABLE_NAME, 
				whereClause, whereArgs);
		verify(mockContentResolver).notifyChange(EventContentProvider.CONTENT_URI, 
				null);
	}
	
	
	@Test
	public void testRegisterWithOnTapDatabaseHelper() {
		// Call method under test
		SQLiteEventTable newTable = new SQLiteEventTable(mockCursorConverter);
		
		// Verify postconditions
		verify(mockDatabaseHelper).registerTable(newTable);
	}
}
