package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JsonDateToJavaDate;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.JsonArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.json.JsonArrayRetrieverImpl;
import com.itllp.barleylegalhomebrewers.ontap.json.JsonUrlEventDatabaseLoader;


public class EventDatabaseLoaderFactory {

	public static final String productionSiteUrl = "http://misdb.com/barleylegalapp/getevent.aspx";

	public static void createProductionSiteEventDatabaseLoader(
			NetworkConnectivity networkConnectivity) {
		EventDatabase eventDatabase = EventDatabase.getInstance();
		StringToJavaDateConverter dateConverter = new JsonDateToJavaDate(); 
		JsonArrayEventDatabaseLoader arrayLoader = 
				new JsonArrayEventDatabaseLoaderImpl(dateConverter, eventDatabase);
		JsonArrayRetriever arrayRetriever = new JsonArrayRetrieverImpl();
		JsonUrlEventDatabaseLoader.create(networkConnectivity, productionSiteUrl, 
				arrayRetriever, arrayLoader);
	}
}
