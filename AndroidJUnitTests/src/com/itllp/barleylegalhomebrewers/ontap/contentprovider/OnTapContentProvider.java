package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/* This is stub class for testing only! */
public class OnTapContentProvider extends ContentProvider {

	private static OnTapContentProvider instance = null;
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
		new OnTapDatabaseHelper(getContext());
		return false;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		return OnTapContentProvider.mockCursor;
	}

	
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		throw new UnsupportedOperationException();
	}

	
	public static void mockSetCursor(Cursor cursor) {
		OnTapContentProvider.mockCursor = cursor;
	}

	
	private static Cursor mockCursor = null;
}
