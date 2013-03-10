package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.json.JsonUrlEventDatabaseLoader;


public class EventDatabaseLoaderFactory {

	public static final String productionSiteUrl = "http://misdb.com/barleylegalapp/getevent.aspx";

	public static void createProductionSiteEventDatabaseLoader(NetworkConnectivity networkConnectivity) {
		JsonArrayEventDatabaseLoader arrayLoader = new JsonArrayEventDatabaseLoaderImpl();
		JsonUrlEventDatabaseLoader.create(networkConnectivity, productionSiteUrl, arrayLoader);
	}
}
