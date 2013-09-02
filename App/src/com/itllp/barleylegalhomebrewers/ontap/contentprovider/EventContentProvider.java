package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;

public class EventContentProvider extends ContentProvider {

	private static final String DATABASE_NAME = "event.db";
	private static final int DATABASE_VERSION = 1;
	private static final String EVENT_TABLE_NAME = "event";
	private DatabaseHelper mOpenHelper;	
    private SQLiteDatabase mDb;
	private static UriMatcher sUriMatcher;
	private static final int EVENTS = 1;
	private static final int EVENT_ID = 2;
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(Event.AUTHORITY, Event.Events.NAME, EVENTS);
	}

	// FIXME continue "Programming Android", page 363
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
        private DatabaseHelper(Context context, String name,
                SQLiteDatabase.CursorFactory factory) {
        	super(context, name, factory, DATABASE_VERSION);
        }
		public void onCreate(SQLiteDatabase sqLiteDatabase) {
			createTable(sqLiteDatabase);
		}
		
		// create table method may also be called from onUpgrade
		private void createTable(SQLiteDatabase sqLiteDatabase) {
			String qs = "CREATE TABLE " + EVENT_TABLE_NAME + " (" +
			Event.Events._ID + " INTEGER PRIMARY KEY, " +
			Event.Events.NAME + " TEXT, " +
			Event.Events.DATE + " TEXT);";
			sqLiteDatabase.execSQL(qs);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		}
	}
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		int rowsAffected = 0;
		return rowsAffected;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case EVENTS:
			return Event.Events.CONTENT_TYPE;
		default:
			throw new IllegalArgumentException("Unknown event type: " + uri);
		}
	}
	

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// If no sort order is specified use the default
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			// FIXME Implement default sort order
			// orderBy = Event.Events.DEFAULT_SORT_ORDER;
		} else {
			orderBy = sortOrder;
		}
		int match = sUriMatcher.match(uri);
		Cursor cursor;
		switch (match) {
			case EVENTS:
				// query the database for all events
				cursor = mDb.query(EVENT_TABLE_NAME, projection,
						selection, selectionArgs,
						null, null, sortOrder);
				cursor.setNotificationUri(
						getContext().getContentResolver(),
						Event.Events.CONTENT_URI);
				break;
			default:
				throw new IllegalArgumentException("unsupported uri: " + uri);
			}
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int rowsAffected = 0;
		return rowsAffected;
	}

}
