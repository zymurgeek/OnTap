package com.itllp.barleylegalhomebrewers.ontap.database;

import android.content.ContentValues;
import android.database.Cursor;

public class CursorConverterImpl implements CursorConverter {

	@Override
	public ContentValues getContentValues(Cursor cursor) {
		ContentValues contentValues = new ContentValues();
        android.database.DatabaseUtils.cursorRowToContentValues(cursor, 
        		contentValues);
		return contentValues;
	}

}
