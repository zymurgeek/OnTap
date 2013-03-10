package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.test.JsonUrlEventDatabaseLoader;

public class EventDatabaseLoaderFactory {

	public static final String productionSiteUrl = "http://misdb.com/barleylegalapp/getevent.aspx";

	public static void createProductionSiteEventDatabaseLoader() {
		JsonUrlEventDatabaseLoader.create(productionSiteUrl);
	}
}
