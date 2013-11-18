package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class OnTapDatabaseHelperImpl extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "ontap.db";
	private static final int DATABASE_VERSION = 1;
	private static OnTapDatabaseHelperImpl instance = null;
	private SQLiteEventTable sqliteEventTable = null;

	
	public OnTapDatabaseHelperImpl(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Make a proper factory out of this
		if (null != instance) {
			throw new UnsupportedOperationException
			("This class can only be instantiated once");
		}
		instance = this;
	}

	
	public static OnTapDatabaseHelperImpl getInstance() {
		return instance;
	}
	

	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		if (null != sqliteEventTable) {
			sqliteEventTable.onCreate(sqLiteDatabase);
		}
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		if (null != sqliteEventTable) {
			sqliteEventTable.onUpgrade(database, oldVersion, newVersion);
		}
	}


	public static void setInstance(OnTapDatabaseHelperImpl openHelper) {
		instance = openHelper;
	}


	public void registerTable(SQLiteEventTable newTable) {
		sqliteEventTable = newTable;
	}


}


