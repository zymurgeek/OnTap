package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import java.util.ArrayList;
import java.util.List;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

class SQLiteBeerTable implements BeerTable {
	private OnTapDatabaseHelper openHelper = null;
	// Database table
	public static final String TABLE_NAME = "beer";
	
	public static final String ID_COLUMN_TYPE = "INTEGER";
	public static final String NAME_COLUMN_TYPE = "TEXT";
	public static final String EVENT_ID_COLUMN_TYPE = "INTEGER";
	public static final String BREWER_NAME_COLUMN_TYPE = "TEXT";
	public static final String STYLE_CODE_COLUMN_TYPE = "TEXT";
	public static final String STYLE_NAME_COLUMN_TYPE = "TEXT";
	public static final String STYLE_OVERRIDE_COLUMN_TYPE = "TEXT";
	
	public static final String DATABASE_CREATE = "create table " 
			+ TABLE_NAME
			+ "(" 
			+ BeerTableMetadata.ID_COLUMN 
			+ " " + SQLiteBeerTable.ID_COLUMN_TYPE + " PRIMARY KEY, " 
			+ BeerTableMetadata.NAME_COLUMN 
			+ " " + SQLiteBeerTable.NAME_COLUMN_TYPE + " NOT NULL, " 
			+ BeerTableMetadata.EVENT_ID_COLUMN 
			+ " " + SQLiteBeerTable.EVENT_ID_COLUMN_TYPE + " SECONDARY KEY," 
			+ BeerTableMetadata.BREWER_NAME_COLUMN 
			+ " " + SQLiteBeerTable.BREWER_NAME_COLUMN_TYPE + ", " 
			+ BeerTableMetadata.STYLE_CODE_COLUMN 
			+ " " + SQLiteBeerTable.STYLE_CODE_COLUMN_TYPE + ", " 
			+ BeerTableMetadata.STYLE_NAME_COLUMN 
			+ " " + SQLiteBeerTable.STYLE_NAME_COLUMN_TYPE + ", " 
			+ BeerTableMetadata.STYLE_OVERRIDE_COLUMN 
			+ " " + SQLiteBeerTable.STYLE_OVERRIDE_COLUMN_TYPE + " " 
			+ ");";
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
	private CursorConverter cursorConverter;
	
	
	public SQLiteBeerTable(CursorConverter converter) {
		cursorConverter = converter;
		openHelper = OnTapDatabaseHelper.getInstance();
		openHelper.registerTable(this);
	}

	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteBeerTable.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will delete all old data");
		db.execSQL(DROP_TABLE + TABLE_NAME);
		onCreate(db);
	}

	
	@Override
	public ContentValues getBeer(Integer id) {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		String selection = BeerTableMetadata.ID_COLUMN + "=?";
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
	public List<ContentValues> getAllBeers() {
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
		List<ContentValues> allBeers = getAllBeers();
		for (ContentValues values : allBeers) {
			result.add(values.getAsInteger(BeerTableMetadata.ID_COLUMN));
		}
		return result;
	}

	
	@Override
	public void insert(ContentValues contentValues) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		db.insert(TABLE_NAME, null, contentValues);
		notifyOfBeerTableChange();
	}

	
	private void notifyOfBeerTableChange() {
		OnTapContentProvider contentProvider = 
				OnTapContentProvider.getInstance();
		android.content.Context context = contentProvider.getContext();
		android.content.ContentResolver resolver = 
				context.getContentResolver();
		resolver.notifyChange(OnTapContentProviderMetadata.BEER_CONTENT_URI, null);
	}

	
	@Override
	public void update(ContentValues contentValues) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		String whereClause = BeerTableMetadata.ID_COLUMN + "=?";
		Integer id = contentValues.getAsInteger(BeerTableMetadata.ID_COLUMN);
		if (id == null) {
			return;
		}
		String[] whereArgs = { id.toString() };
		db.update(TABLE_NAME, contentValues, whereClause, whereArgs);
		notifyOfBeerTableChange();
	}

	
	@Override
	public void delete(Integer id) {
		if (id == null) {
			return;
		}
		SQLiteDatabase db = openHelper.getWritableDatabase();
		String whereClause = BeerTableMetadata.ID_COLUMN + "=?";
		String[] whereArgs = { id.toString() };
		db.delete(TABLE_NAME, whereClause, whereArgs);
		notifyOfBeerTableChange();
	}

}
