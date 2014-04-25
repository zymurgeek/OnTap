package com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface;

import android.net.Uri;


public class OnTapContentProviderMetadata {

	public static final String AUTHORITY = 
	"com.itllp.barleylegalhomebrewers.ontap.contentprovider";
	public static final Uri EVENT_CONTENT_URI =	Uri.parse("content://" + 
			AUTHORITY + "/" + OnTapContentProviderMetadata.EVENT_BASE_PATH);
	//FIXME Delete this, as it's not a valid URI
	public static final Uri BEER_CONTENT_URI = Uri.parse("content://" + 
			AUTHORITY + "/" + OnTapContentProviderMetadata.BEER_BASE_PATH);
    public static final Uri EVENT_BUSY_URI = Uri.parse("content://" + 
			AUTHORITY + "/busy_event");
    public static final Uri EVENT_NOT_BUSY_URI = Uri.parse("content://" + 
			AUTHORITY + "/notbusy_event");
    public static final Uri BEER_BUSY_URI = Uri.parse("content://" + 
			AUTHORITY + "/busy_beer");
    public static final Uri BEER_NOT_BUSY_URI = Uri.parse("content://" + 
			AUTHORITY + "/notbusy_beer");

	public static final String EVENT_BASE_PATH = "events";
	public static final String BEER_BASE_PATH = "beers";
	public static final String EVENT_ID_PARAM = "event";

}
