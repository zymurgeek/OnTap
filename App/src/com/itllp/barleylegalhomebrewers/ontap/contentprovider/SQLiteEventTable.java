package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import java.util.ArrayList;
import java.util.List;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

class SQLiteEventTable implements EventTable {
	private OnTapDatabaseHelper openHelper = null;
	// Database table
	public static final String TABLE_NAME = "event";
	
	public static final String ID_COLUMN_TYPE = "INTEGER";
	public static final String NAME_COLUMN_TYPE = "TEXT";
	public static final String START_LOCAL_TIME_COLUMN_TYPE = "TEXT";
	
	public static final String DATABASE_CREATE = "create table " 
			+ TABLE_NAME
			+ "(" 
			+ EventTableMetadata.ID_COLUMN 
			+ " " + SQLiteEventTable.ID_COLUMN_TYPE + " PRIMARY KEY, " 
			+ EventTableMetadata.NAME_COLUMN 
			+ " " + SQLiteEventTable.NAME_COLUMN_TYPE + " NOT NULL, " 
			+ EventTableMetadata.START_LOCAL_TIME_COLUMN 
			+ " " + SQLiteEventTable.START_LOCAL_TIME_COLUMN_TYPE + " NOT NULL);";
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
	private CursorConverter cursorConverter;
	
	
	public SQLiteEventTable(CursorConverter converter) {
		cursorConverter = converter;
		openHelper = OnTapDatabaseHelper.getInstance();
		openHelper.registerTable(this);
	}

	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteEventTable.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will delete all old data");
		db.execSQL(DROP_TABLE + TABLE_NAME);
		onCreate(db);
	}

	
	@Override
	public ContentValues getEvent(Integer id) {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		String selection = EventTableMetadata.ID_COLUMN + "=?";
		String[] selectionArgs = { id.toString() };
		android.database.Cursor cursor = db.query(TABLE_NAME, null, 
				selection, selectionArgs, null, null, null);
		ContentValues contentValues = new ContentValues();
		if (cursor.moveToFirst()) {
			contentValues = cursorConverter.getContentValues(cursor);
		}
		cursor.close();
		return contentValues;
	}

	
	@Override
	public List<ContentValues> getAllEvents() {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		String selection = null;
		String[] selectionArgs = null;
		android.database.Cursor cursor = db.query(TABLE_NAME, null, 
				selection, selectionArgs, null, null, null);
		ArrayList<ContentValues> contentValuesList = 
				new ArrayList<ContentValues>();
		if(cursor.moveToFirst()) {       
		   do {
		        ContentValues contentValues = cursorConverter.getContentValues(cursor);                 
		        contentValuesList.add(contentValues);
		    } while(cursor.moveToNext());
		}

		cursor.close();  
		return contentValuesList;
	}

	
	@Override
	public List<Integer> getAllIds() {
		List<Integer> result = new ArrayList<Integer>();
		List<ContentValues> allEvents = getAllEvents();
		for (ContentValues values : allEvents) {
			result.add(values.getAsInteger(EventTableMetadata.ID_COLUMN));
		}
		return result;
	}

	
	@Override
	public void insert(ContentValues contentValues) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		db.insert(TABLE_NAME, null, contentValues);
		notifyOfEventTableChange();
	}

	
	private void notifyOfEventTableChange() {
		OnTapContentProvider contentProvider = 
				OnTapContentProvider.getInstance();
		android.content.Context context = contentProvider.getContext();
		android.content.ContentResolver resolver = 
				context.getContentResolver();
		resolver.notifyChange(OnTapContentProviderMetadata.CONTENT_URI, null);
	}

	
	@Override
	public void update(ContentValues contentValues) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		String whereClause = EventTableMetadata.ID_COLUMN + "=?";
		Integer id = contentValues.getAsInteger(EventTableMetadata.ID_COLUMN);
		if (id == null) {
			return;
		}
		String[] whereArgs = { id.toString() };
		db.update(TABLE_NAME, contentValues, whereClause, whereArgs);
		notifyOfEventTableChange();
	}

	
	@Override
	public void delete(Integer id) {
		if (id == null) {
			return;
		}
		SQLiteDatabase db = openHelper.getWritableDatabase();
		String whereClause = EventTableMetadata.ID_COLUMN + "=?";
		String[] whereArgs = { id.toString() };
		db.delete(TABLE_NAME, whereClause, whereArgs);
		notifyOfEventTableChange();
	}

}
