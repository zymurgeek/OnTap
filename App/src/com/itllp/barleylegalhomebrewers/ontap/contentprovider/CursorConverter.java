package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;

public interface CursorConverter {

	ContentValues getContentValues(Cursor cursor);

}
