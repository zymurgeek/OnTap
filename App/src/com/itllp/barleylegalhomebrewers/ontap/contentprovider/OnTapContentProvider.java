package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;
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
    public static final String EVENT_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/" + OnTapContentProviderMetadata.AUTHORITY + "." + OnTapContentProviderMetadata.EVENT_BASE_PATH;
	public static final String EVENT_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/" + OnTapContentProviderMetadata.AUTHORITY + "." + OnTapContentProviderMetadata.EVENT_BASE_PATH;
    public static final String BEER_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/" + OnTapContentProviderMetadata.AUTHORITY + "." + OnTapContentProviderMetadata.BEER_BASE_PATH;
    public static final String BEER_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/" + OnTapContentProviderMetadata.AUTHORITY + "." + OnTapContentProviderMetadata.BEER_BASE_PATH;
	private static UriMatcher sUriMatcher;
	private static final int EVENTS = 10;
	private static final int EVENT_ID = 20;
	private static final int BEERS = 30;
	private static final int BEER_ID = 40;
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	  static {
	    sURIMatcher.addURI(OnTapContentProviderMetadata.AUTHORITY, OnTapContentProviderMetadata.EVENT_BASE_PATH, EVENTS);
	    sURIMatcher.addURI(OnTapContentProviderMetadata.AUTHORITY, OnTapContentProviderMetadata.EVENT_BASE_PATH + "/#", EVENT_ID);
	    sURIMatcher.addURI(OnTapContentProviderMetadata.AUTHORITY, OnTapContentProviderMetadata.EVENT_BASE_PATH + "/#/"
	    		+ OnTapContentProviderMetadata.BEER_BASE_PATH, BEERS);
	    sURIMatcher.addURI(OnTapContentProviderMetadata.AUTHORITY, OnTapContentProviderMetadata.EVENT_BASE_PATH + "/#/"
	    		+ OnTapContentProviderMetadata.BEER_BASE_PATH + "/#", BEER_ID);
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
			return OnTapContentProvider.EVENT_CONTENT_TYPE;
		case EVENT_ID:
			return OnTapContentProvider.EVENT_CONTENT_ITEM_TYPE;
		case BEERS:
			return OnTapContentProvider.BEER_CONTENT_TYPE;
		case BEER_ID:
			return OnTapContentProvider.BEER_CONTENT_ITEM_TYPE;
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

		int uriType = sURIMatcher.match(uri);
		String eventId = "";
		List<String> paths;
		
		switch (uriType) {
		case EVENTS:
			queryBuilder.setTables(SQLiteEventTable.TABLE_NAME);
			break;
		case EVENT_ID:
			queryBuilder.setTables(SQLiteEventTable.TABLE_NAME);
			eventId = uri.getLastPathSegment();
			queryBuilder.appendWhere(EventTableMetadata.ID_COLUMN + "="
					+ eventId);
			break;
		case BEERS:
			queryBuilder.setTables(SQLiteBeerTable.TABLE_NAME);
			paths = uri.getPathSegments();
			eventId = paths.get(paths.size()-2);
			queryBuilder.appendWhere(EventTableMetadata.ID_COLUMN + "="
					+ eventId);
			break;
		case BEER_ID:
			queryBuilder.setTables(SQLiteBeerTable.TABLE_NAME);
			List<String> segments = uri.getPathSegments();
			queryBuilder.appendWhere(BeerTableMetadata.EVENT_ID_COLUMN + "="
					+ segments.get(segments.size()-3));
			queryBuilder.appendWhere(BeerTableMetadata.ID_COLUMN + "="
					+ uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		TableUpdater tableUpdater = null;
		switch (uriType) {
		case EVENTS:
		case EVENT_ID:
			tableUpdater = EventTableUpdaterFactory.getInstance();
			break;
		case BEERS:
			tableUpdater = BeerTableUpdaterFactory.getInstance(eventId);
			break;
		}
		Runnable sqlLoadTask = new EventTableUpdaterTask(tableUpdater);
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
