package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


import java.util.Arrays;
import java.util.HashSet;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;
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

public class OnTapContentProvider extends ContentProvider {

	private static OnTapContentProvider instance = null;
	private OnTapDatabaseHelper mOpenHelper;	
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/" + OnTapContentProviderMetadata.EVENT_BASE_PATH;
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/" + OnTapContentProviderMetadata.EVENT_BASE_PATH;
	private static UriMatcher sUriMatcher;
	private static final int EVENTS = 10;
	private static final int EVENT_ID = 20;
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	  static {
	    sURIMatcher.addURI(OnTapContentProviderMetadata.AUTHORITY, OnTapContentProviderMetadata.EVENT_BASE_PATH, EVENTS);
	    sURIMatcher.addURI(OnTapContentProviderMetadata.AUTHORITY, OnTapContentProviderMetadata.EVENT_BASE_PATH + "/#", EVENT_ID);
	  }
	  
	  public OnTapContentProvider() {
		  super();
		  instance = this;
	  }

	  
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		throw new UnsupportedOperationException();
	}

	
	public static OnTapContentProvider getInstance() {
		return instance;
	}
	
	
	public static void setInstance(OnTapContentProvider newProvider) {
		instance = newProvider;
	}

	
	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case EVENTS:
			return OnTapContentProvider.CONTENT_TYPE;
		default:
			throw new IllegalArgumentException("Unknown event type: " + uri);
		}
	}
	

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		throw new UnsupportedOperationException();
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
			queryBuilder.appendWhere(EventTableMetadata.ID_COLUMN + "="
					+ uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		TableUpdater eventTableUpdater 
		= EventTableUpdaterFactory.getInstance();
		Runnable sqlLoadTask = new EventTableUpdaterTask(eventTableUpdater);
		Thread t = new Thread(sqlLoadTask);
		t.start();
		
		return cursor;
	}

	
	private void checkForUnknownColumns(String[] projection) {
		String[] available = { EventTableMetadata.ID_COLUMN , EventTableMetadata.NAME_COLUMN,
				EventTableMetadata.START_LOCAL_TIME_COLUMN };
		if (projection != null) {
			HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
			HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
			// Check if all columns which are requested are available
			if (!availableColumns.containsAll(requestedColumns)) {
				throw new IllegalArgumentException("Unknown columns in projection");
			}
		}
	}

	
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		throw new UnsupportedOperationException();
	}

}
