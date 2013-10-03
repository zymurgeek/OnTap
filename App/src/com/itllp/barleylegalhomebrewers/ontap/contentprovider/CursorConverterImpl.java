package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


import android.content.ContentValues;
import android.database.Cursor;

class CursorConverterImpl implements CursorConverter {

	@Override
	public ContentValues getContentValues(Cursor cursor) {
		ContentValues contentValues = new ContentValues();
        android.database.DatabaseUtils.cursorRowToContentValues(cursor, 
        		contentValues);
		return contentValues;
	}

}
