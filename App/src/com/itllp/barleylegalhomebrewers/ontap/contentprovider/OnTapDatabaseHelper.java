package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class OnTapDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "ontap.db";
	private static final int DATABASE_VERSION = 6;
	private static OnTapDatabaseHelper instance = null;
	private SQLiteEventTable sqliteEventTable = null;
	private SQLiteBeerTable sqliteBeerTable = null;

	
	public OnTapDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Make a proper factory out of this
		if (null != instance) {
			throw new UnsupportedOperationException
			("This class can only be instantiated once");
		}
		instance = this;
	}

	
	public static OnTapDatabaseHelper getInstance() {
		return instance;
	}
	

	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		if (null != sqliteEventTable) {
			sqliteEventTable.onCreate(sqLiteDatabase);
		}
		if (null != sqliteBeerTable) {
			sqliteBeerTable.onCreate(sqLiteDatabase);			
		}
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		if (null != sqliteEventTable) {
			sqliteEventTable.onUpgrade(database, oldVersion, newVersion);
		}
		if (null != sqliteBeerTable) {
			sqliteBeerTable.onUpgrade(database, oldVersion, newVersion);
		}
	}


	public static void setInstance(OnTapDatabaseHelper openHelper) {
		instance = openHelper;
	}


	public void registerTable(SQLiteEventTable newTable) {
		sqliteEventTable = newTable;
	}


	public void registerTable(SQLiteBeerTable newTable) {
		sqliteBeerTable = newTable;
	}


}
