package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.TableFromJSONArrayUpdater;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.EventTableUpdater;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.SQLiteEventTableFromJSONArrayUpdaterImpl;
import com.itllp.barleylegalhomebrewers.ontap.database.EventTable;
import com.itllp.barleylegalhomebrewers.ontap.database.SQLiteEventTable;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToSQLiteDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToStringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JSONDateToJavaDate;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.EventJSONObjectToContentValuesConverterImpl;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayToContentValuesListConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayToContentValuesListConverterImpl;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONObjectToContentValuesConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONServerJSONArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONUrlEventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.jsonserver.EventTableFromJSONArrayRetrieverUpdater;


public class EventDatabaseLoaderFactory {

	public static final String betaSiteUrl = "http://misdb.com/barleylegalapp/getevent.aspx";
	public static final String productionSiteUrl = "http://www.barleylegalevents.com/barleylegal/getevent.aspx";

	
	private static void createSQLiteEventTableUpdater(String serverURL) {
		JSONArrayRetriever arrayRetriever = 
				new JSONServerJSONArrayRetriever(serverURL);
		StringToJavaDateConverter jsonDateConverter = new JSONDateToJavaDate();
		JavaDateToStringConverter javaDateConverter = 
				new JavaDateToSQLiteDateConverter();
		JSONObjectToContentValuesConverter objectConverter =
				new EventJSONObjectToContentValuesConverterImpl
				(jsonDateConverter,	javaDateConverter);
		JSONArrayToContentValuesListConverter listConverter =
				new JSONArrayToContentValuesListConverterImpl(objectConverter);
		EventTable eventTable = new SQLiteEventTable();
		TableFromJSONArrayUpdater eventTableFromJSONArrayUpdater = 
				new SQLiteEventTableFromJSONArrayUpdaterImpl(listConverter,
						eventTable);
		eventTableUpdater = new EventTableFromJSONArrayRetrieverUpdater
				(arrayRetriever, eventTableFromJSONArrayUpdater);
	}

	
	private static void createEventDatabaseLoader(
			NetworkConnectivity networkConnectivity, String url) {
		EventDatabase eventDatabase = EventDatabase.getInstance();
		StringToJavaDateConverter dateConverter = new JSONDateToJavaDate(); 
		JSONArrayEventDatabaseLoader arrayLoader = 
				new JSONArrayEventDatabaseLoaderImpl(dateConverter, eventDatabase);
		JSONArrayRetriever arrayRetriever = new JSONServerJSONArrayRetriever(url);
		JSONUrlEventDatabaseLoader.create(networkConnectivity, url, 
				arrayRetriever, arrayLoader);
	}

	
	public static void createBetaSiteEventDatabaseLoader(
			NetworkConnectivity networkConnectivity) {
		createEventDatabaseLoader(networkConnectivity, betaSiteUrl);
	}

	
	public static void createProductionSiteEventDatabaseLoader(
			NetworkConnectivity networkConnectivity) {
		createEventDatabaseLoader(networkConnectivity, productionSiteUrl);
	}


	public static void createSQLiteEventTableFromProductionServerUpdater() {
		createSQLiteEventTableUpdater(productionSiteUrl);
	}
	
	
	public static void createSQLiteEventTableFromBetaServerUpdater() {
		createSQLiteEventTableUpdater(betaSiteUrl);
	}

	
	public static EventTableUpdater getInstance() {
		return eventTableUpdater;
	}
	
	
	private static EventTableUpdater eventTableUpdater = null;
}
