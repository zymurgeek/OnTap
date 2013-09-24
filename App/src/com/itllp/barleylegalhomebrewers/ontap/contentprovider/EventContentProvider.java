package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


import java.util.Arrays;
import java.util.HashSet;

import com.itllp.barleylegalhomebrewers.ontap.database.EventTable;
import com.itllp.barleylegalhomebrewers.ontap.database.SQLiteEventTable;
import com.itllp.barleylegalhomebrewers.ontap.database.OnTapDatabaseHelper;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class EventContentProvider extends ContentProvider {

	private static EventContentProvider instance = null;
	private OnTapDatabaseHelper mOpenHelper;	
    public static final String AUTHORITY = 
	"com.itllp.barleylegalhomebrewers.ontap.contentprovider";
	public static final String BASE_PATH = "events";
	public static final Uri CONTENT_URI =
	Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
	//public static final String CONTENT_TYPE =	"vnd.android.cursor.dir/vnd.ontap.event";
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/events";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/event";
	private static UriMatcher sUriMatcher;
	private static final int EVENTS = 10;
	private static final int EVENT_ID = 20;
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	  static {
	    sURIMatcher.addURI(AUTHORITY, BASE_PATH, EVENTS);
	    sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", EVENT_ID);
	  }
	  
	  public EventContentProvider() {
		  super();
		  instance = this;
	  }

	// FIXME continue "Programming Android", page 363
	
	  // TODO Implement Event delete
	  // see http://www.vogella.com/articles/AndroidSQLite/#todo_contentprovider
	  // or Programming Android page 362
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		throw new UnsupportedOperationException();
		// int rowsAffected = 0;
		// return rowsAffected;
	}

	public static EventContentProvider getInstance() {
		return instance;
	}
	
	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case EVENTS:
			return EventContentProvider.CONTENT_TYPE;
		default:
			throw new IllegalArgumentException("Unknown event type: " + uri);
		}
	}
	

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		throw new UnsupportedOperationException();
		/*
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = mOpenHelper.getWritableDatabase();
		long id = 0;
		switch (uriType) {
		case EVENTS:
			id = sqlDB.insert(SQLiteEventTable.TABLE_NAME, null, values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse(BASE_PATH + "/" + id);
		*/
	}

	
	@Override
	public boolean onCreate() {
		mOpenHelper = new OnTapDatabaseHelper(getContext());
		return false;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		SQLiteDatabase db = mOpenHelper.getReadableDatabase();

		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		checkForUnknownColumns(projection);

		queryBuilder.setTables(SQLiteEventTable.TABLE_NAME);

		int uriType = sURIMatcher.match(uri);
		switch (uriType) {
		case EVENTS:
			break;
		case EVENT_ID:
			queryBuilder.appendWhere(EventTable.ID_COLUMN + "="
					+ uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		EventTableUpdater eventTableUpdater 
		= EventTableUpdaterFactory.getInstance();
		Runnable sqlLoadTask = new EventTableUpdaterTask(eventTableUpdater);
		Thread t = new Thread(sqlLoadTask);
		t.start();
		
		return cursor;
	}

	
	private void checkForUnknownColumns(String[] projection) {
		String[] available = { EventTable.ID_COLUMN , EventTable.NAME_COLUMN,
				SQLiteEventTable.START_LOCAL_TIME_COLUMN };
		if (projection != null) {
			HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
			HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
			// Check if all columns which are requested are available
			if (!availableColumns.containsAll(requestedColumns)) {
				throw new IllegalArgumentException("Unknown columns in projection");
			}
		}
	}

	// TODO Implement Event update
	// see http://www.vogella.com/articles/AndroidSQLite/#todo_contentprovider
	// or Programming Android page 361
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		throw new UnsupportedOperationException();
		//int rowsAffected = 0;
		//return rowsAffected;
	}

}
