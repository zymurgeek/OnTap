package com.itllp.barleylegalhomebrewers.ontap.test;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import com.itllp.barleylegalhomebrewers.ontap.CursorLoaderFactoryImplementationInterface;

public class StubCursorLoaderFactory implements
		CursorLoaderFactoryImplementationInterface {

	@Override
	public CursorLoader createCursorLoader(Context context, Uri uri,
			String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		CursorLoader stubCursorLoader = new CursorLoader(context, uri, 
				projection, selection, selectionArgs, sortOrder) {

					@Override
					public Cursor loadInBackground() {
						return null;
					}
			
		};
		return stubCursorLoader;
	}

}
