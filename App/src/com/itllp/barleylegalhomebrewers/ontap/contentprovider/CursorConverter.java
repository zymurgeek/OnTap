package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;

interface CursorConverter {

	ContentValues getContentValues(Cursor cursor);

}
