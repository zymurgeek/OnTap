package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


import java.util.Arrays;
import java.util.HashSet;
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
	    sURIMatcher.addURI(OnTapContentProviderMetadata.AUTHORITY, OnTapContentProviderMetadata.BEER_BASE_PATH, BEERS);
	    sURIMatcher.addURI(OnTapContentProviderMetadata.AUTHORITY, OnTapContentProviderMetadata.BEER_BASE_PATH + "/#", BEER_ID);
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


		int uriType = sURIMatcher.match(uri);
		String eventId = "";
		switch (uriType) {
		case EVENTS:
			checkForUnknownEventColumns(projection);
			queryBuilder.setTables(SQLiteEventTable.TABLE_NAME);
			break;
		case EVENT_ID:
			checkForUnknownEventColumns(projection);
			queryBuilder.setTables(SQLiteEventTable.TABLE_NAME);
			eventId = uri.getLastPathSegment();
			queryBuilder.appendWhere(EventTableMetadata.ID_COLUMN + "="
					+ eventId);
			break;
		case BEERS:
			checkForUnknownBeerColumns(projection);
			queryBuilder.setTables(SQLiteBeerTable.TABLE_NAME);
			eventId = uri.getQueryParameter(OnTapContentProviderMetadata.EVENT_ID_PARAM);
			if (eventId != null) {
				queryBuilder.appendWhere(BeerTableMetadata.EVENT_ID_COLUMN + "="
						+ eventId);
			}
			break;
		case BEER_ID:
			checkForUnknownBeerColumns(projection);
			queryBuilder.setTables(SQLiteBeerTable.TABLE_NAME);
			String whereClause = BeerTableMetadata.ID_COLUMN + "="
					+ uri.getLastPathSegment();
			queryBuilder.appendWhere(whereClause);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		Runnable sqlLoadTask = null;
		switch (uriType) {
		case EVENTS:
		case EVENT_ID:
			TableUpdater eventUpdater = EventTableUpdaterFactory.getInstance();
			sqlLoadTask = new TableUpdaterTask(eventUpdater);
			break;
		case BEERS:
			BeerTableUpdater beerUpdater = BeerTableUpdaterFactory.getInstance();
			sqlLoadTask = new BeerTableUpdaterTask(beerUpdater, eventId);
			break;
		}
		if (sqlLoadTask != null) {
			Thread t = new Thread(sqlLoadTask);
			t.start();
		}
		
		return cursor;
	}

	
	private void checkForUnknownEventColumns(String[] projection) {
		String[] available = { EventTableMetadata.ID_COLUMN , EventTableMetadata.NAME_COLUMN,
				EventTableMetadata.START_LOCAL_TIME_COLUMN };
		checkForUnknownColumns(projection, available);
	}


	private void checkForUnknownBeerColumns(String[] projection) {
		String[] available = { BeerTableMetadata.ID_COLUMN , 
				BeerTableMetadata.NAME_COLUMN,
				BeerTableMetadata.EVENT_ID_COLUMN,
				BeerTableMetadata.BREWER_NAME_COLUMN,
				BeerTableMetadata.STYLE_CODE_COLUMN,
				BeerTableMetadata.STYLE_NAME_COLUMN,
				BeerTableMetadata.STYLE_OVERRIDE_COLUMN,
				BeerTableMetadata.IS_KICKED_COLUMN,
				BeerTableMetadata.TAP_NUMBER_COLUMN,
				BeerTableMetadata.PACKAGING_COLUMN,
				BeerTableMetadata.DESCRIPTION_COLUMN};
		checkForUnknownColumns(projection, available);
	}

	
	private void checkForUnknownColumns(String[] projection, String[] available) {
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
