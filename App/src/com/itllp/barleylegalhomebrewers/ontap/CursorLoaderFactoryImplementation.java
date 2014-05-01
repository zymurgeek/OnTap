package com.itllp.barleylegalhomebrewers.ontap;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

public class CursorLoaderFactoryImplementation implements
		CursorLoaderFactoryImplementationInterface {

	@Override
	public CursorLoader createCursorLoader(Context context, Uri uri,
			String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		return new CursorLoader(context, uri, projection, selection, 
				selectionArgs, sortOrder);
	}

}
