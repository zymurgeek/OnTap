package com.itllp.barleylegalhomebrewers.ontap;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

public class CursorLoaderFactory {
	public static CursorLoader createCursorLoader(Context context, Uri uri, 
			String[] projection, String selection, String[] selectionArgs, 
			String sortOrder) {
		return factory.createCursorLoader(context, uri, projection, selection, 
				selectionArgs, sortOrder);
	}
	
	public static void setImplementation(CursorLoaderFactoryImplementationInterface 
			implementation) {
		factory = implementation;
	}
	
	private static CursorLoaderFactoryImplementationInterface factory = null;
}
