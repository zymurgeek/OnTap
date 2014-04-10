package com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface;

import android.net.Uri;


public class OnTapContentProviderMetadata {

	public static final String AUTHORITY = 
	"com.itllp.barleylegalhomebrewers.ontap.contentprovider";
	public static final Uri EVENT_CONTENT_URI =	Uri.parse("content://" + 
			AUTHORITY + "/" + OnTapContentProviderMetadata.EVENT_BASE_PATH);
	public static final Uri BEER_CONTENT_URI = Uri.parse("content://" + 
			AUTHORITY + "/" + OnTapContentProviderMetadata.BEER_BASE_PATH);
	public static final String EVENT_BASE_PATH = "events";
	public static final String BEER_BASE_PATH = "beers";

}
