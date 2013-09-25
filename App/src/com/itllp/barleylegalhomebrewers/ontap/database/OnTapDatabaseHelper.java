package com.itllp.barleylegalhomebrewers.ontap.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OnTapDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "ontap.db";
	private static final int DATABASE_VERSION = 1;
	private static SQLiteOpenHelper instance = null;

	
	public OnTapDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Make a proper factory out of this
		if (null != instance) {
			throw new UnsupportedOperationException
			("This class can only be instantiated once");
		}
		instance = this;
	}

	
	public static SQLiteOpenHelper getInstance() {
		return instance;
	}
	

	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		SQLiteEventTable.onCreate(sqLiteDatabase);
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		SQLiteEventTable.onUpgrade(database, oldVersion, newVersion);
	}


	public static void setInstance(SQLiteOpenHelper openHelper) {
		instance = openHelper;
	}


}


